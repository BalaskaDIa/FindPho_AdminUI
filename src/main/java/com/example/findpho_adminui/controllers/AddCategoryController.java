package com.example.findpho_adminui.controllers;

import com.example.findpho_adminui.Controller;
import com.example.findpho_adminui.api.CategoryApi;
import com.example.findpho_adminui.classes.Category;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AddCategoryController extends Controller {

    @FXML
    private TextField txt_Name;

    @FXML
    private AnchorPane mainAnchor;

    private double x, y = 0;

    @FXML
    private Label lbl_Name;

    @FXML
    private Pane pane;

    @FXML
    public void btn_closeCategoryWindow(ActionEvent actionEvent) {
        stage = (Stage) mainAnchor.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void btn_SaveCategory(ActionEvent actionEvent) {
        String name = txt_Name.getText().trim();

        if (name.isEmpty()) {
            alert("Name is required!");
            return;
        }

        try {
            Category newCategory = new Category(0, name);
            Category created = CategoryApi.postCategory(newCategory);
            if (created != null) {
                alert("Creation successful");
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
