package com.nibble.wheelofjeopardy.game;

public class Player {
	private int id;
	private String name;
	private Score roundScore = new Score(1);
	private Score totalScore = new Score(1);
	private int freeSpins = 0;

	public Player(int id)
    {
        this.id = id;
        this.name = "Player" + id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
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
        roundScore = new Score(2);
    }
	
}