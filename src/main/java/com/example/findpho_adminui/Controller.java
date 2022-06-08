package com.example.findpho_adminui;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    protected Stage stage;

    public static Pane pane;

    public static DialogPane dialogPane;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private static Image icon = new Image(Controller.class.getResourceAsStream("/com/example/findpho_adminui/img/findpho.jpg"));

    protected void alert(String message) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setContentText(message);
        alert.getButtonTypes().add(ButtonType.OK);
        dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/com/example/findpho_adminui/css/alert.css").toExternalForm());
        dialogPane.getStyleClass().add("dialogPane");
        Stage stage = (Stage) dialogPane.getScene().getWindow();
        stage.getIcons().add(icon);
        alert.show();
    }

    protected boolean confirm(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Are you sure?");
        alert.setHeaderText(message);
        dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/com/example/findpho_adminui/css/alert.css").toExternalForm());
        dialogPane.getStyleClass().add("dialogPane");
        Stage stage = (Stage) dialogPane.getScene().getWindow();
        stage.getIcons().add(icon);
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    protected void error(Exception e) {
        e.printStackTrace(System.err);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(e.getClass().toString());
        alert.setContentText(e.getMessage());
        dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Controller.class.getResource("/com/example/findpho_adminui/css/alert.css").toExternalForm());
        dialogPane.getStyleClass().add("dialogPane");
        Stage stage = (Stage) dialogPane.getScene().getWindow();
        stage.getIcons().add(icon);
        Timer alertTimer = new Timer();
        alertTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> alert.show());
            }
        }, 500);
    }

    public static Controller newWindow(String fxml, String title, int width, int height) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(FindphoApplication.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle(title);
        stage.setScene(scene);
        Controller controller = fxmlLoader.getController();
        controller.stage = stage;
        return controller;
    }

    protected void maximizeWindowToggle(Stage stage) {
        if (stage.isMaximized()) {
            stage.setMaximized(false);
        } else {
            stage.setMaximized(true);
        }
    }

    protected void minimizeWindow(Stage stage) {
        stage.setIconified(true);
    }
}
