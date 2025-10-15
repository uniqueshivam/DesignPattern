package ObserverPattern;

import java.util.ArrayList;
import java.util.List;

public class MQTTBroker implements BrokerConfiguration{
    public String message;
    List<MessagingQueueObserver> messagingQueueObserverList = new ArrayList<>();
    @Override
    public void addObserver(MessagingQueueObserver observer) {
        messagingQueueObserverList.add(observer);
    }

    @Override
    public void removeObserver(MessagingQueueObserver observer) {
        messagingQueueObserverList.remove(observer);
    }

    @Override
    public void notifyObserver() {
        messagingQueueObserverList.stream().forEach(messagingQueueObserver -> messagingQueueObserver.update(message));
    }
}
