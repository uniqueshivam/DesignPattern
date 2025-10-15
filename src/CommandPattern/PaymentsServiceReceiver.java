package CommandPattern;

public class PaymentsServiceReceiver {
    public void performSale(){
        System.out.println("Performing sale transaction");
    }

    public void performVoid(){
        System.out.println("Performing void transaction");
    }

    public void performRefund(){
        System.out.println("Performing refund transaction");
    }

    public void revertChanges(){
        System.out.println("reverting all the changes performed");
    }
}
