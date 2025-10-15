package CommandPattern;

public interface PaymentCommander {
    public void executeCommand();
    public void undoCommand();
    public void redoCommand();
}
