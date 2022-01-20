package stock;

public class inventoryCondition {

    private int temperature=1,humidity=1;


public void inventoryCondition ( int temperature){

    this.temperature=temperature;
}
public int getTemperature() {
    return this.temperature;
}
    public int gethumidity() {
        return this.humidity;
    }
    /**public void Allowedtemperature (int temperature){
    if (temperature>20){
        System.out.print ("Not Good Temp");
    }
    if (temperature<20){
        System.out.print ("Good Temp");
     }   }**/

}

