package stock;
import Database.SQLQueries;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


//flyweight design pattern for products and supplier

//if there is weakness in products notifyObs manag .
//GetProduct(); searches hashmap for products with product name as parameter and returns it.
//hashmap impl for products
public class Stock {
    SQLQueries s = new SQLQueries();
    private static HashMap<Integer,supplier> Supplier ;
    private static ArrayList<Type> Types ;

    @Override
    public String toString() {
        return "Stock{" +
                ", Types=" + Types +
                " ,supplier" + Supplier+
                '}';
    }

     public ArrayList<Type> GetTypes() {
        if(Types == null)
            Types=s.fetchTypes();
         return Types;
     }
    public supplier GetSupplier(int id) {
        if(Supplier == null)
            Supplier=s.fetchSupplier();
        return Supplier.get(id);
    }
}
