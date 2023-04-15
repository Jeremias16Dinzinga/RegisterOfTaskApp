package com.example.myfirstapp.Dao;

import com.example.myfirstapp.model.Tasks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CrudTask extends DataConnection {

    private String taskTable = "tasks";

    private String idTask = "taskid";
    private String task = "task";
    private String idUser = "userid";
    private String dataCreated = "datacreated";
    private String decription = "description";

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    public void insertTask(Tasks tasks) throws SQLException, ClassNotFoundException {
        String sqlInsert = "INSERT INTO " + taskTable + " (" + task + "," + idUser + "," + dataCreated + "," + decription + ")VALUES(?,?,?,?)";
        connection = getDbConnection();

        preparedStatement = connection.prepareStatement(sqlInsert);
        preparedStatement.setString(1, tasks.getTask());
        preparedStatement.setInt(2, tasks.getUserid());
        preparedStatement.setTimestamp(3, tasks.getDatecreated());
        preparedStatement.setString(4, tasks.getDescription());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public void updateTask(Tasks tasks) throws SQLException, ClassNotFoundException {
        String sqlInsert = "UPDATE " + taskTable + " set " + task + " = ?," + decription + " = ? where "+idTask+" = ?";
        connection = getDbConnection();

        preparedStatement = connection.prepareStatement(sqlInsert);
        preparedStatement.setString(1, tasks.getTask());
        preparedStatement.setString(2, tasks.getDescription());
        preparedStatement.setInt(3, tasks.getTaskid());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void deleteTask(int taskId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM "+taskTable+" WHERE "+idTask+"=?";
        connection = getDbConnection();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,taskId);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public ResultSet selectTaskByUser(int userId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;
        String sql = "SELECT * FROM " + taskTable +" WHERE "+idUser+" =? ";

        connection = getDbConnection();

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,userId);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public ResultSet selectTaskById(int taskId) throws SQLException, ClassNotFoundException {
        ResultSet tasks = null;
        String sql = "SELECT * FROM " + taskTable +" WHERE "+idTask+" =? ";

        connection = getDbConnection();

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,taskId);
        tasks = preparedStatement.executeQuery();
        return tasks;
    }

    public ResultSet CountAllTask(int userId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;
        String sql = "SELECT * FROM " + taskTable +" WHERE "+idUser+" =? ";

        connection = getDbConnection();

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,userId);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public Object selectTask() {
        return null;
    }

    public void updateTask() {

    }
}
