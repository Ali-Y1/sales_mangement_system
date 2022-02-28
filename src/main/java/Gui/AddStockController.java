package Gui;

import Database.SQLQueries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import order.product;
import stock.Stock;
import stock.StockProducts;
import stock.Type;
import stock.supplier;

import java.util.Map;
import java.util.function.Supplier;

public class AddStockController {

    @FXML
    private TextField txt1;

    @FXML
    private TextField txt2;

    @FXML
    private TextField txt3;

    @FXML
    private TextField SEmail;

    @FXML
    private TextField SNumber;

    @FXML
    private TextField Sname;
    
    @FXML
    private ComboBox<String> types;
    @FXML
    private VBox newTypeVBox;
    @FXML
    private TextField NewTypeName;
    @FXML
    private TextField Search;
    @FXML
    private ListView<String> SupplierListView;
    ObservableList<String> StockTypes;
    SQLQueries query = new SQLQueries();
    Stock s = new Stock();
    boolean newSupplier=true;
    StockProducts stockProduct;
    String typeE;
    boolean edited=false;
    public AddStockController(){

    }
    public AddStockController(StockProducts s,String type){
        stockProduct = s;
        typeE = type;
    }
    @FXML
    void initialize(){
        if(stockProduct != null) {
            txt1.setText(stockProduct.getName());
            txt2.setText(stockProduct.getPrice());
            txt3.setText(stockProduct.getAmount());
            types.setValue(typeE);
            Sname.setText(stockProduct.getSup().getName());
            SEmail.setText(stockProduct.getSup().getEmail());
            SNumber.setText(String.valueOf(stockProduct.getSup().getPhoneNumber()));

        }
            Stock s = new Stock();
            StockTypes = FXCollections.observableArrayList();
            for (Type t : s.GetTypes()) {
                StockTypes.add(t.getName());
            }
            types.getItems().setAll(StockTypes);

            SupplierListView.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && SupplierListView.getSelectionModel().getSelectedItem() != null) {
                    Search.setText(SupplierListView.getSelectionModel().getSelectedItem());
                    SupplierListView.toBack();
                    Sname.setText(SupplierListView.getSelectionModel().getSelectedItem());
                    SEmail.setText(s.GetSupplier(SupplierListView.getSelectionModel().getSelectedItem()).getEmail());
                    SNumber.setText(String.valueOf(s.GetSupplier(SupplierListView.getSelectionModel().getSelectedItem()).getPhoneNumber()));
                    newSupplier = false;
                }
            });


    }
    @FXML
    void close(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    void NewType(){
        newTypeVBox.toFront();
    }
    @FXML
    void SaveNewType(){
        newTypeVBox.toBack();
        String name = NewTypeName.getText();
        if(!StockTypes.contains(name)){
            StockTypes.add(name);
            query.AddType(name);
            s.AddType(name);
            types.getItems().removeAll();
            types.getItems().setAll(StockTypes);
            NewTypeName.setText("");
        }
    }
    @FXML
    void CancelNewType(){
        newTypeVBox.toBack();
        NewTypeName.setText("");
    }
    @FXML
    void SupplierSearch(){
        String sub = Search.getText();
        ObservableList<String> result = FXCollections.observableArrayList();
        if(!SupplierListView.isFocused()) {
            SupplierListView.toFront();
        }
        for(Map.Entry<String, supplier> S :s.getSuppliers().entrySet()){
            if (String.valueOf(S.getValue().getName()).contains(sub)) {
                result.add(S.getValue().getName());
            }
        }
        if(sub.equals(""))
            SupplierListView.toBack();
        SupplierListView.getItems().setAll(result);
    }

    @FXML
    void Save(ActionEvent event) {
        if (edited) {
            //edit the object without creating new one
        } else {
            if (types.getValue() == null)
                System.out.println("you should select a type");
            if (newSupplier) {
                supplier S = new supplier(Sname.getText(), Integer.parseInt(SNumber.getText()), SEmail.getText());
                query.Addsupplier(S);
                s.refreshSupplier();
            }
            for (Type t : s.GetTypes()) {
                if (t.getName().equals(types.getValue())) {
                    StockProducts product = new StockProducts(txt1.getText(), Integer.parseInt(txt2.getText()), Integer.parseInt(txt3.getText()), new supplier(Sname.getText(), Integer.parseInt(SNumber.getText()), SEmail.getText()));
                    t.addProduct(product);
                    query.AddStockProduct(product, t.getName());
                }

            }
            close(event);
        }
    }
}
