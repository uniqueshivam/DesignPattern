package MachineProblem.ParkingSystem.Service;

import MachineProblem.ParkingSystem.Models.Floor;
import MachineProblem.ParkingSystem.Models.ParkingSlots;

import java.util.List;

public class ParkingSlotsOnboardingService {
   public void onBoardLotsFloorWise(List<Floor> floorList){
       for(Floor floor : floorList){
           List<ParkingSlots> parkingSlotsList = floor.getParkingSlotsList();
           for(ParkingSlots parkingSlot : parkingSlotsList){
               parkingSlot.setSlotId(gateRandomSlotNumber());
               parkingSlot.setFloorNumber(floor.getFloorNumber());
           }
       }
       System.out.println("Lots are created");
   }


    private Integer gateRandomSlotNumber() {
        int min = 10;
        int max = 50;

        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
