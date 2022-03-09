package order;

public class regularOrder extends Orders {
    status s;
    OrderDetails od;
    public regularOrder() {
        s = new status();
    }

    public void setStatus(int i) {
        s.updateStatus(i);
    }
    public String getStatus(){
        return s.getStatus();
    }


}