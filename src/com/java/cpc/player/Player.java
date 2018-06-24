/**
 * 
 */
package com.java.cpc.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.java.cpc.deck.Card;
import com.java.cpc.deck.Suit;
import com.java.cpc.rules.SpecialAnnounce;

/**
 * @author Vasilen Polimenov
 *
 */
public abstract class Player {

	protected List<Card> cardsInHand = new ArrayList<>();
	protected Card lastGiven = new Card();
	protected int points = 0;
	protected int roundPoints = 0;
	private List<SpecialAnnounce> specialAnounces = new ArrayList<>();
	public Player(String name) {
		this.name = name;
	}
	
	public List<Card> getCardsInHand() {
		return this.cardsInHand;
	}
	
	public void fillHand(List<Card> cards) {
		for (Card card : cards) {
			cardsInHand.add(card);
		}
	}

	public String name = null;
	public String team = null;
	
	public boolean hasCurrenSuit (Suit suit) {
		boolean hasCurrenSuit = false;
		for(Card card: cardsInHand) {
			if(card.getSuit().equals(suit)) {
				hasCurrenSuit =  true;
			}
		}
		return hasCurrenSuit;	
	}
	
	public List<Card> sort(List<Card> cardsOnTable) {

		Collections.sort(cardsOnTable, new Comparator<Card>() {
			@Override
			public int compare(Card one, Card two) {

				if (one.getSuit().compareTo(two.getSuit()) == 0) {
					return one.getRank().compareTo(two.getRank());
				}
				return one.getSuit().compareTo(two.getSuit());

			}
		});
		return cardsOnTable;
	}

	protected void checkForSpecialAnnounces() {

	};
	public Card getLastGiven() {
		return lastGiven;
}
	public void calculateRoundScore(int roundPoints){
		this.roundPoints += roundPoints;
	}
	
	public int getRoundScore() {
		return this.roundPoints;
	}
	public void calculateScore(int points){
		this.points += points;
	}
	
	public int getScore() {
		return this.points;
	}
	public abstract Card chooseCard();

}