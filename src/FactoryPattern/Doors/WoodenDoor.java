package FactoryPattern.Doors;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public class WoodenDoor implements Door{
    int width;
    int height;
    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }
}
