package stock;
public class supplier {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private int id;
    private String name,email;
    private int PhoneNumber;

    public void supplier(){

    }
    public void supplier(String StockName , int PhoneNumber,String email){
        name=StockName;
        this.email=email;
        this.PhoneNumber=PhoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getSupplierName(){
        return this.name;
    }
    public int getPhoneNumber(){
        return this.PhoneNumber;
    }
    public String getEmail(){
        return this.email;
    }

    @Override
    public String toString() {
        return "supplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", PhoneNumber=" + PhoneNumber +
                '}';
    }
}
