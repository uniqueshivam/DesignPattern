package MachineProblem.ParkingSystem.Models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParkingSession {
    private Integer sessionId;
    private Vehicle vehicle;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Transaction transaction;
    private String status;
    private ParkingSlots parkingSlots;
}
