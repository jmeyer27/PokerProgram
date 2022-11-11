package com.example.pokerprogram;

import javafx.scene.image.Image;

public class Card {

//    private final Enum<Card.Rank> Rank;
//    private final Enum<Card.Suit> Suit;
    private Image cardImage;
    private int rank;
    private int suit;

    //if you want to implement Suit and Rank differently please feel free to do whatever and delete what I have here
    //~~~~~~~~~~thanks~~~~~~~~~~

//    //set up enums
//    public enum Suit{
//        HEARTS, CLUBS, DIAMONDS, SPADES
//    };

//    public enum Rank{
//        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6),
//        SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11),
//        QUEEN(12), KING(13), ACE(1);
//
//        final int value;
//        private Rank(int value){
//            this.value = value;
//        }
//
//    }


     //constructor for Card class
    public Card(int newRank, int newSuit)
    {
        rank = newRank;
        suit = newSuit;
        String imageRank = getRank();
        String imageSuit = getSuit();
        String fileName = imageRank + "_of_" +imageSuit + ".png";
        //todo test if this actually returns image (lol no it is broken)
        //cardImage = new Image("/images/pokerCardImages/"+fileName);

    }

    //todo test this method
    private String getSuit() {
       return switch(this.suit){
           case 1 -> "hearts";
           case 2 -> "clubs";
           case 3 -> "diamonds";
           case 4-> "spades";
           default -> "error"; //will cause error hopefully
       };
    }

    //todo test this method
    private String getRank() {
        return switch (this.rank) {
            case 11 -> "jack";
            case 12 -> "queen";
            case 13 -> "king";
            default -> String.valueOf(this.rank);
        };
    }


}
