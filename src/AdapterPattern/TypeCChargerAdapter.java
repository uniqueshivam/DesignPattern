package AdapterPattern;

public class TypeCChargerAdapter implements Charger{
    private TypeCCharger typeCCharger;
    public TypeCChargerAdapter (TypeCCharger typeCCharger){
        this.typeCCharger = typeCCharger;
    }
    @Override
    public void plugCharging() {
        typeCCharger.fastCharging();
    }
}
