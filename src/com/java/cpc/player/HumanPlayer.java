/**
 * 
 */
package com.java.cpc.player;

import com.java.cpc.deck.Card;
import com.java.cpc.deck.Suit;
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
	public Card chooseCard(Suit trump) {
		ConsoleIOManager.printPlayerInfo(getName(), roundPoints);
		int index = 1;
		for(Card card: cardsInHand){
			ConsoleIOManager.printCardInfo(index++, card);
		}
		int choiceFromHand = 0;
		do{
			choiceFromHand = ConsoleIOManager.getPlayerCardChoice(cardsInHand.size());
		} while (choiceFromHand < 0 || choiceFromHand >= cardsInHand.size()) ;
		lastGiven = cardsInHand.get(choiceFromHand);
		makeSpecialAnnounce(lastGiven, trump);	
		ConsoleIOManager.printPlayedCardInfo(getName(), lastGiven);
		return lastGiven;
	}

}
