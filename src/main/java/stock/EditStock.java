package stock;

//transforming from constructor to setters
public class EditStock {
private Products product;
public void EditStock(String name, int price, int quantity ,String productname,Stock stock){
    product=stock.GetProduct(productname);

    product.SetNames(name);
    product.setPrice(price);
    product.setAmount(quantity);

}


/**public String getname(){

    return this.name;
}
    public int getprice(){
        return this.price;
    }
    public int getquan(){
        return this.quantity;
    }
       public void editname(String newname){

        this.name =newname;


    }

    public void editprice(int newprice){

        this.price=newprice;

    }**/
}
