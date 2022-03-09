package com.example.findpho_adminui.controllers;

import com.example.findpho_adminui.Controller;
import com.example.findpho_adminui.api.UserApi;
import com.example.findpho_adminui.classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;

public class UserController extends Controller {

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn <User,Integer> idCol;
    @FXML
    private TableColumn <User,String> nameCol;
    @FXML
    private TableColumn <User,String> usernameCol;
    @FXML
    private TableColumn <User,String> emailCol;
    @FXML
    private TableColumn <User,Boolean> adminCol;

    @FXML
    private Button btn_search;
    @FXML
    private Button btn_edit;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_delete;

    @FXML
    private TextField txt_Search;

    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private AnchorPane sideBar;
    @FXML
    private Pane pane;
    private Stage stage;
    private double x, y = 0;

    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        adminCol.setCellValueFactory(new PropertyValueFactory<>("admin"));
        addList();
    }

    private void addList() {
        try {
            List<User> userList = UserApi.getUser();
            userTable.getItems().clear();
            for (User user : userList) {
                userTable.getItems().add(user);
            }
        }catch (IOException e) {
            error(e);
        }
    }

    @FXML
    public void btn_search(ActionEvent actionEvent) {
    }

    @FXML
    public void btn_add(ActionEvent actionEvent) {
        try {
            Controller add = newWindow("views/addUser-view.fxml", "Add user",
                    400, 400);
            add.getStage().show();
        } catch (Exception e) {
            error(e);
        }
        stage = (Stage) mainAnchor.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void btn_edit(ActionEvent actionEvent) {
        /*try {
            Controller add = newWindow("views/editUser-view.fxml", "Edit user",
                    400, 400);
            add.getStage().show();
        } catch (Exception e) {
            error(e);
        }
        stage = (Stage) mainAnchor.getScene().getWindow();
        stage.close();*/
        int selectedIndex = userTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            alert("First select an item from the table");
            return;
        }
        User updateUser = userTable.getSelectionModel().getSelectedItem();
        try {
            EditUserController modify = (EditUserController) newWindow("views/editUser-view.fxml",
                    "Edit user", 400, 400);
            modify.setUpdateUser(updateUser);
            modify.getStage().setOnHiding(event -> userTable.refresh());
            modify.getStage().show();
        } catch (IOException e) {
            error(e);
        }
    }

    @FXML
    public void btn_delete(ActionEvent actionEvent) {
        int selectedIndex = userTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            alert("First select an item from the table");
            return;
        }
        User userDeletion = userTable.getSelectionModel().getSelectedItem();
        if (!confirm("Are you sure you want to delete the user below : " + userDeletion.getName())) {
            return;
        }
        try {
            boolean success = UserApi.deleteUser(userDeletion.getId());
            alert(success ? "Delete successfully" : "Delete failed");
            addList();
        } catch (IOException e) {
            error(e);
        }
    }

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
    public void btn_maxWindow(ActionEvent actionEvent) {
        stage = (Stage) mainAnchor.getScene().getWindow();
        this.maximizeWindowToggle(stage);
    }

    @FXML
    public void dragPane(MouseEvent event) {
        Stage stage = (Stage)pane.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    public void pressPane(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @Deprecated
    public void btn_Logout(ActionEvent actionEvent) {
    }

    @Deprecated
    public void btn_Settings(ActionEvent actionEvent) {
    }

    @Deprecated
    public void btn_Home(ActionEvent actionEvent) {
    }

    @FXML
    public void btn_Back(ActionEvent actionEvent) {
        try {
            Controller add = newWindow("views/main-view.fxml", "FindPho",
                    900, 650);
            add.getStage().show();
        } catch (Exception e) {
            error(e);
        }
        stage = (Stage) mainAnchor.getScene().getWindow();
        stage.close();
    }
}
