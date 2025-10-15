package RentalCarBooking.Model;

import lombok.*;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Store {
    private int storeId;
    private Location location;
    private String name;
    private List<Vehicle> vehicleList;

}
