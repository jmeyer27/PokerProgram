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
    }

    @Test
    public void threeOfAKind() {
    }

    @Test
    public void fourOfAKind() {
    }

    @Test
    public void flush() {
    }

    @Test
    public void fullHouse() {
    }

    @Test
    public void straight() {
    }

    @Test
    public void straightFlush() {
    }

    @Test
    public void royalFlush() {
    }
}