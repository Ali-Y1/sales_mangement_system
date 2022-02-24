package Gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import order.OrderInfo;
import order.product;

import static java.lang.Thread.sleep;


public class VieworderController {
    @FXML
    private Label caddr;

    @FXML
    private Label cemail;

    @FXML
    private Label cname;

    @FXML
    private Label cphone;

    @FXML
    private Label finished;

    @FXML
    private Label paid;

    @FXML
    private Label pendding;

    @FXML
    private Label pvcc;

    @FXML
    private Label id;

    @FXML
    private Label price;

    @FXML private TableColumn<product, Integer> AmountCol;
    @FXML private TableColumn<product, String> NameCol;
    @FXML private TableColumn<product, Integer> PriceCol;
    @FXML private TableView<product> tableproducts;

    ObservableList<product> products;

    VieworderController(OrderInfo orderInfo){
        this.orderInfo = orderInfo;
    }
    @FXML
    public void initialize(){
        cname.setText(orderInfo.getC().getFnName().toUpperCase() + " " + orderInfo.getC().getLnName().toUpperCase());
        cemail.setText(orderInfo.getC().getEmail());
        caddr.setText(orderInfo.getC().getAddress());
        cphone.setText(String.valueOf(orderInfo.getC().getPhone()));
        id.setText("#" + orderInfo.getId());
        price.setText(String.valueOf(orderInfo.getPrice()));
        switch(orderInfo.getStatus()){
            case "finished":
                pendding.setStyle("-fx-background-color: grey");
                pvcc.setStyle("-fx-background-color: grey");
                paid.setStyle("-fx-background-color: grey");
                break;
            case "pending":
                finished.setStyle("-fx-background-color: grey");
                break;
        }
        products = FXCollections.observableList(orderInfo.getProducts());
        PriceCol.setCellValueFactory(new PropertyValueFactory<product, Integer>("Price"));
        NameCol.setCellValueFactory(new PropertyValueFactory<product, String>("Name"));
        AmountCol.setCellValueFactory(new PropertyValueFactory<product, Integer>("Amount"));
        tableproducts.getItems().setAll(products);


    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    private OrderInfo orderInfo;

    @FXML
    void close(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();

    }
}
