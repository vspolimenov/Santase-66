/**
 * 
 */
package com.java.x8academy.deck;

import static org.junit.Assert.*;

import org.junit.Test;

import com.java.cpc.deck.Card;
import com.java.cpc.deck.Rank;
import com.java.cpc.deck.Suit;
import com.java.cpc.rules.Trump;

/**
 * @author Vasilen Polimenov
 *
 */
public class TestCard {

	@Test
	public void testGetScoreAce() {
		Announce trump = new Announce(Trump.Hearts, Modifier.Normal);
		Card card = new Card(Rank.Ace, Suit.Clubs);

		assertEquals("Ace must be  11 points! ", 11, card.getScoce(trump));
	}

	@Test
	public void testGetScoreJackOnTrump() {
		Announce trump = new Announce(Trump.Hearts, Modifier.Normal);
		Card card = new Card(Rank.Jack, Suit.Hearts);

		assertEquals("Jack must be  20 points on trump! ", 20, card.getScoce(trump));
	}

	@Test
	public void testGetScoreJackNotOnTrump() {
		Announce trump = new Announce(Trump.Hearts, Modifier.Normal);
		Card card = new Card(Rank.Jack, Suit.Clubs);

		assertEquals("Jack must be  2 points not on trump! ", 2, card.getScoce(trump));
	}

	@Test
	public void testGetScoreNineAllTrumps() {
		Announce trump = new Announce(Trump.AllTrumps, Modifier.Normal);
		Card card = new Card(Rank.Nine, Suit.Hearts);

		assertEquals("Nine must be  18 points on all trump! ", 14, card.getScoce(trump));
	}

	@Test
	public void testGetScoreNineNoTrumps() {
		Announce trump = new Announce(Trump.NoTrump, Modifier.Normal);
		Card card = new Card(Rank.Nine, Suit.Hearts);

		assertEquals("Nine must be  0 points on no trump! ", 0, card.getScoce(trump));
	}

	@Test
	public void testGetScoreKingNoTrumpsContra() {
		Announce trump = new Announce(Trump.NoTrump, Modifier.Contra);
		Card card = new Card(Rank.King, Suit.Hearts);

		assertEquals("King must be  8 points on no trump with Contra! ", 8, card.getScoce(trump));
	}
	
	@Test
	public void testGetScoreQueenAllTrumpsReContra() {
		Announce trump = new Announce(Trump.AllTrumps, Modifier.ReContra);
		Card card = new Card(Rank.King, Suit.Hearts);

		assertEquals("King must be 16  points on no trump with ReContra! ", 16, card.getScoce(trump));
	}
	
	

}
