import ChainOfResponsiblity.DebugLogProcessor;
import ChainOfResponsiblity.ErrorLogProcessor;
import ChainOfResponsiblity.InfoLogProcessor;
import ChainOfResponsiblity.LogProcessor;
import RentalCarBooking.Controller.BookingController;
import RentalCarBooking.Controller.SearchController;
import RentalCarBooking.FareStrategy.DayBasisFare;
import RentalCarBooking.FareStrategy.FareStrategy;
import RentalCarBooking.FareStrategy.HourBasisFare;
import RentalCarBooking.Model.Location;
import RentalCarBooking.Model.SearchRecord;
import RentalCarBooking.Model.User;
import decoratorPattern.decorator.ExtraHardDrive;
import decoratorPattern.decorator.ExtraRam;
import decoratorPattern.product.BaseLaptop;
import decoratorPattern.product.Macbook;
import strategyPattern.product.ConsumerApp;
import strategyPattern.product.GmpQr;
import strategyPattern.product.Product;
import strategyPattern.strategy.CardPayment;
import strategyPattern.strategy.UpiPayment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;


public class Main {
    public static void main(String[] args) {

        //GMP QR PAYMENT PRODUCT
//        Product gmpQr = new GmpQr(new UpiPayment());
//        gmpQr.initiatePayment();
//        Product consumerApp = new ConsumerApp(new CardPayment());
//        consumerApp.initiatePayment();

//        BaseLaptop macbook = new ExtraHardDrive(new ExtraRam(new Macbook()));
//        System.out.println(macbook.calculatePrice());

        //RENTAL BOOKING SYSTEM

        //Adding data to test
//        LocalDateTime startTime = LocalDateTime.now();
//        LocalDateTime endTime = LocalDateTime.of(2023,05,21,13,30);
//        Location searchLocation = new Location();
//        searchLocation.setLatitude(111);
//        searchLocation.setLongitude(222);
//        //CREATING SEARCH CONTROLLER
//        SearchController searchController = new SearchController();
//        searchController.setStartDateTime(startTime);
//        searchController.setEndDateTime(endTime);
//        searchController.setStartLocation(searchLocation);
//        //CREATING THE FARE STRATEGY
//        FareStrategy fareStrategy = Duration.between(startTime,endTime).toDays()>1  ? new DayBasisFare() : new HourBasisFare();
//        searchController.setFareStrategy(fareStrategy);
//        //FETCHING THE CAR LIST FOR THAT LOCATION
//        List<SearchRecord> searchRecordList = searchController.getAvailableVehicles();
//        for(SearchRecord searchRecord :  searchRecordList)
//            System.out.println(searchRecord);
//        //BOOKING THE CAR
//        BookingController bookingController =BookingController.builder().startDateTime(startTime).endDateTime(endTime).build();
//        System.out.println(bookingController.bookRide(searchRecordList.get(1), User.builder().userId(1331).name("Shivam kumar").build()));
//
//        for(SearchRecord searchRecord :  searchRecordList)
//            System.out.println(searchRecord);


        //Chain of responsiblity
//        LogProcessor logProcessor  = new LogProcessor(new InfoLogProcessor(new DebugLogProcessor(new ErrorLogProcessor(null))));
//        logProcessor.log(LogProcessor.INFO,"This is just a info");
//        logProcessor.log(LogProcessor.DEBUG,"Debugging the code");
//        logProcessor.log(LogProcessor.ERROR,"Error occurred please throw exception");

        List<List<String>> queries = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add("CREATE_ACCOUNT");
        list.add("1");
        list.add("account1");
        list.add("2800");
        queries.add(list);



        Map<String,Integer> map = new HashMap<>();
        List<String> ans = new ArrayList<>();

        for(int i = 0;i<queries.size();i++){
            List<String> query = queries.get(i);
            String type = query.get(0);
            String timeStamp = query.get(1);

            switch (type){
                case "CREATE_ACCOUNT":
                    String accountId = query.get(2);
                    if (!map.containsKey(accountId)) {
                        ans.add("false");
                        break;
                    } else {
                        map.put(accountId, 0);
                        ans.add("true");
                    }
                case "DEPOSIT":
                    String existing = query.get(2);
                    int amount = Integer.parseInt(query.get(3));
                    if(Objects.equals("non-existing",existing)){
                        ans.add("");
                        break;
                    } else {
                        if(!map.containsKey(query.get(2))){
                            ans.add("");
                            break;
                        }
                        int existingBalance = map.get(query.get(2));
                        int newBalance = Objects.isNull(existingBalance) ? amount : existingBalance+amount;
                        map.put(query.get(2),existingBalance+amount);
                        ans.add(String.valueOf(existingBalance+amount));
                    }
                    break;

                case "PAY":
                    String isExisting = query.get(2);
                    int amountToWithdraw =  Integer.parseInt(query.get(3));
                    if(Objects.equals("non-existing",isExisting)){
                        ans.add("");
                    } else{
                        if(!map.containsKey(query.get(2))){
                            ans.add("");
                            break;
                        }
                        int existingBalance = map.get(query.get(2));
                        if(amountToWithdraw>existingBalance){
                            ans.add("");
                            break;
                        } else{
                            existingBalance = existingBalance - amountToWithdraw;
                            ans.add(String.valueOf(existingBalance));
                            map.put(query.get(2),existingBalance);
                            ans.add(String.valueOf(existingBalance));
                        }
                    }
                    break;
            }

        }
    }
}
