package com.javanoo6.todowithsql2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
//import java.util.ArrayList;

public class DataBase {

    // Данные для подключения к локальной базе данных
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DB_NAME = "todo-sql-java";
    private static final String LOGIN = "root";
    private static final String PASS = "qwerty54321";

    public static Connection ConnectToDataBase() throws ClassNotFoundException, SQLException{
            String connStr = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(connStr, LOGIN, PASS);

    }
    public static ObservableList<Assignment> getTaskData() throws SQLException, ClassNotFoundException {
        Connection dataBaseConnection = ConnectToDataBase();
        ObservableList<Assignment> list = FXCollections.observableArrayList();
        try{
            PreparedStatement preparedStatement = dataBaseConnection.prepareStatement("select * from todo");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                list.add(new Assignment(Integer.parseInt(resultSet.getString("id")),resultSet.getString("task")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
