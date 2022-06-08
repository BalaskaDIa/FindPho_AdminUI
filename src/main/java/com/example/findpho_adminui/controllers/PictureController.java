package com.example.findpho_adminui.controllers;

import com.example.findpho_adminui.Controller;
import com.example.findpho_adminui.api.PictureApi;
import com.example.findpho_adminui.classes.Category;
import com.example.findpho_adminui.classes.Picture;
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

public class PictureController extends Controller {

    @FXML
    private TextField txt_Search;

    @FXML
    private Button btn_edit;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private AnchorPane sideBar;

    @FXML
    private Pane pane;

    private double x, y = 0;

    @FXML
    private TableColumn<Picture, Integer> idCol;
    @FXML
    private TableColumn<Picture, Integer> userIdCol;
    @FXML
    private TableView<Picture> pictureTable;
    @FXML
    private TableColumn<Picture, String> imageCol;
    @FXML
    private TableColumn<Picture, String> titleCol;
    @FXML
    private TableColumn<Picture, String> descriptionCol;
    @FXML
    private TableColumn<Picture, String> categoryCol;

    private final ObservableList<Picture> pictureList = FXCollections.observableArrayList();

    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        imageCol.setCellValueFactory(new PropertyValueFactory<>("image"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        addList();
        search();
    }

    private void addList() {
        try {
            pictureList.clear();
            pictureList.addAll(PictureApi.getPicture());
        } catch (IOException e) {
            error(e);
        }
    }

    @FXML
    public void btn_delete(ActionEvent actionEvent) {
        int selectedIndex = pictureTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            alert("First select an item from the table");
            return;
        }
        Picture pictureDeletion = pictureTable.getSelectionModel().getSelectedItem();
        if (!confirm("Are you sure you want to delete this file?")) {
            return;
        }
        try {
            boolean success = PictureApi.deletePicture(pictureDeletion.getId());
            alert(success ? "Delete successful" : "Delete failed");
            addList();
        } catch (IOException e) {
            error(e);
        }
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

    private void search() {
        FilteredList<Picture> filteredData = new FilteredList<>(pictureList, b -> true);

        txt_Search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(picture -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (picture.getImage().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (picture.getTitle().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (picture.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else return picture.getCategory().toLowerCase().contains(lowerCaseFilter);
            });
        });
        SortedList<Picture> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(pictureTable.comparatorProperty());
        pictureTable.setItems(sortedData);
    }

    @FXML
    public void btn_edit(ActionEvent actionEvent) {
        int selectedIndex = pictureTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            alert("First select an item from the table");
            return;
        }
        Picture updatePicture = pictureTable.getSelectionModel().getSelectedItem();
        try {
            EditPictureController modify = (EditPictureController) newWindow("views/editPicture-view.fxml",
                    "Edit picture", 400, 400);
            modify.setUpdatePicture(updatePicture);
            modify.getStage().setOnHiding(event -> pictureTable.refresh());
            modify.getStage().show();
        } catch (IOException e) {
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

    @FXML
    public void btn_minWindow(ActionEvent actionEvent) {
        stage = (Stage) mainAnchor.getScene().getWindow();
        this.minimizeWindow(stage);
    }

    @FXML
    public void btn_maxWindow(ActionEvent actionEvent) {
        stage = (Stage) mainAnchor.getScene().getWindow();
        this.maximizeWindowToggle(stage);
    }

    @FXML
    public void btn_closeWindow(ActionEvent actionEvent) {
        if (!(confirm("Do you really want to leave?"))) {
            return;
        }
        System.exit(0);
    }
}
