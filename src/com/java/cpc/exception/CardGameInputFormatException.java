/**
 * 
 */
package com.java.cpc.exception;

import java.util.InputMismatchException;

/**
 * @author Vasilen Polimenov
 *
 */
public class CardGameInputFormatException extends InputMismatchException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CardGameInputFormatException(){
		super();
	}
	
	public CardGameInputFormatException(String message) {
		super(message);
	}

}
