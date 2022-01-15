package order;
import java.time.*;

public class OrderDetails {

    private String Details;
    private LocalDateTime Date;

    public OrderDetails(String details) {
        LocalDateTime Date = LocalDateTime.now();
        Details = details;

    }


}