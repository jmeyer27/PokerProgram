package com.example.pokerprogram.controllers;

import com.example.pokerprogram.ThePokerGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


//this class will handle the events from the pokerMenu-view.xml file
public class PokerMenuController {
    @FXML
    public Button bHowToPlay;
    @FXML
    public Button gameButton;
    @FXML
    private CheckBox toggleMusic;
    @FXML
    private javafx.scene.control.Button exitButton;
    /*
    This button is so that users are able to load up their statistics information
     */
    public Button statisticsButton;

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
    This button's purpose is to start the poker game from the poker game menu
    */
    public void startGameAction(ActionEvent event) throws IOException {

        //stop music pls
        mediaPlayer.pause();
        toggleMusic.setSelected(false);

        Stage chooseGameStage = new Stage();
        chooseGameStage.setResizable(false);

        //this makes the choose game type a new window which wasnt very good v.v
        FXMLLoader choosefxmlLoader = new FXMLLoader(ThePokerGame.class.getResource("chooseGame-view.fxml"));
        Scene choosescene = new Scene(choosefxmlLoader.load());
        ChooseGameController choosecontroller = choosefxmlLoader.getController();
        chooseGameStage.setTitle("Chose your game type!");
        chooseGameStage.setScene(choosescene);
        chooseGameStage.showAndWait();


        //This loads the single player poker game without any intermediary window
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
     * This action event toggles music when the user clicks the checkmark in the checkbox
     * @param actionEvent the checkbox is clicked by the user
     */
    public void toggleMusic(ActionEvent actionEvent) {
        //String songName = "Smooth as Ice - Steven O'Brien (Must Credit, CC-BY, www.steven-obrien.net).mp3";

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
     * This should open a new window and show the user their statistics info
     * @param actionEvent the user clicks on the statistics button
     */
    public void openStatistics(ActionEvent actionEvent) {
        //file name
        String fileName = "statistics.txt";


        try{
            File file = new File(fileName);

            if(!file.exists()){//if file does not exist

                //todo tell user that file does not exist
                System.out.println("There was no file.");//debug line

            }else{//the file exists open Statistics window

                Stage howToPlayStage = new Stage();
                howToPlayStage.setResizable(false);

                FXMLLoader fxmlLoader = new FXMLLoader(ThePokerGame.class.getResource("statistics-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());

                StatisticsController controller = fxmlLoader.getController();

                howToPlayStage.setTitle("Player Statistics");
                howToPlayStage.setScene(scene);
                howToPlayStage.show();

            }//end if file exists


        }catch(IOException e){
            System.out.println("There was a file input stream error when opening statistics in poker menu!!!");
        }
    }//end of openStatistics event




}//end of Controller class
