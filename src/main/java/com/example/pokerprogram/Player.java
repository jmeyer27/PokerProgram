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
    //the name of the user
    private String username;

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
    public int getBalance(){
        return balance;
    }

    /**
     * Set method for player class
     * @param value new value of players balance
     */
    public void setBalance(int value){
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

    /**
     * A setter to set a new username for the user
     * @param newName the value of the new name
     */
    public void setUsername(String newName){
        this.username = newName;
    }

    /**
     * A getter to return the current username
     * @return the value of the players username
     */
    public String getUsername(){
        return this.username;
    }


}//end Player class
