package com.example.findpho_adminui.controllers;

import com.example.findpho_adminui.Controller;
import com.example.findpho_adminui.api.UserApi;
import com.example.findpho_adminui.classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.ToggleSwitch;

import java.io.IOException;

public class AddUserController extends Controller {

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private TextField txt_Email;
    @FXML
    private TextField txt_Username;
    @FXML
    private TextField txt_Name;
    @FXML
    private ToggleSwitch ts_Admin;

    @FXML
    private Pane pane;

    private double x, y = 0;

    @FXML
    public void btnCancel(ActionEvent actionEvent) {
        stage = (Stage) mainAnchor.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void btn_AddSave(ActionEvent actionEvent) throws IOException {
        String name = txt_Name.getText().trim();
        String username = txt_Username.getText().trim();
        String email = txt_Email.getText().trim();
        boolean admin = ts_Admin.isSelected();

        if (name.isEmpty()) {
            alert("Name is required!");
            return;
        }
        if (username.isEmpty()) {
            alert("Username is required!");
            return;
        }

        if (email.isEmpty()) {
            alert("Email address is required!");
            return;
        }
        if (!email.contains("@")) {
            alert("The email address you entered is invalid.");
            return;
        }

        try {
            User newUser = new User(0, name, username, email, admin);
            User created = UserApi.postUser(newUser);
            if (created != null) {
                alert("Creation successful");
                this.stage.close();
            } else {
                alert("Creation failed");
            }
        } catch (Exception e) {
            error(e);
        }
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
}
