package MachineProblem.AtmMachine.Strategy;

import MachineProblem.AtmMachine.Repository.DataBaseRepo;

public interface UserValidationStrategy {
    public boolean validateUser(String id, DataBaseRepo dataBaseRepo);
}
