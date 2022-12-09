package com.example.pokerprogram;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.testng.Assert.assertTrue;

public class TestPlayer {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onePair_True() {
        Player test = new Player();
        Card card1 = new Card(1, 1);
        Card card2 = new Card(1, 2);
        test.addCard(card1);
        test.addCard(card2);

        assertTrue(test.onePair());
    }

    @Test
    public void onePair_False() {
        Player test = new Player();
        Card card1 = new Card(1, 1);
        Card card2 = new Card(2, 1);
        test.addCard(card1);
        test.addCard(card2);

        assertFalse(test.onePair());
    }
    @Test
    public void onePair_False_MorePair() {
        Player test = new Player();
        Card card1 = new Card(1, 1);
        Card card2 = new Card(2, 1);
        Card card3 = new Card(1, 4);
        Card card4 = new Card(1, 2);
        Card card5 = new Card(5, 1);
        test.addCard(card1);
        test.addCard(card2);
        test.addCard(card3);
        test.addCard(card4);
        test.addCard(card5);

        assertFalse(test.onePair());
    }

    @Test
    public void noPair_True() {
        Player test = new Player();
        Card card1 = new Card(1, 1);
        Card card2 = new Card(2, 1);
        Card card3 = new Card(3, 1);
        Card card4 = new Card(4, 1);
        Card card5 = new Card(5, 1);
        test.addCard(card1);
        test.addCard(card2);
        test.addCard(card3);
        test.addCard(card4);
        test.addCard(card5);

        assertTrue(test.noPair());
    }

    @Test
    public void noPair_False() {
        Player test = new Player();
        Card card1 = new Card(1, 1);
        Card card2 = new Card(2, 1);
        Card card3 = new Card(3, 1);
        Card card4 = new Card(4, 1);
        Card card5 = new Card(1, 2);
        test.addCard(card1);
        test.addCard(card2);
        test.addCard(card3);
        test.addCard(card4);
        test.addCard(card5);

        assertFalse(test.noPair());
    }

    @Test
    public void twoPair_True() {
        Player test = new Player();
        Card card1 = new Card(1, 1);
        Card card2 = new Card(2, 1);
        Card card3 = new Card(1, 2);
        Card card4 = new Card(2, 2);
        Card card5 = new Card(4, 4);
        test.addCard(card1);
        test.addCard(card2);
        test.addCard(card3);
        test.addCard(card4);
        test.addCard(card5);

        assertTrue(test.twoPair());
    }

    @Test
    public void twoPair_False() {
        Player test = new Player();
        Card card1 = new Card(1, 1);
        Card card2 = new Card(2, 1);
        Card card3 = new Card(3, 1);
        Card card4 = new Card(4, 1);
        Card card5 = new Card(1, 2);
        test.addCard(card1);
        test.addCard(card2);
        test.addCard(card3);
        test.addCard(card4);
        test.addCard(card5);

        assertFalse(test.twoPair());
    }
    @Test
    public void twoPair_False_ThreeOfAKind() {
        Player test = new Player();
        Card card1 = new Card(1, 1);
        Card card2 = new Card(2, 1);
        Card card3 = new Card(3, 1);
        Card card4 = new Card(1, 3);
        Card card5 = new Card(1, 2);
        test.addCard(card1);
        test.addCard(card2);
        test.addCard(card3);
        test.addCard(card4);
        test.addCard(card5);

        assertFalse(test.twoPair());
    }


    @Test
    public void threeOfAKind_True() {
        Player test = new Player();
        Card card1 = new Card(1, 1);
        Card card2 = new Card(2, 1);
        Card card3 = new Card(3, 1);
        Card card4 = new Card(1, 3);
        Card card5 = new Card(1, 2);
        test.addCard(card1);
        test.addCard(card2);
        test.addCard(card3);
        test.addCard(card4);
        test.addCard(card5);

        assertTrue(test.threeOfAKind());
    }

    @Test
    public void threeOfAKind_False() {
        Player test = new Player();
        Card card1 = new Card(1, 1);
        Card card2 = new Card(2, 1);
        Card card3 = new Card(3, 1);
        Card card4 = new Card(5, 3);
        Card card5 = new Card(1, 2);
        test.addCard(card1);
        test.addCard(card2);
        test.addCard(card3);
        test.addCard(card4);
        test.addCard(card5);

        assertFalse(test.threeOfAKind());
    }

