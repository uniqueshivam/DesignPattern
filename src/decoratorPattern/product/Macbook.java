package decoratorPattern.product;

public class Macbook extends BaseLaptop{

    @Override
    public int calculatePrice() {
        return 100000;
    }
}
