package MachineProblem.TrueCaller.Searching;

import MachineProblem.TrueCaller.Repository.UserRepository;
import MachineProblem.TrueCaller.entities.Contact;

import java.util.List;

public interface SearchingStrategy {
    public List<Contact> searchPhoneNumber(String query, UserRepository userRepository);
}
