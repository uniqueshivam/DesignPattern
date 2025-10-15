package ChainOfResponsiblity.ChainOfResponsiblity2;

public class BlikPayment extends PaymentHandler{
    @Override
    public void handlePaymentRequest(String paymentType) {
        if("BLIK".equalsIgnoreCase(paymentType)){
            System.out.println("Proceeding payment with BLIK payment");
        } else if(nextPaymentHandler!=null){
            nextPaymentHandler.handlePaymentRequest(paymentType);
        }
    }
}
