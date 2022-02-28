package Gui;

import Database.SQLQueries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import order.OrderInfo;
import order.fastOrder;
import order.product;
import order.regularOrder;

import java.util.Map;

public class CreateOrderController {
    @FXML
    private Button EInvoice;
    @FXML
    private RadioButton credit;

    @FXML
    private RadioButton paid;

    @FXML
    private RadioButton pending;

    @FXML
    private CheckBox FBox;

    @FXML
    private TextField CAdd;

    @FXML
    private TextField CEmail;

    @FXML
    private TextField CName;

    @FXML
    private TextField CPhone;

    @FXML
    private TextArea Note;

    @FXML
    private TextField Search;

    @FXML
    private ListView<String> DropeList;

    @FXML
    private VBox Table;

    @FXML
    private StackPane stackpane;
    @FXML
    private Label totalPrice;

    @FXML
    private TableColumn<product, String> Amount;
    @FXML
    private TableColumn<product, String> Pname;

    @FXML
    private TableColumn<product, Float> ProPrice;
    @FXML
    private TableView<product> table;
    ObservableList<product> p = FXCollections.observableArrayList();
    @FXML
    void initialize(){
        final ToggleGroup group = new ToggleGroup();
        paid.setToggleGroup(group);
        pending.setToggleGroup(group);
        credit.setToggleGroup(group);

        DropeList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && DropeList.getSelectionModel().getSelectedItem() != null) {
                Search.setText(DropeList.getSelectionModel().getSelectedItem());
                DropeList.toBack();
            }
            });
        Pname.setCellValueFactory(new PropertyValueFactory<product, String>("name"));
        Amount.setCellValueFactory(new PropertyValueFactory<product, String>("amount"));
        ProPrice.setCellValueFactory(new PropertyValueFactory<product, Float>("price"));
        Amount.setCellFactory(TextFieldTableCell.forTableColumn());
        Amount.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<product, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<product, String> t) {
                        ((product) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setAmount(Integer.parseInt(t.getNewValue()));
                        Sum();
                    }
                }
        );
        table.setItems(p);
    }

    @FXML
    void close(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    void search(){
        String sub = Search.getText();
        ObservableList<String> result = FXCollections.observableArrayList();
        if(!DropeList.isFocused())
            DropeList.toFront();
            for(Map.Entry<String, product> p :OrderController.getProducts().entrySet()){
                if (String.valueOf(p.getValue().getName()).contains(sub)) {
                    result.add(p.getValue().getName());
                }
            }
            if(sub.equals(""))
                DropeList.toBack();
            DropeList.getItems().setAll(result);

    }
    @FXML
    void ADDProduct(ActionEvent event) {
        product pro = OrderController.getProduct(Search.getText());
        p.add(pro);
        DropeList.toBack();

    }
    void Sum(){
        float total=0;
        for(product prod :p){
            total+=Integer.parseInt(prod.getAmount()) * prod.getPrice();
        }
        totalPrice.setText(String.valueOf(total));
    }
    @FXML
    void fastOrder(){
        if(FBox.isSelected()) {
            paid.setDisable(true);
            pending.setDisable(true);
            credit.setDisable(true);
            Note.setDisable(true);
            EInvoice.setDisable(true);
        }else{
            paid.setDisable(false);
            pending.setDisable(false);
            credit.setDisable(false);
            Note.setDisable(false);
            EInvoice.setDisable(false);
        }
    }
    @FXML
    void Save(){
        SQLQueries q =new SQLQueries();
        if(FBox.isSelected()){
            OrderInfo order = new OrderInfo();
            fastOrder fast = new fastOrder();
            fast.CreateAnOrder("");
            fast.setCustomer(CName.getText().split(" ")[0],CName.getText().split(" ")[1],CEmail.getText(),CAdd.getText(), Integer.parseInt(CPhone.getText()));
            for(product pro:p) {
                fast.AddProduct(pro.getName(), Integer.parseInt(pro.getAmount()));
                order.AddProduct(pro);
            }
            fast.setPayment(0,0,0,null);
            fast.setStatus();
            order.setC(fast.getC());
            order.setStatus("finished");
            order.setPrice(fast.getOd().getTotalPrice());
            order.setDate(String.valueOf(fast.getOd().getDate()));
            order.setDetails("");
            q.AddOrders(order);
        }else{
            regularOrder regular = new regularOrder();
            OrderInfo order = new OrderInfo();
            regular.CreateAnOrder(Note.getText());
            regular.setCustomer(CName.getText().split(" ")[0],CName.getText().split(" ")[1],CEmail.getText(),CAdd.getText(), Integer.parseInt(CPhone.getText()));
            for(product pro:p) {
                regular.AddProduct(pro.getName(), Integer.parseInt(pro.getAmount()));
                order.AddProduct(pro);
            }
            if(pending.isSelected())
                regular.setStatus(1);
            else
                regular.setStatus(3);
            regular.setPayment(0,0,0,null);
            order.setC(regular.getC());
            order.setPrice(regular.getOd().getTotalPrice());
            order.setDate(String.valueOf(regular.getOd().getDate()));
            order.setDetails(regular.getOd().getDetails());
            q.AddOrders(order);
        }
    }
}
