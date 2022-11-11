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
        for (int suit = 1; suit <= 4; suit++) {
            for(int rank = 1; rank <= 13; rank++)
            {
                deck.add(new Card(rank,suit));
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


