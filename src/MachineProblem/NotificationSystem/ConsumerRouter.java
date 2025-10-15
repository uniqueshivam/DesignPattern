package MachineProblem.NotificationSystem;

import MachineProblem.NotificationSystem.POJO.Notification;
import MachineProblem.NotificationSystem.Repo.NotificationInMemoryRepository;
import MachineProblem.NotificationSystem.Utils.InMemoryBroker;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ConsumerRouter {
    private final InMemoryBroker inMemoryBroker;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final String mainTopic;
    private final String emailTopic = "email-topic";
    private final String smsTopic = "sms-topic";
    private final String pushTopic = "push-topic";
    private final ScheduledExecutorService scheduledExecutorService; //this is for retry
    private final NotificationInMemoryRepository notificationInMemoryRepository;

    public ConsumerRouter(InMemoryBroker inMemoryBroker, String mainTopic, ScheduledExecutorService scheduledExecutorService, NotificationInMemoryRepository notificationInMemoryRepository) {
        this.inMemoryBroker = inMemoryBroker;
        this.mainTopic = mainTopic;
        this.scheduledExecutorService = scheduledExecutorService;
        this.notificationInMemoryRepository = notificationInMemoryRepository;
    }

    public void start() {
        executorService.submit(()->{
            BlockingQueue<Notification> notificationBlockingQueue  = inMemoryBroker.getTopic(mainTopic);
            while(true) {
                try{
                    Notification notification = notificationBlockingQueue.take();

                    //we can put idempotency check

                    notificationInMemoryRepository.save(notification);
                    switch (notification.getChannel()) {
                        case EMAIL:
                            inMemoryBroker.publish(emailTopic,notification);
                        case SMS:
                            inMemoryBroker.publish(smsTopic,notification);
                        case PUSH:
                            inMemoryBroker.publish(pushTopic,notification);
                    }
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    break;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            }

        });
    }

    public void stop() {
        executorService.shutdown();
    }


}
