package com.example.pokerprogram.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ChooseGameController {
    @FXML
    public Button bSinglePlayer;



    public void launchGame(ActionEvent actionEvent) throws IOException {
        //todo change void to something else (boolean?) when multiplayer is an option. for now keep it simple.
        // give boolean to pokemenucontroller somehow and use that to choose if single player or multiplayer is launched :)

        //for now when it is clicked it just closes the window
        Stage stage = (Stage) bSinglePlayer.getScene().getWindow();
        stage.close();



    }

    public void launchMultiPlayer(ActionEvent actionEvent) {
    //multiplayer is not implemented yet!

    }
}
