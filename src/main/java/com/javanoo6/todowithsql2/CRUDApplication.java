package com.javanoo6.todowithsql2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class CRUDApplication extends Application {
    public CRUDApplication(){

    }
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("hello-view.fxml")));
        stage.setTitle(" ");
        stage.setScene(new Scene(root,600.0D, 400.0D));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}