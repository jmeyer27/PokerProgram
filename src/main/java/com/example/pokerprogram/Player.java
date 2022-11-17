package com.example.pokerprogram;

import java.util.ArrayList;

/**
 * This is a class that will represent the user and their resources in the Poker Game
 */
public class Player {

    //the amount of chips the player can use for betting
    private int balance;
    //this holds players cards
    private ArrayList<Card> hand;

    /**
     * Constructor for player class
     */
    public Player(){
        balance = 1000;
        hand = new ArrayList<>(5);
    }

    /**
     * Get method for player class
     * @return value of players balance
     */
    private int getBalance(){
        return balance;
    }

    /**
     * Set method for player class
     * @param value new value of players balance
     */
    private void setBalance(int value){
        this.balance = value;
    }

    /**
     * Add card to players hand
     * @param card a Card
     */
    public void addCard(Card card){
        hand.add(card);
    }

    /**
     * This method will return the card from the players hand
     * @param index an integer that represents which card in the players hand is being asked for (the first card, second.. etc)
     * @return the card in the deck in the index that was asked for.
     */
    public Card getHand(int index){
        return hand.get(index);
    }


}//end Player class
