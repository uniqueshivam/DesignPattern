package AdapterPattern;

public class DellLaptop{
    Charger charger;
   public DellLaptop(Charger charger){
       this.charger = charger;
   }

   public void chargeLaptop(){
       charger.plugCharging();
   }
}
