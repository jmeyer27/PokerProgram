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

    private Deck deckOfCards;

    public Player(){
        balance = 1000;
        hand = new ArrayList<>(5);
        deckOfCards = new Deck();
    }

    public void replaceFirstCard()
    {
        hand.set(1, deckOfCards.dealTopCard());
    }

    public void replaceSecondCard()
    {
        hand.set(2, deckOfCards.dealTopCard());
    }

    public void replaceThirdCard()
    {
        hand.set(3, deckOfCards.dealTopCard());
    }

    public void replaceFourthCard()
    {
        hand.set(4, deckOfCards.dealTopCard());
    }

    public void replaceFifthCard()
    {
        hand.set(5, deckOfCards.dealTopCard());
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


    public boolean onePair()
    {
        int count = 0;
        for (int i = 0; i < hand.size(); i++)
        {
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
        for(int i = 0; i < hand.size(); i++)
        {
            if(hand.get(i).getRank().equals(hand.get(i + 1).getRank()))
            {
                count++;
            }
        }
        return count == 2;
    }

    public boolean threeOfAKind()
    {
        int count = 0;

        for(int i = 0; i < hand.size(); i++)
        {
            for(int j = 0; j < hand.size(); j++)
            {
                if((hand.get(i).getRank().equals(hand.get(j).getRank())) && (i !=j))
                {
                    count++;
                }
            }
        }
        return count == 3;
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
        return count == 4;
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


}//end Player class
