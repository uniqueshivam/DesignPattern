package MachineProblem.TrueCaller;

import MachineProblem.TrueCaller.Services.CallerIdService;
import MachineProblem.TrueCaller.Services.UserRegistrationService;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
public class TrueCaller {
    private static TrueCaller trueCallerInstance;
    private UserRegistrationService userRegistrationService;
    private CallerIdService callerIdService;

    public TrueCaller(UserRegistrationService userRegistrationService, CallerIdService callerIdService) {
        this.userRegistrationService = userRegistrationService;
        this.callerIdService = callerIdService;
    }

    public static synchronized TrueCaller getInstance(){
        if(Objects.isNull(trueCallerInstance)){
            trueCallerInstance = new TrueCaller();
        }
        return trueCallerInstance;
    }
}
