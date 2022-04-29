package com.example.findpho_adminui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController extends Controller {

    @FXML
    private BorderPane contentArea;
    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private Pane pane;

    private Stage stage;

    private double x, y = 0;

    public void initialize() throws IOException {
        changeStage("views/home-view.fxml");
    }

    @FXML
    public void btn_Home(ActionEvent actionEvent) throws IOException {
        changeStage("views/home-view.fxml");
    }

    private void changeStage(String fxml) throws IOException {
        AnchorPane view = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
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
        if (!(confirm("Do you really want to leave?"))) {
            return;
        }
        System.exit(0);
    }

    @FXML
    public void btn_Logout(ActionEvent actionEvent) throws IOException {
        if (confirm("You will be returned to the login screen.")) {
            try {
                Controller add = newWindow("views/login-view.fxml", "Login",
                        600, 500);
                add.getStage().show();
            } catch (Exception e) {
                error(e);
            }
            stage = (Stage) mainAnchor.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    public void btn_Statistics(ActionEvent actionEvent) throws IOException {
        changeStage("views/statistics-view.fxml");
    }
}