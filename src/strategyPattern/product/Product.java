package strategyPattern.product;

import strategyPattern.strategy.PaymentStrategy;

public class Product {
    PaymentStrategy paymentStrategy;

    public Product(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;
    }

    public void initiatePayment(){
        paymentStrategy.makePayment("request");
    }
}
