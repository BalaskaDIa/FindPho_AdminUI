package com.example.findpho_adminui.controllers;

import com.example.findpho_adminui.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class AddUserController extends Controller {

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private Pane pane;

    private double x, y = 0;

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
    public void btnCancel(ActionEvent actionEvent) {
        try {
            Controller add = newWindow("views/user-view.fxml", "Users",
                    900, 600);
            add.getStage().show();
        } catch (Exception e) {
            error(e);
        }
        stage = (Stage) mainAnchor.getScene().getWindow();
        stage.close();
    }
}
