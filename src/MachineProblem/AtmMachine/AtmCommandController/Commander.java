package MachineProblem.AtmMachine.AtmCommandController;

import MachineProblem.AtmMachine.Entities.User;
import MachineProblem.AtmMachine.Strategy.UserValidationStrategy;

public abstract class Commander {
    public abstract void executeCommand();
    public abstract void undoCommand();
    public abstract void redoCommand();

    public void validateUser(User user, UserValidationStrategy userValidationStrategy){
        //validate the user
    }
}
