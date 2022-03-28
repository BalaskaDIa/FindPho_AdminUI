package com.example.findpho_adminui.controllers;

import com.example.findpho_adminui.Controller;
import com.example.findpho_adminui.api.PictureApi;
import com.example.findpho_adminui.classes.Picture;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class EditPictureController extends Controller{
    @FXML
    private TextField txt_Url;
    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private TabPane tabPane;
    @FXML
    private TextField txt_Caption;

    private double x, y = 0;

    private Picture updatePicture;

    @FXML
    private TextArea txtArea_Url;
    @FXML
    private TextArea txtArea_Caption;

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
        String url = txt_Url.getText().trim();
        String caption = txt_Caption.getText().trim();

        if (url.isEmpty()){
            alert("Url is required!");
            return;
        }
        if (caption.isEmpty()){
            alert("Caption is required!");
            return;
        }

        updatePicture.setUrl(url);
        updatePicture.setCaption(caption);

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

        txtArea_Url.setText(updatePicture.getUrl());
        txtArea_Caption.setText(updatePicture.getCaption());
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
