package com.example.pokerprogram.controllers;

import com.example.pokerprogram.Card;
import com.example.pokerprogram.Deck;
import com.example.pokerprogram.Player;
import com.example.pokerprogram.ThePokerGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;


//this class will handle the events from the pokerGame-view.xml file
public class PokerGameController {

    public ImageView imageView_Dealer1;
    public ImageView imageView_Dealer2;
    public Button bFold;
    public Button bHowToPlay;

    @FXML
    public Label gameText;
    public Button bBet;
    public Button bCall;
    public Button bCheck;
    public Button bRaise;
    private Deck deck;
    @FXML
    private ImageView imageView_Hand2;
    @FXML
    private ImageView imageView_Hand1;

    public Button bStartGame;


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



    /**
     * Starts game when Start Game button is clicked by user
     * @param actionEvent user clicks Start Game button
     */
    public void startGame(ActionEvent actionEvent) {
        //get new deck and shuffle deck
        deck = new Deck();
        deck.shuffle();
        //new player and give player 2 cards to their hand
        Player player1 = new Player();
        player1.addCard(deck.dealTopCard()); //add card 1
        player1.addCard(deck.dealTopCard()); //add card 2

        //the two player cards are set out in front of them
        imageView_Hand1.setImage(player1.getHand(0).getCardImage());
        imageView_Hand2.setImage(player1.getHand(1).getCardImage());

        //todo: place these cards in a dealer/table arraylist? Need to keep track of cards on table.
        imageView_Dealer1.setImage(deck.dealTopCard().getCardImage());
        imageView_Dealer2.setImage(deck.dealTopCard().getCardImage());

        //set text in game text field
        gameText.setText("Poker game begins!");

        //Raise and Call cannot be used before a bet has been wagered
        bRaise.setVisible(false);
        bCall.setVisible(false);
    }

    public void fold(ActionEvent actionEvent) {
        //set text in game text field
        gameText.setText("Player has chosen to fold.");
        //Todo fold function
        //When the player folds they no longer are able to make other moves?
        //Can only start new game in single player
        //If we can make multiplayer then they would be unable to have a turn for the rest of the game?
    }

    public void getHowToPLay(ActionEvent actionEvent) throws IOException {
        Stage howToPlayStage = new Stage();
        howToPlayStage.setResizable(false);

        FXMLLoader fxmlLoader = new FXMLLoader(ThePokerGame.class.getResource("howToPlay-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.setFill(Color.web("#81c483"));

        //Todo: This is optional but the icon for the new window is not set. The path (/images/icon.png) is not correct
        //add icon picture (this shows up in the upper left corner of the window and is the icon in the task bar)
//        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icon.png")));
//        howToPlayStage.getIcons().add(icon);

        HowToPlayController controller = fxmlLoader.getController();

        howToPlayStage.setTitle("How to play");
        howToPlayStage.setScene(scene);
        howToPlayStage.show();
    }

    public void bet(ActionEvent actionEvent) {
        //set text in game text field
        gameText.setText("Player has chosen to make a bet."); //maybe add text to how much they chose to bet also :)
        //Todo bet function
        // prompt user how much they wish to bet
        // make sure the bet amount is within their currency range
        // subtract bet from currency
        // add bet to betting pool WHICH DOES NOT EXIST ATM btw

        //make raise and call buttons no longer invisible for next round
        bRaise.setVisible(true);
        bCall.setVisible(true);

    }

    public void call(ActionEvent actionEvent) {
        //set text in game text field
        gameText.setText("Player has chosen to call.");
        //Todo call function
    }

    public void check(ActionEvent actionEvent) {
        //set text in game text field
        gameText.setText("Player has chosen to check.");
        //Todo check function
    }

    public void raise(ActionEvent actionEvent) {
        //set text in game text field
        gameText.setText("Player has chosen to raise.");
        //Todo raise function
    }
}
