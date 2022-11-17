package com.example.pokerprogram.controllers;

import com.example.pokerprogram.ThePokerGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;


//this class will handle the events from the pokerMenu-view.xml file
public class PokerMenuController {

    public Button bHowToPlay;
    public Button gameButton;
    @FXML
    private javafx.scene.control.Button exitButton;
    /*
    This button is so that users are able to load up their statistics information
    Todo: Make this button do some action when clicked
     */
    public Button statisticsButton;

    /*
    This button's purpose is to start the poker game from the poker game menu
    */
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
    This is the action event function for when the exit button is clicked in the ThePokerGame pokerMenu
    This button can probably be deleted tbh
     */
    @FXML
    protected void closeButtonAction(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }



    /**
     *The user clicks a button called bHowToPlay and gets helpful information about how to play poker
     * @param actionEvent The user clicks a button called bHowToPlay
     */
    public void getHowToPlay(ActionEvent actionEvent) throws IOException {
        //todo don't forget to finish this :) if someone else want to tackle this, Jasmine has a good sample if you need one
        Stage howToPlayStage = new Stage();
        howToPlayStage.setResizable(false);

        FXMLLoader fxmlLoader = new FXMLLoader(ThePokerGame.class.getResource("howToPlay-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        //Todo: This is optional but the icon for the new window is not set. The path (/images/icon.png) is not correct
        //add icon picture (this shows up in the upper left corner of the window and is the icon in the task bar)
//        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icon.png")));
//        howToPlayStage.getIcons().add(icon);

        HowToPlayController controller = fxmlLoader.getController();

        howToPlayStage.setTitle("How to play");
        howToPlayStage.setScene(scene);
        howToPlayStage.show();
    }
}//end of Controller class
