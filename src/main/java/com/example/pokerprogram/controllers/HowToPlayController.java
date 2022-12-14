package com.example.pokerprogram.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HowToPlayController {
    @FXML public Button bMenu;

    public void closeMenu(ActionEvent actionEvent){
        Stage stage = (Stage) bMenu.getScene().getWindow();
        stage.close();
    }
}
