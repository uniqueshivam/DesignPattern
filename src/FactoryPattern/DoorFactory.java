package FactoryPattern;

import FactoryPattern.Doors.Door;
import FactoryPattern.Doors.WoodenDoor;

public class DoorFactory {
    public Door makeDoor(int width, int height){
        return new WoodenDoor(width,height);
    }
}
