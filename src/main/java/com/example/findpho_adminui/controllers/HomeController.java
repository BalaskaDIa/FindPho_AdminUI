package com.example.findpho_adminui.controllers;

import com.example.findpho_adminui.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class HomeController extends Controller{

    @FXML
    private Button btn_Users;
    @FXML
    private AnchorPane contentHome;

    @FXML
    public void btn_Users(ActionEvent actionEvent) {
        try {
            Controller hozzaadas = newWindow("views/user-view.fxml", "Users",
                    850, 450);
            hozzaadas.getStage().show();
        } catch (Exception e) {
            error(e);
        }
    }
}
