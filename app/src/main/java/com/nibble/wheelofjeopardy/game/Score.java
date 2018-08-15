package com.nibble.wheelofjeopardy.game;

public class Score {
	private int multiplier = 1;
	private int score = 0;

	public Score(int multiplier){
		this.multiplier = multiplier;
	}
	
	public int getScore(){
	    System.out.println("Score object has score: " + score);
	    return score;
    }
	
	public void addToScore(int points){
	    System.out.println("current score: " + score);
		System.out.println("Adding " + points + " with multiplier " + multiplier);
	    score += (multiplier * points);
        System.out.println("new score: " + score);
    }
	
	public void bankruptScore(){
	    score = 0;
    }
	
	public void doubleScore(){
	    score *= 2;
    }
	
}