package AdapterPattern;

public class main {
    public static void main(String[] args) {
        TypeCCharger typeCCharger = new TypeCCharger();
        TypeCChargerAdapter typeCChargerAdapter = new TypeCChargerAdapter(typeCCharger);
        DellLaptop dellLaptop = new DellLaptop(typeCChargerAdapter);
        dellLaptop.chargeLaptop();
    }
}
