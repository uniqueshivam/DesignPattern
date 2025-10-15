package MachineProblem.ParkingSystem.Models;

import lombok.Data;

@Data
public class Transaction {
    private Integer totalAmountInCents;
    private Integer vatInCents;
    private Integer discount;
    private String discountCode;
    private PaymentMode paymentMode;
}
