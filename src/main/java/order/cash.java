package order;
public class cash implements Payment {

    public cash() {
    }

    @Override
    public void pay(int a) {
        System.out.println("Payment of Value:" + a + "$ Has been successfully paid");
    }
}