package Builder;

import lombok.ToString;

@ToString
public class Burger {
    private Integer size;
    private boolean cheese;
    private boolean pepperoni;
    private boolean lettuce;
    private boolean tomato;

    public Burger(BurgerBuilder burgerBuilder) {
        this.size = burgerBuilder.size;
        this.cheese = burgerBuilder.cheese;
        this.pepperoni = burgerBuilder.pepperoni;
        this.lettuce = burgerBuilder.lettuce;
        this.tomato = burgerBuilder.tomato;

    }
}