    @Test
    public void fourOfAKind_True() {
        Player test = new Player();
        Card card1 = new Card(1, 1);
        Card card2 = new Card(2, 1);
        Card card3 = new Card(1, 4);
        Card card4 = new Card(1, 3);
        Card card5 = new Card(1, 2);
        test.addCard(card1);
        test.addCard(card2);
        test.addCard(card3);
        test.addCard(card4);
        test.addCard(card5);

        assertTrue(test.fourOfAKind());
    }
    @Test
    public void fourOfAKind_False() {
        Player test = new Player();
        Card card1 = new Card(1, 1);
        Card card2 = new Card(2, 1);
        Card card3 = new Card(1, 4);
        Card card4 = new Card(6, 3);
        Card card5 = new Card(1, 2);
        test.addCard(card1);
        test.addCard(card2);
        test.addCard(card3);
        test.addCard(card4);
        test.addCard(card5);

        assertFalse(test.fourOfAKind());
    }
    @Test
    public void fourOfAKind_False_TwoPairs() {
        Player test = new Player();
        Card card1 = new Card(1, 1);
        Card card2 = new Card(2, 1);
        Card card3 = new Card(6, 4);
        Card card4 = new Card(6, 3);
        Card card5 = new Card(1, 2);
        test.addCard(card1);
        test.addCard(card2);
        test.addCard(card3);
        test.addCard(card4);
        test.addCard(card5);

        assertFalse(test.fourOfAKind());
    }

    @Test
    public void flush_True() {
        Player test = new Player();
        Card card1 = new Card(1, 1);
        Card card2 = new Card(2, 1);
        Card card3 = new Card(3, 1);
        Card card4 = new Card(4, 1);
        Card card5 = new Card(5, 1);
        test.addCard(card1);
        test.addCard(card2);
        test.addCard(card3);
        test.addCard(card4);
        test.addCard(card5);

        assertTrue(test.flush());
    }

    @Test
    public void flush_False() {
        Player test = new Player();
        Card card1 = new Card(1, 1);
        Card card2 = new Card(2, 2);
        Card card3 = new Card(3, 3);
        Card card4 = new Card(4, 4);
        Card card5 = new Card(5, 1);
        test.addCard(card1);
        test.addCard(card2);
        test.addCard(card3);
        test.addCard(card4);
        test.addCard(card5);

        assertFalse(test.flush());
    }

    @Test
    public void fullHouse_True() {
        Player test = new Player();
        Card card1 = new Card(1, 1);
        Card card2 = new Card(2, 1);
        Card card3 = new Card(2, 4);
        Card card4 = new Card(1, 3);
        Card card5 = new Card(1, 2);
        test.addCard(card1);
        test.addCard(card2);
        test.addCard(card3);
        test.addCard(card4);
        test.addCard(card5);

        assertTrue(test.fullHouse());
    }

    @Test
    public void fullHouse_False() {
        Player test = new Player();
        Card card1 = new Card(1, 1);
        Card card2 = new Card(2, 1);
        Card card3 = new Card(1, 4);
        Card card4 = new Card(6, 3);
        Card card5 = new Card(1, 2);
        test.addCard(card1);
        test.addCard(card2);
        test.addCard(card3);
        test.addCard(card4);
        test.addCard(card5);

        assertFalse(test.fullHouse());
    }

    @Test
    public void straight() {
        Player test = new Player();
        Card card1 = new Card(2, 1);
        Card card2 = new Card(3, 1);
        Card card3 = new Card(4, 4);
        Card card4 = new Card(5, 3);
        Card card5 = new Card(6, 2);
        test.addCard(card1);
        test.addCard(card2);
        test.addCard(card3);
        test.addCard(card4);
        test.addCard(card5);

        assertTrue(test.straight());
    }

    @Test
    public void straight_unOrdered() {
        Player test = new Player();
        Card card1 = new Card(5, 1);
        Card card2 = new Card(8, 1);
        Card card3 = new Card(6, 4);
        Card card4 = new Card(4, 3);
        Card card5 = new Card(7, 2);
        test.addCard(card1);
        test.addCard(card2);
        test.addCard(card3);
        test.addCard(card4);
        test.addCard(card5);

        assertTrue(test.straight());
    }

    @Test
    public void straightFlush() {
        Player test = new Player();
        Card card1 = new Card(9, 1);
        Card card2 = new Card(10, 1);
        Card card3 = new Card(11, 1);
        Card card4 = new Card(12, 1);
        Card card5 = new Card(13, 1);
        test.addCard(card1);
        test.addCard(card2);
        test.addCard(card3);
        test.addCard(card4);
        test.addCard(card5);

        assertTrue(test.straightFlush());
    }


    @Test
    public void royalFlush_orderedTrue() {
        Player test = new Player();
        Card card1 = new Card(10, 1);
        Card card2 = new Card(11, 1);
        Card card3 = new Card(12, 1);
        Card card4 = new Card(13, 1);
        Card card5 = new Card(1, 1);
        test.addCard(card1);
        test.addCard(card2);
        test.addCard(card3);
        test.addCard(card4);
        test.addCard(card5);

        assertTrue(test.royalFlush());
    }

    @Test
    public void royalFlush_unOrderedTrue() {
        Player test = new Player();
        Card card1 = new Card(13, 1);
        Card card2 = new Card(10, 1);
        Card card3 = new Card(11, 1);
        Card card4 = new Card(1, 1);
        Card card5 = new Card(12, 1);
        test.addCard(card1);
        test.addCard(card2);
        test.addCard(card3);
        test.addCard(card4);
        test.addCard(card5);

        assertTrue(test.royalFlush());
    }
}