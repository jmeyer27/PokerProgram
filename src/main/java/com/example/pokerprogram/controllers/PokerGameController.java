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
import javafx.scene.input.MouseEvent;
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
    public Label labelBalance;
    public Label labelBet;
    Player player1;//player
    String name; //players name
    int betAmount; //initial bet

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

    public Button bHowToPlay;
    @FXML
    public Label gameText;
    public Button bReplace;

    public Button bReplaceNothing;

    public Button bReplaceCardConfirm;

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
        count = 0;
    }


    /**
     * This will return player to the menu screen from poker game screen
     * Will also prompt user to specify if they wish to end the game or not
     * @param actionEvent the returnToMenuButton is clicked in the pokerGame-view.fxml window
     * @throws IOException exception thrown in the event of an error
     */
    public void returnToMenuAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        count = 0;

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
        //ask user for name here
        // going to make a new window with a new view? it will be reused for multiplayer also
        // the below commented code works to get the users name but has a million ways the user can not put a name in
        // it also does not say anything about insert name here or whatever but it works????
        // maybe I'll just accept it lol
        //window.initModality(Modality.APPLICATION_MODAL)

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

        //make new player and give them a name
        player1 = new Player();
        player1.setUsername(name);

    }



    /**
     * Starts game when Start Game button is clicked by user
     * @param actionEvent user clicks Start Game button
     */
    public void startGame(ActionEvent actionEvent) throws IOException {

        //set count to 0
        count = 0;

        //get new deck and shuffle deck
        deck = new Deck();
        deck.shuffle();


        //if the player loses all of their betting money then they can get 1000 again
        //why not lol
        if(player1.getBalance() == 0){
            player1.setBalance(1000);
        }
        //removes cards from players hand just in case there was a previously played game
        player1.removeCards();

        //give player 5 cards to their hand
        player1.addCard(deck.dealTopCard()); //add card 1
        player1.addCard(deck.dealTopCard()); //add card 2
        player1.addCard(deck.dealTopCard()); //add card 3
        player1.addCard(deck.dealTopCard()); //add card 4
        player1.addCard(deck.dealTopCard()); //add card 5

        //open new window to inquire about bet
        Stage getBetStage = new Stage();
        getBetStage.setResizable(false);
        //keep stage from being ignored by user
        getBetStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(ThePokerGame.class.getResource("getBet-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GetBetController controller = fxmlLoader.getController();

        //give player info to next stage, so it can check player balance
        controller.initData(player1);

        getBetStage.setTitle("Enter Bet");
        getBetStage.setScene(scene);
        getBetStage.showAndWait();

        betAmount = controller.getBet();

        //the two player cards are set out in front of them
        imageView_Hand1.setImage(player1.getHand(0).getCardImage());
        imageView_Hand2.setImage(player1.getHand(1).getCardImage());
        imageView_Hand3.setImage(player1.getHand(2).getCardImage());
        imageView_Hand4.setImage(player1.getHand(3).getCardImage());
        imageView_Hand5.setImage(player1.getHand(4).getCardImage());


        //set text in game text field
        gameText.setText("Poker game begins!");
        //change bettingPot and user/player balance text
        //potBalance.setText("Pot balance: "+bettingPot);
        playerName.setText(player1.getUsername() +"'s cards: ");
        //here update player balance by - bet number
        player1.setBalance(player1.getBalance()-betAmount);
        labelBalance.setText(player1.getUsername() +"'s balance: " + player1.getBalance());
        labelBet.setText("Bet: " + betAmount);

        bReplace.setVisible(true);
        bReplaceNothing.setVisible(true);
        bReplaceCardConfirm.setVisible(false);

        clearFields();
        setCheckboxes(false);


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
        HowToPlayController controller = fxmlLoader.getController();
        howToPlayStage.setTitle("How to play");
        howToPlayStage.setScene(scene);
        howToPlayStage.show();
    }



    /**
     * Call functions when replace button in clicked on
     */
    public void replace(ActionEvent actionEvent) {
        //set text in game text field
        gameText.setText("Select up to three cards to replace.");
        setCheckboxes(true);
        bReplaceCardConfirm.setVisible(true);
        setInvisibleButtons();
    }

    public void noReplace(ActionEvent actionEvent) throws IOException {
        //System.out.println(evaluate());
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setHeaderText("Game Results");
        infoAlert.setContentText(evaluate());
        infoAlert.showAndWait();
    }

    /**
     * Call functions when the 2nd confirm replace button in clicked on
     */
    public void replaceConfirm() throws IOException {
        //System.out.println("Replace Confirm, count is: " +count);

        if(numCardsSelected())
        {
            replaceCards();
            //System.out.println(evaluate());
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setHeaderText("Game Results");
            infoAlert.setContentText(evaluate());
            infoAlert.showAndWait();
            count = 0;
        }
        //count = 0;


    }


    /**
     * Check that user only selects 3 cards max to replace
     */
    public boolean numCardsSelected()
    {
        //System.out.println("count was: " +count +"in numCardsSelected()");
        if(count > 3)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Number of Cards Selected Invalid");
            errorAlert.setContentText("Please select a max of 3 cards to replace");
            errorAlert.showAndWait();
            return false;
        }
        return true;
    }



    /**
     * Set visibility on checkboxes
     */
    public void setCheckboxes(boolean state)
    {

        checkReplaceCard1.setVisible(state);
        checkReplaceCard2.setVisible(state);
        checkReplaceCard3.setVisible(state);
        checkReplaceCard4.setVisible(state);
        checkReplaceCard5.setVisible(state);

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
        if(checkReplaceCard1.isSelected()) {
            count++;
        } else{
            count--;
        }

    }

    /**
     * Check if checkbox for second card is selected
     */
    public void checkSelectedCard2() {
        if (checkReplaceCard2.isSelected()) {
            count++;
        } else {
            count--;
        }
    }

    /**
     * Check if checkbox for third card is selected
     */
    public void checkSelectedCard3(){
        if(checkReplaceCard3.isSelected()) {
            count++;
        } else{
            count--;
        }
    }

    /**
     * Check if checkbox for fourth card is selected
     */
    public void checkSelectedCard4()
    {
        if(checkReplaceCard4.isSelected()) {
            count++;
        } else{
            count--;
        }
    }

    /**
     * Check if checkbox for fifth card is selected
     */
    public void checkSelectedCard5()
    {
        if(checkReplaceCard5.isSelected()) {
            count++;
        } else{
            count--;
        }
    }

    /**
     * Replace the cards that the users desires
     */
    public void replaceCards()
    {
        if(checkReplaceCard1.isSelected())
        {
            //System.out.println("HERE 1");
            player1.replaceCard(0, deck.dealTopCard());
            imageView_Hand1.setImage(player1.getHand(0).getCardImage());

        }
        if(checkReplaceCard2.isSelected())
        {
            player1.replaceCard(1, deck.dealTopCard());
            imageView_Hand2.setImage(player1.getHand(1).getCardImage());

        }
        if(checkReplaceCard3.isSelected())
        {
            player1.replaceCard(2, deck.dealTopCard());
            imageView_Hand3.setImage(player1.getHand(2).getCardImage());
        }
        if(checkReplaceCard4.isSelected())
        {
            player1.replaceCard(3, deck.dealTopCard());
            imageView_Hand4.setImage(player1.getHand(3).getCardImage());

        }
        if(checkReplaceCard5.isSelected())
        {
            player1.replaceCard(4, deck.dealTopCard());
            imageView_Hand5.setImage(player1.getHand(4).getCardImage());

        }
    }



    public void clearFields(){

        checkReplaceCard1.setSelected(false);
        checkReplaceCard2.setSelected(false);
        checkReplaceCard3.setSelected(false);
        checkReplaceCard4.setSelected(false);
        checkReplaceCard5.setSelected(false);

    }

    public String evaluate() throws IOException {
        gameText.setText("Start a new game");
        String eval = "";
        int winnings = 0;
        setCheckboxes(false);
        bReplaceCardConfirm.setVisible(false);
        setInvisibleButtons();
        //System.out.println("Evaluate");

        if(player1.royalFlush())
        {
            eval = "Royal Flush";
            setWinnings(100);
            return eval;
        }
        if(player1.straightFlush())
        {
            eval = "Straight Flush";
            winnings = 12;
            player1.setBalance(player1.getBalance()+winnings);
            setWinnings(50);
            return eval;
        }else
        if(player1.fourOfAKind())
        {
            eval = "Four Of A Kind";
            setWinnings(10);
            return eval;
        }else
        if(player1.fullHouse())
        {
            eval = "Full House";
            setWinnings(8);
            return eval;
        }else
        if(player1.flush())
        {
            eval = "Flush";
            setWinnings(5);
            return eval;
        }else
        if(player1.straight())
        {
            eval = "Straight";
            setWinnings(4);
            return eval;
        }else
        if(player1.threeOfAKind())
        {
            eval = "Three Of A Kind";
            setWinnings(3);
            return eval;
        }else
        if(player1.twoPair())
        {
            eval = "Two Pairs";
            setWinnings(2);
            return eval;
        }else
        if(player1.onePair())
        {
            eval = "One Pair";
            setWinnings(1);
            return eval;
        }else
        if(player1.noPair())
        {
            eval = "No Pair";
            setWinnings(0);
            return eval;
        }

        //save stats here before returning eval to user
        saveStatistics();
        return eval;
    }

    /**
     * Set the winnings for a player who has won the poker game
     * @param multiplier a value depending on how strong their hand was
     */
    public void setWinnings(int multiplier) throws IOException {
        result = multiplier; //this is used for statistics purposes
        saveStatistics();

        //calculate winnings for user to win $$
        int winnings = betAmount*multiplier;
        winnings = player1.getBalance() + winnings;
        player1.setBalance(winnings);
        labelBalance.setText(player1.getUsername() +"'s balance: " + player1.getBalance());
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

    //this is used to for statistics to show what kind of hands the player has won
    int result;


    /**
     * This will save information to statistics
     *
     */
    public void saveStatistics() throws IOException {

        String fileName = "statistics.txt";


        try{
            File file = new File(fileName);


            //check if file does not exist
            if(!file.exists()){
                PrintWriter output = new PrintWriter(new FileOutputStream(file));

                int games = 1;

                int nopair, onepair, twopair, threepair, fourpair, straight, flush, fullhouse, straightflush, royalflush;
                nopair = onepair = twopair = threepair = fourpair = straight= flush = fullhouse = straightflush = royalflush = 0;

                switch (result) {
                    case 0 -> nopair = 1;
                    case 1 -> onepair = 1;
                    case 2 -> twopair = 1;
                    case 3 -> threepair = 1;
                    case 4 -> straight = 1;
                    case 5 -> flush = 1;
                    case 8 -> fullhouse = 1;
                    case 10 -> fourpair = 1;
                    case 12 -> straightflush = 1;
                    case 100 -> royalflush = 1;
                }


                output.println(games);
                output.println(nopair);
                output.println(onepair);
                output.println(twopair);
                output.println(threepair);
                output.println(straight);
                output.println(flush);
                output.println(fullhouse);
                output.println(fourpair);
                output.println(straightflush);
                output.println(royalflush);

                //System.out.println("Successfully written data to a new file"); //debug line
                output.close();

            }else{//file exist
                Scanner scanner = new Scanner(new File(fileName));
                int[] statistics = new int [12];
                int i = 0;


                while(scanner.hasNextInt()){
                    //System.out.println("There was a next int: " +statistics[i]); //debug line
                    statistics[i++] = scanner.nextInt();
                }


                PrintWriter output = new PrintWriter(new FileOutputStream(file));


                statistics[0] ++;


                switch (result) {
                    case 0 -> statistics[1] ++;
                    case 1 -> statistics[2] ++;
                    case 2 -> statistics[3] ++;
                    case 3 -> statistics[4] ++;
                    case 4 -> statistics[5] ++;
                    case 5 -> statistics[6] ++;
                    case 8 -> statistics[7] ++;
                    case 10 -> statistics[8] ++;
                    case 12 -> statistics[9] ++;
                    case 100 -> statistics[10] ++;
                }

                //put numbers into file
                //output.println(statistics[0]);
                for(i = 0; i < 12; i++){
                    output.println(statistics[i]);
                }


                //System.out.println("Successfully updated data to the file"); //debug
                output.close();
                scanner.close();
            }



        }catch(IOException e){
            //e.printStackTrace();
            System.out.println("Error in saving file in Poker Game!");
        }




    }


    /**
     * If the card image is clicked in the poker game then it selects and deselects the checkboxes
     * @param mouseEvent the card image is clicked
     */
    public void selectCard1(MouseEvent mouseEvent) {
        checkReplaceCard1.setSelected(!checkReplaceCard1.isSelected());
        checkSelectedCard1();
    }

    /**
     * If the card image is clicked in the poker game then it selects and deselects the checkboxes
     * @param mouseEvent the card image is clicked
     */
    public void selectCard2(MouseEvent mouseEvent) {
        checkReplaceCard2.setSelected(!checkReplaceCard2.isSelected());
        checkSelectedCard2();
    }

    /**
     * If the card image is clicked in the poker game then it selects and deselects the checkboxes
     * @param mouseEvent the card image is clicked
     */
    public void selectCard3(MouseEvent mouseEvent) {
        checkReplaceCard3.setSelected(!checkReplaceCard3.isSelected());
        checkSelectedCard3();
    }

    /**
     * If the card image is clicked in the poker game then it selects and deselects the checkboxes
     * @param mouseEvent the card image is clicked
     */
    public void selectCard4(MouseEvent mouseEvent) {
        checkReplaceCard4.setSelected(!checkReplaceCard4.isSelected());
        checkSelectedCard4();
    }

    /**
     * If the card image is clicked in the poker game then it selects and deselects the checkboxes
     * @param mouseEvent the card image is clicked
     */
    public void selectCard5(MouseEvent mouseEvent) {
        checkReplaceCard5.setSelected(!checkReplaceCard5.isSelected());
        checkSelectedCard5();
    }
}
