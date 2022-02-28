module com.example.salesmangement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.mail;
    requires kernel;
    requires layout;


    opens Gui to javafx.fxml;
    opens order to javafx.base;
    opens stock to javafx.fxml;
    opens invoice to javafx.fxml;
    opens login to javafx.fxml;
    exports Gui;
    exports invoice;
    exports login;
}