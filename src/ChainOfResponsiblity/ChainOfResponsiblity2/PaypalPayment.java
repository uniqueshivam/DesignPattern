package ChainOfResponsiblity.ChainOfResponsiblity2;

public class PaypalPayment extends  PaymentHandler{
    @Override
    public void handlePaymentRequest(String paymentType) {
        if("PAYPAL".equalsIgnoreCase(paymentType)){
            System.out.println("Proceeding the payment with paypal");
        } else if(nextPaymentHandler!=null) {
            nextPaymentHandler.handlePaymentRequest(paymentType);
        }
    }
}
