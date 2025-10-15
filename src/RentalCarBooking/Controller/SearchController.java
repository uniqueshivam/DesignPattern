package RentalCarBooking.Controller;

import RentalCarBooking.FareStrategy.FareStrategy;
import RentalCarBooking.Model.Location;
import RentalCarBooking.Model.SearchRecord;
import RentalCarBooking.Model.Store;
import RentalCarBooking.Model.Vehicle;
import RentalCarBooking.Respository.DummyRepository;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchController {
    private Location startLocation;
    private Location endLocation;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Vehicle vehicle;
    private FareStrategy fareStrategy;
    private static int searchId = 1;

    public List<SearchRecord> getAvailableVehicles(){
        System.out.println("searching for vehicles");
        List<Store> storeList = DummyRepository.getStoreList().stream().filter(store ->
            Objects.equals(store.getLocation(),startLocation)).collect(Collectors.toList());

        List<SearchRecord> searchRecordList = new ArrayList<>();
        for(Store store : storeList){
            for(Vehicle vehicle : store.getVehicleList()){
                if(!vehicle.isAvailable)
                    continue;
                SearchRecord searchRecord = new SearchRecord();
                searchRecord.setSearchId(searchId);
                searchRecord.setVehicle(vehicle);
                fareStrategy.setVehicle(vehicle);
                fareStrategy.setStartDateTime(startDateTime);
                fareStrategy.setEndDateTime(endDateTime);
                searchRecord.setFare(fareStrategy.calculateFare());
                searchRecord.setStore(store);
                searchRecordList.add(searchRecord);
                searchId++;
            }

        }
        return searchRecordList;
    }

    public double calculateFare(){
        return fareStrategy.calculateFare();
    }


}
