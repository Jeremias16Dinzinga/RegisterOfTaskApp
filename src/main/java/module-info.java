module com.example.myfirstapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.myfirstapp to javafx.fxml;
    exports com.example.myfirstapp;
    exports com.example.myfirstapp.controller;
    opens com.example.myfirstapp.controller to javafx.fxml;
}