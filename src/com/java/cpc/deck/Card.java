/**
 * 
 */
package com.java.cpc.deck;

/**
 * @author Vasilen Polimenov
 *
 */
public class Card {

	private Rank rank = null;
	private Suit suit = null;

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public Card() {
		this.rank = Rank.Ace;
		this.suit = Suit.Clubs;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	private int compareRanks(Card card) {
		return this.getRank().compareTo(card.rank);
	}

	public int compareTo(Card card, Suit trump) {
		int result = 0;

		if (this.getSuit().equals(card.getSuit())) {
			result = this.compareRanks(card);
		} else if (card.getSuit().equals(trump)) {
			result = -1;
		} else if (this.getSuit().equals(trump)) {
			result = 1;
		}

		return result;
	}

	@Override
	public boolean equals(Object o) {
		Card card = (Card) o;
		boolean isEquals = false;
		if (this.getRank() == card.getRank() && this.getSuit() == card.getSuit()) {
			isEquals = true;
		}

		return isEquals;

	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + rank.hashCode();
		result = 31 * result + suit.hashCode();
		return result;
	}

}
