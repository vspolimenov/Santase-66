package com.java.cpc;
import com.java.cpc.exception.CardGameInputFormatException;
import com.java.cpc.game.GameCycle;
import com.java.cpc.io.ConsoleIO;



/**
 * @author Vasilen Polimenov
 *
 */
public class TestInMain {

	/**
	 * @param args
	 */
	public static void main(String[] args)  {
		try{
		GameCycle.startGame();
		}catch(CardGameInputFormatException e) {
			ConsoleIO.errorMessage(e);
		}
	}

}
