package com.example.pokerprogram;

import javafx.scene.image.Image;

import java.util.Objects;

public class Card implements Comparable<Card> {

    private Image cardImage;
    private Image backOfCard;
    private int rank;
    private int suit;


    /**
     * Constructor for card class
     * @param newRank the rank of the new card
     * @param newSuit the suit of the new card
     */
    public Card(int newRank, int newSuit)
    {
        rank = newRank;
        suit = newSuit;
        String imageRank = getRank();
        String imageSuit = getSuit();
        String fileName = imageRank + "_of_" +imageSuit + ".png";
        //System.out.println(fileName); //line for debugging file name and card creation
        cardImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/pokerCardImages/" + fileName)));
        backOfCard = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/pokerCardImages/Bicycle_Playing_Cards_red.jpg")));
    }

    /**
     * A get method that returns the suit of the card
     * @return the suit of the card
     */
    public String getSuit() {
       return switch(this.suit){
           case 1 -> "hearts";
           case 2 -> "clubs";
           case 3 -> "diamonds";
           case 4-> "spades";
           default -> "error"; //will cause error hopefully
       };
    }

    /**
     * This is a get method that will return the integer value of a suit
     * Useful for comparisons
     * @return the suit represented as a number
     */
    public Integer getSuitInteger(){
        return suit;
    }

    /**
     * A get method that determines the correct rank of the card based on the int value of their rank
     * @return return cards rank
     */
    public String getRank() {
        return switch (this.rank) {
            case 1 -> "ace";
            case 11 -> "jack";
            case 12 -> "queen";
            case 13 -> "king";
            default -> String.valueOf(this.rank);
        };
    }

    /**
     * This is a method to return the integer value of the rank
     * Useful for comparisons
     * @return the rank represented as a number
     */
    public Integer getRankInteger(){
        return rank;
    }

    /**
     * A get method that returns the image of the card used in GUI
     * @return the .png image of the card
     */
    public Image getCardImage(){
        return cardImage;
    }
    public Image getBackOfCard(){ return backOfCard;}

    public int compareTo(Card card2)
    {
        int compare = 0;

        if(this.rank > card2.rank)
        {
            compare = 1;
        }
        if(this.rank < card2.rank)
        {
            compare = -1;
        }
        if(this.rank == card2.rank)
        {
            compare = 0;
        }
        return compare;
    }
}
