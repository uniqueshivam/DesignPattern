package RentalCarBooking.Model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Vehicle {
    public Integer id;
    public String modelName;
    public ModelType type;
    public LocalDate registeredOn;
    public boolean isAvailable;
    public double baseFare;

    abstract void deRegister();
}
