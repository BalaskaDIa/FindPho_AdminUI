package com.example.findpho_adminui.controllers;

import com.example.findpho_adminui.Controller;
import com.example.findpho_adminui.MainController;
import com.example.findpho_adminui.api.LoginApi;
import com.example.findpho_adminui.classes.Login;
import com.example.findpho_adminui.classes.Token;
import com.example.findpho_adminui.classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class LoginController extends Controller {

    @FXML
    private TextField txt_Username;
    @FXML
    private Label lbl_ForgotPass;
    @FXML
    private TextField txt_Password;
    @FXML
    private Label lbl_CreateAcc;
    @FXML
    private Pane pane;
    @FXML
    private AnchorPane mainAnchor;

    private Stage stage;

    private double x, y = 0;

    @FXML
    public void btn_minWindow(ActionEvent actionEvent) {
        stage = (Stage) mainAnchor.getScene().getWindow();
        this.minimizeWindow(stage);
    }

    @FXML
    public void btn_closeWindow(ActionEvent actionEvent) {
        Controller.closeWindow(actionEvent);
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
    public void btn_Login(ActionEvent actionEvent) {
        String username = txt_Username.getText();
        String password = txt_Password.getText();

        Login login = new Login(username,password);
        try {
            Token token = LoginApi.postLogin(login);
            User data = LoginApi.getLoginData(token.getToken());
            if(data.isAdmin()) {
                Controller add = newWindow("views/main-view.fxml", "FindPho",
                        900, 650);
                add.getStage().show();
            } else {
                alert("Bruh");
            }
        } catch (IOException e) {
            error(e);
        }
        stage = (Stage) mainAnchor.getScene().getWindow();
        stage.close();
    }
}
