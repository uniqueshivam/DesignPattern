package strategyPattern.strategy;

public class CardPayment implements PaymentStrategy {
    @Override
    public void makePayment(String request) {
        System.out.println("Initiating card payment");
    }
}
