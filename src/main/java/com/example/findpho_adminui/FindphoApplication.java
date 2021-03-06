package com.example.findpho_adminui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class FindphoApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FindphoApplication.class.getResource("views/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.initStyle(StageStyle.UNDECORATED);
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/findpho.jpg")));
        stage.getIcons().add(icon);
        stage.setTitle("FindPho");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}