package stock;
//   supplierprod
public class supplier {

    private String name,email;
    private int phonenumber;

    public void supplier(String Stockname , int phonenumber,String email){
        name=Stockname;
        this.email=email;
        this.phonenumber=phonenumber;
    }
    public String getsuppliername(){
        return this.name;
    }
    public int getPhonenumber(){
        return this.phonenumber;
    }
    public String getEmail(){
        return this.email;
    }

}
