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
    private static HashMap<String,supplier> Supplier ;
    private ArrayList<Type> Types ;

    @Override
    public String toString() {
        return "Stock{" +
                ", Types=" + Types +
                " ,supplier" + Supplier+
                '}';
    }

    Stock(){
        System.out.println("test");
       Supplier=s.fetchSupplier();
       Types=s.fetchTypes();
    }
     public  List<Type> GetTypes() {
         if (Types == null) {
             //  Types=FETCHTYPES();
         }
         return Types;
     }
    public  supplier GetSupplier(String SupName) {
         return    Supplier.get(SupName);
    }
     }
