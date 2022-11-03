package com.example.pokerprogram;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ThePokerGame extends Application {

    /*
    The main menu is the entry point to the program.
    When the run button is clicked, it will launch The Poker Game
     */
    public static void main(String[] args) {
        launch();
    }

    //when the program starts, this is where the poker menu will load up.
    @Override
    public void start(Stage stage) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("/view/pokerMenu-view.fxml"));


        //load poker menu into application
        FXMLLoader fxmlLoader = new FXMLLoader(ThePokerGame.class.getResource("pokerMenu-view.fxml"));

        //initalize new scene and set size of menu
        //if this is too big or too small feel free to change the values
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setTitle("Welcome to the Poker Game Menu!");
        stage.setScene(scene);
        stage.show();

    }//end of start function




}//end class ThePokerGame
