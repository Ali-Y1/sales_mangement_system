
package Gui;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import order.OrderInfo;
import stock.Stock;
import stock.StockProducts;
import stock.Type;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


public class StockController {
    @FXML
    private AreaChart<String,Number> chart1;
    @FXML
    private Label label1,label2,label3,label4,label5,label6;

    //tree table view
    @FXML
    private TreeTableView<StockProducts> tr;
    @FXML
    private TreeTableColumn<StockProducts, String> amount;
    @FXML
    private TreeTableColumn<StockProducts, String> name;

    @FXML
    private TreeTableColumn<StockProducts, String> price;

    @FXML
    private TreeTableColumn<StockProducts, String> supplier;
    ///////

    public static ScheduledExecutorService scheduledExecutorService;

    @FXML
    public void initialize() {
        StockControllers();
        tree();
    }

    private void StockControllers(){
        Series<String, Number> series = new Series<>();
        series.setName("Temperature Variation");
        chart1.getData().add(series);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            Integer random = ThreadLocalRandom.current().nextInt(30,40);
            //  Integer random2 = ThreadLocalRandom.current().nextInt(10);
            //  Integer random1 = ThreadLocalRandom.current().nextInt(40);
            Integer random3 = ThreadLocalRandom.current().nextInt(90,100);
            Integer random4 = ThreadLocalRandom.current().nextInt(450,500);

            Platform.runLater(() -> {
                Date now = new Date();
                label1.setText(""+random);
                if (random>=20 && random<=30){
                    label2.setText("Good");
                }
                else label2.setText("Normal");
                label3.setText(""+random3);
                if (random3>=50 && random3<=100){
                    label4.setText("Good");
                }
                else label4.setText("Normal");
                label5.setText(""+random4);
                if (random4>=0 && random4<=200){
                    label6.setText("Good");
                }
                else label6.setText("Normal");
                series.getData().add(new Data<>(simpleDateFormat.format(now), random));
                if (series.getData().size() > 10)
                    series.getData().remove(0);
            });
        }, 0, 1, TimeUnit.SECONDS);
    }

    public void tree(){
        Stock s = new Stock();
        name.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<StockProducts, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().getName())
        );
        price.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<StockProducts, String> param) ->
                        new ReadOnlyStringWrapper(String.valueOf(param.getValue().getValue().getPrice()))
        );
        amount.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<StockProducts, String> param) ->
                        new ReadOnlyStringWrapper(String.valueOf(param.getValue().getValue().getAmount()))
        );
        supplier.setCellValueFactory(
                    (TreeTableColumn.CellDataFeatures<StockProducts, String> param) ->
                            new ReadOnlyStringWrapper(param.getValue().getValue().getSup().getName())
        );

        TreeItem All = new TreeItem(new Type());
        s.GetTypes().stream().forEach((type) -> {
            TreeItem t = new TreeItem<StockProducts>(type);

            type.getProductList().stream().forEach((product) ->{t.getChildren().add(new TreeItem(product));});
            All.getChildren().add(t);
            });
        All.setExpanded(true);
        tr.setShowRoot(false);
        tr.setRoot(All);
        tr.setRowFactory((tv) -> {
            TreeTableRow<StockProducts> row = new TreeTableRow<>();
                row.setOnMouseClicked((event) -> {
                    if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                        StockProducts r = row.getItem();
                        if(r instanceof Type){

                        }else{
                            try {
                                Stage window = new Stage();

                                window.initModality(Modality.APPLICATION_MODAL);
                                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddStock.fxml"));
                                AddStockController AddController = new AddStockController(r,row.getTreeItem().getParent().getValue().getName());

                                fxmlLoader.setController(AddController);
                                Scene scene = new Scene(fxmlLoader.load(), 720, 440);
                                window.initStyle(StageStyle.UNDECORATED);
                                window.setScene(scene);
                                window.showAndWait();
                                tree();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        System.out.println(row.getItem());
                    }
                });
            return row;
        });

    }


    public void writeExcel(ActionEvent event) throws Exception {
        Stock s = new Stock();
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
                String text = "Name , Price ,Amount ,Supplier-Id,Supplier-Name,Supplier-Email \n";
                writer.write(text);
                for (Type type : s.GetTypes()) {
                    AtomicReference<String> Pr = new AtomicReference<>("");
                    AtomicInteger x = new AtomicInteger(1);
                    type.getProductList().stream().forEach((product) -> {
                        Pr.updateAndGet(v -> v + product);
                        x.getAndIncrement();
                    });

                    text = type.getName()+ ":\n" + Pr + "\n";

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

    public void AddStock() throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddStock.fxml"));
        AddStockController AddController = new AddStockController();
        fxmlLoader.setController(AddController);
        Scene scene = new Scene(fxmlLoader.load(), 720, 440);
        window.initStyle(StageStyle.UNDECORATED);
        window.setScene(scene);
        window.showAndWait();
        tree();
    }
}

