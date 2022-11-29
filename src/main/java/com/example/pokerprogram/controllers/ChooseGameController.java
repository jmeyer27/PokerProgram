package com.example.pokerprogram.controllers;

import com.example.pokerprogram.ThePokerGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ChooseGameController {
    @FXML
    public Button bSinglePlayer;
    /*
        This is to set up the previous scene information so that the user can return to the menu
         */
//    private Scene preScene;
//    public void setPreScene(Scene preScene){
//        this.preScene = preScene;
//    }



    public void launchGame(ActionEvent actionEvent) throws IOException {
        //todo change void to something else (boolean?) when multiplayer is an option. for now keep it simple.
        // give boolean to pokemenucontroller somehow and use that to choose if single player or multiplayer is launched :)

        //for now when it is clicked it just closes the window
        Stage stage = (Stage) bSinglePlayer.getScene().getWindow();
        stage.close();


        //this old code loads the game from this screen, but it leaves the menu open and kind of sucks
//        FXMLLoader fxmlLoader = new FXMLLoader(ThePokerGame.class.getResource("pokerGame-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//
//        PokerGameController controller = fxmlLoader.getController();
//        //controller.setPreScene(bSinglePlayer.getScene());
//
//        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
//        //Stage chooseGameStage = new Stage();
//        stage.setTitle("Welcome to The Poker Game!");
//        stage.setScene(scene);
//        stage.show();



    }

    public void launchMultiPlayer(ActionEvent actionEvent) {

        //todo popup saying this is not implemented yet!
    }
}
