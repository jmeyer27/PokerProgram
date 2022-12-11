package com.example.pokerprogram;

import java.util.ArrayList;
import java.util.Collections;

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
        hand = new ArrayList<Card>(5);

    }

    public void replaceCard(int place, Card card){
        hand.set(place, card);
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
     * This method will reset the players hand so they do not have any cards
     * Useful for ending a game
     */
    public void removeCards(){
        hand = new ArrayList<Card>(5);
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


    /**
     * This method will look for at least 2 matching cards
     * @return true of there were 2 matching cards
     */
    public boolean onePair()
    {
        int count = 0;
        for (int i = 0; i < hand.size(); i++)
        {
            count = 0;
            for (int j = 0; j < hand.size(); j++) {
                if (hand.get(i).getRank().equals(hand.get(j).getRank()) && (i != j))
                    count++;
            }
            if(count == 1){
                return true;
            }

        }
        return false;
    }

    /**
     * Checks if there are no pairs in the hand of cards
     * @return boolean true if the cards have no pairs, false if there is a pair in the cards
     */
    public boolean noPair()
    {
        for (int i = 0; i < hand.size(); i++)
        {
            for (int j = i+1; j < hand.size(); j++)//(also i'm worried if i is cards.size-1 then j would be cards.size would that be an overflow exception?)
            {
                String rank1 = hand.get(i).getRank();
                String rankNext2 = hand.get(j).getRank();
                String suit1 = hand.get(i).getSuit();
                String suitNext2 = hand.get(j).getSuit();

                //if rank is equal but suit is not equal (these are a pair)
                if ((rank1.equals(rankNext2)) && (!suit1.equals(suitNext2)))
                {
                    return false;
                }
            }
        }
        //none of them were a pair, so it is true that there is no pair
        return true;
    }

    /**
     * This method will see if there are two different pairs of cards in the players hand
     * @return true if there are two different pairs of cards in the deck
     */
    public boolean twoPair()
    {
        int firstPair = 0;
        int secondPair = 0;
        for(int i = 0; i < hand.size(); i++){

            for(int j = 0; j < hand.size(); j++)
            {
                //if first pair has not been found yet and two cards are the same rank then set it to the first pair
                if((hand.get(i).getRank().equals(hand.get(j).getRank())) && (i !=j) && (firstPair == 0))
                {
                    firstPair = hand.get(i).getRankInteger();
                }
                //if the first pair has been found and there are two cards with the same rank then set it to the second pair
                if((hand.get(i).getRank().equals(hand.get(j).getRank())) && (i !=j) && (firstPair != 0)){
                    secondPair = hand.get(i).getRankInteger();
                }
            }

        }

        //if the first pair has been found and the second pair was found
        //AND the second pair was not the same as the first pair
        //return true if there are two different pairs in the deck
        //return false if something was not true (1 pair, 3 of a kind, etc)
        return firstPair != 0 && secondPair != firstPair && secondPair != 0;
    }

    /**
     * This method will search for three cards that are the same rank in a players hand
     * KNOWN ISSUE: Will return true if it is a 4 of a kind. Pls be mindful
     * @return true if 3 cards are found to be the same rank
     */
    public boolean threeOfAKind()
    {
        int count = 0;

        for(int i = 0; i < hand.size(); i++)
        {
            count = 0;
            for(int j = 0; j < hand.size(); j++)
            {
                if((hand.get(i).getRank().equals(hand.get(j).getRank())) && (i !=j))
                {
                    count++;
                }
                if(count == 2){
                    return true;
                }
            }
        }
       return false;
    }

    public boolean fourOfAKind()
    {
        int count = 0;

        for(int i = 0; i < hand.size(); i++)
        {
            count = 0;
            for(int j = 0; j < hand.size(); j++)
            {
                if((hand.get(i).getRank().equals(hand.get(j).getRank())) && (i != j))
                {
                    count++;
                }
            }
            if(count == 3){
                return true;
            }
        }
        return false;
    }


    public boolean flush()
    {
        //all cards are same suit
        for(int i = 0; i < hand.size()-1; i++)
        {
            if(!hand.get(i).getSuit().equals(hand.get(i + 1).getSuit()))
            {
                return false;
            }
        }
        return true;
    }

    public boolean fullHouse()
    {

        //has both a pair and a triple
        return onePair() && threeOfAKind();
    }


    public boolean straight() {
        int count = 0;
        Collections.sort(hand);
        for (int i = 0; i < hand.size()-1; i++) {
            //if the ace is first
            if(hand.get(i).getRankInteger() == 14){
                //if the next one is 10
                if((hand.get(i+1).getRankInteger() == 10)){
                    //add to the count
                    count++;
                }
            }//end if the first card is an ace, which is a weird scenario
            if ((hand.get(i+1).getRankInteger() == (hand.get(i).getRankInteger() + 1))){
                count++;
            }
            }
        return count == 4;
    }


    public boolean straightFlush()
    {
        //both a straight and a flush
        return straight() && flush();
    }

    public boolean royalFlush()
    {
        //both flush, straight, and begin with a 10
        //if cards are 10, jack, king, queen, and ace with the same suit
        if(straightFlush()){
            Collections.sort(hand);

            if((hand.get(0).getRankInteger() == 14)){
                return true;
            }
        }
        return false;
    }
}//end Player class
