package order;

public class test {
    public static void main(String []arg){
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
    }
}
