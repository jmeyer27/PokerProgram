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
    public void onePair() {
        Player test = new Player();
        Card card = new Card(1, 1);
        test.addCard(card);
        test.addCard(card);

        assertTrue(test.onePair());
    }

    @Test
    public void noPair() {
    }

    @Test
    public void twoPair() {
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