package stock;

//  @Override
//  public void start(Stage stage) throws IOException {

import java.util.HashMap;

/**
 * FXMLLoader fxmlLoader = new FXMLLoader(StockApp.class.getResource("view.fxml"));
 * Scene scene = new Scene(fxmlLoader.load(), 320, 240);
 * stage.setTitle("Stock");
 * stage.setScene(scene);
 * stage.show();
 **/
//flyweight design pattern for products and supplier
//mvc stock
//if there is weakness in products notifyObs manag .
//GetProduct(); searches hashmap for products with product name as parameter and returns it.
//hashmap impl for products
public class Stock {
private static final HashMap product = new HashMap();

public Products GetProduct(String ProductName){
    Products prod=(Products)product.get(ProductName);
    if (prod==null){
        prod = new Products();
        product.put(ProductName,prod);
        System.out.println("Creating a new product : "+ prod);
    }
    return prod;
}

}



   /** public static void main(String[] args) {
        launch();
    }**/
