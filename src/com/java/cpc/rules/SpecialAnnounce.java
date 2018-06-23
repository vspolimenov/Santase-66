/**
 * 
 */
package com.java.cpc.rules;

/**
 * @author Vasilen Polimenov
 *
 */
public enum SpecialAnnounce {
	Twenty(20), Fourty(20);
	
	public int points;
	SpecialAnnounce(int points) {
		this.points = points;
	}
}

// A tierce — a sequence of three (sequences are in the "A K Q J 10 9 8 7" order
// of the same suit) — is worth 20 points
// A quarte — a sequence of four — is worth 50 points.
// A quint — a sequence of five - is worth 100 points (longer sequences are not
// awarded, a sequence of eight is counted as a quint plus a tierce)
// A carré of Jacks is worth 200 points.
// A carré of Nines is worth 150 points.
// A carré of Aces, Kings, Queens, or Tens is worth 100 points. (Sevens and
// Eights are not awarded