package com.example.findpho_adminui.controllers;

import com.example.findpho_adminui.Controller;
import com.example.findpho_adminui.api.LoginApi;
import com.example.findpho_adminui.classes.Login;
import com.example.findpho_adminui.classes.Token;
import com.example.findpho_adminui.classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.awt.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class LoginController extends Controller {

    @FXML
    private TextField txt_Username;
    @FXML
    private TextField txt_Password;
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
        if (!(confirm("Do you really want to leave?"))) {
            return;
        }
        System.exit(0);
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
                stage = (Stage) mainAnchor.getScene().getWindow();
                stage.close();
            } else {
                alert("You do not have sufficient privileges! Log on again with administrator privileges.");
            }
        } catch (IOException e) {
            error(e);
        }
    }

    @FXML
    public void goToRegistration(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("http://localhost:8000/register"));
    }
}
