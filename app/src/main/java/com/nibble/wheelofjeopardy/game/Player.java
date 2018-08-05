package com.nibble.wheelofjeopardy.game;

public class Player {
	private int id;
	private String name;
	private Score roundScore;
	private Score totalScore;
	private int freeSpins;

	public Player(String name, int id)
    {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName(String name) {
        return name;
    }

    public Score getRoundScore() {
        return roundScore;
    }

    public Score getTotalScore() {
        return totalScore;
    }

    public int getFreeSpins() {
        return freeSpins;
    }

    public void awardFreeSpin() {
        freeSpins++;
    }

    public void useFreeSpin() {
        if (freeSpins > 0) {
            freeSpins--;
        } else {
            throw new IllegalStateException("This user doesn't have any free spins left");
        }
    }

    public void endRound(){
        totalScore.addToScore(roundScore.getScore());
    }
	
}