package MachineProblem.NotificationSystem.Utils;

import MachineProblem.NotificationSystem.POJO.Notification;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class InMemoryBroker {
    private final Map<String, BlockingQueue<Notification>> topics = new ConcurrentHashMap<>();


    public final BlockingQueue<Notification> getTopic(String topic) {
        return topics.computeIfAbsent(topic, k -> new LinkedBlockingQueue<>());
    }

    public void publish(String topic, Notification notification) throws InterruptedException {
        getTopic(topic).put(notification);
    }


}
