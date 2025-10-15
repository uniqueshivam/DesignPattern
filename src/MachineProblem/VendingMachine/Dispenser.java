package MachineProblem.VendingMachine;

import MachineProblem.VendingMachine.ProductItems.Products;

public class Dispenser {
    public void dispenseItem(Products product){
        System.out.println("Dispensing the product with product code "+product.getProductCde());
    }
}
