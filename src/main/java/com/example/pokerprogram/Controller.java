package com.example.pokerprogram;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

//this class will control events like button clicks
public class Controller {

    @FXML
    private javafx.scene.control.Button exitButton;

    @FXML
    protected void closeButtonAction(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }


}
