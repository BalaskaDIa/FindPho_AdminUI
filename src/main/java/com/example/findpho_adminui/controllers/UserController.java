package com.example.findpho_adminui.controllers;

import com.example.findpho_adminui.Controller;
import com.example.findpho_adminui.api.UserApi;
import com.example.findpho_adminui.classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
    private TextField txt_Search;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private Pane pane;

    private Stage stage;
    private double x, y = 0;
    private final ObservableList<User> userList = FXCollections.observableArrayList();

    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        adminCol.setCellValueFactory(new PropertyValueFactory<>("admin"));
        addList();
        search();
    }

    protected void addList() {
        try {
            userList.clear();
            userList.addAll(UserApi.getUser());
        }catch (IOException e) {
            error(e);
        }
    }

    private void search() {
        FilteredList<User> filteredData = new FilteredList<>(userList, b -> true);

        txt_Search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (user.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (user.getUsername().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else return user.getEmail().toLowerCase().contains(lowerCaseFilter);
            });
        });
        SortedList<User> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(userTable.comparatorProperty());
        userTable.setItems(sortedData);
    }

    @FXML
    public void btn_add(ActionEvent actionEvent) {
        try {
            Controller add = newWindow("views/addUser-view.fxml", "Add user",
                    400, 400);
            add.getStage().setOnCloseRequest(event -> addList());
            add.getStage().show();
        } catch (Exception e) {
            error(e);
        }
    }

    @FXML
    public void btn_edit(ActionEvent actionEvent) {
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
        if (!confirm("Are you sure, you want to delete the user below : " + userDeletion.getName() + " ?")) {
            return;
        }
        try {
            boolean success = UserApi.deleteUser(userDeletion.getId());
            alert(success ? "Delete successful" : "Delete failed");
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
        if (!(confirm("Do you really want to leave?"))) {
            return;
        }
        System.exit(0);
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

    @FXML
    public void btn_back(ActionEvent actionEvent) {
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
