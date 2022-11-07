package com.example.pokerprogram.controllers;

import com.example.pokerprogram.Deck;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;


//this class will handle the events from the pokerGame-view.xml file
public class PokerGameController {

    private Deck deck;

    /*
    This is to set up the previous scene information so that the user can return to the menu
     */
    private Scene preScene;
    public void setPreScene(Scene preScene){
        this.preScene = preScene;
    }


    /*
    This button's purpose is to return the user to the menu screen
    Todo: Prompt user if they really want to return to the menu
     */
    public Button returnToMenuButton;
    public void returnToMenuAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Welcome to The Poker Game Menu!");
        stage.setScene(preScene);
        stage.show();
    }







}
