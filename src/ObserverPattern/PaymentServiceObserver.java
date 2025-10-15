package ObserverPattern;

public class PaymentServiceObserver implements MessagingQueueObserver{
    @Override
    public void update(String message) {
        System.out.println("Payment service received the message "+ message);
    }
}
