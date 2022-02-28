package stock;
import Database.SQLQueries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


//flyweight design pattern for products and supplier

//if there is weakness in products notifyObs manag .
//GetProduct(); searches hashmap for products with product name as parameter and returns it.
//hashmap impl for products
public class Stock {
    SQLQueries s = new SQLQueries();

    public static HashMap<String, supplier> getSuppliers() {
        return Supplier;
    }

    private static HashMap<String,supplier> Supplier ;
    private static ArrayList<Type> Types ;

    public void refreshSupplier(){
        Supplier=s.fetchSupplier();
    }

    @Override
    public String toString() {
        return "Stock{" +
                ", Types=" + Types +
                " ,supplier" + Supplier+
                '}';
    }
    public void AddType(String TypeName){
        Type t = new Type();
        t.setName(TypeName);
        Types.add(t);
    }

     public ArrayList<Type> GetTypes() {
        if(Types == null)
            Types=s.fetchTypes();
         return Types;
     }
    public supplier GetSupplier(int id) {
        if(Supplier == null)
            Supplier=s.fetchSupplier();
        for(Map.Entry<String, supplier> s:Supplier.entrySet()){
            if(s.getValue().getId() == id)
                return s.getValue();
        }
        return null;
    }
    public supplier GetSupplier(String id) {
        if(Supplier == null)
            Supplier=s.fetchSupplier();
        return Supplier.get(id);
    }
}
