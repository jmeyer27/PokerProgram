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

    //private Deck deckOfCards;

    public Player(){
        balance = 1000;
        hand = new ArrayList<Card>(5);


        //this deck is not the same that the player gets in the beginning of the game, so there could be duplicates
        //it also isnt shuffled so it only gives out A<3 2<3 3<3 4<4 5<3
        //deckOfCards = new Deck();
    }

    public void replaceCard(int place, Card card){
        hand.set(place, card);
    }

    //redundant code, replaced with replaceCard(int place, Card card)
//    public void replaceFirstCard(Card card)
//    {
//        hand.set(0, card);
//    }
//
//    public void replaceSecondCard(Card card)
//    {
//        hand.set(1, card);
//    }
//
//    public void replaceThirdCard(Card card)
//    {
//        hand.set(2, card);
//    }
//
//    public void replaceFourthCard(Card card)
//    {
//        hand.set(3, card);
//    }
//
//    public void replaceFifthCard(Card card)
//    {
//        hand.set(4, card);
//    }

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

        }
        return count == 1;
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

    public boolean twoPair()
    {
        int count = 0;
        for(int i = 0; i < hand.size()-1; i++)
        {
            if(hand.get(i).getRank().equals(hand.get(i + 1).getRank()))
            {
                count++;
            }
        }
        if(count == 2)
        {
            return true;
        }
        return false;
    }

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
            }
        }
       if(count == 3)
       {
           return true;
       }
       return false;
    }

    public boolean fourOfAKind()
    {
        int count = 0;

        for(int i = 0; i < hand.size(); i++)
        {
            for(int j = 0; j < hand.size(); j++)
            {
                if((hand.get(i).getRank().equals(hand.get(j).getRank())) && (i != j))
                {
                    count++;
                }
            }
        }
        return count == 3;
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
            if ((hand.get(i+1).getRank() == (hand.get(i).getRank() + 1))
                    || ((hand.get(0).getRank().equals(2)) && (hand.get(1).getRank().equals(3))
                    && (hand.get(2).getRank().equals(4))
                    && (hand.get(3).getRank().equals(5)) && (hand.get(4).getRank().equals(12))))
                count++;
        }
        if (count == 4)
        {
            return true;
        }
        return false;
    }

    public boolean straightFlush()
    {
        //both a straight and a flush
        if(straight() == true && flush() == true)
        {
            return true;
        }
        return false;
    }

    public boolean royalFlush()
    {
        //both flush, straight, and begin with a 10
        return true;
    }
}//end Player class
