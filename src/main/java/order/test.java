package order;

import EmailServices.ShopaholicCustomer;
import EmailServices.sales;

import java.util.Observer;

public class test {
    public static void main(String []arg){
        /*
        fastOrder mug = new fastOrder();
        mug.CreateAnOrder("I want it to have my pic on it");
        mug.AddProduct("mugs",2);
        mug.AddProduct("t-shirts",1);
        mug.setPayment(1,0,0,null);
        orderClass o= new orderClass();
        o.setCustomer(mug.setCustomer("name","ln","yourname@yourlastname.com","addres",71000000));
        o.setOD(mug.od);
        o.setStatus(mug.getStatus());
        System.out.println(o);
         */
        test();
    }
    public static void test(){
        sales s = new sales();
        ShopaholicCustomer customer2 = new ShopaholicCustomer();
        ShopaholicCustomer customer3 = new ShopaholicCustomer();

        // Adding two customers to the newsletter
        sales.addSubscriber( customer2);
        sales.addSubscriber( customer3);

        // Notifying customers (observers)
        sales.notifySubscribers();

    }
}
