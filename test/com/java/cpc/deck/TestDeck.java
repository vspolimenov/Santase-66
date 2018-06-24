package com.java.cpc.deck;

import static org.junit.Assert.*;

import org.junit.Test;

import com.java.cpc.deck.Deck;

public class TestDeck {
	
	@Test
	public void testDealFirstCards() {
		Deck deck = new Deck();
		
		assertEquals("Must be 1 card dealed!", 1,deck.dealOneCard().size());
	}
	
	@Test
	public void testDealLastCards() {
		Deck deck = new Deck();
		
		assertEquals("Must be 3 cards dealed!", 3,deck.dealThreeCards().size());
	}
	
}
