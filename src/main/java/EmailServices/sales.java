package EmailServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class sales implements subject{
    private List<Observer> customers = new ArrayList<>();
    @Override
    public void addSubscriber(Observer o) {
        customers.add(o);
    }
    @Override
    public void removeSubscriber(Observer o) {
        customers.remove(o);
    }
    @Override
    public void notifySubscribers() {
        System.out.println("A new item is on sale! Act fast before it sells out!");
        for(Observer o: customers) {
            o.update(null,"Sale!");
        }
    }
}