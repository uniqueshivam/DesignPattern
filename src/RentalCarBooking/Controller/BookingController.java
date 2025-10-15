package RentalCarBooking.Controller;

import RentalCarBooking.Model.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.sound.midi.Soundbank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Getter
@Setter
public class BookingController {
    public static int bookingId = 1;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public BookingDetail bookRide(SearchRecord searchRecord,User user ){
        System.out.println("Booking request received for user - "+user.getName());
        Vehicle bookedVehicle = searchRecord.getVehicle();
        if(!bookedVehicle.isAvailable){
            System.out.println("Vehicle not available, please try another vehicle");
            return null;
        }
        bookedVehicle.setAvailable(false);
        BookingDetail bookingDetail = new BookingDetail();
        bookingDetail.setBookingId(bookingId);
        bookingDetail.setBookedByUser(user);
        bookingDetail.setFare(searchRecord.getFare());
        bookingDetail.setStartDateTime(startDateTime);
        bookingDetail.setEndDateTime(endDateTime);
        bookingDetail.setVehicle(searchRecord.getVehicle());
        System.out.println("Car successfully booked");
        return bookingDetail;
    }
}
