package order;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class OrderInfo {
    //order class for customer and the order details


    private int id;
    private String Details;
    private float price;
    private String status;
    private String Date;
    private ArrayList<product> products = new ArrayList<product>();
    private customer c;

    public ArrayList<product> getProducts() {
        return products;
    }

    public void AddProduct(order.product product) {
       products.add(product);
    }
    public void parseProduct(String productString){

        for(String product : productString.split("#")){
            String[] p = product.split("@");
                AddProduct(new product(p[0],Integer.parseInt(p[1]),Integer.parseInt(p[2].trim().replaceAll(" +", " "))));

        }

    }


    public customer getC() {
        return c;
    }

    public void setC(customer c) {
        this.c = c;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "c=" + c +
                ", id=" + id +
                ", Details='" + Details + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", Date=" + Date +
                ", products=" + products +
                '}';
    }
}
