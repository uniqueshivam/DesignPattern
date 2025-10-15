package RentalCarBooking.Model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@ToString
@Data
public class User {
    private int userId;
    private String name;
    private String licenseNumber;
    private long phoneNumber;

}
