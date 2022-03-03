package com.example.findpho_adminui.controllers;

import com.example.findpho_adminui.Controller;
import com.example.findpho_adminui.FindPhoDB;
import com.example.findpho_adminui.classes.User;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import java.sql.SQLException;
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

    private FindPhoDB db;
    private Stage stage;
    private double x, y = 0;

    public void initialize() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        adminCol.setCellValueFactory(new PropertyValueFactory<>("admin"));
        try {
            db = new FindPhoDB();
            addList();
        } catch (SQLException e) {
            error(e);
        }
    }

    private void addList() {
        try {
            List<User> userList = db.getUser();
            userTable.getItems().clear();
            for(User user: userList){
                userTable.getItems().add(user);
            }
        } catch (SQLException e) {
            error(e);
        }
    }

    @FXML
    public void btn_search(ActionEvent actionEvent) {
    }

    @FXML
    public void btn_add(ActionEvent actionEvent) {
    }

    @FXML
    public void btn_edit(ActionEvent actionEvent) {
    }

    @FXML
    public void btn_delete(ActionEvent actionEvent) {
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
            Controller hozzaadas = newWindow("views/main-view.fxml", "FindPho",
                    900, 650);
            hozzaadas.getStage().show();
        } catch (Exception e) {
            error(e);
        }
        stage = (Stage) mainAnchor.getScene().getWindow();
        stage.close();
    }
}
