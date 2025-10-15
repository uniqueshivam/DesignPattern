package CommandPattern;

public class RefundCommand implements PaymentCommander{
    PaymentsServiceReceiver paymentsServiceReceiver;
    @Override
    public void executeCommand() {
        paymentsServiceReceiver.performRefund();
    }

    public RefundCommand(PaymentsServiceReceiver paymentsServiceReceiver) {
        this.paymentsServiceReceiver = paymentsServiceReceiver;
    }

    @Override
    public void undoCommand() {
        paymentsServiceReceiver.revertChanges();
    }

    @Override
    public void redoCommand() {
        this.executeCommand();
    }
}
