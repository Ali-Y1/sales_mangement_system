package EmailServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class sales{
    private static List<Observers> customers = new ArrayList<Observers>();

    public static void addSubscriber(Observers o) {
        customers.add(o);
    }

    public static void removeSubscriber(Observers o) {
        customers.remove(o);
    }

    public static void notifySubscribers() {
        System.out.println("A new item is on sale! Act fast before it sells out!");
        for(Observers o: customers) {
            o.update("Sale!");
        }
    }
}