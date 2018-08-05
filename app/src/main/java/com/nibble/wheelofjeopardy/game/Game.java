package com.nibble.wheelofjeopardy.game;

import com.nibble.wheelofjeopardy.wheel.Wheel;
import com.nibble.wheelofjeopardy.questionBank.Question;
import java.util.Queue;
import java.util.LinkedList;

public class Game {

	enum Round {
		ROUND1(1, "Round1"),
		ROUND2(2, "Round2");

		private int mID;
		private String mName;

		Round(int id, String name) {
			mID = id;
			mName = name;
		}

		public int getID() { return mID; }
		public String getName() { return mName; }
	}


	private Queue<Player> players;
	private Round currentRound;
	private Player currentPlayer;
	private Wheel wheel;
	private int spin;
	//private QuestionBoard questionBoard; // uncomment when QuestionBoard class is added
	private Question currentQuestion;

	public Game(int numPlayers, int questionGroup)
	{
		for(int i =0 ; i < numPlayers; i += 1)
		{
			players.add(new Player("Player"+i+1, i+1));
		}
		spin = 50;
		currentPlayer = players.peek();
	}
	
	public int getNumPlayers(){
	    return players.size();
	}
	
	public int getRemainingSpins(){
	    // todo
        return spin;
    }
	
	public int getRemainingQuestions(){
	    // todo
        return 0;
    }
	
	public Round getCurrentRound(){
	    return currentRound;
	}
	
	public Player getCurrentPlayer(){
	    return currentPlayer;
	}

	/**
	 * this should be under player's function? YC
	 * @return
	 */
	public int spinWheel(){
	    // todo
        return 0;
    }
	
	public Question getQuestion(int row, int column){
	    // todo
        return null;
    }
	
	public void answerQuestion(boolean correct){
	    // todo;
    }


	public int[] getPlayerRoundScores(){
		int[] scores = new int[getNumPlayers()];
		for(Player p: players)
		{
			scores[p.getId()-1] = p.getRoundScore().getScore();
		}
		return scores;
    }
	
	public int[] getPlayerRTotalScores(){
        // todo
		int[] scores = new int[getNumPlayers()];
		for(Player p: players)
		{
			scores[p.getId()-1] = p.getTotalScore().getScore();
		}
        return scores;
	}
	
}