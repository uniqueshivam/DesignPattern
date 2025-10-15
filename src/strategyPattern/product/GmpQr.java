package strategyPattern.product;

import strategyPattern.strategy.PaymentStrategy;

public class GmpQr extends Product{
    public GmpQr(PaymentStrategy paymentStrategy) {
        super(paymentStrategy);
    }
}
