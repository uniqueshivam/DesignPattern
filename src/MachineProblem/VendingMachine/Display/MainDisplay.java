package MachineProblem.VendingMachine.Display;

public class MainDisplay implements DisplayObserver{
    @Override
    public void updateMessage(String message) {
        System.out.println("The main display displaying the message "+message);
    }
}
