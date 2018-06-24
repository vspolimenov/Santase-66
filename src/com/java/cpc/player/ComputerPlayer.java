package com.java.cpc.player;

import java.util.Random;

import com.java.cpc.GameConstants;
import com.java.cpc.deck.Card;
import com.java.cpc.deck.Suit;
import com.java.cpc.io.ConsoleIO;

public class ComputerPlayer extends Player {

	public ComputerPlayer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	private int randomGeneratedChoice() {

		int randomNum = 0;
		try {
			Random rn = new Random();
			randomNum = getRandomNumberInRange(1, cardsInHand.size());
		} catch (IllegalArgumentException e) {
			ConsoleIO.errorMessage(e);
		}
		return randomNum;
	}

	public Card chooseCard(Suit trump) {

		int choiceFromHand = this.randomGeneratedChoice();
		lastGiven = cardsInHand.get(choiceFromHand);

		makeSpecialAnnounce(lastGiven, trump);
		return lastGiven;
	}

	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException(GameConstants.ILLEGAL_ARGUMET_EX_TEXT);
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min - 1;
	}
}
