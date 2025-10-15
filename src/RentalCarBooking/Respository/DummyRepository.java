package RentalCarBooking.Respository;

import RentalCarBooking.Model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DummyRepository {
    public List<Store> storeList = new ArrayList<>();
    public List<Vehicle> vehicleList = new ArrayList<>();

    public static List<Vehicle> getListOfVehicles(){
        List<Vehicle> vehicleList = new ArrayList<>();
        Vehicle swift = new Car();
        swift.setId(1);
        swift.setAvailable(true);
        swift.setBaseFare(1000);
        swift.setModelName("Swift");
        swift.setRegisteredOn(LocalDate.of(1996,12,21));
        swift.setType(ModelType.FOUR_SEATER);

        Vehicle alto = new Car();
        alto.setId(2);
        alto.setAvailable(true);
        alto.setBaseFare(800);
        alto.setModelName("Alto");
        alto.setRegisteredOn(LocalDate.of(1998,1,21));
        alto.setType(ModelType.FOUR_SEATER);


        Vehicle bmw = new Car();
        bmw.setId(2);
        bmw.setAvailable(true);
        bmw.setBaseFare(8000);
        bmw.setModelName("BMW");
        bmw.setRegisteredOn(LocalDate.of(2010,1,1));
        bmw.setType(ModelType.FOUR_SEATER);

        Vehicle suv = new Car();
        suv.setId(3);
        suv.setAvailable(false);
        suv.setBaseFare(5000);
        suv.setModelName("SUV");
        suv.setRegisteredOn(LocalDate.of(2012,10,10));
        suv.setType(ModelType.SIX_SEATER);

        vehicleList.add(swift);
        vehicleList.add(alto);
        vehicleList.add(bmw);
        vehicleList.add(suv);
        return vehicleList;
    }

    public static List<Store> getStoreList(){
        List<Store> storeList = new ArrayList<>();
        Store store1 = Store.builder()
                .storeId(111)
                .vehicleList(getListOfVehicles())
                .name("Store 1")
                .location(Location.builder().latitude(111).longitude(222).build()).build();
        List<Vehicle> vehicleListStore2 = new ArrayList<>(getListOfVehicles());
        vehicleListStore2.remove(2);
        Store store2 = Store.builder()
                .storeId(112)
                .vehicleList(vehicleListStore2)
                .name("Store 2")
                .location(Location.builder().latitude(112).longitude(223).build()).build();

        storeList.add(store1);
        storeList.add(store2);
        return storeList;
    }
}
