package decoratorPattern.CoffeeExample;

public class main {
    public static void main(String[] args) {
        Coffee coffee = new BasicCoffee();
//        coffee = new Milk(coffee);
        coffee = new BlackSugar(coffee);

        System.out.println(coffee.getAmount());
    }
}
