package com.example.findpho_adminui.controllers;

import com.example.findpho_adminui.Controller;
import com.example.findpho_adminui.api.PictureApi;
import com.example.findpho_adminui.classes.Picture;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class EditPictureController extends Controller{
    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private TabPane tabPane;

    private double x, y = 0;

    private Picture updatePicture;

    @FXML
    private TextArea txtArea_Image;
    @FXML
    private TextField txt_Image;
    @FXML
    private TextArea txtArea_Title;
    @FXML
    private TextArea txtArea_Description;
    @FXML
    private TextField txt_Description;
    @FXML
    private TextField txt_Title;

    @FXML
    public void btn_closeWindowData(ActionEvent actionEvent) {
        stage = (Stage) mainAnchor.getScene().getWindow();
        stage.close();
    }

    public void setUpdatePicture(Picture updatePicture) {
        this.updatePicture = updatePicture;
        setValue();
    }

    @FXML
    public void btn_Save(ActionEvent actionEvent) {
        String image = txt_Image.getText().trim();
        String title = txt_Title.getText().trim();
        String description = txt_Description.getText().trim();

        if (image.isEmpty()){
            alert("Image is required!");
            return;
        }
        if (title.isEmpty()){
            alert("Title is required!");
            return;
        }

        if (title.contentEquals("#")) {
            alert("Some characters are not allowed");
            return;
        }

        if (description.isEmpty()){
            alert("Description is required!");
            return;
        }

        updatePicture.setImage(image);
        updatePicture.setTitle(title);
        updatePicture.setDescription(description);

        try {
            Picture updated = PictureApi.putPicture(updatePicture);
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
        txt_Image.setText(updatePicture.getImage());
        txt_Title.setText(updatePicture.getTitle());
        txt_Description.setText(updatePicture.getDescription());

        txtArea_Image.setText(updatePicture.getImage());
        txtArea_Title.setText(updatePicture.getTitle());
        txtArea_Description.setText(updatePicture.getDescription());
    }

    @FXML
    public void btn_closeWindowUpdate(ActionEvent actionEvent) {
        stage = (Stage) mainAnchor.getScene().getWindow();
        stage.close();
    }

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
}
