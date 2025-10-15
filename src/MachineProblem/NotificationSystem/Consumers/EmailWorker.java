package MachineProblem.NotificationSystem.Consumers;

import MachineProblem.NotificationSystem.POJO.Notification;
import MachineProblem.NotificationSystem.Repo.NotificationInMemoryRepository;
import MachineProblem.NotificationSystem.Utils.InMemoryBroker;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

public class EmailWorker extends ChannelWorker{
    public EmailWorker(InMemoryBroker inMemoryBroker, NotificationInMemoryRepository notificationInMemoryRepository, ScheduledExecutorService scheduledExecutorService, Integer maxAttempts) {
        super(inMemoryBroker, notificationInMemoryRepository, scheduledExecutorService,maxAttempts, "email-topic",4);
    }

    @Override
    protected boolean sendNotification(Notification notification) {
        //validate and send email here
        System.out.println("Email sent to user "+notification.getUserDetails().getEmailId());
        return true;
    }
}
