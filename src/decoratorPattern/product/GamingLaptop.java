package decoratorPattern.product;

public class GamingLaptop extends BaseLaptop{
    @Override
    public int calculatePrice() {
        return 15000;
    }
}
