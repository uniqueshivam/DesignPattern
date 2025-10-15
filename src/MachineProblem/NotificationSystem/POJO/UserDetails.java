package MachineProblem.NotificationSystem.POJO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@Getter
public class UserDetails {
    private final String userId;
    private final String emailId;
    private final String phoneNumber;
    private final String appId;
}
