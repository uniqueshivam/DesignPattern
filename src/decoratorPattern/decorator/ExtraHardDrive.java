package decoratorPattern.decorator;

import decoratorPattern.product.BaseLaptop;

public class ExtraHardDrive extends LaptopDecorator{
    BaseLaptop baseLaptop;

    public ExtraHardDrive(BaseLaptop baseLaptop){
        this.baseLaptop = baseLaptop;
    }
    @Override
    public int calculatePrice() {
        return baseLaptop.calculatePrice()+5500;
    }
}
