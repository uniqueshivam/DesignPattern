package MachineProblem.TrueCaller.Searching;

import MachineProblem.TrueCaller.Repository.UserRepository;
import MachineProblem.TrueCaller.entities.Contact;

import java.util.List;

public class SearchByName implements SearchingStrategy{
    @Override
    public List<Contact> searchPhoneNumber(String query, UserRepository userRepository) {
        return userRepository.fetchUserByName(query);
    }
}
