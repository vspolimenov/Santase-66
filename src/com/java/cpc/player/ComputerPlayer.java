package com.java.cpc.player;

import java.util.Random;

import com.java.cpc.deck.Card;

public class ComputerPlayer extends Player {

	public ComputerPlayer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	private int randomGeneratedChoice() {

		Random rn = new Random();
		int randomNum = getRandomNumberInRange(1, cardsInHand.size());
		return randomNum;
	}

	public Card chooseCard(int maxChoice) {

		int choiceFromHand = this.randomGeneratedChoice();
		lastGiven = cardsInHand.get(choiceFromHand);

		cardsInHand.remove(choiceFromHand);
		return lastGiven;
	}

	public void checkForSpecialAnnounces() {

	}

	private void makeSpecialAnnounce() {

	}

	
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min - 1;
	}
}
