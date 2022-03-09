package stock;

public class  StockProducts {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String Name;
    private String Price="";
    private String Amount="";
    private supplier sup;


    //discount

    public StockProducts(){

    }
    public  StockProducts(String name, int Price, int Amount, supplier sup) {
    this.Name=name;
    this.sup = sup;
    this.Amount = String.valueOf(Amount);
    this.Price = String.valueOf(Price);
}

    public void setName(String name) {
        Name = name;
    }

    public void setPrice(int price) {
        Price = String.valueOf(price);
    }

    public void setAmount(int amount) {
        Amount = String.valueOf(amount);
    }

    public void setSup(supplier sup) {
        this.sup = sup;
    }


    public String getName() {
        return Name;
    }

    public String getPrice() {
        return Price;
    }

    public String getAmount() {
        return Amount;
    }

    public supplier getSup() {
        return sup;
    }


    @Override
    public String toString() {
        return  "   "+Name + ',' + Price + "," + Amount +
                "," + sup.getId() + "," + sup.getName() + "," + sup.getEmail();
    }
}
