package com.example.findpho_adminui.controllers;

import com.example.findpho_adminui.Controller;
import com.example.findpho_adminui.api.PictureApi;
import com.example.findpho_adminui.api.UserApi;
import com.example.findpho_adminui.classes.Picture;
import com.example.findpho_adminui.classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class EditPictureController extends Controller{
    @FXML
    private Label lbl_Caption;
    @FXML
    private Label lbl_Image;
    @FXML
    private TextField txt_Url;
    @FXML
    private TextField txt_Image;
    @FXML
    private Label lbl_Url;
    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private TabPane tabPane;
    @FXML
    private TextField txt_Caption;

    private double x, y = 0;

    private Picture updatePicture;

    @FXML
    public void btn_closeWindowData(ActionEvent actionEvent) {
        try {
            Controller add = newWindow("views/picture-view.fxml", "Catalog",
                    900, 600);
            add.getStage().show();
        } catch (Exception e) {
            error(e);
        }
        stage = (Stage) mainAnchor.getScene().getWindow();
        stage.close();
    }

    public void setUpdatePicture(Picture updatePicture) {
        this.updatePicture = updatePicture;
        setValue();
    }

    @FXML
    public void btn_Save(ActionEvent actionEvent) {
        String url = txt_Url.getText().trim();
        String caption = txt_Caption.getText().trim();
        String image = txt_Image.getText().trim();

        if (url.isEmpty()){
            alert("Url is required!");
            return;
        }
        if (caption.isEmpty()){
            alert("Caption is required!");
            return;
        }
        if (image.isEmpty()){
            alert("Image is required!");
            return;
        }

        updatePicture.setUrl(url);
        updatePicture.setCaption(caption);
        updatePicture.setImage(image);

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
        txt_Url.setText(updatePicture.getUrl());
        txt_Caption.setText(updatePicture.getCaption());
        txt_Image.setText(updatePicture.getImage());

        lbl_Url.setText(updatePicture.getUrl());
        lbl_Caption.setText(updatePicture.getCaption());
        lbl_Image.setText(updatePicture.getImage());
    }

    @FXML
    public void btn_closeWindowUpdate(ActionEvent actionEvent) {
        try {
            Controller add = newWindow("views/picture-view.fxml", "Catalog",
                    900, 600);
            add.getStage().show();
        } catch (Exception e) {
            error(e);
        }
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
