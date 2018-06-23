/**
 * 
 */
package com.java.cpc.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.java.cpc.GameConstants;
import com.java.cpc.deck.Card;
import com.java.cpc.deck.Deck;
import com.java.cpc.deck.Suit;
import com.java.cpc.player.ComputerPlayer;
import com.java.cpc.player.HumanPlayer;
import com.java.cpc.player.Player;
import com.java.cpc.player.Team;


/**
 * @author Vasilen Polimenov
 *
 */

public class GameCycle {

	static private Team firstTeam = new Team(new HumanPlayer("Player One"), new ComputerPlayer("Player Two"), "Team 1");
	static private Team secondTeam = new Team(new ComputerPlayer("Player Three"), new ComputerPlayer("Player Four"), "Team 2");

	static List<Player> players = new ArrayList<Player>();

	static private Suit trump ;
	static private String playerOnHand = firstTeam.firstPlayer.name;
	static private String winningTeamName = firstTeam.name;
	static private Deck deck = new Deck();

	public static void startGame() {

		players.add(firstTeam.firstPlayer);
		players.add(secondTeam.secondPlayer);
		
		while (firstTeam.getScore() < 151 || secondTeam.getScore() < 151) {
			playRound();
		}
	}

	static private void playRound() {

		for (int i = 0; i < 2; i++) {
			players.get(i).fillHand(deck.dealThreeCards());
		}
		Random r = new Random();
		trump.id = (r.nextInt((GameConstants.SUIT_COUNT - GameConstants.SUIT_MIN_COUNT) + 1) + GameConstants.SUIT_MIN_COUNT - 1);
		System.out.println(trump);

		for (int i = 0; i < 2; i++) {
			players.get(i).fillHand(deck.dealThreeCards());
		}

		for (int i = 0; i < 8; i++) {
			System.out.println("Round " + (i +1));
			playOneTurn();
		}

		calculateRoundScore();

	}

	static private List<Card> sort(List<Card> cardsOnTable) {

		Collections.sort(cardsOnTable, new Comparator<Card>() {
			@Override
			public int compare(Card one, Card two) {
				Trump currentTrump = trump.getTrump();
				String currentTrumpName = currentTrump.toString();

				if (currentTrump == Trump.AllTrumps || currentTrump == Trump.NoTrump) {
					currentTrumpName = cardsOnTable.get(0).getSuit().toString();
				}

				String oneSuit = one.getSuit().toString();
				String twoSuit = two.getSuit().toString();

				if (oneSuit.equals(currentTrumpName) && twoSuit.equals(currentTrumpName) == false) {
					return -1;
				} else if (oneSuit.equals(currentTrumpName) == false && twoSuit.equals(currentTrumpName)) {
					return 1;
				} else {
					if (one.getScoce(trump) > two.getScoce(trump)) {
						return -1;
					} else {
						return 1;
					}
				}
			}
		});
		return cardsOnTable;

	}

	static private void calculateRoundScore() {
		firstTeam.calculateScore();
		secondTeam.calculateScore();
	}

	static private void calculateTurnScore(List<Card> playedCards) {

		int score = 0;
		for (Card card : playedCards) {
			score += card.getScoce(trump);
		}

		if (winningTeamName.equals(firstTeam.name)) {
			firstTeam.updateLocalScore(score);
		} else {
			secondTeam.updateLocalScore(score);
		}
	}


	static private void playOneTurn() {
		List<Card> playedCards = new ArrayList<Card>();

		int numberOfPlayerOnHand = 0;

		for (int i = 0; i < 4; i++) {
			if (playerOnHand.equals(players.get(i).name)) {
				numberOfPlayerOnHand = i;
			}
		}

		for (int i = 0; i < 4; i++) {

			if (numberOfPlayerOnHand == 4) {
				numberOfPlayerOnHand = 0;
			}
			
			Card choosen = players.get(numberOfPlayerOnHand).chooseCard(players.get(numberOfPlayerOnHand).getCardsInHand().size());

			if(playedCards.size() != 0) {
				boolean isValid = true;

				for (int j = 0; j < players.get(numberOfPlayerOnHand).getCardsInHand().size(); j++) {
					if (isValid(players.get(numberOfPlayerOnHand).getCardsInHand().get(j), playedCards)) {
						isValid = false;
					}
				}
				if (isValid == false) {
					while (isValid(choosen, playedCards) == false) {
						choosen = players.get(numberOfPlayerOnHand).chooseCard(players.get(numberOfPlayerOnHand).getCardsInHand().size());
					}
				}
			}
			numberOfPlayerOnHand++;
			playedCards.add(choosen);
		}

		playerOnHand = getWinnerName(playedCards);
		System.out.println(playerOnHand);
		calculateTurnScore(playedCards);
	}

	private static boolean isValid(Card choosen, List<Card> playedCards) {

		Card firstPlayed = playedCards.get(0);
		playedCards = sort(playedCards);
		boolean isValid = false;

		boolean isValidOnTrumps = choosen.getSuit().equals(trump.getTrump())
				&& choosen.compareTo(playedCards.get(playedCards.size() - 1), trump) == 1;
		boolean isValidOnNoTrump = trump.getTrump().equals(Trump.NoTrump)
				&& firstPlayed.getSuit().equals(choosen.getSuit());

		if (isValidOnTrumps || isValidOnNoTrump) {
			isValid = true;
		}

		return isValid;
	}

	static private String getWinnerName(List<Card> playedCards) {

		playedCards = sort(playedCards);
		String playerOnHand = null;

		for (int i = 0; i < 4; i++) {
			if (players.get(i).getLastGiven().equals(playedCards.get(3))) {
				playerOnHand = players.get(i).name;
				if ((i + 1) % 2 == 0) {
					winningTeamName = firstTeam.name;
				} else {
					winningTeamName = secondTeam.name;
				}
				break;
			}
		}

		return playerOnHand;
	}
}
