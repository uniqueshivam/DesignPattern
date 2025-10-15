package MachineProblem.ParkingSystem.Service;

import MachineProblem.ParkingSystem.Factory.GateCreationFactory;
import MachineProblem.ParkingSystem.Models.EntryGate;
import MachineProblem.ParkingSystem.Models.ExitGate;
import MachineProblem.ParkingSystem.Models.Floor;
import MachineProblem.ParkingSystem.Models.ParkingStructure;

import java.util.ArrayList;
import java.util.List;

public class ParkingOnBoardingService {
    private GateCreationFactory gateCreationFactory;
    private ParkingSlotsOnboardingService parkingSlotsOnboardingService;

    private ParkingStructure createParkingStructure(Integer numberOfFloors, Integer numberOfSlotsAtEachFloor){
        System.out.println("Onboarding the parking");
        EntryGate entryGate= (EntryGate) gateCreationFactory.getParkingGate("ENTRY");
        ExitGate exitGate = (ExitGate) gateCreationFactory.getParkingGate("EXIT");
        List<Floor> floorList = new ArrayList<>();
        for(int i = 0;i<numberOfFloors;i++){
            Floor floor = new Floor(numberOfSlotsAtEachFloor);
            floorList.add(floor);
        }
        System.out.println("Floors are created");
        parkingSlotsOnboardingService.onBoardLotsFloorWise(floorList);
        ParkingStructure parkingStructure = new ParkingStructure();
        parkingStructure.setEntryGate(entryGate);
        parkingStructure.setExitGate(exitGate);
        parkingStructure.setFloorList(floorList);
        System.out.println("Parking onboarding completed");
        return parkingStructure;
    }
}
