package MachineProblem.VendingMachine.Payments;

public class CardPayment implements PaymentStrategy{
    public float currentBalance = 0;

    @Override
    public void updateBalance(float amountQuantity) {
        currentBalance+=amountQuantity;
    }

    @Override
    public float getBalance() {
        return this.currentBalance;
    }

    @Override
    public void resetPayment() {
        this.currentBalance = 0;
    }

    @Override
    public void makePayment(float amount) {
        this.currentBalance-=amount;
    }
}
