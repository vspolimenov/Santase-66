/**
 * 
 */
package com.java.cpc.io;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.java.cpc.deck.Card;
import com.java.cpc.deck.Suit;
import com.java.cpc.exception.CardGameInputFormatException;
import com.java.cpc.player.Player;

/**
 * @author Vasilen Polimenov
 *
 */
public class ConsoleIO {

	public static int getPlayerCardChoice(int maxChoice)throws CardGameInputFormatException {
		Scanner sc = new Scanner(System.in);
		System.out.println('\n' + "Enter your choice(1-" + maxChoice + "):");
		
		int result = 0;
		try{
				result = sc.nextInt() - 1;
		}catch(InputMismatchException e) {
			
			throw new CardGameInputFormatException(e.getMessage());
		}
		
		return result;
	}

	public static void printStartMessage() {
		System.out.println("Ok. New game starts. Good luck!" + '\n');
	}

	public static void printTrump(Suit trump) {
		System.out.println(trump + " is trump." + '\n');
	}

	public static void printWinner(Player player) {
		System.out.println(player.getName() + " won this round with " + player.getRoundScore() + " points");
	}

	public static void printOnHandPlayer(String name) {
		System.out.println(name + " is onHand");
	}

	public static void printValidateCardMessage() {
		System.out.println("You must answer your oponent with valid card!");
	}

	public static void printRoundMessage(int round) {
		System.out.println("Round " + (round));
	}

	public static void printTwentyAnnounce() {
		System.out.println("Player has 20!");
	}

	public static void printFourtyAnnounce() {
		System.out.println("Player has 40!");
	}
	
	public static void printPlayerInfo(String name, int roundPoints) {
		System.out.println('\n' + name + " is on hand: " + "Points: " + roundPoints + '\n' );
	}
	
	public static void printCardInfo(int index, Card card) {
		System.out.println(index +") " + card.getRank() +" " + card.getSuit());
	}
	
	public static void printPlayedCardInfo(String name, Card lastGiven) {
		System.out.println(name +" plays " + lastGiven.getRank() +" " +  lastGiven.getSuit());
	}

	public static void printScore(int firstPlayerScore, int secondPlayerScore) {
		System.out.println("Player one: " + firstPlayerScore + "  Player two: " + secondPlayerScore);
		
	}
	
	public static void errorMessage(Exception message) {
		message.printStackTrace();
	}
}
