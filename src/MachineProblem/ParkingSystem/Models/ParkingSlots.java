package MachineProblem.ParkingSystem.Models;

import lombok.Data;

@Data
public class ParkingSlots {
    private Integer slotId;
    private boolean isOccupied;
    private Integer floorNumber;
}
