package MachineProblem.AtmMachine.Strategy;

import MachineProblem.AtmMachine.Entities.Customer;
import MachineProblem.AtmMachine.Repository.DataBaseRepo;

import java.util.Objects;

public class CustomerValidation implements UserValidationStrategy{

    @Override
    public boolean validateUser(String userId, DataBaseRepo dataBaseRepo) {
       Customer c = (Customer) dataBaseRepo.getUserDetails(userId);
       if(Objects.nonNull(c) && c.getCardDetails()!=null){
           return true;
       } else{
           return false;
       }
    }
}
