package com.example.myfirstapp.controller;

import com.example.myfirstapp.Dao.CrudUser;
import com.example.myfirstapp.model.Users;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpController {

    CrudUser crudUser = new CrudUser();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button SignUpButton;

    @FXML
    private RadioButton signUpFamale;

    @FXML
    private TextField signUpFirstName;

    @FXML
    private TextField signUpLacation;

    @FXML
    private TextField signUpLastName;

    @FXML
    private RadioButton signUpMale;

    @FXML
    private PasswordField signUpPassword;

    @FXML
    private TextField signUpUserName;

    @FXML
    void initialize() {
        SignUpButton.setOnAction(event -> {
            createUser();
        });
    }

    private void createUser() {
        String firstname = signUpFirstName.getText();
        String lastname = signUpLastName.getText();
        String username = signUpUserName.getText();
        String password = signUpPassword.getText();
        String location = signUpLacation.getText();
        String gender;
        if (signUpFamale.isSelected()) {
            gender = "female";
        } else gender = "male";

        Users user = new Users(firstname, lastname, username, password, location, gender);
        try {
            crudUser.insertUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            signUpFirstName.setText("");
            signUpLastName.setText("");
            signUpUserName.setText("");
            signUpPassword.setText("");
            signUpLacation.setText("");
        }
    }
}
