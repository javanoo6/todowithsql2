package com.javanoo6.todowithsql2;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.javanoo6.todowithsql2.DataBase.ConnectToDataBase;

public class Controller implements Initializable {
    //таблица
    @FXML
    private TableView<Assignment> table_todo;

    //колонки
    @FXML
    private TableColumn<Assignment, Integer> column_num;

    @FXML
    private TableColumn<Assignment, String> column_assignment;

    //ввод задания
    @FXML
    private TextField text_field;

    @FXML
    private TextField text_id;


    ObservableList<Assignment> listM;

    int index = -1;

    Connection dataBaseConnection = null;
    PreparedStatement preparedStatement = null;


    public void task_add() throws SQLException, ClassNotFoundException {
        dataBaseConnection = ConnectToDataBase();
        String sql = "insert into todo(task)values(?)";
        try {
            if(!text_field.getText().trim().equals("")) {
            preparedStatement = dataBaseConnection.prepareStatement(sql);
            preparedStatement.setString(1, text_field.getText());
            preparedStatement.execute();
            text_id.setText("");
            text_field.setText("");
            UpdateTable();}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void getSelected() {
        index = table_todo.getSelectionModel().getSelectedIndex();
        if (index <= -1) return;
        text_id.setText(column_num.getCellData(index).toString());
        text_field.setText(column_assignment.getCellData(index));

    }

    public void task_update() {
        try {
            if(!text_field.getText().trim().equals("")) {
            dataBaseConnection = ConnectToDataBase();
            String val = text_id.getText();
            String val1 = text_field.getText();
            String sql = "update todo set id = '"+val+"' , task='" +val1+"' where id='" + val + "' ";
            preparedStatement = dataBaseConnection.prepareStatement(sql);
            preparedStatement.execute();
            text_id.setText("");
            text_field.setText("");
            UpdateTable();}
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void task_delete() throws SQLException, ClassNotFoundException {
        dataBaseConnection = ConnectToDataBase();
        String sql = "delete from todo where id = ?";
        try {

            preparedStatement = dataBaseConnection.prepareStatement(sql);
            preparedStatement.setString(1, text_id.getText());
            preparedStatement.execute();
            text_id.setText("");
            text_field.setText("");
            UpdateTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateTable() throws SQLException, ClassNotFoundException {
        column_num.setCellValueFactory(new PropertyValueFactory<>("id"));
        column_assignment.setCellValueFactory(new PropertyValueFactory<>("assignment"));
        listM = DataBase.getTaskData();
        table_todo.setItems(listM);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            UpdateTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}