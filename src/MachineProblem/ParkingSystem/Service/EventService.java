package MachineProblem.ParkingSystem.Service;

import MachineProblem.ParkingSystem.Models.*;

import java.time.LocalDateTime;
import java.util.List;

public class EventService {
    ParkingStructure parkingStructure;

    public ParkingSession starVehicleEntrySession(Vehicle vehicle) throws Exception {
        ParkingSession parkingSession = null;
        List<Floor> floorList = parkingStructure.getFloorList();
        for (Floor floor : floorList) {
            List<ParkingSlots> parkingSlotsList = floor.getParkingSlotsList();
            for (ParkingSlots parkingSlot : parkingSlotsList) {
                if (!parkingSlot.isOccupied()) {
                    parkingSession = new ParkingSession();
//                    parkingSession.setSessionId(gateRandomSessionId());
                    parkingSession.setVehicle(vehicle);
                    parkingSession.setEntryTime(LocalDateTime.now());
                    parkingSession.setStatus("ACTIVE");
                    parkingSession.setParkingSlots(parkingSlot);
                    System.out.println("Parking session created");
                    parkingStructure.getEntryGate().openGate();
                    parkingSlot.setOccupied(true);
                    return parkingSession;

                }
            }
        }
        throw new Exception("Parking slot not available");
    }

//    public


//    private Integer gateRandomSessionId() {
//        int min = 2000;
//        int max = 5000;
//
//        return (int) (Math.random() * (max - min + 1)) + min;
//    }
}
