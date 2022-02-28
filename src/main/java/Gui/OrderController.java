package Gui;

import Database.SQLQueries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import order.OrderInfo;
import order.customer;
import order.product;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

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

    private static HashMap<String,product> products;

    public static HashMap<String, product> getProducts() {
        return products;
    }

    public static product getProduct(String name) {
        return products.get(name);
    }

    @FXML
    public void initialize() {
        orders = query.fetchOrders();
        products = query.fetchAllProduct();
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
                        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
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
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CreateOrder.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        window.initStyle(StageStyle.UNDECORATED);
        window.setScene(scene);
        window.show();
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

    public void writeExcel(ActionEvent event) throws Exception {
        Writer writer = null ;
        try {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.CSV)", "*.csv");
            fileChooser.getExtensionFilters().add(extFilter);

            //Show save file dialog
            File file = fileChooser.showSaveDialog((Stage) ((Node) event.getSource()).getScene().getWindow());

            if (file != null) {
                //File file = new File("D:\\test1.csv.");
                writer = new BufferedWriter(new FileWriter(file));
                String text = "Id,Customer First Name ,Last Name ,Phone number , Email , Address , Price , Status ,Products ,,,,,,, \n";
                writer.write(text);
                for (OrderInfo order : orders) {
                    AtomicReference<String> orderPr = new AtomicReference<>("");
                    AtomicInteger x = new AtomicInteger(1);
                    order.getProducts().stream().forEach((product) -> {
                        orderPr.updateAndGet(v -> v + "Product" + x + ":" + product);
                        x.getAndIncrement();
                    });

                    text = order.getId() + "," + order.getC().getFnName() + "," + order.getC().getLnName() + "," + order.getC().getPhone() + "," + order.getC().getEmail() + "," + order.getC().getAddress() + "," + order.getPrice() + "," + order.getStatus() + "," + orderPr + "\n";

                    writer.write(text);
                }
            }
            } catch(Exception ex){
                ex.printStackTrace();
            }
        finally{

                writer.flush();
                writer.close();
            }
        }

}
