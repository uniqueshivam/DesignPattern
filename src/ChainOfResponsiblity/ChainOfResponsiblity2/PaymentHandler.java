package ChainOfResponsiblity.ChainOfResponsiblity2;

abstract class PaymentHandler {
    protected PaymentHandler nextPaymentHandler;

    public void assignNextHandler(PaymentHandler paymentHandler) {
        this.nextPaymentHandler = paymentHandler;
    }

    public abstract void handlePaymentRequest(String paymentType);
}
