package com.example.pokerprogram.controllers;

import com.example.pokerprogram.Card;
import com.example.pokerprogram.Deck;
import com.example.pokerprogram.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;


//this class will handle the events from the pokerGame-view.xml file
public class PokerGameController {

    private Deck deck;
    @FXML
    private ImageView imageView_Hand2;
    @FXML
    private ImageView imageView_Hand1;


    /*
    This is to set up the previous scene information so that the user can return to the menu
     */
    private Scene preScene;
    public void setPreScene(Scene preScene){
        this.preScene = preScene;
    }


    /**
     * This will return player to the menu screen from poker game screen
     * Will also prompt user to specify if they wish to end the game or not
     * @param actionEvent the returnToMenuButton is clicked in the pokerGame-view.fxml window
     * @throws IOException exception thrown in the event of an error
     */
    public void returnToMenuAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();

        //prompt user if they want to return to menu
        Alert.AlertType type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type, "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        alert.getDialogPane().setContentText("You will lose all progress.");
        alert.getDialogPane().setHeaderText("Do you wish to exit the game?");

        Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                stage.setTitle("Welcome to The Poker Game Menu!");
                stage.setScene(preScene);
                stage.show();
            } else if(result.get() == ButtonType.CANCEL){
                //nothing
            }

    }



    public Button bStartGame;

    /**
     * Starts game when Start Game button is clicked by user
     * @param actionEvent user clicks Start Game button
     */
    public void startGame(ActionEvent actionEvent) {
        //get new deck and shuffle deck
        deck = new Deck();
        deck.shuffle();
        Player player1 = new Player();
        player1.addCard(deck.dealTopCard()); //add card 1
        player1.addCard(deck.dealTopCard()); //add card 2


        //the two player cards are set out in front of them
        imageView_Hand1.setImage(player1.getHand(0).getCardImage());
        imageView_Hand2.setImage(player1.getHand(1).getCardImage());

    }
}
