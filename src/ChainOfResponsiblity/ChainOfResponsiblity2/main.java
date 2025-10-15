package ChainOfResponsiblity.ChainOfResponsiblity2;

public class main {
    public static void main(String[] args) {
        PaymentHandler cardPayment = new CardPayment();
        PaymentHandler blikPayment = new BlikPayment();
        PaymentHandler paypalPayment = new PaypalPayment();
        cardPayment.assignNextHandler(blikPayment);
        blikPayment.assignNextHandler(paypalPayment);

        cardPayment.handlePaymentRequest("card");
    }
}
