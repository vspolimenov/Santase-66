/**
 * 
 */
package com.java.cpc.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.java.cpc.deck.Card;
import com.java.cpc.deck.Rank;
import com.java.cpc.deck.Suit;
import com.java.cpc.io.ConsoleIOManager;
import com.java.cpc.rules.SpecialAnnounce;

/**
 * @author Vasilen Polimenov
 *
 */
public abstract class Player {

	protected List<Card> cardsInHand = new ArrayList<>();
	protected Card lastGiven = new Card();
	protected int points;
	protected int roundPoints;
	private String name = null;
	private String team = null;
	
	public Player(String name) {
		points = 0;
		roundPoints = 0;
		this.setName(name);
	}
	
	public List<Card> getCardsInHand() {
		return this.cardsInHand;
	}
	
	public void fillHand(List<Card> cards) {
		for (Card card : cards) {
			cardsInHand.add(card);
		}
	}


	
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


	protected void makeSpecialAnnounce(Card card, Suit trump) {
		boolean hasKing = card.getRank().equals(Rank.Queen) && cardsInHand.contains(new Card(Rank.King,card.getSuit()));
		boolean hasQueen = card.getRank().equals(Rank.King) && cardsInHand.contains(new Card(Rank.Queen,card.getSuit()));
		
		if(hasKing || hasQueen) {
			if(card.getSuit().equals(trump)) {
				ConsoleIOManager.printFourtyAnnounce();
				this.points += SpecialAnnounce.Fourty.points;
			} else {
				ConsoleIOManager.printTwentyAnnounce();
				this.points += SpecialAnnounce.Twenty.points;
			}
		}

	}
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
	public abstract Card chooseCard(Suit trump);
	
	public void removeCardFromHands(Card card) {
		cardsInHand.remove(card);
	}
	
	public void setStartingScoreForRound() {
		this.roundPoints = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	@Override
	public boolean equals(Object o) {
		Player player = (Player) o;
		boolean isEquals = false;
		if (this.getName() == player.getName() && this.getCardsInHand() == player.getCardsInHand()) {
			isEquals = true;
		}

		return isEquals;

	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + name.hashCode();
		result = 31 * result + cardsInHand.hashCode();
		return result;
	}
}