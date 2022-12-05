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

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;


//this class will handle the events from the pokerGame-view.xml file
public class PokerGameController implements Initializable {
    Player player1;//player
    String name; //players name
    String betAmount; //initial bet

    int count = 0;


    @FXML
    public CheckBox toggleMusic;

    @FXML
    public CheckBox checkReplaceCard1;

    @FXML
    public CheckBox checkReplaceCard2;

    @FXML
    public CheckBox checkReplaceCard3;

    @FXML
    public CheckBox checkReplaceCard4;

    @FXML
    public CheckBox checkReplaceCard5;
    @FXML
    public Label playerName;
    @FXML
    public Label potBalance;

    public ImageView imageView_Dealer1;
    public ImageView imageView_Dealer2;

    public Button bHowToPlay;
    @FXML
    public Label gameText;
    public Button bReplace;

    public Button bReplaceNothing;

    public Button bReplaceCardConfirm;

    @FXML
    private ImageView replaceCard1;

    @FXML
    private ImageView replaceCard2;

    @FXML
    private ImageView replaceCard3;

    @FXML
    private ImageView replaceCard4;

    @FXML
    private ImageView replaceCard5;
    private Deck deck;
    @FXML
    private ImageView imageView_Hand1;
    @FXML
    private ImageView imageView_Hand2;

    @FXML
    private ImageView imageView_Hand3;

    @FXML
    private ImageView imageView_Hand4;

    @FXML
    private ImageView imageView_Hand5;

    public Button bStartGame;
    //betting pot for game, this is where bets go
    private Integer bettingPot;

    boolean fold = false;


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
     * This supposedly will run when the scene is first launched.
     * This will prompt the user for their username for use in the game
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
        player1.addCard(deck.dealTopCard()); //add card 3
        player1.addCard(deck.dealTopCard()); //add card 4
        player1.addCard(deck.dealTopCard()); //add card 5

        TextInputDialog betDialog = new TextInputDialog();
        betDialog.setTitle("Enter Bet Amount");
        Optional<String> result = betDialog.showAndWait();
        result.ifPresent(string -> betAmount = string);

        //check for null first (user clicks cancel or the x button)
        if(betAmount == null){
            betAmount = "0";
        }
        //then check if user didn't put anything in the textbox but clicked confirm
        if(betAmount.equals("") || betAmount.isEmpty() ){
            betAmount = "0";
        }

        //the two player cards are set out in front of them
        imageView_Hand1.setImage(player1.getHand(0).getCardImage());
        imageView_Hand2.setImage(player1.getHand(1).getCardImage());
        imageView_Hand3.setImage(player1.getHand(2).getCardImage());
        imageView_Hand4.setImage(player1.getHand(3).getCardImage());
        imageView_Hand5.setImage(player1.getHand(4).getCardImage());

        //todo: place these cards in a dealer/table arraylist? Need to keep track of cards on table.
        //imageView_Dealer1.setImage(deck.dealTopCard().getCardImage());
        //imageView_Dealer2.setImage(deck.dealTopCard().getCardImage());


        //set text in game text field
        gameText.setText("Poker game begins!");
        //change bettingPot and user/player balance text
        //potBalance.setText("Pot balance: "+bettingPot);
        playerName.setText(player1.getUsername() +"'s cards: ");

