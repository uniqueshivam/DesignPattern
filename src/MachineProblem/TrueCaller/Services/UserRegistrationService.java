package MachineProblem.TrueCaller.Services;

import MachineProblem.TrueCaller.Repository.UserRepository;
import MachineProblem.TrueCaller.entities.Contact;

public class UserRegistrationService {
    UserRepository userRepository;

    public UserRegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUpUser(Contact contact){
       userRepository.saveUser(contact);
    }
}
