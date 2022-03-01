package com.example.findpho_adminui.controllers;

import com.example.findpho_adminui.Controller;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class LoginController extends Controller {


    @javafx.fxml.FXML
    private Button btn_Login;
    @javafx.fxml.FXML
    private TextField txt_Username;
    @javafx.fxml.FXML
    private Label lbl_ForgotPass;
    @javafx.fxml.FXML
    private TextField txt_Password;
    @javafx.fxml.FXML
    private Label lbl_CreateAcc;
    @javafx.fxml.FXML
    private Pane pane;
    @javafx.fxml.FXML
    private AnchorPane mainAnchor;

    private Stage stage;

    private double x, y = 0;

    @javafx.fxml.FXML
    public void btn_minWindow(ActionEvent actionEvent) {
        stage = (Stage) mainAnchor.getScene().getWindow();
        this.minimizeWindow(stage);
    }

    @javafx.fxml.FXML
    public void btn_closeWindow(ActionEvent actionEvent) {
        Controller.closeWindow(actionEvent);
    }

    @javafx.fxml.FXML
    public void dragPane(MouseEvent event) {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @javafx.fxml.FXML
    public void pressPane(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }
}
