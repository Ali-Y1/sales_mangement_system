package Gui;

import Database.SQLQueries;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import order.OrderInfo;

public class OrderController {
    @FXML
    private TableView<OrderInfo> tableOrders;
    private SQLQueries query=new SQLQueries();;
    private ObservableList<OrderInfo> orders ;
    @FXML private TableColumn<OrderInfo, Integer> IdCol;
    @FXML private TableColumn<OrderInfo, String> StatusCol;
    @FXML private TableColumn<OrderInfo, String> PriceCol;

    @FXML
    public void initialize() {
        orders = query.fetchOrders();
        IdCol.setCellValueFactory(new PropertyValueFactory<OrderInfo, Integer>("id"));
        StatusCol.setCellValueFactory(new PropertyValueFactory<OrderInfo, String>("status"));
        PriceCol.setCellValueFactory(new PropertyValueFactory<OrderInfo, String>("price"));

        tableOrders.getItems().setAll(orders);

    }

}
