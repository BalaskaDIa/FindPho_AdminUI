package com.example.findpho_adminui;

import com.example.findpho_adminui.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;


import java.io.IOException;

public class MainController extends Controller {

    @FXML
    private BorderPane contentArea;
    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private Pane pane;

    private Stage stage;

    private double x, y = 0;

    @FXML
    private AnchorPane sideBar;

    public void initialize() throws IOException {
        changeStage("views/home-view.fxml");
    }

    @Deprecated
    public void btn_Users(ActionEvent actionEvent) throws IOException {
        try {
            Controller hozzaadas = newWindow("views/user-view.fxml", "Users",
                    900, 650);
            hozzaadas.getStage().show();
        } catch (Exception e) {
            error(e);
        }
    }

    @FXML
    public void btn_Home(ActionEvent actionEvent) throws IOException {
        changeStage("views/home-view.fxml");
    }

    private void changeStage(String fxml) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource(fxml));
        contentArea.setCenter(view);
    }

    @FXML
    public void dragPane(MouseEvent event) {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    public void pressPane(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    public void btn_minWindow(ActionEvent actionEvent) {
        stage = (Stage) mainAnchor.getScene().getWindow();
        this.minimizeWindow(stage);
    }

    @FXML
    public void btn_maxWindow(ActionEvent actionEvent) {
        stage = (Stage) mainAnchor.getScene().getWindow();
        this.maximizeWindowToggle(stage);
    }

    @FXML
    public void btn_closeWindow(ActionEvent actionEvent) {
        Controller.closeWindow(actionEvent);
    }

    @FXML
    public void btn_Logout(ActionEvent actionEvent) { Controller.closeWindow(actionEvent); }

    @FXML
    public void btn_Settings(ActionEvent actionEvent) throws IOException {
        changeStage("views/settings-view.fxml");
    }
}