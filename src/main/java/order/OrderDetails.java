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
    private SQLQueries query;

    public OrderDetails(String details) {
        Date = LocalDateTime.now();
        Details = details;
    }
    // add product is a method that takes products with a given name from the database and adds it to order
    public void AddProduct(String Name,int amount){
        product p;
        query = new SQLQueries();
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