/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arief.utility;

import javafx.scene.control.Alert;

/**
 *
 * @author Arief Kurniawan 1772049
 */
public class ViewUtil {

    public static void showAlert(Alert.AlertType alertType, String header,
            String content) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
