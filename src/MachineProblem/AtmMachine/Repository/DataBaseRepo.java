package MachineProblem.AtmMachine.Repository;

import MachineProblem.AtmMachine.Entities.User;

import java.util.HashMap;
import java.util.Map;

public class DataBaseRepo {
    public Map<String, User> userDetailsMap = new HashMap<>();

    public void addUser(User user){
        userDetailsMap.put(user.getUserId(), user);
    }

    public User getUserDetails(String userId){
        return userDetailsMap.get(userId);
    }
}
