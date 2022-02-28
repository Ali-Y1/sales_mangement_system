package login;

import Database.SQLDatabaseConnection;
import Database.SQLQueries;
import Gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login {
    @FXML
    private Button cancelbutton;
    @FXML
    private Label loginmessagelable;
    @FXML
    private TextField Emailtextfield;
    @FXML
    private PasswordField passwordpasswordfield;

    public void loginbuttonaction(ActionEvent e) throws IOException {
        String email=Emailtextfield.getText();
        String pass=passwordpasswordfield.getText();
        if(Emailtextfield.getText().isEmpty()){
            loginmessagelable.setText("Please Enter A Email");
        }
        else if(passwordpasswordfield.getText().isEmpty()){
            loginmessagelable.setText("Please Enter Password");
        }
        else{
            validlogin(email,pass,e);
        }
    }

    private void validlogin(String email, String pass,ActionEvent event) throws IOException {
        Employe emp=null;
        SQLDatabaseConnection connectnow = new SQLDatabaseConnection();
        Connection connectDB=connectnow.getConnection();
        String sql = "SELECT * FROM Login WHERE EMAIL=? AND PASSWORD=?";
        try{

            Statement stmt = connectDB.createStatement();

            PreparedStatement preparedStatement = connectDB.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, pass);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                emp = new Employe();
                emp.setName( resultSet.getString("name"));
                emp.setEmail(resultSet.getString("email"));
                emp.setPassword( resultSet.getString("password"));
                emp.setHisJob( resultSet.getString("hisjob"));

            }
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(emp==null){
            loginmessagelable.setText("wrong email or Password");
        }
        if (emp != null) {
            Stage window = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 500);
            window.setTitle("Hello!");
            window.initStyle(StageStyle.UNDECORATED);
            window.getIcons().add(new Image(Main.class.getResourceAsStream("img/sales-removebg-preview.png")));
            window.setScene(scene);
            window.show();
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.close();
        }
        return;
    }

    public void cancelbuttonAction(ActionEvent e){
        Stage stage= (Stage) cancelbutton.getScene().getWindow();
        stage.close();
    }
}
