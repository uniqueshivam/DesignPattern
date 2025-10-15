package decoratorPattern.CoffeeExample;

public class BlackSugar extends  CoffeeDecorator{
    public BlackSugar(Coffee coffee) {
        super(coffee);
    }

    public Integer getAmount(){
        return coffee.getAmount()+980;
    }

}