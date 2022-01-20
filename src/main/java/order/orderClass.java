package order;

public class orderClass {
    //order class for customer and the order details

    private customer c;
    private OrderDetails od;

    public status getStatus() {
        return s;
    }

    public void setStatus(status s) {
        this.s = s;
    }

    private status s;

    public customer getCustomer() {
        return c;
    }

    public void setCustomer(customer c) {
        this.c = c;
    }

    public OrderDetails getOD() {
        return od;
    }

    public void setOD(OrderDetails od) {
        this.od = od;
    }

    @Override
    public String toString() {
        return c +"\n"+od + "\n" + s;
    }
}
