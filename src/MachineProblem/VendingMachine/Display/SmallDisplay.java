package MachineProblem.VendingMachine.Display;

public class SmallDisplay implements DisplayObserver{
    @Override
    public void updateMessage(String message) {
        System.out.println("Small display displaying the message "+message);
    }
}
