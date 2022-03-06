package com.example.findpho_adminui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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
    public void btn_Logout(ActionEvent actionEvent) throws IOException {
        try {
            Controller hozzaadas = newWindow("views/login-view.fxml", "Login",
                    600, 500);
            hozzaadas.getStage().show();
        } catch (Exception e) {
            error(e);
        }
        stage = (Stage) mainAnchor.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void btn_Settings(ActionEvent actionEvent) throws IOException {
        changeStage("views/settings-view.fxml");
    }
}