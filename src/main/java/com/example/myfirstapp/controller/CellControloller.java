package com.example.myfirstapp.controller;

import com.example.myfirstapp.Dao.CrudTask;
import com.example.myfirstapp.model.Tasks;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CellControloller  extends ListCell<Tasks> {

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private Label dateCreated;

    @FXML
    private ImageView deleteBtn;

    @FXML
    private ImageView updateBtn;

    @FXML
    private Label taskLabel;

    @FXML
    private Label description;

    private FXMLLoader fxmlLoader;

    @FXML
    void initialize() {

    }

    @Override
    protected void updateItem(Tasks task, boolean empty) {
        super.updateItem(task, empty);

        if(empty || task == null){
            setText(null);
            setGraphic(null);
        }else{
            if(fxmlLoader == null){
                fxmlLoader =  new FXMLLoader(getClass().getResource("/com/example/myfirstapp/view/cell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            dateCreated.setText(task.getDatecreated().toString());
            description.setText(task.getDescription());
            taskLabel.setText(task.getTask());

            deleteBtn.setOnMouseClicked(event->{
                getListView().getItems().remove(getItem());

                try {
                    new CrudTask().deleteTask(task.getTaskid());
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            });

            updateBtn.setOnMouseClicked(event->{
                ListTaskController.taskId = task.getTaskid();

                //updateBtn.getScene().getWindow().hide();// hide the current window
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/example/myfirstapp/view/listTask.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            });

            setText(null);
            setGraphic(rootAnchorPane);
        }

    }
}
