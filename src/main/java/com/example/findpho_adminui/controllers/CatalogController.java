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

public class CatalogController extends Controller {
    @javafx.fxml.FXML
    private Button btn_search;
    @javafx.fxml.FXML
    private TableColumn usernameCol;
    @javafx.fxml.FXML
    private TableColumn adminCol1;
    @javafx.fxml.FXML
    private TextField txt_Search;
    @javafx.fxml.FXML
    private TableColumn adminCol11;
    @javafx.fxml.FXML
    private Button btn_edit;
    @javafx.fxml.FXML
    private AnchorPane mainAnchor;
    @javafx.fxml.FXML
    private TableColumn adminCol;
    @javafx.fxml.FXML
    private Button btn_add;
    @javafx.fxml.FXML
    private TableColumn idCol;
    @javafx.fxml.FXML
    private AnchorPane sideBar;
    @javafx.fxml.FXML
    private TableColumn nameCol;
    @javafx.fxml.FXML
    private TableColumn emailCol;
    @javafx.fxml.FXML
    private Button btn_delete;
    @javafx.fxml.FXML
    private TableView userTable;

    @FXML
    private Pane pane;

    private double x, y = 0;

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
    public void btn_delete(ActionEvent actionEvent) {

    }

    @FXML
    public void btn_maxWindow(ActionEvent actionEvent) {
        stage = (Stage) mainAnchor.getScene().getWindow();
        this.maximizeWindowToggle(stage);
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

    @javafx.fxml.FXML
    public void btn_search(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btn_add(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btn_edit(ActionEvent actionEvent) {
    }

    @FXML
    public void btn_closeWindow(ActionEvent actionEvent) {
        Controller.closeWindow(actionEvent);
    }
}
