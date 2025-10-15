package MachineProblem.VendingMachine.Payments;

public interface PaymentStrategy {
    public void updateBalance(float amountQuantity);
    public float getBalance();
    public void resetPayment();
    public void makePayment(float amount);
}
