package order;

public class product {

    public product() {
    }

    private String Name;
    private int Price;
    private int Amount;
    //discount


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public int getPrice() {
        return Price;
    }

    public String getAmount() {
        return String.valueOf(Amount);
    }

    public product(String name, int price, int amount) {
        Name = name;
        Price = price;
        Amount = amount;
    }


    @Override
    public String toString() {
        return ",Name=" + Name  +
                ", Price=" + Price +
                ", Amount=" + Amount + ",";
    }
}