package MachineProblem.TrueCaller.Repository;

import MachineProblem.TrueCaller.entities.Contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    Map<String, List<Contact>> map = new HashMap<>();
    public void saveUser(Contact user) {
        map.computeIfAbsent(user.getPhoneNumber(), k -> new ArrayList<>()).add(user);
        map.computeIfAbsent(user.getName(), k -> new ArrayList<>()).add(user);
    }

    public List<Contact> fetchUserByPhone(String phoneNumber){
       return map.get(phoneNumber);
    }

    public List<Contact> fetchUserByName(String userName){
        return map.get(userName);
    }
}

