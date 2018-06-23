package com.java.cpc.deck;

public enum Rank {
	Nine(0), Ten(10), Jack(2), Queen(3), King(4), Ace(11);

   public int points;

	Rank(int points) {		
	this.points = points;
	}
}
