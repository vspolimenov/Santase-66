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

	public static int getPlayerAnnounceNumber(int last) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Now it is your turn! Make announce(1-9):");
		if (last <= 1) {
			System.out.println("1) Clubs.");
		}
		if (last <= 2) {
			System.out.println("2) Dyamonds.");
		}
		if (last <= 3) {
			System.out.println("3) Hearts.");
		}
		if (last <= 4) {
			System.out.println("4) Spades.");
		}
		if (last <= 5) {
			System.out.println("5) NoTrump.");
		}
		if (last <= 6) {
			System.out.println("6) AllTrumps.");
		}
		if (last != 0) {
			System.out.println("7) Contra.");
		} else if (last == 7) {
			System.out.println("8) ReContra.");
		}

		return sc.nextInt();
	}

}
