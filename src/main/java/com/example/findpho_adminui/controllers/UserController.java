package com.example.findpho_adminui.controllers;

import com.example.findpho_adminui.Controller;
import com.example.findpho_adminui.FindPhoDB;
import com.example.findpho_adminui.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.sql.SQLException;
import java.util.List;

public class UserController extends Controller {
    @javafx.fxml.FXML
    private Button btn_search;

    @javafx.fxml.FXML
    private TableColumn usernameCol;

    @javafx.fxml.FXML
    private Button btn_add;

    @javafx.fxml.FXML
    private TableColumn idCol;

    @javafx.fxml.FXML
    private TextField txt_Search;

    @javafx.fxml.FXML
    private TableColumn firstCol;

    @javafx.fxml.FXML
    private Button btn_edit;

    @javafx.fxml.FXML
    private Pane header;

    @javafx.fxml.FXML
    private TableColumn emailCol;

    @javafx.fxml.FXML
    private Button btn_delete;

    @javafx.fxml.FXML
    private TableColumn lastCol;

    @javafx.fxml.FXML
    private TableView<User> userTable;

    private FindPhoDB db;

    public void initialize() {
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        firstCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastCol.setCellValueFactory(new PropertyValueFactory<>("lastname"));
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

    @javafx.fxml.FXML
    public void btn_search(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btn_add(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btn_edit(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btn_delete(ActionEvent actionEvent) {
    }
}
