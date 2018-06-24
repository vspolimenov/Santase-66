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
import com.java.cpc.exception.CardGameInputFormatException;
import com.java.cpc.io.ConsoleIO;
import com.java.cpc.player.HumanPlayer;
import com.java.cpc.player.Player;

/**
 * @author Vasilen Polimenov
 *
 */

public class GameCycle {

	static Player firstPlayer = new HumanPlayer("Player One");
	static Player secondPlayer = new HumanPlayer("Player Two");
	static private boolean noWinner = true;

	static private Suit trump;
	static private boolean isFirstPlayerOnHand = true;
	static private Deck deck;

	public static void startGame() throws CardGameInputFormatException {

		while (firstPlayer.getScore() < GameConstants.GAME_WINNING_RESULT
				|| secondPlayer.getScore() < GameConstants.GAME_WINNING_RESULT) {
			deck = new Deck();
			firstPlayer.setStartingScoreForRound();
			secondPlayer.setStartingScoreForRound();
			ConsoleIO.printStartMessage();
			ConsoleIO.printScore(firstPlayer.getScore(), secondPlayer.getScore());
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

	static private void playRound() throws CardGameInputFormatException {

		trump = Suit.Clubs;

		Random r = new Random();
		trump = Suit.values()[(r.nextInt((GameConstants.SUIT_COUNT - GameConstants.SUIT_MIN_COUNT) + 1)
				+ GameConstants.SUIT_MIN_COUNT - 1)];
		ConsoleIO.printTrump(trump);

		fillPlayersHands();
		int round = 1;

		while (deck.hasCards() && noWinner) {
			ConsoleIO.printRoundMessage(round++);
			playOneTurn();
			getOneCardFromDeck();
		}

		while (firstPlayer.hasCardInHand() && noWinner) {
			playOneTurnFinnal();
		}

	}

	static private void checkForWinner(Player player) {
		if (player.getRoundScore() >= GameConstants.WINNING_RESULT) {
			ConsoleIO.printWinner(player);
			if (player.getRoundScore() >= GameConstants.TWO_POINTS_SAVE_RESULT) {
				player.calculateScore(1);
			} else if (player.getRoundScore() > 0) {
				player.calculateScore(2);
			} else {
				player.calculateScore(3);
			}
			noWinner = false;
		} else {
			noWinner = true;
		}
	}

	static private List<Card> playersChooseCard(Player firstPlayer, Player secondPlayer) {
		checkForWinner(firstPlayer);

		Card firstRoundPlayerHand = new Card();
		

		firstRoundPlayerHand = firstPlayer.chooseCard(trump);
		firstPlayer.removeCardFromHands(firstRoundPlayerHand);

		List<Card> playedCards = new ArrayList<Card>();
		playedCards.add(firstRoundPlayerHand);
		
		Card secondRoundPlayerHand = new Card();
		secondRoundPlayerHand = secondPlayer.chooseCard(trump);
		secondPlayer.removeCardFromHands(secondRoundPlayerHand);
		playedCards.add(secondRoundPlayerHand);

		return playedCards;
	}

	static private List<Card> playersChooseCardFinnal(Player firstPlayer, Player secondPlayer) {
		checkForWinner(firstPlayer);

		
		
		Card firstRoundPlayerHand = new Card();
		firstRoundPlayerHand = firstPlayer.chooseCard(trump);
		firstPlayer.removeCardFromHands(firstRoundPlayerHand);

		List<Card> playedCards = new ArrayList<Card>();
		playedCards.add(firstRoundPlayerHand);
		
		Card secondRoundPlayerHand = new Card();
		secondRoundPlayerHand = secondPlayer.chooseCard(trump);
		while (!secondRoundPlayerHand.getSuit().equals(trump)
				&& (!firstRoundPlayerHand.getSuit().equals(secondRoundPlayerHand.getSuit())
						&& secondPlayer.hasCurrenSuit(firstRoundPlayerHand.getSuit()))) {
			ConsoleIO.printValidateCardMessage();
			secondRoundPlayerHand = secondPlayer.chooseCard(trump);
		}

		secondPlayer.removeCardFromHands(secondRoundPlayerHand);
		playedCards.add(secondRoundPlayerHand);

		return playedCards;
	}

	static private void playOneTurn() {
		List<Card> playedCards = new ArrayList<Card>();

		if (isFirstPlayerOnHand) {
			playedCards = playersChooseCard(firstPlayer, secondPlayer);
		} else {
			playedCards = playersChooseCard(secondPlayer, firstPlayer);
		}

		calculateRoundScore(playedCards);
	}

	static private void playOneTurnFinnal() {
		List<Card> playedCards = new ArrayList<Card>();

		if (isFirstPlayerOnHand) {
			playedCards = playersChooseCardFinnal(firstPlayer, secondPlayer);
		} else {
			playedCards = playersChooseCardFinnal(secondPlayer, firstPlayer);
		}

		calculateRoundScore(playedCards);
	}

	static private void calculateRoundScore(List<Card> playedCards) {
		isFirstPlayerOnHand = isFirstPlayerOnHand(playedCards);
		int pointsForThisRound = playedCards.get(0).getRank().points + playedCards.get(1).getRank().points;
		
		if (isFirstPlayerOnHand) {
			ConsoleIO.printOnHandPlayer(firstPlayer.getName());
			firstPlayer.calculateRoundScore(pointsForThisRound);
		} else {
			ConsoleIO.printOnHandPlayer(secondPlayer.getName());
			secondPlayer.calculateRoundScore(pointsForThisRound);
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
