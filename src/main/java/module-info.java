module com.example.salesmangement {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.salesmangement to javafx.fxml;
    exports com.example.salesmangement;
}