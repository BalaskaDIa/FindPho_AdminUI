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

import java.io.IOException;

public class EditCategoryController extends Controller {
    @FXML
    private Label lbl_Name;
    @FXML
    private TextField txt_Name;

    @FXML
    private AnchorPane mainAnchor;

    private double x, y = 0;

    private Category updateCategory;

    @FXML
    private Pane pane;

    @FXML
    public void btn_closeCategoryWindow(ActionEvent actionEvent) {
        stage = (Stage) mainAnchor.getScene().getWindow();
        stage.close();
    }

    public void setUpdateCategory(Category updateCategory) {
        this.updateCategory = updateCategory;
        setValue();
    }

    @FXML
    public void btn_SaveCategory(ActionEvent actionEvent) {
        String name = txt_Name.getText().trim();

        if (name.isEmpty()) {
            alert("Name is required!");
            return;
        }

        updateCategory.setName(name);

        try {
            Category updated = CategoryApi.putCategory(updateCategory);
            if (updated != null) {
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
        txt_Name.setText(updateCategory.getName());
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
