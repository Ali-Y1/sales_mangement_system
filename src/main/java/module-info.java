module com.example.salesmangement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.mail;


    opens Gui to javafx.fxml;
    opens order to javafx.base;
    exports Gui;
}