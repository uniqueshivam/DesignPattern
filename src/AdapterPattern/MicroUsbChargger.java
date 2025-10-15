package AdapterPattern;

public class MicroUsbChargger implements Charger{
    @Override
    public void plugCharging() {
        System.out.println("Micro usb charging started");
    }
}
