package invoice;

import Database.SQLDatabaseConnection;
import Database.SQLQueries;
import Gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import order.customer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {
    @FXML
    private TableColumn<InvoicesModel, String> caddress;
    @FXML
    private TableView<InvoicesModel> invoicestable;

    @FXML
    private TextField searchf;
    @FXML
    private Label ijj;


    @FXML
    private TableColumn<InvoicesModel, Integer> cid;

    @FXML
    private TableColumn<InvoicesModel, String> cname;

    @FXML
    private TableColumn<InvoicesModel, Integer> cpnumber;

    @FXML
    private TableColumn<InvoicesModel, Integer> tprice;

    @FXML
    private TableColumn<InvoicesModel, Integer> vdate;

    ObservableList<InvoicesModel> invoicesModelsobservablelist= FXCollections.observableArrayList();

    InvoicesModel invoicesModel;

    public void initialize(URL url, ResourceBundle resource){
        SQLDatabaseConnection conectnow=new SQLDatabaseConnection();
        Connection connectDB=conectnow.getConnection();
        String Statment="select id,cid,price,date from orders";
        try {
            Statement statment=connectDB.createStatement();
            ResultSet queryoutput=statment.executeQuery(Statment);
            while(queryoutput.next()) {
                customer c;

                Integer Costumerid = queryoutput.getInt("cid");
                SQLQueries query = new SQLQueries();
                c = query.fetchCustomerByID(Costumerid);
                String CoustmerName = c.getFnName()+" "+ c.getLnName();
                Integer Coustmerphone = c.getPhone();
               String Coustmeraddress = c.getAddress();
                Integer orderprice = queryoutput.getInt("price");
                String orderdate= queryoutput.getString("date");
               /// System.out.println(" "+orderprice+" "+Costumerid);
                invoicesModelsobservablelist.add(new InvoicesModel(orderprice,Costumerid,Coustmerphone,Coustmeraddress,CoustmerName,orderdate));
            }
            caddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            cid.setCellValueFactory(new PropertyValueFactory<>("cid"));
            cname.setCellValueFactory(new PropertyValueFactory<>("cname"));
            cpnumber.setCellValueFactory(new PropertyValueFactory<>("phone"));
            tprice.setCellValueFactory(new PropertyValueFactory<>("price"));
            vdate.setCellValueFactory(new PropertyValueFactory<>("date"));
            invoicestable.setItems(invoicesModelsobservablelist);

            FilteredList <InvoicesModel> filterdata=new FilteredList<>(invoicesModelsobservablelist,b-> true);

            searchf.textProperty().addListener((observable, oldValue, newValue) -> {
               filterdata.setPredicate(invoicesModel ->{

                   if(newValue.isEmpty() ||newValue.isBlank() || newValue==null){
                       return true;
                   }
                   String sreachKey=newValue.toLowerCase();
                   if(invoicesModel.getCname().toLowerCase().indexOf(sreachKey) > -1){
                       return true;
                   }
                   if(invoicesModel.getCid().toString().indexOf(sreachKey) > -1){
                       return true;
                   }
                   else {
                       return false;
                   }
               });
            });
            SortedList<InvoicesModel> soretdata=new SortedList<>(filterdata);
            soretdata.comparatorProperty().bind(invoicestable.comparatorProperty());
            invoicestable.setItems(soretdata);
            invoicestable.setRowFactory(invoices->{
                TableRow<InvoicesModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {

                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    InvoicesModel rowData = row.getItem();
                    try {
                        Stage window =new Stage();
                        window.initModality(Modality.APPLICATION_MODAL);
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("orderview.fxml"));
                        InvoiceprintControler ordercontrol = new InvoiceprintControler(rowData);
                        fxmlLoader.setController(ordercontrol);
                        Scene scene = new Scene(fxmlLoader.load(), 600, 700);
                        window.initStyle(StageStyle.UNDECORATED);
                        window.setScene(scene);
                        window.showAndWait();
                       ///System.out.println(rowData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row ;
        });
        }
        catch (Exception e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE,null,e);
            e.printStackTrace();
        }

    }

}
