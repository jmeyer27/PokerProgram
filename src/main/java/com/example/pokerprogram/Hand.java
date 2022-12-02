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
        cards=new ArrayList<>();
        cards.add(first);
        cards.add(second);
        cards.add(third);
        cards.add(fourth);
        cards.add(fifth);
    }

    private boolean noPair()
    {
        for (int i = 0; i < cards.size(); i++)
        {
            for (int j = i+1; j < cards.size(); j++)
            {
                String rank1 = cards.get(i).getRank();
                String rankNext2 =cards.get(j).getRank();
                String suit1 = cards.get(i).getSuit();
                String suitNext2 = cards.get(j).getSuit();

                if ((rank1!=rankNext2) && (suit1!=suitNext2))
                {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean onePair()
    {
        int count = 0;
        for (int i = 0; i < cards.size(); i++)
        {
            for (int j = 0; j < cards.size(); j++) {
                if ((cards.get(i).getRank()) == (cards.get(j).getRank()) && (i != j))
                    count++;
            }
            if(count == 1)
            {
                return true;
            }

        }
        return false;
    }

    private boolean twoPair()
    {
        int count = 0;
        for(int i = 0; i < cards.size(); i++)
        {
            if(cards.get(i).getRank() == cards.get(i+1).getRank())
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

    private boolean threeOfAKind()
    {
        int count = 0;

        for(int i = 0; i < cards.size(); i++)
        {
            for(int j = 0; j < cards.size(); j++)
            {
                if((cards.get(i).getRank() == cards.get(j).getRank()) && (i !=j))
                {
                    count++;
                }
            }
            if(count == 2)
            {
                return true;
            }
        }
        return false;
    }

    private boolean fourOfAKind()
    {
        int count = 0;

        for(int i = 0; i < cards.size(); i++)
        {
            for(int j = 0; j < cards.size(); j++)
            {
                if((cards.get(i).getRank() == cards.get(j).getRank()) && (i != j))
                {
                    count++;
                }
            }
            if(count == 3)
            {
                return true;
            }
        }
        return false;
    }


    private boolean flush()
    {
        //all cards are same suit
        for(int i = 0; i < cards.size()-1; i++)
        {
            if(cards.get(i).getSuit() != cards.get(i+1).getSuit())
            {
                return false;
            }
        }
        return true;
    }

    private boolean fullHouse()
    {
        //has both a pair and a triple
        if(onePair() == true && threeOfAKind() == true)
        {
            return true;
        }
        return false;
    }

}
