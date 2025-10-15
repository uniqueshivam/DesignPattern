package MachineProblem.NotificationSystem;

import MachineProblem.NotificationSystem.Consumers.EmailWorker;
import MachineProblem.NotificationSystem.Enums.Channel;
import MachineProblem.NotificationSystem.Enums.Status;
import MachineProblem.NotificationSystem.POJO.Notification;
import MachineProblem.NotificationSystem.POJO.UserDetails;
import MachineProblem.NotificationSystem.Repo.NotificationInMemoryImpl;
import MachineProblem.NotificationSystem.Repo.NotificationInMemoryRepository;
import MachineProblem.NotificationSystem.Utils.InMemoryBroker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicLong;

public class main {
    public static void main(String[] args) throws InterruptedException {
        InMemoryBroker inMemoryBroker = new InMemoryBroker();
        NotificationInMemoryRepository notificationInMemoryRepository = new NotificationInMemoryImpl();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
        ConsumerRouter consumerRouter = new ConsumerRouter(inMemoryBroker,"notification-requests",scheduledExecutorService,notificationInMemoryRepository);
        consumerRouter.start();

        EmailWorker emailWorker = new EmailWorker(inMemoryBroker, notificationInMemoryRepository,scheduledExecutorService,5);

        emailWorker.Start();

        Producer producer = new Producer(inMemoryBroker, "notification-requests");

        AtomicLong idGen = new AtomicLong(1000);

        UserDetails userDetails = UserDetails.builder()
                .userId("11")
                .emailId("uniqueshivam@gmail.com")
                .phoneNumber("8622035642")
                .appId("09").build();
        producer.publishContent(new Notification("n"+idGen,userDetails, Channel.EMAIL,"Welcome", (Map<String, String>) new HashMap<>().put("name","shivam"), 0,Status.PENDING));
        Thread.sleep(15000);

        System.out.println("\nShutting down demo...");
        consumerRouter.stop();
        emailWorker.stop();


    }
}
