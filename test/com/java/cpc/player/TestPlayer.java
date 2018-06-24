package com.java.cpc.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.java.cpc.deck.Card;
import com.java.cpc.deck.Rank;
import com.java.cpc.deck.Suit;

public class TestPlayer {

	@Test
	public void testFillingHandTwoCards() {
		final HumanPlayer player = new HumanPlayer("Test");
		final List<Card> cards = new ArrayList<Card>();

		cards.add(new Card(Rank.Ace, Suit.Clubs));
		cards.add(new Card(Rank.Jack, Suit.Clubs));
		player.fillHand(cards);
		assertEquals("Cards must be the same!", cards, player.getCardsInHand());
	}

	@Test
	public void testChooseFirstCard() {
		final Player player = new HumanPlayer("test");
		final List<Card> cards = new ArrayList<Card>();

		cards.add(new Card(Rank.Ace, Suit.Clubs));
		cards.add(new Card(Rank.Jack, Suit.Clubs));
		player.fillHand(cards);

		final ByteArrayInputStream input = new ByteArrayInputStream("1".getBytes());
		System.setIn(input);

		assertTrue(new Card(Rank.Ace, Suit.Clubs).equals(player.chooseCard()));

	}

	@Test
	public void testChooseLastCard() {
		final Player player = new HumanPlayer("test");
		final List<Card> cards = new ArrayList<Card>();

		cards.add(new Card(Rank.Ace, Suit.Clubs));
		cards.add(new Card(Rank.Jack, Suit.Clubs));
		player.fillHand(cards);

		final ByteArrayInputStream input = new ByteArrayInputStream("2".getBytes());
		System.setIn(input);

		assertTrue(new Card(Rank.Jack, Suit.Clubs).equals(player.chooseCard()));

	}

}
