package com.example.findpho_adminui.controllers;

import com.example.findpho_adminui.Controller;
import com.example.findpho_adminui.api.UserApi;
import com.example.findpho_adminui.classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.ToggleSwitch;

import java.io.IOException;

public class EditUserController extends Controller {

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private TabPane tabPane;

    @FXML
    private TextField txt_Name;
    @FXML
    private TextField txt_Username;
    @FXML
    private TextField txt_Email;

    @FXML
    private Label lbl_Name;
    @FXML
    private Label lbl_Username;
    @FXML
    private Label lbl_Email;
    @FXML
    private ToggleSwitch lbl_Admin;

    private User updateUser;
    private double x, y = 0;

    @FXML
    private ToggleSwitch ts_Admin;


    @FXML
    public void dragTab(MouseEvent event) {
        Stage stage = (Stage)tabPane.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    public void pressTab(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    public void btn_closeWindowData(ActionEvent actionEvent) {
        try {
            Controller add = newWindow("views/user-view.fxml", "Users",
                    900, 600);
            add.getStage().show();
        } catch (Exception e) {
            error(e);
        }
        stage = (Stage) mainAnchor.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void btn_closeWindowUpdate(ActionEvent actionEvent) {
        try {
            Controller add = newWindow("views/user-view.fxml", "Users",
                    900, 600);
            add.getStage().show();
        } catch (Exception e) {
            error(e);
        }
        stage = (Stage) mainAnchor.getScene().getWindow();
        stage.close();
    }

    public User getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(User updateUser) {
        this.updateUser = updateUser;
        setValue();
    }

    @FXML
    public void btn_Save(ActionEvent actionEvent) {
        String name = txt_Name.getText().trim();
        String username = txt_Username.getText().trim();
        String email = txt_Email.getText().trim();
        boolean admin = ts_Admin.isSelected();

        if (name.isEmpty()){
            alert("Name is required!");
            return;
        }
        if (username.isEmpty()){
            alert("Username is required!");
            return;
        }
        if (email.isEmpty()){
            alert("Email is required!");
            return;
        }

        updateUser.setName(name);
        updateUser.setUsername(username);
        updateUser.setEmail(email);
        updateUser.setAdmin(admin);

        try {
            User updated = UserApi.putUser(updateUser);
            if (updated != null){
                alert("Update successful");
                this.stage.close();
            } else {
                alert("Update failed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setValue() {
        lbl_Name.setText(updateUser.getName());
        lbl_Username.setText(updateUser.getUsername());
        lbl_Email.setText(updateUser.getEmail());
        lbl_Admin.setSelected(updateUser.isAdmin());

        txt_Name.setText(updateUser.getName());
        txt_Username.setText(updateUser.getUsername());
        txt_Email.setText(updateUser.getEmail());
        ts_Admin.setSelected(updateUser.isAdmin());
    }
}
