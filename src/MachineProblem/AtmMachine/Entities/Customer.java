package MachineProblem.AtmMachine.Entities;

public class Customer implements User{
    private String customerId;
    private String name;
    private String cardDetails;
    @Override
    public void performAction() {

    }

    @Override
    public String getUserId() {
        return this.customerId;
    }

    public Customer(String customerId, String name, String cardDetails) {
        this.customerId = customerId;
        this.name = name;
        this.cardDetails = cardDetails;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getCardDetails() {
        return cardDetails;
    }
}
