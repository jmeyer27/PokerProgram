package com.example.pokerprogram;

import java.util.ArrayList;
import java.util.*;
import java.lang.String;

public class Hand {

    public static final int NO_PAIR= 0;
    public static final int ONE_PAIR = 1;
    public static final int TWO_PAIR = 2;
    public static final int THREE_OF_A_KIND = 3;
    public static final int STRAIGHT = 4;
    public static final int FLUSH = 5;
    public static final int FULL_HOUSE = 6;
    public static final int FOUR_OF_A_KIND = 7;
    public static final int STRAIGHT_FLUSH = 8;
    public static final int ROYAL_FLUSH = 9;

    private ArrayList<Card> cards;

    /**
     * Constructor for objects of class Hand
     */

    public Hand(Card first, Card second, Card third, Card fourth, Card fifth)
    {
        cards=new ArrayList<Card>();
        cards.add(first);
        cards.add(second);
        cards.add(third);
        cards.add(fourth);
        cards.add(fifth);
    }

    /**
     * Checks if there are no pairs in the hand of cards
     * @return boolean true if the cards have no pairs, false if there is a pair in the cards
     */
    private boolean noPair()
    {
        for (int i = 0; i < cards.size(); i++)
        {
            for (int j = i+1; j < cards.size(); j++)//(also i'm worried if i is cards.size-1 then j would be cards.size would that be an overflow exception?)
            {
                String rank1 = cards.get(i).getRank();
                String rankNext2 =cards.get(j).getRank();
                String suit1 = cards.get(i).getSuit();
                String suitNext2 = cards.get(j).getSuit();

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

    private boolean onePair()
    {
        int count = 0;
        for (int i = 0; i < cards.size(); i++)
        {
            for (int j = 0; j < cards.size(); j++) {
                if (cards.get(i).getRank().equals(cards.get(j).getRank()) && (i != j))
                    count++;
            }

        }
        return count == 1;
    }

    private boolean twoPair()
    {
        int count = 0;
        for(int i = 0; i < cards.size(); i++)
        {
            if(cards.get(i).getRank().equals(cards.get(i + 1).getRank()))
            {
                count++;
            }
        }
        return count == 2;
    }

    private boolean threeOfAKind()
    {
        int count = 0;

        for(int i = 0; i < cards.size(); i++)
        {
            for(int j = 0; j < cards.size(); j++)
            {
                if((cards.get(i).getRank().equals(cards.get(j).getRank())) && (i !=j))
                {
                    count++;
                }
            }
        }
        return count == 3;
    }

    private boolean fourOfAKind()
    {
        int count = 0;

        for(int i = 0; i < cards.size(); i++)
        {
            for(int j = 0; j < cards.size(); j++)
            {
                if((cards.get(i).getRank().equals(cards.get(j).getRank())) && (i != j))
                {
                    count++;
                }
            }
        }
        return count == 4;
    }


    private boolean flush()
    {
        //all cards are same suit
        for(int i = 0; i < cards.size()-1; i++)
        {
            if(!cards.get(i).getSuit().equals(cards.get(i + 1).getSuit()))
            {
                return false;
            }
        }
        return true;
    }

    private boolean fullHouse()
    {
        //has both a pair and a triple
        return onePair() && threeOfAKind();
    }

}
