package decoratorPattern.CoffeeExample;

public class Milk extends CoffeeDecorator{
    public Milk(Coffee coffee) {
        super(coffee);
    }

    public Integer getAmount(){
        return coffee.getAmount()+250;
    }


}
