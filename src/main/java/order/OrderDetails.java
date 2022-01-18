package order;
import Database.SQLDatabaseConnection;

import java.time.*;
import java.util.ArrayList;

public class OrderDetails {
    private ArrayList<product> products = new ArrayList<product>();
    private int totalPrice=0;
    private String Details;
    private LocalDateTime Date;
    private SQLDatabaseConnection con;

    public OrderDetails(String details) {
        Date = LocalDateTime.now();
        Details = details;
    }

    public void AddProduct(String Name,int amount){
        product p;
        con = new SQLDatabaseConnection();
        p = con.fetchData(Name);
        p.setAmount(amount);
        totalPrice = totalPrice + amount * p.getPrice();
    }
    public int getTotalPrice(){
        return totalPrice;
    }


}