package MachineProblem.AtmMachine.AtmCommandController;

import MachineProblem.AtmMachine.Entities.User;
import MachineProblem.AtmMachine.Repository.DataBaseRepo;
import MachineProblem.AtmMachine.Strategy.UserValidationStrategy;

public class CashWithDrawlCommand extends Commander{
    private User user;
    private Integer amount;
    private UserValidationStrategy userValidationStrategy;
    private DataBaseRepo dataBaseRepo;

    public CashWithDrawlCommand(User user, Integer amount, UserValidationStrategy userValidationStrategy, DataBaseRepo dataBaseRepo) {
        this.user = user;
        this.amount = amount;
        this.userValidationStrategy = userValidationStrategy;
        this.dataBaseRepo = dataBaseRepo;
    }

    @Override
    public void executeCommand() {
        if(userValidationStrategy.validateUser(user.getUserId(), dataBaseRepo)){
            //check amount exist in his account;
            if(amount>200){
                System.out.println("Command executed");
            }
        }
    }

    @Override
    public void undoCommand() {

    }

    @Override
    public void redoCommand() {

    }


}
