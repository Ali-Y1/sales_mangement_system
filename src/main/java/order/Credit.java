package order;
import java.text.DateFormat;
import java.time.*;


public class Credit implements Payment {
    private int creditCardNumber;
    private int cvv;
    private DateFormat ExpirationDate;

    public Credit(int creditCardNumber,int cvv,DateFormat ExpirationDate) {
        this.creditCardNumber = creditCardNumber;
        this.cvv = cvv;
        this.ExpirationDate = ExpirationDate;
    }

    @Override
    public void pay(int a) {
        System.out.println("Payment of Value: " + a + "$ Has been successfully paid");
    }
}