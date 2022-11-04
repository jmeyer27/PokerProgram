package com.example.pokerprogram;

public class Card {


    //todo someone can finish the class :)
    //if you want to implement Suit and Rank differently please feel free to do whatever and delete what I have here
    //~~~~~~~~~~thanks~~~~~~~~~~

    //set up enums
    enum Suit{
        HEARTS, CLUBS, DIAMONDS, SPADES
    };

    enum Rank{
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6),
        SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11),
        QUEEN(12), KING(13), ACE(1);

        final int value;
        private Rank(int value){
            this.value = value;
        };







    }









}
