package MachineProblem.ParkingSystem.Models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Floor {
    private Integer floorNumber;
    private List<ParkingSlots> parkingSlotsList;
    private boolean isFloorClosed;

    public Floor(Integer numberOfLost){
        this.parkingSlotsList = new ArrayList<>(numberOfLost);
    }
}
