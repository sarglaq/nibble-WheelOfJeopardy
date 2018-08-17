package com.nibble.wheelofjeopardy.game;

public class Score {
	private int multiplier = 1;
	private int score = 0;

	public Score(int multiplier){
		this.multiplier = multiplier;
	}
	
	public int getScore(){
	    return score;
    }
	
	public void addToScore(int points){
	    score += (multiplier * points);
    }
	
	public void bankruptScore(){
	    score = 0;
    }
	
	public void doubleScore(){
	    score *= 2;
    }
	
}