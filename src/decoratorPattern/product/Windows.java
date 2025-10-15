package decoratorPattern.product;

public class Windows extends BaseLaptop{
    @Override
    public int calculatePrice() {
        return 50000;
    }
}
