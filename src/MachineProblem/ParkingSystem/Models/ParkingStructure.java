package MachineProblem.ParkingSystem.Models;

import lombok.Data;

import java.util.List;

@Data
public class ParkingStructure {
    private EntryGate entryGate;
    private ExitGate exitGate;
    List<Floor> floorList;
    private boolean isParkingClosed = false;
}
