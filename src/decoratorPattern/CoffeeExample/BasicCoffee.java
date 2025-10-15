package decoratorPattern.CoffeeExample;

public class BasicCoffee implements Coffee{
    @Override
    public String getDescription() {
        return "I am basic coffee";
    }

    @Override
    public Integer getAmount() {
        return 1400;
    }
}
