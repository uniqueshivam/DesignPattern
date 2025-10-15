package MachineProblem.AtmMachine.AtmCommandController;

import MachineProblem.AtmMachine.Entities.User;
import MachineProblem.AtmMachine.OutputDevices.Display;
import MachineProblem.AtmMachine.Repository.DataBaseRepo;
import MachineProblem.AtmMachine.Strategy.UserValidationStrategy;

public class StatementCommand extends Commander{
    private Display display;
    private User user;
    private String statementDetails;
    private UserValidationStrategy userValidationStrategy;
    private DataBaseRepo dataBaseRepo;

    public StatementCommand(Display display, User user, String statementDetails, UserValidationStrategy userValidationStrategy, DataBaseRepo dataBaseRepo) {
        this.display = display;
        this.user = user;
        this.statementDetails = statementDetails;
        this.userValidationStrategy = userValidationStrategy;
        this.dataBaseRepo = dataBaseRepo;
    }

    @Override
    public void executeCommand() {

    }

    @Override
    public void undoCommand() {

    }

    @Override
    public void redoCommand() {

    }
}
