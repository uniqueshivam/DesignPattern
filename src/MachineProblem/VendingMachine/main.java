package MachineProblem.VendingMachine;

import MachineProblem.VendingMachine.Display.DisplayObserver;
import MachineProblem.VendingMachine.Display.MainDisplay;
import MachineProblem.VendingMachine.Display.SmallDisplay;
import MachineProblem.VendingMachine.Payments.CardPayment;
import MachineProblem.VendingMachine.Payments.PaymentStrategy;
import MachineProblem.VendingMachine.ProductItems.Products;

import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        Dispenser dispenser = new Dispenser();
        MainDisplay mainDisplay = new MainDisplay();
        SmallDisplay smallDisplay = new SmallDisplay();
        List<DisplayObserver> displayObserverList = new ArrayList<>();
        displayObserverList.add(mainDisplay);
        displayObserverList.add(smallDisplay);

        VendingMachine vendingMachine = new VendingMachine(dispenser,displayObserverList);
        vendingMachine.addProduct("Snacks",100,30,"Snack-1");
        vendingMachine.addProduct("Drink",150,20,"Drink-1");

        Products selectProduct = vendingMachine.getSelectedProduct("Snack-1",12);
        System.out.println("Product fetched with total amount "+selectProduct.getAmount()*12);

        String paymentType = "Card";
        float amountEntered = 400;
        PaymentStrategy paymentStrategy = null;
        switch (paymentType){
            case "Card":
                paymentStrategy = new CardPayment();
                paymentStrategy.updateBalance(amountEntered);
                break;
        }
        vendingMachine.makePayment(selectProduct.getProductCde(),paymentStrategy,12);

    }
}
