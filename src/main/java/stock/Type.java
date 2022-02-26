package stock;
import java.util.ArrayList;
import java.util.List;

public class Type extends StockProducts{

    private String Name;
    private ArrayList<StockProducts> ProductList = new ArrayList<StockProducts>();

    public ArrayList<StockProducts> getProductList() {
        return ProductList;
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public int getAmount() {
        return 0;
    }

    @Override
    public supplier getSup() {
        return new supplier("",0,"");
    }

    public void addProduct(StockProducts pro)
    {
        ProductList.add(pro);
    }
    public void removeProduct(StockProducts pro)
    {
        ProductList.remove(pro);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Type{"+"Name: " + Name+
                " ProductList=" + ProductList +
                "}\n";
    }
}