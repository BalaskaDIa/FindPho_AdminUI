package com.example.findpho_adminui.controllers;

import com.example.findpho_adminui.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


import java.io.IOException;

public class MainController extends Controller {
    @FXML
    private Button btn_Users;

    @FXML
    public void btn_Users(ActionEvent actionEvent) throws IOException {
        try {
            Controller hozzadas = newWindow("user.fxml", "Users",
                    900, 650);
            hozzadas.getStage().show();
        } catch (Exception e) {
            error(e);
        }
    }
}