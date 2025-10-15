package MachineProblem.AtmMachine.Strategy;

import MachineProblem.AtmMachine.Repository.DataBaseRepo;

public class AdminValidation implements UserValidationStrategy{
    @Override
    public boolean validateUser(String adminId, DataBaseRepo dataBaseRepo) {
        return false;
    }
}
