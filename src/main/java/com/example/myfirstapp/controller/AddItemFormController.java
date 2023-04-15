package com.example.myfirstapp.controller;

import com.example.myfirstapp.Dao.CrudTask;
import com.example.myfirstapp.animantions.Animantion;
import com.example.myfirstapp.model.Tasks;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AddItemFormController {

    CrudTask crudTask = new CrudTask();
    private int numberOfTask;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AddTaskBtn;

    @FXML
    private TextField AddTaskDescription;

    @FXML
    private TextField AddTaskField;

    @FXML
    private Label labelSuccessful;

    @FXML
    private Button NumberOfTask;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        Animantion animantion = new Animantion();
        countTask();//Count how many task are into database

        AddTaskBtn.setOnAction(event -> {
            Calendar calendar = Calendar.getInstance();
            Timestamp myTime = new Timestamp(calendar.getTimeInMillis());

            System.out.println("This is the id once again: "+AddItemController.userId);
            System.out.println("Task successful add!");

            Tasks task = new Tasks();
            task.setTask(AddTaskField.getText());
            task.setUserid(AddItemController.userId);//calling a global variable userId
            task.setDatecreated(myTime);
            task.setDescription(AddTaskDescription.getText());

            try {
                crudTask.insertTask(task);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                labelSuccessful.setVisible(true);
                animantion.fade(labelSuccessful,false,1f,0f,1,7000);

                AddTaskField.setText("");
                AddTaskDescription.setText("");
            }

            try {
                countTask();//Count how many task are into database
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });

        NumberOfTask.setOnAction(event->{
            NumberOfTask.getScene().getWindow().hide();// hide the current window
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

    }

    private void countTask() throws SQLException, ClassNotFoundException {
        ResultSet tasks = crudTask.CountAllTask(AddItemController.userId);
        int n=0;
        while (tasks.next()){
            n++;
        }
        this.numberOfTask = n;
        NumberOfTask.setText(numberOfTask+" Task");
    }

}
