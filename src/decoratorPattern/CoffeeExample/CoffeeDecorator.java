package decoratorPattern.CoffeeExample;

public abstract class CoffeeDecorator implements Coffee{
    public Coffee coffee;
    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public String getDescription(){
        return coffee.getDescription();
    }

    public Integer getAmount(){
        return coffee.getAmount();
    }
}
