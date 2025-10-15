package MachineProblem.ParkingSystem.Factory;

import MachineProblem.ParkingSystem.Models.EntryGate;
import MachineProblem.ParkingSystem.Models.ExitGate;
import MachineProblem.ParkingSystem.Models.Gate;

public class GateCreationFactory {
    public Gate getParkingGate(String gateType) {
        switch (gateType) {
            case "ENTRY":
                return new EntryGate(gateRandomGateNumber());
            case "EXIT":
                return new ExitGate(gateRandomGateNumber());
            default:
                return null;
        }
    }

    private Integer gateRandomGateNumber() {
        int min = 100;
        int max = 1000;

        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
