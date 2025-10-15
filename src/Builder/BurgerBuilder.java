package Builder;

public class BurgerBuilder {
    public Integer size;
    public boolean cheese;
    public boolean pepperoni;
    public boolean lettuce;
    public boolean tomato;

    public BurgerBuilder (Integer size){
        this.size = size;
    }

    public void addCheese() {
        this.cheese = true;
    }

    public void addPepperoni() {
        this.pepperoni = true;
    }

    public void addLettuce() {
        this.lettuce = true;
    }

    public void addTomato() {
        this.tomato = true;
    }

}
