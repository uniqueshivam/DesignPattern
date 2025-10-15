package CommandPattern;

public class SaleCommand implements PaymentCommander{
    private PaymentsServiceReceiver paymentsServiceReceiver;

    public SaleCommand(PaymentsServiceReceiver paymentsServiceReceiver) {
        this.paymentsServiceReceiver = paymentsServiceReceiver;
    }

    @Override
    public void executeCommand() {
        paymentsServiceReceiver.performSale();
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
