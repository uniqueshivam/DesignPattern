package MachineProblem.ParkingSystem.Models;

import lombok.Data;

import java.util.List;

@Data
public class Vehicle {
    private String vehicleNumber;
    private String vehicleBrand;
    private List<ParkingSession> parkingSessionList;
 }
