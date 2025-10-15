package MachineProblem.ParkingSystem.Strategy;

import MachineProblem.ParkingSystem.Models.ParkingSession;
import MachineProblem.ParkingSystem.Models.Transaction;
import MachineProblem.ParkingSystem.Utils.ParkingSessionHelper;

import java.time.Duration;
import java.time.LocalDateTime;

public class IncrementalTariff implements TariffStrategy{
    @Override
    public void calculateAndCreateTransaction(ParkingSession parkingSession) {
        if(ParkingSessionHelper.validateSession(parkingSession)){
            LocalDateTime entryTime = parkingSession.getEntryTime();
            LocalDateTime exitTime = parkingSession.getExitTime();
            int tariffAmount = 100;
            Duration duration = Duration.between(exitTime,entryTime);
            long minutes = duration.toMinutes();
            tariffAmount = Math.toIntExact(tariffAmount + minutes * 18);
            Transaction transaction = new Transaction();
            transaction.setTotalAmountInCents(tariffAmount);
            parkingSession.setTransaction(transaction);
        }
    }
}
