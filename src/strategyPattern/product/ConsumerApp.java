package strategyPattern.product;

import strategyPattern.strategy.PaymentStrategy;

public class ConsumerApp extends Product{
    public ConsumerApp(PaymentStrategy paymentStrategy) {
        super(paymentStrategy);
    }
}
