package strategyPattern.strategy;

import strategyPattern.strategy.PaymentStrategy;

public class UpiPayment implements PaymentStrategy {
    @Override
    public void makePayment(String request) {
        System.out.println("Initiating UPI payment");
    }
}
