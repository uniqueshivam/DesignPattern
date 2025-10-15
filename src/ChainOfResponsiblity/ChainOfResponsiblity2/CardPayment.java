package ChainOfResponsiblity.ChainOfResponsiblity2;

public class CardPayment extends PaymentHandler{
    @Override
    public void handlePaymentRequest(String paymentType) {
        if("CARD".equalsIgnoreCase(paymentType)){
            System.out.println("Proceeding the payment with card");
        } else if(nextPaymentHandler!=null){
            nextPaymentHandler.handlePaymentRequest(paymentType);
        }
    }
}
