package com.example.findpho_adminui.controllers;

import com.example.findpho_adminui.Controller;
import com.example.findpho_adminui.api.CategoryApi;
import com.example.findpho_adminui.api.PictureApi;
import com.example.findpho_adminui.api.StatisticsApi;
import com.example.findpho_adminui.classes.Category;
import com.example.findpho_adminui.classes.Picture;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CategoryController extends Controller {

    @FXML
    private TableColumn <Category,String> categoryNameCol;
    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private TableColumn <Category,Integer> categoryIdCol;
    @FXML
    private AnchorPane sideBar;
    @FXML
    private Pane pane;
    @FXML
    private TableView  <Category> categoryTable;

    private double x,y = 0;

    private int flowers;
    private int cars;
    private int eyes;
    private int dogs;
    private int food;
    private int spring;
    private int nature;
    private int others;
    private int people;
    private int winter;

    private final ObservableList<Category> categoryList = FXCollections.observableArrayList();

    @FXML
    private TextField txt_Search;
    @FXML
    private PieChart pieChart;
    @FXML
    private Label randomLbl;

    public void initialize() throws IOException {
        flowers = StatisticsApi.categoryFlowers();
        cars = StatisticsApi.categoryCars();
        eyes = StatisticsApi.categoryEyes();
        dogs = StatisticsApi.categoryDogs();
        food = StatisticsApi.categoryFood();
        spring = StatisticsApi.categorySpring();
        nature = StatisticsApi.categoryNature();
        others = StatisticsApi.categoryOthers();
        people = StatisticsApi.categoryPeople();
        winter = StatisticsApi.categoryWinter();

        categoryIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        categoryNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addList();
        search();

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Flowers", flowers),
                new PieChart.Data("Cars", cars),
                new PieChart.Data("Eyes", eyes),
                new PieChart.Data("Dogs", dogs),
                new PieChart.Data("Food", food),
                new PieChart.Data("Spring", spring),
                new PieChart.Data("Nature", nature),
                new PieChart.Data("Others", others),
                new PieChart.Data("People", people),
                new PieChart.Data("Winter", winter)
        );
        pieChart.setData(pieChartData);
        pieChart.setLegendVisible(false);
        pieChart.setStartAngle(90);
    }

    private void addList() {
        try {
            categoryList.clear();
            categoryList.addAll(CategoryApi.getCategory());
        }catch (IOException e) {
            error(e);
        }
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

    private void search() {
        FilteredList<Category> filteredData = new FilteredList<>(categoryList, b -> true);

        txt_Search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(category -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (category.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Category> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(categoryTable.comparatorProperty());
        categoryTable.setItems(sortedData);
    }

    @FXML
    public void btn_add(ActionEvent actionEvent) {
        try {
            Controller add = newWindow("views/addCategory-view.fxml", "Add Category",
                    400, 200);
            add.getStage().setOnHiding(event -> addList());

            add.getStage().show();
        } catch (Exception e) {
            error(e);
        }
    }

    @FXML
    public void btn_editCategory(ActionEvent actionEvent) {
        int selectedIndex = categoryTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            alert("First select an item from the table");
            return;
        }
        Category updateCategory = categoryTable.getSelectionModel().getSelectedItem();
        try {
            EditCategoryController modify = (EditCategoryController) newWindow("views/editCategory-view.fxml",
                    "Edit Category", 400, 200);
            modify.setUpdateCategory(updateCategory);
            modify.getStage().setOnHiding(event -> categoryTable.refresh());
            modify.getStage().show();
        } catch (IOException e) {
            error(e);
        }
    }

    @FXML
    public void btn_deleteCategory(ActionEvent actionEvent) {
        int selectedIndex = categoryTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            alert("First select an item from the table");
            return;
        }
        Category categoryDeletion = categoryTable.getSelectionModel().getSelectedItem();
        if (!confirm("Are you sure you want to delete the below category: " + categoryDeletion.getName() + " ?") ) {
            return;
        }
        try {
            boolean success = CategoryApi.deleteCategory(categoryDeletion.getId());
            alert(success ? "Delete successful" : "Delete failed");
            addList();
        } catch (IOException e) {
            error(e);
        }
    }

    @FXML
    public void btn_minWindow(ActionEvent actionEvent) {
        stage = (Stage) mainAnchor.getScene().getWindow();
        this.minimizeWindow(stage);
    }

    @FXML
    public void btn_closeWindow(ActionEvent actionEvent) {
        if (!(confirm("Do you really want to leave?"))) {
            return;
        }
        System.exit(0);
    }


    @FXML
    public void btn_back(ActionEvent actionEvent) {
        try {
            Controller add = newWindow("views/main-view.fxml", "FindPho",
                    900, 650);
            add.getStage().show();
        } catch (Exception e) {
            error(e);
        }
        stage = (Stage) mainAnchor.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void btn_maxWindow(ActionEvent actionEvent) {
        stage = (Stage) mainAnchor.getScene().getWindow();
        this.maximizeWindowToggle(stage);
    }
}
