package com.nibble.wheelofjeopardy.game;

import com.nibble.wheelofjeopardy.wheel.Wheel;
import com.nibble.wheelofjeopardy.questionBank.Question;

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


	private Player[] players;
	private Round currentRound;
	private Player currentPlayer;
	private Wheel wheel;
	//private QuestionBoard questionBoard; // uncomment when QuestionBoard class is added
	private Question currentQuestion;

	public Game(int numPlayers, int questionGroup){}
	
	public int getNumPlayers(){
	    return players.length;
	}
	
	public int getRemainingSpins(){
	    // todo
        return 0;
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
	    // todo
        return null;
    }
	
	public int[] getPlayerRTotalScores(){
        // todo
        return null;
	}
	
}