package com.java.x8academy.deck;

import static org.junit.Assert.*;

import org.junit.Test;

import com.java.cpc.deck.Deck;

public class TestDeck {
	
	@Test
	public void testDealFirstCards() {
		Deck deck = new Deck();
		
		assertEquals("Must be 5 cards dealed!", 5,deck.dealFirstCards().size());
	}
	
	@Test
	public void testDealLastCards() {
		Deck deck = new Deck();
		
		assertEquals("Must be 3 cards dealed!", 3,deck.dealThreeCards().size());
	}
	
}
