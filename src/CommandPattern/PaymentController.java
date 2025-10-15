package CommandPattern;

public class PaymentController {
    private PaymentCommander paymentCommander;

    public void setCommand(PaymentCommander paymentCommander){
        this.paymentCommander = paymentCommander;
    }

    public void executeCommand(){
        this.paymentCommander.executeCommand();;
    }

    public void revertCommand(){
        this.paymentCommander.undoCommand();
    }

    public void redoCommand(){
        this.paymentCommander.redoCommand();
    }


}
