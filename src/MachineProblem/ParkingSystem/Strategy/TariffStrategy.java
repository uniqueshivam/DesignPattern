package MachineProblem.ParkingSystem.Strategy;

import MachineProblem.ParkingSystem.Models.ParkingSession;

public interface TariffStrategy {
    public void calculateAndCreateTransaction(ParkingSession parkingSession);
}
