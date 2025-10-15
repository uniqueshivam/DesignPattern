package MachineProblem.NotificationSystem.Consumers;

import MachineProblem.NotificationSystem.Enums.Status;
import MachineProblem.NotificationSystem.POJO.Notification;
import MachineProblem.NotificationSystem.Repo.NotificationInMemoryRepository;
import MachineProblem.NotificationSystem.Utils.InMemoryBroker;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public abstract class ChannelWorker {
    private final InMemoryBroker inMemoryBroker;
    private final NotificationInMemoryRepository notificationInMemoryRepository;
    private final ScheduledExecutorService scheduledExecutorService;
    private final String retryTopic ="retry-topic";
    private final String dlqTopic ="dlq-topic";
    private final Integer maxAttempts;
    private final ExecutorService workPool;
    private final BlockingQueue<Notification> channelSpecificQueue;

    public ChannelWorker(InMemoryBroker inMemoryBroker, NotificationInMemoryRepository notificationInMemoryRepository, ScheduledExecutorService scheduledExecutorService, Integer maxAttempts,String topic,Integer threads) {
        this.inMemoryBroker = inMemoryBroker;
        this.notificationInMemoryRepository = notificationInMemoryRepository;
        this.scheduledExecutorService = scheduledExecutorService;
        this.maxAttempts = maxAttempts;
        this.workPool = Executors.newFixedThreadPool(threads);
        this.channelSpecificQueue = inMemoryBroker.getTopic(topic);
    }

    protected  abstract boolean sendNotification(Notification notification);

    public void Start() {
        workPool.submit(()->{
            while(true) {
                Notification n = channelSpecificQueue.take();
                workPool.submit(()-> {
                    try {
                        ProcessNotification(n);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        });
    }

    public void ProcessNotification(Notification notification) throws InterruptedException {
        try {
            boolean status = sendNotification(notification);

            if(status) {
                notification.setAttemptCount(notification.getAttemptCount()+1);
                notification.setStatus(Status.SENT);
                notificationInMemoryRepository.updateStatus(notification.getNotificationId(),Status.SENT);
                System.out.println("Sent successfully for "+notification.getChannel());
            } else  {
                notification.setAttemptCount(notification.getAttemptCount()+1);
                notification.setStatus(Status.FAILED);
                notificationInMemoryRepository.updateStatus(notification.getNotificationId(),Status.FAILED);
                inMemoryBroker.publish(dlqTopic,notification);
                System.out.println("Notification sent to dlg");

            }
        } catch (Exception ex) {
            notification.setAttemptCount(notification.getAttemptCount()+1);
            if(notification.getAttemptCount()>=maxAttempts) {
                notification.setStatus(Status.FAILED);
                notificationInMemoryRepository.updateStatus(notification.getNotificationId(),Status.FAILED);
                inMemoryBroker.publish(dlqTopic,notification);
            } else  {
                notification.setStatus(Status.RETRYING);
                notificationInMemoryRepository.updateStatus(notification.getNotificationId(),Status.RETRYING);
                //add in retrying logic.

            }

        }
    }

    public void stop() { workPool.shutdownNow(); }

}
