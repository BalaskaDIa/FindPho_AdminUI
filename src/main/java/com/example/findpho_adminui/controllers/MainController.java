package com.example.findpho_adminui.controllers;

import com.example.findpho_adminui.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;


import java.io.IOException;

public class MainController extends Controller {
    @FXML
    private Button btn_Users;
    @FXML
    private Button btn_Home;
    @FXML
    private AnchorPane mainAnchor;

    private boolean fullscreen = false;

    @Deprecated
    public void btn_Users(ActionEvent actionEvent) throws IOException {
        try {
            Controller hozzadas = newWindow("user.fxml", "Users",
                    900, 650);
            hozzadas.getStage().show();
        } catch (Exception e) {
            error(e);
        }
    }

    @FXML
    public void btn_minWindow(ActionEvent actionEvent) {
        stage = (Stage) mainAnchor.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void btn_closeWindow(ActionEvent actionEvent) {
        if (!(confirm("Are u sure?"))) {
            return;
        }
        System.exit(0);
    }

    @FXML
    public void btn_maxWindow(ActionEvent actionEvent) {
        stage = (Stage) mainAnchor.getScene().getWindow();
        if (!fullscreen) {
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX(primaryScreenBounds.getMinX());
            stage.setY(primaryScreenBounds.getMinY());

            stage.setMaxWidth(primaryScreenBounds.getWidth());
            stage.setMinWidth(primaryScreenBounds.getWidth());

            stage.setMaxHeight(primaryScreenBounds.getHeight());
            stage.setMinHeight(primaryScreenBounds.getHeight());

            fullscreen = true;
        } else {
            stage.setMaxWidth(900);
            stage.setMinWidth(900);

            stage.setMaxHeight(650);
            stage.setMinHeight(640);

            stage.centerOnScreen();

            fullscreen = false;
        }
    }
}