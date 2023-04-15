package com.example.myfirstapp.controller;

import com.example.myfirstapp.animantions.Animantion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddItemController {

    public static int userId;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AddItemAnchorPane;

    @FXML
    private ImageView addItemBtn;

    @FXML
    private Label addItemNoTask;

    @FXML
    void initialize() {
        addItemBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, Event ->{
            Animantion animantion = new Animantion();

            animantion.fade(AddItemAnchorPane,false,0f,1f,1,2000);

            try {
                AnchorPane formAddItem = FXMLLoader.load(getClass().getResource("/com/example/myfirstapp/view/addItemForm.fxml"));

                AddItemAnchorPane.getChildren().setAll(formAddItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void setUserId(int userId) {
        AddItemController.userId = userId;
        System.out.println("This is the id: "+userId);
    }
}
