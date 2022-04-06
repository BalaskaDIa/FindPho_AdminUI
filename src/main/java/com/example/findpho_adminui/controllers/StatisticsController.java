package com.example.findpho_adminui.controllers;

import com.example.findpho_adminui.Controller;
import com.example.findpho_adminui.api.StatisticsApi;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
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
    private int january;
    private int february;
    private int march;
    private int april;
    private int may;
    private int june;
    @FXML
    private BarChart barChart;

    public void initialize() throws IOException {

        users = StatisticsApi.allUsers();
        photos = StatisticsApi.allPhotos();
        categories = StatisticsApi.allCategories();

        january = StatisticsApi.janUpload();
        february = StatisticsApi.febUpload();
        march = StatisticsApi.marchUpload();
        april = StatisticsApi.aprUpload();
        may = StatisticsApi.mayUpload();
        june = StatisticsApi.junUpload();

        lbl_User.setText(String.valueOf(users));
        lbl_Photo.setText(String.valueOf(photos));
        lbl_Category.setText(String.valueOf(categories));


        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Photo uploads by months");
        series.getData().add(new XYChart.Data<>("January", january));
        series.getData().add(new XYChart.Data<>("February", february));
        series.getData().add(new XYChart.Data<>("March", march));
        series.getData().add(new XYChart.Data<>("April", april));
        series.getData().add(new XYChart.Data<>("May", may));
        series.getData().add(new XYChart.Data<>("June", june));

        barChart.getData().add(series);
    }
}
