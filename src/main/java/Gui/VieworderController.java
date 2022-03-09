package Gui;

import Database.SQLQueries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
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
    private Label id;

    @FXML
    private Label price;
    @FXML
    private RadioButton paid;

    @FXML
    private RadioButton payC;

    @FXML
    private RadioButton pending;

    @FXML
    private HBox bH;
    @FXML
    private HBox radioC;

    @FXML
    private TextArea note;

    @FXML private TableColumn<product, Integer> AmountCol;
    @FXML private TableColumn<product, String> NameCol;
    @FXML private TableColumn<product, Integer> PriceCol;
    @FXML private TableView<product> tableproducts;

    ObservableList<product> products;
    Button save;
    SQLQueries q = new SQLQueries();
    VieworderController(OrderInfo orderInfo){
        this.orderInfo = orderInfo;
    }
    @FXML
    public void initialize(){
        final ToggleGroup group = new ToggleGroup();
        paid.setToggleGroup(group);
        pending.setToggleGroup(group);
        payC.setToggleGroup(group);
        pending.setUserData("pending");
        paid.setUserData("paid");
        payC.setUserData("payC");
        group.selectedToggleProperty().addListener((event) ->{
            if(!group.getSelectedToggle().getUserData().toString().equals("pending")){
                if(save == null) {
                    save = new Button();
                    save.setText("Save");
                    save.setStyle("-fx-background-color:#5d3c75;-fx-text-fill: white;-fx-font-size: 10px;");
                    save.setOnMouseClicked((e) -> {
                        orderInfo.setStatus("finished");
                        q.UpdateOrderState(orderInfo.getStatus(),orderInfo.getId());
                        bH.getChildren().remove(save);
                        radioC.setDisable(true);
                    });
                    bH.getChildren().add(save);
                }
            }
        });
        cname.setText(orderInfo.getC().getFnName().toUpperCase() + " " + orderInfo.getC().getLnName().toUpperCase());
        cemail.setText(orderInfo.getC().getEmail());
        caddr.setText(orderInfo.getC().getAddress());
        cphone.setText(String.valueOf(orderInfo.getC().getPhone()));
        id.setText("#" + orderInfo.getId());
        price.setText(String.valueOf(orderInfo.getPrice()));
        if(orderInfo.getDetails() != null)
            note.setText(orderInfo.getDetails().trim().replaceAll(" +", " "));
        switch(orderInfo.getStatus()){
            case "finished":
                paid.setDisable(true);
                pending.setDisable(true);
                payC.setDisable(true);
                break;
            case "pending":
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
