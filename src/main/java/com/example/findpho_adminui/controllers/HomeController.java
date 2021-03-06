package com.example.findpho_adminui.controllers;

import com.example.findpho_adminui.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomeController extends Controller {

    @FXML
    private AnchorPane contentHome;

    @FXML
    public void btn_Users(ActionEvent actionEvent) {
        try {
            Controller add = newWindow("views/user-view.fxml", "Users",
                    900, 600);
            add.getStage().show();
        } catch (Exception e) {
            error(e);
        }
        stage = (Stage) contentHome.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void btn_Catalog(ActionEvent actionEvent) {
        try {
            Controller add = newWindow("views/picture-view.fxml", "Catalog",
                    900, 600);
            add.getStage().show();
        } catch (Exception e) {
            error(e);
        }
        stage = (Stage) contentHome.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void btn_Statistics(ActionEvent actionEvent) {
        try {
            Controller add = newWindow("views/category-view.fxml", "Categories",
                    900, 600);
            add.getStage().show();
        } catch (Exception e) {
            error(e);
        }
        stage = (Stage) contentHome.getScene().getWindow();
        stage.close();
    }
}
