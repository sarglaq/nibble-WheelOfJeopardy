package com.nibble.wheelofjeopardy.game;

import com.nibble.wheelofjeopardy.questionBoard.Category;
import com.nibble.wheelofjeopardy.wheel.Sector;
import com.nibble.wheelofjeopardy.wheel.Wheel;
import com.nibble.wheelofjeopardy.questionBank.Question;
import java.util.Queue;

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
	public Sector spinWheel(){
        wheel.spin();
        return wheel.get_State();
    }
	
	public Question getQuestion(Category category){
	    // todo

		/*
		 * The question that needs to be answered needs to be retrieved and stored in currentQuestion.
		 * This should mostly be a pass through to the question board getQuestion method. Something
		 * like:
		 * currentQuestion = questionBoard.getQuestion(category);
		 *
		 * The QuesitonBoard class hasn't been added yet. That needs to be filled. I'm not sure if
		 * there was a jeopardy program that we were going to use for this or not.
		 */
        return null;
    }
	
	public void answerQuestion(boolean correct){
	    // todo;

        /*
         * This function should get the value from currentQuestion and apply that many points
         * to currentPlayer's score. If correct is true, it should apply positive points, if
         * correct is false it should apply negative points. It should then clear currentQuestion
         * (set it to null) as once answered the questions should be cleared.
         */
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

	public void endTurn(boolean changePlayer) {
	    // todo
        /*
         * if changePlayer is true, end this turn and start the next turn for the other player
         * if changePlayer is false, end this turn and start the next turn for the current player
         */
    }
	
}