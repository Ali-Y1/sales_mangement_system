package order;


public class fastOrder extends orders {
    status s = new status();

    public fastOrder() {
        s.updateStatus(3);
    }

    public void setStatus() {
        System.out.println("This Order is finished");
    }

    public void setPayment() {
        System.out.println("This Order is finished");
    }

}