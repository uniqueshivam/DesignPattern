package RentalCarBooking.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRecord {
    private int searchId;
    private Vehicle vehicle;
    private double fare;
    private Store store;

    @Override
    public String toString() {
        return "SearchRecord{" +
                "searchId=" + searchId +
                ", vehicle=" + vehicle +
                ", fare=" + fare +
                '}';
    }
}