        bReplace.setVisible(true);
        bReplaceNothing.setVisible(true);
        bReplaceCardConfirm.setVisible(false);
        setCardsInvisble();


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

    }

    /**
     * Action for the user to fold their cards in the Poker Game
     * Sets card images to the back of the cards
     * Disables button clicks for the rest of the game
     * @param actionEvent the bFold button is clicked
     */
    public void fold(ActionEvent actionEvent) throws IOException {
        //set text in game text field
        gameText.setText(player1.getUsername() +" has chosen to fold.");
        //fold hand
        imageView_Hand1.setImage(player1.getHand(0).getBackOfCard());
        imageView_Hand2.setImage(player1.getHand(1).getBackOfCard());

        //game cannot be used so are turned invisible
        setInvisibleButtons();

        //fold function:
        // turn should go to next player if multiplayer
        //When the player folds they no longer are able to make other moves
        //Can only start new game in single player
        //If we can make multiplayer then they would be unable to have a turn for the rest of the game
        //would have to make the buttons visible for each player and not attached to the game? idk trickyy

        //todo move this to wherever the end of the game is l8r
        saveStatistics();
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
    }

    /**
     * Call functions when replace button in clicked on
     */
    public void replace(ActionEvent actionEvent) {
        //set text in game text field
        gameText.setText("Select up to three cards to replace.");
        //fold hand

        //game cannot be used so are turned invisible
        //setVisibleButtons();

        setCardsVisble();
        bReplaceCardConfirm.setVisible(true);
        showCards();
        //saveStatistics();//save stats
        setInvisibleButtons();
    }

    public void noReplace(ActionEvent actionEvent)
    {
        System.out.println("At do not replace any cards.");
    }

    /**
     * Call functions when the 2nd confirm replace button in clicked on
     */
    public void replaceConfirm()
    {
        disableChecks();
        System.out.println("Replace Confirm");
        numCardsSelected();
        replaceCards();
        count = 0;

    }


    /**
     * Check that user only selects 3 cards max to replace
     */
    public void numCardsSelected()
    {
        if(count > 3)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Number of Cards Selected Invalid");
            errorAlert.setContentText("Please select a max of 3 cards to replace");
            errorAlert.showAndWait();
            enableChecks();
            //debugging
            //System.out.println("Total is over 3");
        }
    }

    /**
     * Show player cards that they can replace
     */
    public void showCards()
    {
        replaceCard1.setImage(player1.getHand(0).getCardImage());
        replaceCard2.setImage(player1.getHand(1).getCardImage());
        replaceCard3.setImage(player1.getHand(2).getCardImage());
        replaceCard4.setImage(player1.getHand(3).getCardImage());
        replaceCard5.setImage(player1.getHand(4).getCardImage());

    }

    /**
     * Set mini cards and checkboxes to invisible
     */
    public void setCardsInvisble()
    {
        replaceCard1.setVisible(false);
        replaceCard2.setVisible(false);
        replaceCard3.setVisible(false);
        replaceCard4.setVisible(false);
        replaceCard5.setVisible(false);
        checkReplaceCard1.setVisible(false);
        checkReplaceCard2.setVisible(false);
        checkReplaceCard3.setVisible(false);
        checkReplaceCard4.setVisible(false);
        checkReplaceCard5.setVisible(false);

    }

    /**
     * Set mini cards and checkboxes to visible
     */
    public void setCardsVisble()
    {
        replaceCard1.setVisible(true);
        replaceCard2.setVisible(true);
        replaceCard3.setVisible(true);
        replaceCard4.setVisible(true);
        replaceCard5.setVisible(true);
        checkReplaceCard1.setVisible(true);
        checkReplaceCard2.setVisible(true);
        checkReplaceCard3.setVisible(true);
        checkReplaceCard4.setVisible(true);
        checkReplaceCard5.setVisible(true);

    }

    /**
     * Set all game buttons invisible so that the user is not able to click them when the game is not in session
     */
    public void setInvisibleButtons(){
        bReplace.setVisible(false);
        bReplaceNothing.setVisible(false);

    }

    /**
     * Check if checkbox for first card is selected
     */
    public void checkSelectedCard1()
    {
        if(checkReplaceCard1.isSelected())
        {
            count++;
        }
    }

    /**
     * Check if checkbox for second card is selected
     */
    public void checkSelectedCard2()
    {
        if(checkReplaceCard2.isSelected())
        {
            count++;
        }
    }

    /**
     * Check if checkbox for third card is selected
     */
    public void checkSelectedCard3()
    {
        if(checkReplaceCard3.isSelected())
        {
            count++;
        }
    }

    /**
     * Check if checkbox for fourth card is selected
     */
    public void checkSelectedCard4()
    {
        if(checkReplaceCard4.isSelected())
        {
            count++;
        }
    }

    /**
     * Check if checkbox for fifth card is selected
     */
    public void checkSelectedCard5()
    {
        if(checkReplaceCard5.isSelected())
        {
            count++;
        }
    }

    /**
     * Replace the cards that the users desires
     */
    public void replaceCards()
    {
        if(checkReplaceCard1.isSelected())
        {
            System.out.println("HERE 1");
            player1.replaceFirstCard();
            imageView_Hand1.setImage(player1.getHand(0).getCardImage());

        }
    }

    /**
     * Disables the replace card checkboxes
     */
    public void disableChecks()
    {
        checkReplaceCard1.setDisable(true);
        checkReplaceCard2.setDisable(true);
        checkReplaceCard3.setDisable(true);
        checkReplaceCard4.setDisable(true);
        checkReplaceCard5.setDisable(true);

    }

    /**
     * Enbles the replace card checkboxes
     */
    public void enableChecks()
    {
        checkReplaceCard1.setDisable(false);
        checkReplaceCard2.setDisable(false);
        checkReplaceCard3.setDisable(false);
        checkReplaceCard4.setDisable(false);
        checkReplaceCard5.setDisable(false);

    }

    public String evaluate()
    {
        String eval = "";

        if(player1.onePair() == true)
        {
            eval = "One Pair";
            return eval;
        }
        if(player1.twoPair() == true)
        {
            eval = "Two Pair";
            return eval;
        }
        if(player1.threeOfAKind() == true)
        {
            eval = "Three Of A Kind";
            return eval;
        }
        if(player1.fourOfAKind() == true)
        {
            eval = "Four Of A Pair";
            return eval;
        }
        if(player1.flush() == true)
        {
            eval = "Flush";
            return eval;
        }
        if(player1.onePair() == true)
        {
            eval = "One Pair";
            return eval;
        }
        return eval;
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


    /**
     * This will save information to statistics
     * todo add more statistics info for player
     */
    public void saveStatistics() throws IOException {

        String fileName = "statistics.txt";


        try{
            File file = new File(fileName);


            //check if file does not exist
            if(!file.exists()){
                PrintWriter output = new PrintWriter(new FileOutputStream(file));

                //todo if player wins game then wins = 1, update $$ won or lost
                int wins = 0;

                output.println(wins);

                //System.out.println("Successfully written data to a new file"); //debug line
                output.close();

            }else{//file exist
                Scanner scanner = new Scanner(new File(fileName));
                int[] statistics = new int [10]; //picked 10 randomly
                int i = 0;


                while(scanner.hasNextInt()){
                    //System.out.println("There was a next int: " +statistics[i]); //debug line
                    statistics[i++] = scanner.nextInt();
                }


                PrintWriter output = new PrintWriter(new FileOutputStream(file));

                //todo if player has won, then add to count
                statistics[0] ++;

                //put number into file
                output.println(statistics[0]);


                //System.out.println("Successfully updated data to the file"); //debug
                output.close();
                scanner.close();
            }



        }catch(IOException e){
            //e.printStackTrace();
            System.out.println("Error in saving file in Poker Game!");
        }

            //save to file about wins
            //save to file about currency won/lost (1000-playersbalance >0)
        //System.out.println("the game is done and statistics should be saved here");//debug line






    }



}
