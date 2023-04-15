package com.example.myfirstapp.controller;

import com.example.myfirstapp.Dao.CrudTask;
import com.example.myfirstapp.animantions.Animantion;
import com.example.myfirstapp.model.Tasks;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;

public class ListTaskController {

    @FXML
    private Button AddTaskBtn;
    @FXML
    private TextField AddTaskDescription;
    @FXML
    private TextField AddTaskField;
    @FXML
    private Label labelSuccessful;
    @FXML
    private ListView<Tasks> listTaskView;

    private ObservableList<Tasks> tasks;

    CrudTask crudTask = new CrudTask();
    public static int taskId=0;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        int userId = AddItemController.userId;

        tasks = FXCollections.observableArrayList();

        if(taskId!=0){
            chargeToUpdate();
        }


        ResultSet resultSet  = crudTask.selectTaskByUser(userId);
        while (resultSet.next()){
            Tasks task1 = new Tasks();
            task1.setTaskid(resultSet.getInt("taskid"));
            task1.setTask(resultSet.getString("task"));
            task1.setDescription(resultSet.getString("description"));
            task1.setDatecreated(resultSet.getTimestamp("datacreated"));
            tasks.add(task1);
        }

        listTaskView.setItems(tasks);
        listTaskView.setCellFactory(CellControloller -> new CellControloller());

        AddTaskBtn.setOnAction(event->{
           if(taskId==0){
               try {
                   addNewTask();
               } catch (SQLException e) {
                   e.printStackTrace();
               } catch (ClassNotFoundException e) {
                   e.printStackTrace();
               }
           }else{
               try {
                   updateTask();
               } catch (SQLException e) {
                   e.printStackTrace();
               } catch (ClassNotFoundException e) {
                   e.printStackTrace();
               }
           }

        });
    }

    private void addNewTask() throws SQLException, ClassNotFoundException {
        Animantion animantion = new Animantion();
        if(AddTaskField.getText()!=""||AddTaskDescription.getText()!="") {
            Tasks newTask = new Tasks();

            Calendar calendar = Calendar.getInstance();
            Timestamp myTime = new Timestamp(calendar.getTimeInMillis());

            newTask.setTask(AddTaskField.getText());
            newTask.setUserid(AddItemController.userId);//calling a global variable userId
            newTask.setDatecreated(myTime);
            newTask.setDescription(AddTaskDescription.getText());

            crudTask.insertTask(newTask);

            labelSuccessful.setVisible(true);
            animantion.fade(labelSuccessful, false, 1f, 0f, 1, 7000);

            AddTaskField.setText("");
            AddTaskDescription.setText("");

            initialize();
        }
    }
    private void updateTask() throws SQLException, ClassNotFoundException {
        Animantion animantion = new Animantion();

        if(AddTaskField.getText()!=""||AddTaskDescription.getText()!="") {
            Tasks newTask = new Tasks();

            newTask.setTaskid(taskId);
            newTask.setTask(AddTaskField.getText());
            newTask.setDescription(AddTaskDescription.getText());

            crudTask.updateTask(newTask);
            taskId=0;

            labelSuccessful.setVisible(true);
            animantion.fade(labelSuccessful, false, 1f, 0f, 1, 7000);

            AddTaskField.setText("");
            AddTaskDescription.setText("");
            AddTaskBtn.setText("Save Task");

            initialize();
        }
    }

     private void chargeToUpdate(){
         Tasks tasks = new Tasks();
         try {
             ResultSet resultSet = crudTask.selectTaskById(taskId);
             while(resultSet.next()){
                 tasks.setTaskid(resultSet.getInt("taskid"));
                 tasks.setTask(resultSet.getString("task"));
                 tasks.setDescription(resultSet.getString("description"));
                 tasks.setDatecreated(resultSet.getTimestamp("datacreated"));
             }
         } catch (SQLException e) {
             e.printStackTrace();
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }

         taskId = tasks.getTaskid();
         AddTaskField.setText(tasks.getTask());
         AddTaskDescription.setText(tasks.getDescription());
         AddTaskBtn.setText("Update Task");

     }
}
