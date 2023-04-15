package com.example.myfirstapp.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnection {
    private Connection DbConnection;
    private String dbHost="localhost";
    private String dbUser="root";
    private String dbPassword="";
    private String dbName = "myfirstapp";
    private int dbPort = 3306;

    String url ="jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName;

    protected Connection getDbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        DbConnection = DriverManager.getConnection(this.url,this.dbUser,this.dbPassword);
        return DbConnection;
    }

}
