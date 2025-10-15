package RentalCarBooking.FareStrategy;

import RentalCarBooking.Model.Vehicle;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class FareStrategy {
    Vehicle vehicle;
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;

    public abstract double calculateFare();

}
