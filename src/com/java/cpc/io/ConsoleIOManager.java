/**
 * 
 */
package com.java.cpc.io;

import java.util.Scanner;

/**
 * @author Vasilen Polimenov
 *
 */
public class ConsoleIOManager {

	public static int getPlayerCardChoice(int maxChoice) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your choice(1-"+maxChoice+"):");

		return sc.nextInt() - 1;
	}

	

}
