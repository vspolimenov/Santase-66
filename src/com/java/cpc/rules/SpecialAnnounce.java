/**
 * 
 */
package com.java.cpc.rules;

/**
 * @author Vasilen Polimenov
 *
 */
public enum SpecialAnnounce {
	Twenty(20), Fourty(40);
	
	public int points;
	SpecialAnnounce(int points) {
		this.points = points;
	}
}
