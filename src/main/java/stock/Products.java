package stock;

public class Products {


    private String Name;
    private int Price;
    private int Amount;
    supplier sup;
    //discount

public void products(String name,int Price,int Amount,supplier sup) {
    this.sup = sup;
    this.Amount = Amount;
    this.Price = Price;
}
    public String getName() {
        return Name;
    }
    public void SetNames(String name) {
        Name = name;
    }
    public void SetProdName(String name) {
        Name = name;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }



}
