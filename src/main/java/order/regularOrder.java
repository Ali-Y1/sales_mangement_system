package order;

public class regularOrder extends Orders {
    status s;
    OrderDetails od;
    public regularOrder() {
    }

    public void setStatus(int i) {
        s.updateStatus(i);
    }


}