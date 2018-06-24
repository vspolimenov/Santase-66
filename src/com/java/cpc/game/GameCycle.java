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

	static Player firstPlayer = new HumanPlayer("Player One");
	static Player secondPlayer = new HumanPlayer("Player Two");
	static private boolean hasWinner = false;
	
	static private Suit trump = Suit.Clubs;
	static private boolean isFirstPlayerOnHand = true;
	static private Deck deck = new Deck();

	public static void startGame() {
		
		while (firstPlayer.getScore() < 11 || secondPlayer.getScore() < 11) {
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
		trump.id = (r.nextInt((GameConstants.SUIT_COUNT - GameConstants.SUIT_MIN_COUNT) + 1) + GameConstants.SUIT_MIN_COUNT - 1);
		System.out.println(trump);
		
		fillPlayersHands();
		int round = 1;
		while(!deck.isEmpty() && !hasWinner) {
			System.out.println("Round " + (round++));
			playOneTurn();
			getOneCardFromDeck();
		}
		
		while(!firstPlayer.getCardsInHand().isEmpty()) {
			playOneTurnFinnal();
		}

	}


	
	static private void checkForWinner(Player player) {
		if(player.getRoundScore() >= 66) {
			System.out.println(player.name + " won this round with " + player.getRoundScore() +" points");
			if(player.getRoundScore() >= 33) {
				player.calculateScore(1);
			} else if(player.getRoundScore() > 0) {
				player.calculateScore(2);
			} else {
				player.calculateScore(3);
			}
			hasWinner =  true;
		} 
		hasWinner =  false;
	}




	static private void playOneTurn() {
		List<Card> playedCards = new ArrayList<Card>();
		Card firstRoundPlayerHand = new Card();
		Card secondRoundPlayerHand = new Card();
		
		if(isFirstPlayerOnHand) {
			checkForWinner(firstPlayer);
			firstRoundPlayerHand= firstPlayer.chooseCard();
			playedCards.add(firstRoundPlayerHand);
			secondRoundPlayerHand= secondPlayer.chooseCard();
			playedCards.add(secondRoundPlayerHand);
		} else {
			checkForWinner(secondPlayer);
			firstRoundPlayerHand = secondPlayer.chooseCard();
			playedCards.add(firstRoundPlayerHand);
			secondRoundPlayerHand= firstPlayer.chooseCard();
			playedCards.add(secondRoundPlayerHand);
		}
		
		isFirstPlayerOnHand = isFirstPlayerOnHand(playedCards);
		if(isFirstPlayerOnHand) {
			System.out.println(firstPlayer.name + " is onHand");
		}	else {
			System.out.println(secondPlayer.name + " is onHand");
		}
		
		calculateRoundScore(playedCards);
		
	}
	
	static private void playOneTurnFinnal() {
		List<Card> playedCards = new ArrayList<Card>();
		Card firstRoundPlayerHand = new Card();
		Card secondRoundPlayerHand = new Card();
		
		if(isFirstPlayerOnHand) {
			checkForWinner(firstPlayer);
			firstRoundPlayerHand= firstPlayer.chooseCard();
			playedCards.add(firstRoundPlayerHand);
			secondRoundPlayerHand= secondPlayer.chooseCard();
			while(!secondRoundPlayerHand.getSuit().equals(trump) && 
					(!firstRoundPlayerHand.getSuit().equals(secondRoundPlayerHand.getSuit()) && 
				secondPlayer.hasCurrenSuit(firstRoundPlayerHand.getSuit()))) {
				System.out.println("You must answer your oponent with valid card!");
				secondRoundPlayerHand= secondPlayer.chooseCard();
			}
			playedCards.add(secondRoundPlayerHand);
		} else {
			checkForWinner(secondPlayer);
			firstRoundPlayerHand = secondPlayer.chooseCard();
			playedCards.add(firstRoundPlayerHand);
			secondRoundPlayerHand= firstPlayer.chooseCard();
			while(!secondRoundPlayerHand.getSuit().equals(trump) && 
					(!firstRoundPlayerHand.getSuit().equals(secondRoundPlayerHand.getSuit()) && 
							firstPlayer.hasCurrenSuit(firstRoundPlayerHand.getSuit()))) {
				System.out.println("You must answer your oponent with valid card!");
				secondRoundPlayerHand= firstPlayer.chooseCard();
			}
			playedCards.add(secondRoundPlayerHand);
		}
		
		isFirstPlayerOnHand = isFirstPlayerOnHand(playedCards);
		if(isFirstPlayerOnHand) {
			System.out.println(firstPlayer.name + " is onHand");
		}	else {
			System.out.println(secondPlayer.name + " is onHand");
		}
		
		calculateRoundScore(playedCards);
	}

	static private void calculateRoundScore(List<Card> playedCards) {
		if(isFirstPlayerOnHand) {
			firstPlayer.calculateRoundScore(playedCards.get(0).getRank().points + playedCards.get(1).getRank().points);
		} else {
			secondPlayer.calculateRoundScore(playedCards.get(0).getRank().points + playedCards.get(1).getRank().points);
		}
	}

	static private boolean isFirstPlayerOnHand(List<Card> playedCards) {

		boolean isSamePlayerOnHand = isFirstPlayerOnHand;
		if(playedCards.get(0).compareTo(playedCards.get(1), trump ) == -1) {
			isSamePlayerOnHand =  !isFirstPlayerOnHand;
		}
		
		return isSamePlayerOnHand;
	}
}
