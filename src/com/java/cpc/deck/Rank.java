package com.java.cpc.deck;

public enum Rank {
	Nine(0), Jack(2), Queen(3), King(4), Ten(10), Ace(11);

   public int points;

	Rank(int points) {		
	this.points = points;
	}
}
