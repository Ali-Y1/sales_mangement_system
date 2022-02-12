package order;


public class fastOrder extends Orders {
    status s = new status();

    public fastOrder() {
        s.updateStatus(3);
    }

    public void setStatus() {
        System.out.println("This Order is finished");
    }

    public status getStatus() {
        return s;
    }


}