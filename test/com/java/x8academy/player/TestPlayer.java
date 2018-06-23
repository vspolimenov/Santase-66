package com.java.x8academy.player;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.java.cpc.deck.Card;
import com.java.cpc.deck.Rank;
import com.java.cpc.deck.Suit;
import com.java.cpc.player.HumanPlayer;
import com.java.cpc.player.Player;
import com.java.cpc.rules.Announce;
import com.java.cpc.rules.Modifier;
import com.java.cpc.rules.Trump;

public class TestPlayer {

	@Test
	public void testFillingHandTwoCards() {
		HumanPlayer player = new HumanPlayer("Test");
		List<Card> cards = new ArrayList<Card>();

		cards.add(new Card(Rank.Ace, Suit.Clubs));
		cards.add(new Card(Rank.Jack, Suit.Clubs));
		player.fillHand(cards);
		assertTrue("Cards must be the same!", cards.equals(player.getCardsInHand()));
	}

	@Test
	public void testMakeAnnounceNoTrump() {
		Player player = new HumanPlayer("test");

		assertEquals(new Announce(Trump.NoTrump, Modifier.Normal).getTrump(),
				player.createAnnounceFromAnnounceNumber(5).getTrump());
	}

	@Test
	public void testMakeAnnounceContra() {
		Announce last = new Announce(Trump.Hearts, null);
		Player player = new HumanPlayer("test");
		ByteArrayInputStream in = new ByteArrayInputStream("7".getBytes());
		System.setIn(in);

		assertEquals(new Announce(null, Modifier.Contra).getModifier(), player.makeAnnounce(last).getModifier());
	}

	@Test
	public void testMakeAnnounceReContra() {
		Announce last = new Announce(Trump.Hearts, Modifier.Contra);
		Player player = new HumanPlayer("test");
		ByteArrayInputStream in = new ByteArrayInputStream("8".getBytes());
		System.setIn(in);

		assertEquals(new Announce(null, Modifier.ReContra).getModifier(), player.makeAnnounce(last).getModifier());
	}

	@Test
	public void testChooseFirstCard() {
		Player player = new HumanPlayer("test");
		List<Card> cards = new ArrayList<Card>();

		cards.add(new Card(Rank.Ace, Suit.Clubs));
		cards.add(new Card(Rank.Jack, Suit.Clubs));
		player.fillHand(cards);

		ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
		System.setIn(in);

		assertTrue(new Card(Rank.Ace, Suit.Clubs).equals(player.chooseCard()));

	}

	@Test
	public void testChooseLastCard() {
		Player player = new HumanPlayer("test");
		List<Card> cards = new ArrayList<Card>();

		cards.add(new Card(Rank.Ace, Suit.Clubs));
		cards.add(new Card(Rank.Jack, Suit.Clubs));
		player.fillHand(cards);

		ByteArrayInputStream in = new ByteArrayInputStream("2".getBytes());
		System.setIn(in);

		assertTrue(new Card(Rank.Jack, Suit.Clubs).equals(player.chooseCard()));

	}

}
