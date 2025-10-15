package RentalCarBooking.Model;

import lombok.*;

@Data
public class Car extends Vehicle{

    @Override
    void deRegister() {
        System.out.println("vehicle de registered from the store");
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", modelName='" + modelName + '\'' +
                ", type=" + type +
                ", registeredOn=" + registeredOn +
                ", isAvailable=" + isAvailable +
                ", baseFare=" + baseFare +
                '}';
    }
}
