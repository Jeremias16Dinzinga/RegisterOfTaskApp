package com.example.myfirstapp.controller;

import com.example.myfirstapp.Dao.CrudUser;
import com.example.myfirstapp.animantions.Animantion;
import com.example.myfirstapp.model.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    CrudUser crudUser = new CrudUser();
    ResultSet resultSet = null;
    private int userId;

    @FXML
    private Button LoginBtn;

    @FXML
    private PasswordField LoginPassword;

    @FXML
    private Button LoginSignupBtn;

    @FXML
    private TextField LoginUsername;

    @FXML
    private Label loginMessage;

    @FXML
    void initialize() {

        //login button
        LoginBtn.setOnAction(event -> {
            String username = LoginUsername.getText().trim();
            String password = LoginPassword.getText().trim();
            Users user = new Users();
            user.setUsername(username);
            user.setPassword(password);

            if (!user.getUsername().equals("") && !user.getPassword().equals("")) {
                try {
                    resultSet = crudUser.selectUser(user);
                    int counter = 0;
                    while (resultSet.next()) {
                        this.userId = resultSet.getInt("userid");
                        counter++;
                    }
                    if (counter == 1) {
                        showAddItem(); //open a next window
                    } else {
                        Animantion shaker = new Animantion();
                        shaker.shake(LoginPassword);
                        shaker.shake(LoginUsername);

                        loginMessage.setVisible(true);
                        shaker.fade(loginMessage,false,1f,0f,1,4000);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("You need to fill all the field");
            }
        });

        //sign up button to open a signup form
        LoginSignupBtn.setOnAction(event -> {

            LoginSignupBtn.getScene().getWindow().hide();// hide the current window

            //create a new window
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/myfirstapp/view/signUp.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //show up the created window
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

        });

    }

    //open a AddItem window
    void showAddItem(){
        LoginBtn.getScene().getWindow().hide();// hide the current window

        //create a new window
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/myfirstapp/view/addItem.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //show up the created window
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        AddItemController addItemController = loader.getController();//creating a controller
        addItemController.setUserId(this.userId);// set a setUser for transferring user id
        stage.show();
    }
}