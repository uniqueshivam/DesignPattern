package CommandPattern;

public class VoidCommand implements PaymentCommander {
    PaymentsServiceReceiver paymentsServiceReceiver;

    public VoidCommand(PaymentsServiceReceiver paymentsServiceReceiver) {
        this.paymentsServiceReceiver = paymentsServiceReceiver;
    }

    @Override
    public void executeCommand() {
        paymentsServiceReceiver.performVoid();
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
