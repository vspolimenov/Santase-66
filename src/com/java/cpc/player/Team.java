package com.java.cpc.player;

public class Team {
	
	private int score = 0;
	private int localScore = 0;
	
	public String name = null;
	public Player firstPlayer = null;
	public Player secondPlayer = null;

	public Team(Player firstPlayerName, Player secondPlayerName, String name){
		this.firstPlayer = firstPlayerName;
		this.secondPlayer = secondPlayerName;
		this.name = name;
	}
	
	public Team(){
		this.firstPlayer = new ComputerPlayer("Player 1");
		this.secondPlayer = new ComputerPlayer("Player 2");
		this.name = "Team";
	}
	
	public int getScore() {
		return score;
	}
	
	public int getLocalScore() {
		return localScore;
		
	}
	
	public void calculateScore() {
		
	}

	public void updateLocalScore(int score) {
	
	}
	

}
