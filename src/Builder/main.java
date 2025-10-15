package Builder;

public class main {
    public static void main(String[] args) {
        BurgerBuilder burgerBuilder = new BurgerBuilder(10);
        burgerBuilder.addCheese();
        burgerBuilder.addLettuce();
        Burger burger = new Burger(burgerBuilder);
        System.out.println(burger);
    }
}
