/**
 * 
 */
package com.java.cpc.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Vasilen Polimenov
 *
 */
public class Deck {

	private List<Card> deck = null;
	private int dealed = 0;

	public Deck() {

		this.deck = new ArrayList<Card>();
		this.fillDeck();
	}

	private void fillDeck() {
		fillOneSuit(Suit.Clubs);
		fillOneSuit(Suit.Dyamonds);
		fillOneSuit(Suit.Clubs);
		fillOneSuit(Suit.Hearts);
		fillOneSuit(Suit.Spade);

		shuffle();
	}

	private void fillOneSuit(Suit suit) {
		deck.add(new Card(Rank.Nine, suit));
		deck.add(new Card(Rank.Ten, suit));
		deck.add(new Card(Rank.Jack, suit));
		deck.add(new Card(Rank.Queen, suit));
		deck.add(new Card(Rank.King, suit));
		deck.add(new Card(Rank.Ace, suit));
	}

	private void shuffle() {
		Collections.shuffle(deck);
	}


	public List<Card> dealThreeCards() {
		List<Card> dealedCards = new ArrayList<Card>();

		for (int i = dealed; i < dealed + 3; i++) {
			dealedCards.add(deck.get(i));
		}

		dealed += 3;
		return dealedCards;
	}
}
