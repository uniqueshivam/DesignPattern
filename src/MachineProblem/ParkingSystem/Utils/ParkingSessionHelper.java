package MachineProblem.ParkingSystem.Utils;

import MachineProblem.ParkingSystem.Models.ParkingSession;

import java.util.Objects;

public class ParkingSessionHelper {
    public static boolean validateSession(ParkingSession parkingSession){
        if(Objects.isNull(parkingSession) || Objects.isNull(parkingSession.getEntryTime()) || Objects.isNull(parkingSession.getExitTime())){
            System.out.println("Parking session is not valid");
            return false;
        }
        return true;
    }
}
