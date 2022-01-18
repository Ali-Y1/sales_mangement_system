package order;


public abstract class orders {

    OrderDetails od;
    status s;
    //discount,VTA design pattern visitor / singlton for database
    public customer setCustomer(String FnName, String LnName, String email, String Address, int phone) {
        customer c = new customer();
        c.setAddress(Address);
        c.setEmail(email);
        c.setFnName(FnName);
        c.setLnName(LnName);
        c.setPhone(phone);
        return c;
    }
    public void CreateAnOrder(String Details) {
        od = new OrderDetails(Details);
    }
    public void AddProduct(String Name,int amount){
        od.AddProduct(Name,amount);

    }
    public void setPayment(int type) {
        if(type == 1){
            cash c = new cash();
            c.pay(od.getTotalPrice());
        }else{


        }

    }

}