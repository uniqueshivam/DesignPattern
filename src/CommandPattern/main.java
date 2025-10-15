package CommandPattern;

public class main {
    public static void main(String[] args) {
        PaymentsServiceReceiver paymentsServiceReceiver = new PaymentsServiceReceiver();
        PaymentController paymentController = new PaymentController();
        paymentController.setCommand(new SaleCommand(paymentsServiceReceiver));
        paymentController.executeCommand();
        paymentController.revertCommand();
        paymentController.redoCommand();


        paymentController.setCommand(new VoidCommand(paymentsServiceReceiver));
        paymentController.executeCommand();
        paymentController.revertCommand();
        paymentController.redoCommand();

        paymentController.setCommand(new RefundCommand(paymentsServiceReceiver));
        paymentController.executeCommand();
        paymentController.revertCommand();
        paymentController.redoCommand();
    }
}
