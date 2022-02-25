package stock;

public class  StockProducts {

    private String Name;
    private int Price;
    private int Amount;
    supplier sup;
    //discount

    public void setName(String name) {
        Name = name;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public void setSup(supplier sup) {
        this.sup = sup;
    }

    public void StockProducts(String name, int Price, int Amount, supplier sup) {
    this.Name=name;
    this.sup = sup;
    this.Amount = Amount;
    this.Price = Price;
}


    public String getName() {
        return Name;
    }

    public int getPrice() {
        return Price;
    }

    public int getAmount() {
        return Amount;
    }

    public supplier getSup() {
        return sup;
    }


    @Override
    public String toString() {
        return "StockProducts{" +
                "Name='" + Name + '\'' +
                ", Price=" + Price +
                ", Amount=" + Amount +
                ", sup=" + sup +
                '}';
    }
}
