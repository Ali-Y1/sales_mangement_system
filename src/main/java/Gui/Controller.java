package Gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import stock.StockController;

import java.io.IOException;

public class Controller{
    @FXML
    private VBox panel;



    double x,y;



    @FXML
    protected void orderButtonClick() throws IOException {
        Parent fxmlLoader = FXMLLoader.load(Main.class.getResource("orders.fxml"));
        panel.getChildren().removeAll();
        panel.getChildren().setAll(fxmlLoader);


    }
    /*
    @FXML
    protected void onHover(){
        imageView.setStyle("-fx-background-color: #bd944e");
        System.out.println("done");
    }
    @FXML
    protected void onHover2(){
        imageView2.setStyle("-fx-background-color: #bd944e");

    }
    @FXML
    protected void onHover3(){
        imageView3.setStyle("-fx-background-color: #bd944e");
    }

     */
    @FXML
    protected void stockButtonClick() throws IOException {
        Parent fxmlLoader = FXMLLoader.load(Main.class.getResource("stock.fxml"));
        panel.getChildren().removeAll();
        panel.getChildren().setAll(fxmlLoader);
    }
    @FXML
    protected void invoiceButtonClick() throws IOException {
        Parent fxmlLoader = FXMLLoader.load(Main.class.getResource("invoice.fxml"));
        panel.getChildren().removeAll();
        panel.getChildren().setAll(fxmlLoader);

    }
    @FXML
    void dragged(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX()-x);
        stage.setY(event.getScreenY()-y);
    }
    @FXML
    void pressed(MouseEvent event) {
        x= event.getSceneX();
        y= event.getSceneY();
    }
    @FXML
    void max(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setFullScreen(true);
    }

    @FXML
    void min(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);

    }
    @FXML
    void close(ActionEvent event) {
        try {
            StockController.scheduledExecutorService.shutdownNow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();

    }

}