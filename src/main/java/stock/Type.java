package stock;
import java.util.ArrayList;
import java.util.List;
public class Type{
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    private String Name;

    private List<StockProducts> ProductList = new ArrayList<StockProducts>();
    public void addProduct(StockProducts pro)
    {
        ProductList.add(pro);
    }
    public void removeProduct(StockProducts pro)
    {
        ProductList.remove(pro);
    }
@Override
    public String toString() {
        return "Type{"+"Name: " + Name+
                " ProductList=" + ProductList +
                "}\n";
    }
}