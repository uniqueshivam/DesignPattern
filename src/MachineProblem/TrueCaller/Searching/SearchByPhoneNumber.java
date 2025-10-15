package MachineProblem.TrueCaller.Searching;

import MachineProblem.TrueCaller.Repository.UserRepository;
import MachineProblem.TrueCaller.entities.Contact;

import java.util.List;

public class SearchByPhoneNumber implements SearchingStrategy{
    UserRepository userRepository;
    @Override
    public List<Contact> searchPhoneNumber(String query, UserRepository userRepository) {
        return userRepository.fetchUserByPhone(query);
    }
}
