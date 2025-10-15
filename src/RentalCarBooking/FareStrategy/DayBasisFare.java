package RentalCarBooking.FareStrategy;

import lombok.ToString;

import java.time.Duration;
import java.time.LocalDateTime;

@ToString
public class DayBasisFare extends FareStrategy{
    @Override
    public double calculateFare() {
        double baseFare = this.vehicle.getBaseFare();
        LocalDateTime start = this.startDateTime;
        LocalDateTime end = this.endDateTime;
        return baseFare + Duration.between(start,end).toDays() *2400;
    }
}
