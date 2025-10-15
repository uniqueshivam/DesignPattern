package RentalCarBooking.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingDetail {
    private int bookingId;
    private Vehicle vehicle;
    private double fare;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private User bookedByUser;
}
