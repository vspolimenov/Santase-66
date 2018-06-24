/**
 * 
 */
package com.java.cpc.player;

import java.util.List;

import com.java.cpc.deck.Card;
import com.java.cpc.io.ConsoleIOManager;

/**
 * @author Vasilen Polimenov
 *
 */
public class HumanPlayer extends Player {

	public HumanPlayer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Card chooseCard() {
		for(Card c: cardsInHand){
			System.out.println(c.getRank() +" " +  c.getSuit());
		}
		
		int choiceFromHand = ConsoleIOManager.getPlayerCardChoice(cardsInHand.size());
		lastGiven = cardsInHand.get(choiceFromHand);

		cardsInHand.remove(choiceFromHand);
		return lastGiven;

	}

	@Override
	public void checkForSpecialAnnounces() {
		// TODO Auto-generated method stub

	}

	private void makeSpecialAnnounce() {

	}

}
