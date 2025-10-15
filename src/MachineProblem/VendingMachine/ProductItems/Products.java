package MachineProblem.VendingMachine.ProductItems;

import lombok.ToString;

@ToString
public abstract class Products {
    private String productCde;
    private float amount;
    private Integer quantity;

    public Products(String productCde, float amount, Integer quantity) {
        this.productCde = productCde;
        this.amount = amount;
        this.quantity = quantity;
    }

    public String getProductCde() {
        return productCde;
    }

    public float getAmount() {
        return amount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void decrementQuantity(){
        quantity--;
    }

    public void updateQuantity(Integer quantity){
        quantity+=quantity;
    }
}
