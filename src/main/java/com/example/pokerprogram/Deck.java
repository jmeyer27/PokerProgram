package com.example.pokerprogram;

import java.util.ArrayList;
import java.util.Collections;
public class Deck {

    ArrayList<Card> deck = new ArrayList<Card>();

    private Card[] card;

    /**
     * Constructor of Deck class
     */
    public Deck() {
        createDeck();
    }

    public void createDeck()
    {
        for (int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 13; j++)
            {
                deck.add(new Card(i,j));
            }
        }
    }

    public void shuffle()
    {
        Collections.shuffle(deck);
    }

    public void deal(){

    }

}


