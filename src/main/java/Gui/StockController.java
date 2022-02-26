
package Gui;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import stock.Stock;
import stock.StockProducts;
import stock.Type;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


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
        StockController();
        tree();
    }

    private void StockController(){
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

    }


}

