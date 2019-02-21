/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arief.controller;

import com.arief.entity.Category;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CategoryController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;

    @FXML
    private TableView<Category> tbCategory;

    private MainFormController mainController;
    private ObservableList<Category> categorys;
    @FXML
    private Button saveButton;
    @FXML
    private TableColumn<Category, String> colId;
    @FXML
    private TableColumn<Category, String> colName;

    public ObservableList<Category> getCategorys() {
        return categorys;
    }

    public void setCategorys(ObservableList<Category> categorys) {
        this.categorys = categorys;
    }

    public void setMainController(MainFormController mainController) {
        this.mainController = mainController;
        tbCategory.setItems(mainController.getCategories());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colId.setCellValueFactory((data) -> {
            Category cat = data.getValue();
            return new SimpleStringProperty(String.valueOf(cat.getId()));
        });
        colName.setCellValueFactory((data) -> {
            Category cat = data.getValue();
            return new SimpleStringProperty(cat.getName());
        });
    }

    @FXML
    private void saveAction(ActionEvent event) {
        Category cat = new Category();
        cat.setId(Integer.valueOf(txtId.getText().trim()));
        cat.setName(txtName.getText().trim());
        mainController.getCategoryDao().addData(cat);
        mainController.getCategories().add(cat);
    }

}
