package com.example.pokerprogram;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ThePokerGame extends Application {

    public static void main(String[] args) {
        launch();
    }

    //when the program starts, this is where the menu will load up
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pokerMenu-view.fxml"));

        //initalize new scene and set size of menu
        //if this is too big or too small feel free to change the values
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setTitle("Welcome to the Poker Game Menu!");
        stage.setScene(scene);
        stage.show();

    }



}//end class ThePokerGame
