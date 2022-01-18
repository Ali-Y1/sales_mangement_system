module com.example.salesmangement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.salesmangement to javafx.fxml;
    exports com.example.salesmangement;
}