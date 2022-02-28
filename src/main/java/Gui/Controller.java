package Gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller{
    @FXML
    private AnchorPane panel;


    double x,y;



    @FXML
    protected void orderButtonClick() throws IOException {
        Parent fxmlLoader = FXMLLoader.load(Main.class.getResource("orders.fxml"));
        panel.getChildren().removeAll();
        panel.getChildren().setAll(fxmlLoader);
        panel.getChildren().stream().forEach((child) -> {
            panel.setBottomAnchor(child,0.0);
            panel.setTopAnchor(child,0.0);
            panel.setLeftAnchor(child,0.0);
            panel.setRightAnchor(child,0.0);
        });


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
        panel.getChildren().stream().forEach((child) -> {
            panel.setBottomAnchor(child,0.0);
            panel.setTopAnchor(child,0.0);
            panel.setLeftAnchor(child,0.0);
            panel.setRightAnchor(child,0.0);
        });
    }
    @FXML
    protected void invoiceButtonClick() throws IOException {
        Parent fxmlLoader = FXMLLoader.load(Main.class.getResource("Invoices.fxml"));
        panel.getChildren().removeAll();
        panel.getChildren().setAll(fxmlLoader);
        panel.getChildren().stream().forEach((child) -> {
            panel.setBottomAnchor(child,0.0);
            panel.setTopAnchor(child,0.0);
            panel.setLeftAnchor(child,0.0);
            panel.setRightAnchor(child,0.0);
        });

    }
    @FXML
    protected void AboutBtClicked() throws IOException {
        Parent fxmlLoader = FXMLLoader.load(Main.class.getResource("About.fxml"));
        panel.getChildren().removeAll();
        panel.getChildren().setAll(fxmlLoader);
        panel.getChildren().stream().forEach((child) -> {
            panel.setBottomAnchor(child,0.0);
            panel.setTopAnchor(child,0.0);
            panel.setLeftAnchor(child,0.0);
            panel.setRightAnchor(child,0.0);
        });
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
            System.out.println(" ");
        }
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();

    }

}