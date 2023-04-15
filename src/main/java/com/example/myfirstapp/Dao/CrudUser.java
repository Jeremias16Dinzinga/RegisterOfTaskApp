package com.example.myfirstapp.Dao;

import com.example.myfirstapp.model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CrudUser extends DataConnection {

    private String userTable = "users";

    private String userid = "userid";
    private String firstname = "firstname";
    private String lastname = "lastname";
    private String username = "username";
    private String password = "password";
    private String location = "location";
    private String gender = "gender";

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    public void insertUser(Users user) throws SQLException, ClassNotFoundException {

        String sqlInsert = "INSERT INTO " + userTable + " (" + firstname + "," + lastname + "," + username + "," + password + "," + location + "," + gender + ")VALUES(?,?,?,md5(?),?,?)";
        connection = getDbConnection();

        preparedStatement = connection.prepareStatement(sqlInsert);
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getUsername());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setString(5, user.getLocation());
        preparedStatement.setString(6, user.getGender());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void deleteUser() {

    }

    public ArrayList selectAllUser() {
        return null;
    }

    //select one user by username and password
    public ResultSet selectUser(Users user) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;
        String sql = "SELECT * FROM " + userTable + " WHERE " + username + "=? AND " + password + "=md5(?)";

        connection = getDbConnection();

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public void updateUser() {

    }
}
