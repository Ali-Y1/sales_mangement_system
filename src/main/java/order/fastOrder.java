package order;


public class fastOrder implements orders {

    public fastOrder() {
    }

    @Override
    public customer setCustomer(String FnName,String LnName,String email,String Address,int phone) {
        customer c = new customer();
        c.setAddress(Address);
        c.setEmail(email);
        c.setFnName(FnName);
        c.setLnName(LnName);
        c.setPhone(phone);
        return c;
    }


    public void setOrderDetails() {
        // TODO implement here
    }

    public void setStatus() {
        // TODO implement here
    }

    public void Operation1() {
        // TODO implement here
    }

    public void setPayment() {
        // TODO implement here
    }

}