package decoratorPattern.decorator;

import decoratorPattern.product.BaseLaptop;

public class ExtraRam extends LaptopDecorator{
    BaseLaptop baseLaptop;

    public ExtraRam(BaseLaptop baseLaptop){
        this.baseLaptop = baseLaptop;
    }
    @Override
    public int calculatePrice() {
        return baseLaptop.calculatePrice()+3000;
    }
}
