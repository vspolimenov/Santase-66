/**
 * 
 */
package com.java.cpc.deck;

import static org.junit.Assert.*;

import org.junit.Test;

import com.java.cpc.deck.Card;
import com.java.cpc.deck.Rank;
import com.java.cpc.deck.Suit;

/**
 * @author Vasilen Polimenov
 *
 */
public class TestCard {

	@Test
	public void testGetScoreAce() {
		Card card = new Card(Rank.Ace, Suit.Clubs);

		assertEquals("Ace must be  11 points! ", 11, card.getRank().points);
	}

	@Test
	public void testGetScoreJack() {
	
		Card card = new Card(Rank.Jack, Suit.Hearts);

		assertEquals("Jack must be  2 points on trump! ", 2, card.getRank().points);
	}

	@Test
	public void testGetScoreTen() {
		Card card = new Card(Rank.Ten, Suit.Clubs);

		assertEquals("Jack must be  10 points not on trump! ", 10, card.getRank().points);
	}

	@Test
	public void testGetScoreNine() {
		
		Card card = new Card(Rank.Nine, Suit.Hearts);

		assertEquals("Nine must be  0 points on all trump! ", 0, card.getRank().points);
	}

	@Test
	public void testGetScoreQueenHearts() {
	
		Card card = new Card(Rank.Queen, Suit.Hearts);

		assertEquals("Nine must be  3 points on no trump! ", 3, card.getRank().points);
	}

	@Test
	public void testGetScoreKing() {
		Card card = new Card(Rank.King, Suit.Hearts);

		assertEquals("King must be  4 points on no trump with Contra! ", 4, card.getRank().points);
	}
	
	@Test
	public void testGetScoreQueeSpade() {
	
		Card card = new Card(Rank.Queen, Suit.Spade);

		assertEquals("King must be 3  points on no trump with ReContra! ", 3, card.getRank().points);
	}
	
	

}
