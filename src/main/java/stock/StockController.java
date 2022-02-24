
package stock;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;

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
    public static ScheduledExecutorService scheduledExecutorService;
    @FXML
    public void initialize() {
        Series<String, Number> series = new Series<>();
        series.setName("Temperature Variation");
        chart1.getData().add(series);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            Integer random = ThreadLocalRandom.current().nextInt(40);
            //  Integer random2 = ThreadLocalRandom.current().nextInt(10);
            //  Integer random1 = ThreadLocalRandom.current().nextInt(40);
            Integer random3 = ThreadLocalRandom.current().nextInt(100);
            Integer random4 = ThreadLocalRandom.current().nextInt(500);

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




}

