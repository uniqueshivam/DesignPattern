package MachineProblem.VendingMachine.Factory;

import MachineProblem.VendingMachine.ProductItems.DrinkItems;
import MachineProblem.VendingMachine.ProductItems.Products;
import MachineProblem.VendingMachine.ProductItems.Snacks;

public class ProductFactory {

    public static Products createProduct(String productType, String productCode,float price, Integer quantity){
        switch (productType){
            case "Snacks":
                return new Snacks(productCode,price,quantity);
            case "Drink":
                return new DrinkItems(productCode,price,quantity);
        }
        return null;
    }
}
