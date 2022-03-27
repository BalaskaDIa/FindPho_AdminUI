package com.example.findpho_adminui.controllers;

import com.example.findpho_adminui.Controller;
import com.example.findpho_adminui.api.CategoryApi;
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
import java.util.List;

import java.io.IOException;

public class PictureController extends Controller {
    @javafx.fxml.FXML
    private TextField txt_Search;
    @javafx.fxml.FXML
    private Button btn_edit;
    @javafx.fxml.FXML
    private AnchorPane mainAnchor;
    @javafx.fxml.FXML
    private AnchorPane sideBar;
    @javafx.fxml.FXML
    private Button btn_delete;

    @FXML
    private Pane pane;

    private double x, y = 0;

    @FXML
    private TableColumn <Picture,Integer> idCol;
    @FXML
    private TableColumn <Picture,Integer> userIdCol;
    @FXML
    private TableColumn <Picture,String> urlCol;
    @FXML
    private TableView <Picture> pictureTable;
    @FXML
    private TableColumn <Picture,String> captionCol;
    @FXML
    private TableColumn <Category,String> categoryNameCol;
    @FXML
    private TableColumn <Category, Integer> categoryIdCol;
    @FXML
    private TableView <Category> categoryTable;

    private final ObservableList<Picture> pictureList = FXCollections.observableArrayList();
    private final ObservableList<Category> categoryList = FXCollections.observableArrayList();


    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        urlCol.setCellValueFactory(new PropertyValueFactory<>("url"));
        captionCol.setCellValueFactory(new PropertyValueFactory<>("caption"));

        categoryIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        categoryNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addList();
        search();
    }

    private void addList() {
        try {
            pictureList.clear();
            pictureList.addAll(PictureApi.getPicture());
            List<Category> categoryList = CategoryApi.getCategory();
            categoryTable.getItems().clear();
            for (Category category : categoryList) {
                categoryTable.getItems().add(category);
            }
        }catch (IOException e) {
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
    public void btn_delete(ActionEvent actionEvent) {
        int selectedIndex = pictureTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            alert("First select an item from the table");
            return;
        }
        Picture pictureDeletion = pictureTable.getSelectionModel().getSelectedItem();
        if (!confirm("Are you sure you want to delete the picture below : " + pictureDeletion.getCaption())) {
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
    public void btn_maxWindow(ActionEvent actionEvent) {
        stage = (Stage) mainAnchor.getScene().getWindow();
        this.maximizeWindowToggle(stage);
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

                if (picture.getUrl().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (picture.getCaption().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else return picture.getImage().toLowerCase().contains(lowerCaseFilter);
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
    public void btn_closeWindow(ActionEvent actionEvent) {
        Controller.closeWindow(actionEvent);
    }

    @FXML
    public void btn_add(ActionEvent actionEvent) {
        try {
            Controller add = newWindow("views/addCategory-view.fxml", "Add Category",
                    400, 200);
            add.getStage().show();
        } catch (Exception e) {
            error(e);
        }
    }

    @FXML
    public void btn_editCategory(ActionEvent actionEvent) {
        int selectedIndex = categoryTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            alert("First select an item from the table");
            return;
        }
        Category updateCategory = categoryTable.getSelectionModel().getSelectedItem();
        try {
            EditCategoryController modify = (EditCategoryController) newWindow("views/editCategory-view.fxml",
                    "Edit Category", 400, 200);
            modify.setUpdateCategory(updateCategory);
            modify.getStage().setOnHiding(event -> categoryTable.refresh());
            modify.getStage().show();
        } catch (IOException e) {
            error(e);
        }
    }

    @FXML
    public void btn_deleteCategory(ActionEvent actionEvent) {
        int selectedIndex = categoryTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            alert("First select an item from the table");
            return;
        }
        Category categoryDeletion = categoryTable.getSelectionModel().getSelectedItem();
        if (!confirm("Are you sure you want to delete the category below : " + categoryDeletion.getName())) {
            return;
        }
        try {
            boolean success = CategoryApi.deleteCategory(categoryDeletion.getId());
            alert(success ? "Delete successful" : "Delete failed");
            addList();
        } catch (IOException e) {
            error(e);
        }
    }
}
