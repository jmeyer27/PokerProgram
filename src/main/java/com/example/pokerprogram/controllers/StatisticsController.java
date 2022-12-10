package com.example.pokerprogram.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class StatisticsController {

    @FXML
    public Label textBox;


    /**
     * Show statistics info (and later charts and stuff) to user
     * @param actionEvent player clicks show stats button
     */
    public void showStats(ActionEvent actionEvent) {

        String fileName = "statistics.txt";

        try{
            File file = new File(fileName);
            Scanner inputStream = new Scanner(new FileInputStream(file));

            //todo update this after finalizing statistics in PokerGameController
            //get values from textfile to put into label
            String numWins = "Number of games played: " +inputStream.nextLine();

            //set textbox to have value from textfile
            textBox.setText(numWins);

            //important step of closing file
            inputStream.close();
        }catch(IOException e){//encountered an error
            System.out.println("Error in StatisticsController opening file!!");
        }
    }
}//end statistics
