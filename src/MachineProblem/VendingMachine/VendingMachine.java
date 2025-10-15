package MachineProblem.VendingMachine;

import MachineProblem.VendingMachine.Display.DisplayObserver;
import MachineProblem.VendingMachine.Factory.ProductFactory;
import MachineProblem.VendingMachine.Payments.PaymentStrategy;
import MachineProblem.VendingMachine.ProductItems.Products;

import java.util.*;

public class VendingMachine {
    private Dispenser dispenser;
    private List<DisplayObserver> displayObserverList;
    private List<Products> productsList;

    public VendingMachine(Dispenser dispenser, List<DisplayObserver> displayObserverList) {
        this.dispenser = dispenser;
        this.displayObserverList = displayObserverList;
        this.productsList = new ArrayList<>();
    }

    public Products getSelectedProduct(String productCode, Integer quantity){
        return productsList.stream().filter(products -> productCode == products.getProductCde() && products.getQuantity()>quantity).findFirst().orElseThrow(() ->new RuntimeException("Product not found"));
    }

    public void makePayment(String productCode, PaymentStrategy paymentStrategy,Integer quantity){
        if(Objects.nonNull(paymentStrategy)){
            Products product = productsList.stream()
                    .filter(p -> productCode.equals(p.getProductCde()))  // Use .equals() for String comparison
                    .findFirst()
                    .orElse(null);
            if(product.getQuantity()>quantity){
                float totalAmount = product.getAmount()*quantity;
                if(paymentStrategy.getBalance()>=totalAmount){
                    paymentStrategy.makePayment(totalAmount);
                    product.decrementQuantity();
                    displayMessage("Payment is successful for product "+product.getProductCde());
                    displayMessage("Total Payment done "+totalAmount);
                    displayMessage("Remaining balance "+paymentStrategy.getBalance());
                    dispenser.dispenseItem(product);
                } else{
                    displayMessage("Payment can't be performed as balance is low");
                }
            }
        }
    }

    public void addProduct(String productType, Integer quantity, float amount, String productCode) {
        if (productsList.isEmpty()) {
            Products p = ProductFactory.createProduct(productType, productCode, amount, quantity);
            productsList.add(p);
            return;
        }

        boolean productFound = false;

        // Using an iterator to safely modify the list while iterating
        Iterator<Products> iterator = productsList.iterator();
        while (iterator.hasNext()) {
            Products product = iterator.next();
            if (product.getProductCde().equals(productCode)) {  // Use .equals() for String comparison
                product.updateQuantity(quantity);
                productFound = true;
                break;  // No need to check further, exit loop
            }
        }

        // If product was not found, add it to the list
        if (!productFound) {
            Products p = ProductFactory.createProduct(productType, productCode, amount, quantity);
            productsList.add(p);
        }
    }


    private void displayMessage(String message){
        displayObserverList.forEach(d->{d.updateMessage(message);});
    }
}
