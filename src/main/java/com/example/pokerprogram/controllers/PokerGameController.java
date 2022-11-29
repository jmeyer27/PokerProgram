package com.example.pokerprogram.controllers;

import com.example.pokerprogram.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;


//this class will handle the events from the pokerGame-view.xml file
public class PokerGameController implements Initializable {
    Player player1;//player
    String name; //players name


    @FXML
    public CheckBox toggleMusic;
    @FXML
    public Label playerBalance;
    @FXML
    public Label potBalance;

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
    //betting pot for game, this is where bets go
    private Integer bettingPot;


    //This is all for the menu music. Gets path and sets up media player.
    String path = "src/main/java/com/example/pokerprogram/music/Smooth as Ice - Steven O'Brien (Must Credit, CC-BY, www.steven-obrien.net).mp3";
    Media media = new Media(new File(path).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    //credit music to creator Steven O'Brien (Must Credit, CC-BY, www.steven-obrien.net) with below
    /*
        Music by: Steven O'Brien
        https://www.steven-obrien.net/
        Smooth as Ice
        (Used for free under a Creative Commons Attribution-NoDerivatives 4.0 License: https://creativecommons.org/licenses/by-nd/4.0/)
     */


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
                //pause music first
                mediaPlayer.pause();
                stage.setTitle("Welcome to The Poker Game Menu!");
                stage.setScene(preScene);
                stage.show();
            } else if(result.get() == ButtonType.CANCEL){
                //nothing
            }

    }


    /**
     * This supposedly will run some stuff when the scene is first launched. to be tested
     * @param url idk
     * @param resourceBundle idk
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //todo ask user for name here
        // going to make a new window with a new view? it will be reused for multiplayer also
        // the below commented code works to get the users name but has a million ways the user can not put a name in
        // it also does not say anything about insert name here or whatever but it works????
        // maybe I'll just accept it lol
        //window.initModality(Modality.APPLICATION_MODAL)=-0b987

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter Name");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(string -> name = string);

        //check for null first (user clicks cancel or the x button)
        if(name == null){
            name = "Player";
        }
        //then check if user didn't put anything in the textbox but clicked confirm
        if(name.equals("") || name.isEmpty() ){
            name = "Player";
        }
        //debug line to check name value
        //System.out.println(name);
    }



    /**
     * Starts game when Start Game button is clicked by user
     * @param actionEvent user clicks Start Game button
     */
    public void startGame(ActionEvent actionEvent) {
        //set pot to 0
        bettingPot = 0;
        //get new deck and shuffle deck
        deck = new Deck();
        deck.shuffle();
        //new player and give player 2 cards to their hand
        player1 = new Player();
        //todo set player name (below)
        player1.setUsername(name);
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
        //change bettingPot and user/player balance text
        potBalance.setText("Pot balance: "+bettingPot);
        playerBalance.setText(player1.getUsername() +"'s balance: "+player1.getBalance());

        //Raise and Call cannot be used before a bet has been wagered
        bBet.setVisible(true);
        bFold.setVisible(true);
        bCheck.setVisible(true);
        //sets bet to be not disabled in the event that the last round had a bet
        bBet.setDisable(false);
        //sets raise and call to be disabled
        bRaise.setVisible(false);
        bCall.setVisible(false);
    }

    /**
     * Action that allows user to access how to play window
     * @param actionEvent User clicks on how to play button
     * @throws IOException exception thrown
     */
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

    /**
     * Action for the user to bet
     * @param actionEvent the bBet button is clicked
     */
    public void bet(ActionEvent actionEvent) {
        //set text in game text field
        gameText.setText(player1.getUsername() +" has chosen to make a bet."); //maybe add text to how much they chose to bet also :)

        //Todo bet function
        // prompt user how much they wish to bet
        // make sure the bet amount is within their currency range
        // subtract bet from currency
        // add bet to pot (bettingPot)
        //change bettingPot and user/player balance text
        potBalance.setText("Pot balance: "+bettingPot);
        playerBalance.setText(player1.getUsername() +" balance: "+player1.getBalance());

        //make raise and call buttons no longer invisible for next round
        bRaise.setVisible(true);
        bCall.setVisible(true);
        //set bet to now be disabled
        bBet.setDisable(true);

    }

    /**
     * Action for the user to fold their cards in the Poker Game
     * Sets card images to the back of the cards
     * Disables button clicks for the rest of the game
     * @param actionEvent the bFold button is clicked
     */
    public void fold(ActionEvent actionEvent) {
        //set text in game text field
        gameText.setText(player1.getUsername() +" has chosen to fold.");
        //fold hand
        imageView_Hand1.setImage(player1.getHand(0).getBackOfCard());
        imageView_Hand2.setImage(player1.getHand(1).getBackOfCard());

        //game cannot be used so are turned invisible
        setInvisibleButtons();

        //Todo fold function
        // turn should go to next player (round if single player)
        //When the player folds they no longer are able to make other moves
        //Can only start new game in single player
        //If we can make multiplayer then they would be unable to have a turn for the rest of the game
        //would have to make the buttons visible for each player and not attached to the game? idk trickyy
    }

    /**
     * Action for the user to call their bet in the Poker Game
     * @param actionEvent the bCall button is clicked
     */
    public void call(ActionEvent actionEvent) {
        //set text in game text field
        gameText.setText(player1.getUsername() +" has chosen to call.");
        //Todo call function
        // check if enough balance
        // add bet to pot (bettingPot)
        // turn should go to next player (round if single player?)
    }

    /**
     * Action for the user to check their bet in the Poker Game
     * @param actionEvent the bCheck button is clicked
     */
    public void check(ActionEvent actionEvent) {
        //set text in game text field
        gameText.setText(player1.getUsername() +" has chosen to check.");
        //todo turn should go to next player (round if single player)
    }

    /**
     * Action for the user to raise their bet in the Poker Game
     * @param actionEvent the bRaise button is clicked
     */
    public void raise(ActionEvent actionEvent) {
        //set text in game text field
        gameText.setText(player1.getUsername() +" has chosen to raise.");
        //Todo raise function
        // check if enough balance
        // prompt player for raise amount
        // add amount to pot (bettingPot)
        // turn should go to next player (round if single player)
        //change bettingPot and user/player balance text
        potBalance.setText("Pot balance: "+bettingPot);
        playerBalance.setText(player1.getUsername() +" balance: "+player1.getBalance());
    }

    /**
     * Set all game buttons invisible so that the user is not able to click them when the game is not in session
     */
    public void setInvisibleButtons(){
        bRaise.setVisible(false);
        bCall.setVisible(false);
        bFold.setVisible(false);
        bCheck.setVisible(false);
        bBet.setVisible(false);
    }


    public void toggleMusic(ActionEvent actionEvent) {
        //this lets the music loop
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.play();
            }
        });

        //set credit text for song
        toggleMusic.setTooltip(new Tooltip("Music by: Steven O'Brien\n" +
                "https://www.steven-obrien.net/\n" +
                "\n" +
                "Smooth as Ice\n" +
                "\n" +
                "(Used for free under a Creative Commons Attribution-NoDerivatives 4.0 License: https://creativecommons.org/licenses/by-nd/4.0/)"));

        //if checkmark
        if(toggleMusic.isSelected()){
            //play song
            mediaPlayer.play();
        }else{//no checkmark
            //no music
            mediaPlayer.pause();
        }
    }


}
