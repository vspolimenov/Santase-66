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

	private List<Card> deck;
	private int dealed;

	public Deck() {
		this.dealed = 0;
		this.deck = new ArrayList<Card>();
		this.fillDeck();
	}

	private void fillDeck() {
		fillOneSuit(Suit.Clubs);
		fillOneSuit(Suit.Dyamonds);
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

	public List<Card> dealOneCard() {
		List<Card> dealedCards = new ArrayList<Card>();
		dealedCards.add(deck.get(dealed));

		dealed++;
		return dealedCards;
	}
	
	public boolean isEmpty(){
		boolean isEmpty = true;
		if(dealed < 24) {
			isEmpty = false ;
		}
		
		return isEmpty;
	}
	
	@Override
	public boolean equals(Object o) {
		Deck deck = (Deck) o;
		boolean isEquals = false;
		if (this.deck.equals(deck)) {
			isEquals = true;
		}

		return isEquals;

	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + deck.hashCode();
		return result;
	}
}
