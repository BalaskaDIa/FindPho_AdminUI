package com.example.findpho_adminui.controllers;

import com.example.findpho_adminui.Controller;
import com.example.findpho_adminui.api.StatisticsApi;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class StatisticsController extends Controller {

    @FXML
    private Label lbl_User;

    @FXML
    private Label lbl_Category;

    @FXML
    private Label lbl_Photo;

    private int users;
    private int photos;
    private int categories;

    public void initialize() throws IOException {
        users = StatisticsApi.allUsers();
        photos = StatisticsApi.allPhotos();
        categories = StatisticsApi.allCategories();
        lbl_User.setText(String.valueOf(users));
        lbl_Photo.setText(String.valueOf(photos));
        lbl_Category.setText(String.valueOf(categories));
    }
}
