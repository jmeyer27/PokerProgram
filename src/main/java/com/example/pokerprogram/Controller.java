package com.example.pokerprogram;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

//this class will control events like button clicks
public class Controller {

    /*
    This button's purpose is to start the poker game from the poker game menu
    */
    public Button gameButton;
    public void startGameAction() throws IOException {
        Stage stage = (Stage) gameButton.getScene().getWindow();
        System.out.println("Game start!");
        Parent newRoot = FXMLLoader.load(getClass().getResource("pokerGame-view.fxml"));
        stage.getScene().setRoot(newRoot);
    }



    /*
        This button's purpose is to have an easy place for the user to exit the program.
        This button resides in pokerMenu-view.fxml
         */
    @FXML
    private javafx.scene.control.Button exitButton;

    /*
    This is the action event function for when the exit button is clicked in the ThePokerGame pokerMenu
    Todo: Make this quit (or exit) button confirm with user that they really want to quit.
     */
    @FXML
    protected void closeButtonAction(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }


}//end of Controller class
