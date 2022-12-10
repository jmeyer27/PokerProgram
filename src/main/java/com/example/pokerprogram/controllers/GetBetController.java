package com.example.pokerprogram.controllers;

import com.example.pokerprogram.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GetBetController {

    public Button bConfirm;
    public Label labelBetRange;
    int playerBalance;
    int bet;

    @FXML
    public Label labelCorrection;
    @FXML
    public TextField textFieldBet;


    public void initData(Player player){
        playerBalance = player.getBalance();
    }



    public void clickConfirm(ActionEvent actionEvent) {

        //if it is empty or not a number
        if(textFieldBet.getText().isEmpty() || !textFieldBet.getText().matches("[0-9]*")){
            //show label correcting the user
            labelCorrection.setVisible(true);
            labelBetRange.setText("Please enter a value between 0 and " +playerBalance);
            labelBetRange.setVisible(true);
        }
        else{//
            labelBetRange.setText("Please enter a value between 0 and " +playerBalance);
            bet = Integer.parseInt(textFieldBet.getText());
            //System.out.println("bet: " +bet);

            if(bet > playerBalance){
                labelBetRange.setVisible(true);
            }else{
                //close window and pass bet to next thing :)
                //System.out.println("Was good");


                Stage stage = (Stage) bConfirm.getScene().getWindow();
                stage.close();

            }

        }



        //labelCorrection.setVisible(true);
    }

    public int getBet(){
        return bet;
    }


}
