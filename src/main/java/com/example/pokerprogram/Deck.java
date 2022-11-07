package com.example.pokerprogram;

import java.util.ArrayList;
import java.util.Collections;
public class Deck {
    private final int deckSize = 52;

    ArrayList<Card> deck = new ArrayList<Card>();
    private Card[] card;
    public void createDeck()
    {
        int cardNo = 0;

        for (int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 13; j++)
            {
                deck.add(card[cardNo] = new Card(i, j));
                cardNo++;
            }
        }
    }

    public void shuffle()
    {
        Collections.shuffle(deck);
    }

}
