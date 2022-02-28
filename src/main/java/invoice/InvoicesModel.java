package invoice;

import order.product;

import java.util.ArrayList;

public class InvoicesModel {
    @Override
    public String toString() {
        return "InvoicesModel{" +
                "price=" + price +
                ", cid=" + cid +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                ", cname='" + cname + '\'' +
                ", products=" + products +
                ", date='" + date + '\'' +
                '}';
    }

    private Integer price;
    private Integer cid;
    private Integer phone;
    private String address;
    private String cname;
    private ArrayList<product> products = new ArrayList<product>();
    private String date;
    
    public InvoicesModel(Integer price,Integer cid,Integer phone,String address,String cname,String date){
        this.price=price;
        this.cid=cid;
        this.phone=phone;
        this.address=address;
        this.cname=cname;
        this.date=date;
    }

    public Integer getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public ArrayList<product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<product> products) {
        this.products = products;
    }
}