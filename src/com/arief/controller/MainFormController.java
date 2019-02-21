/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arief.controller;

import com.arief.MainApp;
import com.arief.dao.CategoryDaoImpl;
import com.arief.dao.MenuDaoImpl;
import com.arief.entity.Category;
import com.arief.entity.Menu;
//import com.arief.utility.TextUtil;
//import com.arief.utility.ViewUtil;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
//import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Arief Kurniawan (1772049)
 */
public class MainFormController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtDescription;
    @FXML
    private CheckBox checkBoxRecommended;
    @FXML
    private TableView<Menu> tbMenu;
    @FXML
    private ComboBox<Category> cbCategory;
    private Stage newStage;
    private CategoryDaoImpl categoryDao;
    private BorderPane borderPane;
    @FXML
    private MenuItem filetxtcat;
    @FXML
    private TableColumn<Menu, String> colId;
    @FXML
    private TableColumn<Menu, String> colName;
    @FXML
    private TableColumn<Menu, String> colPrice;
    @FXML
    private TableColumn<Menu, String> colRecom;
    @FXML
    private TableColumn<Menu, String> colCat;
    @FXML
    private BorderPane root;

    public CategoryDaoImpl getCategoryDao() {
        if (categoryDao == null) {
            categoryDao = new CategoryDaoImpl();
        }
        return categoryDao;
    }

    private ObservableList<Category> categories;

    public ObservableList<Category> getCategories() {
        if (categories == null) {
            categories = FXCollections.observableArrayList();
            categories.addAll(getCategoryDao().showAllData());
        }
        return categories;
    }

    private MenuDaoImpl menuDao;

    public MenuDaoImpl getMenuDao() {
        if (menuDao == null) {
            menuDao = new MenuDaoImpl();
        }
        return menuDao;
    }

    private ObservableList<Menu> menus;

    public ObservableList<Menu> getMenus() {
        if (menus == null) {
            menus = FXCollections.observableArrayList();
            menus.addAll(getMenuDao().showAllData());
        }
        return menus;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tbMenu.setItems(getMenus());
        cbCategory.setItems(getCategories());

        colId.setCellValueFactory((data) -> {
            Menu m = data.getValue();
            return new SimpleStringProperty(String.valueOf(m.getId()));
        });
        colName.setCellValueFactory((data) -> {
            Menu m = data.getValue();
            return new SimpleStringProperty(m.getName());
        });
        colPrice.setCellValueFactory((data) -> {
            Menu m = data.getValue();
            return new SimpleStringProperty(String.valueOf(m.getPrice()));
        });
        colRecom.setCellValueFactory((data) -> {
            Menu m = data.getValue();
            return new SimpleStringProperty(String.valueOf(m.isRecommended()));
        });
        colCat.setCellValueFactory((data) -> {
            Menu m = data.getValue();
            return new SimpleStringProperty(m.getCategory().getName());
        });
    }

    @FXML
    private void saveActionBtn(ActionEvent event) {
        Menu m = new Menu();
        m.setId(Integer.valueOf(txtId.getText().trim()));
        m.setName(txtName.getText().trim());
        m.setPrice(Integer.valueOf(txtPrice.getText().trim()));
        m.setRecommended(checkBoxRecommended.isSelected());
        m.setCategory(cbCategory.getValue());
        getMenus().add(m);
        tbMenu.refresh();

    }

    @FXML
    private void resetActionBtn(ActionEvent event) {
        txtId.clear();
        txtName.clear();
        txtPrice.clear();
        txtDescription.clear();
        checkBoxRecommended.isSelected();

    }

    @FXML
    private void updateActionBtn(ActionEvent event) {
        //     if (TextUtil.isEmptyField(txtId, txtName, txtDescription, txtPrice) == null) {
        //         ViewUtil.showAlert(Alert.AlertType.ERROR, "Error", "Please fill id, name, description, and price");
        //     } else {
        //        Menu.setId(txtId.getText().trim());
        //    }
    }

    @FXML
    private void deleteActionBtn(ActionEvent event) {
    }

    @FXML
    private void editCategory(ActionEvent event) {
        try {
            newStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Category.fxml"));
            BorderPane root = loader.load();
            Scene scene = new Scene(root);
            CategoryController controller = loader.getController();
            controller.setMainController(this);
            newStage.initOwner(borderPane.getScene().getWindow());
            newStage.initModality(Modality.WINDOW_MODAL);
            newStage.setScene(scene);
            newStage.setTitle("PBO Window_Modal");
            newStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainFormController.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

}
