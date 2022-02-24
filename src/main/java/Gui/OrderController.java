package Gui;

import Database.SQLQueries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import order.OrderInfo;
import order.customer;

import java.io.IOException;

public class OrderController {
    private SQLQueries query=new SQLQueries();
    private ObservableList<OrderInfo> orders ;
    //All orders Table
    @FXML private TableView<OrderInfo> tableOrders;

    @FXML private TableColumn<OrderInfo, Integer> IdCol;
    @FXML private TableColumn<OrderInfo, String> StatusCol;
    @FXML private TableColumn<OrderInfo, String> PriceCol;
    @FXML private TableColumn<OrderInfo, String> DateCol;
    @FXML private TableColumn<OrderInfo, customer> customerCol;
    //Pendding orders Table
    @FXML private TableView<OrderInfo> tableOrdersPennding;

    @FXML private TableColumn<OrderInfo, Integer> IdCol1;
    @FXML private TableColumn<OrderInfo, String> StatusCol1;
    @FXML private TableColumn<OrderInfo, String> PriceCol1;
    @FXML private TableColumn<OrderInfo, String> DateCol1;
    @FXML private TableColumn<OrderInfo, customer> customerCol1;
    //other fields
    @FXML private TextField search;

    @FXML
    public void initialize() {
        orders = query.fetchOrders();
        IdCol.setCellValueFactory(new PropertyValueFactory<OrderInfo, Integer>("id"));
        StatusCol.setCellValueFactory(new PropertyValueFactory<OrderInfo, String>("status"));
        PriceCol.setCellValueFactory(new PropertyValueFactory<OrderInfo, String>("price"));
        DateCol.setCellValueFactory(new PropertyValueFactory<OrderInfo, String>("Date"));
        customerCol.setCellValueFactory(new PropertyValueFactory<OrderInfo, customer>("c"));
        tableOrders.getItems().setAll(orders);
        tableOrders.setRowFactory( tv -> {
            TableRow<OrderInfo> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    OrderInfo rowData = row.getItem();
                    try {
                        Stage window = new Stage();
                        window.initModality(Modality.APPLICATION_MODAL);
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("viewOrder.fxml"));
                        VieworderController vieworder = new VieworderController(rowData);
                        fxmlLoader.setController(vieworder);
                        Scene scene = new Scene(fxmlLoader.load(), 720, 460);
                        window.initStyle(StageStyle.UNDECORATED);
                        window.setScene(scene);
                        window.showAndWait();
                        System.out.println(rowData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row ;
        });

    }
@FXML
    public void create() throws IOException {

    }
    @FXML
    public void searchOrders(){
        String sub = search.getText();
        ObservableList<OrderInfo> result = FXCollections.observableArrayList();
        for (OrderInfo part : orders) {
            if (String.valueOf(part.getId()).contains(sub) || (part.getC().getFnName() + " " + part.getC().getLnName()).contains(sub)) {
                result.add(part);
            }
        }
        tableOrders.getItems().removeAll();
        tableOrders.getItems().setAll(result);
    }

    @FXML
    public void pennding(){
        ObservableList<OrderInfo> result = FXCollections.observableArrayList();
        for (OrderInfo part : orders) {
            if (part.getStatus().equals("pending")) {
                result.add(part);
            }
        }
        IdCol1.setCellValueFactory(new PropertyValueFactory<OrderInfo, Integer>("id"));
        StatusCol1.setCellValueFactory(new PropertyValueFactory<OrderInfo, String>("status"));
        PriceCol1.setCellValueFactory(new PropertyValueFactory<OrderInfo, String>("price"));
        DateCol1.setCellValueFactory(new PropertyValueFactory<OrderInfo, String>("Date"));
        customerCol1.setCellValueFactory(new PropertyValueFactory<OrderInfo, customer>("c"));
        tableOrdersPennding.getItems().setAll(result);
        tableOrdersPennding.setRowFactory( tv -> {
            TableRow<OrderInfo> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    OrderInfo rowData = row.getItem();
                    try {
                        Stage window = new Stage();
                        window.initModality(Modality.APPLICATION_MODAL);
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("viewOrder.fxml"));
                        VieworderController vieworder = new VieworderController(rowData);
                        fxmlLoader.setController(vieworder);
                        Scene scene = new Scene(fxmlLoader.load(), 720, 460);
                        window.initStyle(StageStyle.UNDECORATED);
                        window.setScene(scene);
                        window.showAndWait();
                        System.out.println(rowData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row ;
        });
    }


}
