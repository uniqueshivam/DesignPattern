package MachineProblem.AtmMachine.OutputDevices;

public class Display implements OutputObserver{
    @Override
    public void notify(String message) {
        System.out.println("Displaying the message "+message);
    }
}
