package order;


import Database.SQLQueries;

import java.text.DateFormat;

public abstract class Orders {
    customer c;
    OrderDetails od;
    status s;
    //discount,VTA design pattern visitor / singlton for database
    public customer setCustomer(String FnName, String LnName, String email, String Address, int phone) {
        checkCustomer cC = new checkCustomer();
        int id = cC.isCustomer(phone);
        if(id != -1){
            SQLQueries query = new SQLQueries();
            c = query.fetchCustomerByID(id);
        }else{
            c = cC.addCustomer(FnName, LnName, email, Address, phone);
        }
        return c;
    }
    public void CreateAnOrder(String Details) {
        od = new OrderDetails(Details);
    }
    public void AddProduct(String Name,int amount){
        od.AddProduct(Name,amount);

    }
    public void setPayment(int type, int CN, int CVV, DateFormat date) {
        if(type == 1){
            cash c = new cash();
            c.pay(od.getTotalPrice());
        }else{
            Credit C = new Credit(CN,CVV,date);
            C.pay(od.getTotalPrice());
        }

    }

}