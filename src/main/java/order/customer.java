package order;

public class customer {
    private int Phone;
    private String FnName;
    private String LnName;
    private String Email;
    private String Address;

    public customer() {
    }

    public String getEmail() {
        return Email;
    }
    public String getFnName() {
        return FnName;
    }

    public void setFnName(String fnName) {
        FnName = fnName;
    }

    public String getLnName() {
        return LnName;
    }

    public void setLnName(String lnName) {
        LnName = lnName;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public String toString() {
        return FnName + " "+ LnName;
    }
}