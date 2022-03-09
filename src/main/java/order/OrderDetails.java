package order;
import Database.SQLDatabaseConnection;
import Database.SQLQueries;

import java.time.*;
import java.util.ArrayList;

public class OrderDetails {
    private ArrayList<product> products = new ArrayList<product>();
    private int totalPrice=0;
    private String Details;
    private LocalDateTime Date;
    private SQLQueries query = new SQLQueries();
    private static int id=-1;

    public static int getId() {
        return id;
    }

    public String getDetails() {
        return Details;
    }

    public LocalDateTime getDate() {
        return Date;
    }

    public OrderDetails(String details) {
        Date = LocalDateTime.now();
        Details = details;
        if(id == -1)
            id=query.getLastOrderId();
        id++;
    }
    // add product is a method that takes products with a given name from the database and adds it to order
    public void AddProduct(String Name,int amount){
        product p;
        p = query.fetchProducts(Name);
        p.setAmount(amount);
        products.add(p);
        totalPrice = totalPrice + amount * p.getPrice();
    }
    public int getTotalPrice(){
        return totalPrice;
    }

    @Override
    public String toString() {
        return "OrderDetails:\n" +
                "products=" + products +
                "\n Details='" + Details + '\'' +
                "\n Date=" + Date ;
    }
}