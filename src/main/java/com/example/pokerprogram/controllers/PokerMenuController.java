package com.example.pokerprogram.controllers;

import com.example.pokerprogram.ThePokerGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;


//this class will handle the events from the pokerMenu-view.xml file
public class PokerMenuController {

    /*
    This button's purpose is to start the poker game from the poker game menu
    */
    public Button gameButton;
    public void startGameAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ThePokerGame.class.getResource("pokerGame-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        PokerGameController controller = fxmlLoader.getController();
        controller.setPreScene(gameButton.getScene());

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Welcome to The Poker Game!");
        stage.setScene(scene);
        stage.show();

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



    /*
    This button is so that users are able to load up their statistics information
    Todo: Make this button do some action when clicked
     */
    public Button statisticsButton;




}//end of Controller class
