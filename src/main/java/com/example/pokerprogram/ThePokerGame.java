package com.example.pokerprogram;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ThePokerGame extends Application {

    /*
    The main menu is the entry point to the program.
    When the run button is clicked, it will launch The Poker Game
     */
    public static void main(String[] args) {
        launch();
    }

    //When the program launches from main, this is where The Poker Menu will load up.
    @Override
    public void start(Stage stage) throws IOException {

        //load poker menu into application
        FXMLLoader fxmlLoader = new FXMLLoader(ThePokerGame.class.getResource("pokerMenu-view.fxml"));

        //initialize new scene and set size of menu
        //Todo: If this is too big or too small feel free to change the values :)
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);

        //add icon picture
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/icon.png")));
        stage.getIcons().add(icon);

        //Todo: set background of menu to image (can change image if you wish)
        //Image background = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/menu_background.jpg")));
        //scene.getStylesheets().addAll(Objects.requireNonNull(this.getClass().getResource("style/menuStyle.css")).toExternalForm());

        //set title of scene
        stage.setTitle("Welcome to The Poker Game Menu!");

        //set up the scene and run the menu
        stage.setScene(scene);
        stage.show();

    }//end of start function




}//end class ThePokerGame
