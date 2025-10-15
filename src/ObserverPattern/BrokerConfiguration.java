package ObserverPattern;

public interface BrokerConfiguration {
    public void addObserver(MessagingQueueObserver observer);
    public void removeObserver(MessagingQueueObserver observer);
    public void notifyObserver();
}
