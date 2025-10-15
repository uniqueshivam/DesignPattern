package MachineProblem.NotificationSystem;

import MachineProblem.NotificationSystem.POJO.Notification;
import MachineProblem.NotificationSystem.Utils.InMemoryBroker;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Producer {
    private final InMemoryBroker inMemoryBroker;
    private final String mainTopic;

    public void publishContent(Notification notification) throws InterruptedException {
        inMemoryBroker.publish(mainTopic,notification);
    }

}
