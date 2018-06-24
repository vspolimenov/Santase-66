/**
 * 
 */
package com.java.cpc.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.java.cpc.GameConstants;
import com.java.cpc.deck.Card;
import com.java.cpc.deck.Deck;
import com.java.cpc.deck.Suit;
import com.java.cpc.io.ConsoleIOManager;
import com.java.cpc.player.HumanPlayer;
import com.java.cpc.player.Player;

/**
 * @author Vasilen Polimenov
 *
 */

public class GameCycle {

	static Player firstPlayer = new HumanPlayer("Player One");
	static Player secondPlayer = new HumanPlayer("Player Two");
	static private boolean hasWinner = false;

	static private Suit trump;
	static private boolean isFirstPlayerOnHand = true;
	static private Deck deck;

	public static void startGame() {

		while (firstPlayer.getScore() < 11 || secondPlayer.getScore() < 11) {
			deck = new Deck();
			firstPlayer.setStartingScoreForRound();
			secondPlayer.setStartingScoreForRound();
			ConsoleIOManager.printStartMessage();
			ConsoleIOManager.printScore(firstPlayer.getScore(), secondPlayer.getScore());
			playRound();
		}
	}

	static private void fillPlayersHands() {

		for (int i = 0; i < 2; i++) {
			firstPlayer.fillHand(deck.dealThreeCards());
			secondPlayer.fillHand(deck.dealThreeCards());
		}
	}

	static private void getOneCardFromDeck() {
		firstPlayer.fillHand(deck.dealOneCard());
		secondPlayer.fillHand(deck.dealOneCard());
	}
	
	static private void playRound() {

		Random r = new Random();
		trump = Suit.Clubs;
		trump = Suit.values()[(r.nextInt((GameConstants.SUIT_COUNT - GameConstants.SUIT_MIN_COUNT) + 1)
				+ GameConstants.SUIT_MIN_COUNT - 1)];
		ConsoleIOManager.printTrump (trump);

		fillPlayersHands();
		int round = 1;
		while (!deck.isEmpty() && !hasWinner) {
			ConsoleIOManager.printRoundMessage(round++);
			playOneTurn();
			getOneCardFromDeck();
		}

		while (!firstPlayer.getCardsInHand().isEmpty() && !hasWinner) {
			playOneTurnFinnal();
		}

	}

	static private void checkForWinner(Player player) {
		if (player.getRoundScore() >= 66) {
			ConsoleIOManager.printWinner(player);
			if (player.getRoundScore() >= 33) {
				player.calculateScore(1);
			} else if (player.getRoundScore() > 0) {
				player.calculateScore(2);
			} else {
				player.calculateScore(3);
			}
			hasWinner = true;
		} else {
			hasWinner = false;
		}
	}

	static private void playOneTurn() {
		List<Card> playedCards = new ArrayList<Card>();
		Card firstRoundPlayerHand = new Card();
		Card secondRoundPlayerHand = new Card();

		if (isFirstPlayerOnHand) {
			checkForWinner(firstPlayer);
			firstRoundPlayerHand = firstPlayer.chooseCard(trump);
			firstPlayer.removeCardFromHands(firstRoundPlayerHand);
			playedCards.add(firstRoundPlayerHand);
			secondRoundPlayerHand = secondPlayer.chooseCard(trump);
			secondPlayer.removeCardFromHands(secondRoundPlayerHand);
			playedCards.add(secondRoundPlayerHand);
		} else {
			checkForWinner(secondPlayer);
			firstRoundPlayerHand = secondPlayer.chooseCard(trump);
			secondPlayer.removeCardFromHands(firstRoundPlayerHand);
			playedCards.add(firstRoundPlayerHand);
			secondRoundPlayerHand = firstPlayer.chooseCard(trump);
			firstPlayer.removeCardFromHands(secondRoundPlayerHand);
			playedCards.add(secondRoundPlayerHand);
		}

		isFirstPlayerOnHand = isFirstPlayerOnHand(playedCards);
		if (isFirstPlayerOnHand) {
			ConsoleIOManager.printOnHandPlayer(firstPlayer.getName());
		} else {
			ConsoleIOManager.printOnHandPlayer(secondPlayer.getName());
		}

		calculateRoundScore(playedCards);

	}

	static private void playOneTurnFinnal() {
		List<Card> playedCards = new ArrayList<Card>();
		Card firstRoundPlayerHand = new Card();
		Card secondRoundPlayerHand = new Card();

		if (isFirstPlayerOnHand) {
			checkForWinner(firstPlayer);
			firstRoundPlayerHand = firstPlayer.chooseCard(trump);
			firstPlayer.removeCardFromHands(firstRoundPlayerHand);
			playedCards.add(firstRoundPlayerHand);
			secondRoundPlayerHand = secondPlayer.chooseCard(trump);
			while (!secondRoundPlayerHand.getSuit().equals(trump)
					&& (!firstRoundPlayerHand.getSuit().equals(secondRoundPlayerHand.getSuit())
							&& secondPlayer.hasCurrenSuit(firstRoundPlayerHand.getSuit()))) {
				ConsoleIOManager.printValidateCardMessage();
				secondRoundPlayerHand = secondPlayer.chooseCard(trump);
			}
			secondPlayer.removeCardFromHands(secondRoundPlayerHand);
			playedCards.add(secondRoundPlayerHand);
		} else {
			checkForWinner(secondPlayer);
			firstRoundPlayerHand = secondPlayer.chooseCard(trump);
			secondPlayer.removeCardFromHands(firstRoundPlayerHand);
			playedCards.add(firstRoundPlayerHand);
			secondRoundPlayerHand = firstPlayer.chooseCard(trump);
			while (!secondRoundPlayerHand.getSuit().equals(trump)
					&& (!firstRoundPlayerHand.getSuit().equals(secondRoundPlayerHand.getSuit())
							&& firstPlayer.hasCurrenSuit(firstRoundPlayerHand.getSuit()))) {
				ConsoleIOManager.printValidateCardMessage();
				secondRoundPlayerHand = firstPlayer.chooseCard(trump);
			}
			firstPlayer.removeCardFromHands(secondRoundPlayerHand);
			playedCards.add(secondRoundPlayerHand);
		}

		isFirstPlayerOnHand = isFirstPlayerOnHand(playedCards);
		if (isFirstPlayerOnHand) {
			ConsoleIOManager.printOnHandPlayer(firstPlayer.getName());
		} else {
			ConsoleIOManager.printOnHandPlayer(secondPlayer.getName());
		}

		calculateRoundScore(playedCards);
	}

	static private void calculateRoundScore(List<Card> playedCards) {
		if (isFirstPlayerOnHand) {
			firstPlayer.calculateRoundScore(playedCards.get(0).getRank().points + playedCards.get(1).getRank().points);
		} else {
			secondPlayer.calculateRoundScore(playedCards.get(0).getRank().points + playedCards.get(1).getRank().points);
		}
	}

	static private boolean isFirstPlayerOnHand(List<Card> playedCards) {

		boolean isSamePlayerOnHand = isFirstPlayerOnHand;
		if (playedCards.get(0).compareTo(playedCards.get(1), trump) < 0) {
			isSamePlayerOnHand = !isFirstPlayerOnHand;
		}

		return isSamePlayerOnHand;
	}
}
