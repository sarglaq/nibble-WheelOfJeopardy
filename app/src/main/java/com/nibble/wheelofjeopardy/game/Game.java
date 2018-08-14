package com.nibble.wheelofjeopardy.game;

import com.nibble.wheelofjeopardy.questionBoard.Category;
import com.nibble.wheelofjeopardy.questionBoard.QuestionBoard;
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
	private int maxSpins;
	private QuestionBoard questionBoard;
	private Question currentQuestion;
	private boolean gameOver = false;

	public Game(int numPlayers, int questionGroup)
	{
		for(int i =0 ; i < numPlayers; i += 1)
		{
			players.add(new Player("Player"+i+1, i+1));
		}
		maxSpins = 50;
		currentPlayer = players.peek();
	}
	
	public int getNumPlayers(){
	    return players.size();
	}
	
	public int getRemainingSpins(){
        return maxSpins - wheel.getSpinCount();
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

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    /**
	 * this should be under player's function? YC
	 * @return
	 */
	public Sector spinWheel(){
        wheel.spin();
        Sector state = wheel.get_State();


        switch (state) {
            case CATEGORY_ONE:
            case CATEGORY_TWO:
            case CATEGORY_THREE:
            case CATEGORY_FOUR:
            case CATEGORY_FIVE:
            case CATEGORY_SIX:
            case PLAYERS_CHOICE:
            case OPPONENTS_CHOICE:
                /* The UI must ask the player or opponent which category to use sometimes
                 * so the question cannot be retrieved yet. The UI will trigger the loadQuestion
                 * to set currentQuestion when it knows the category.
                 */
                break;
            case FREE_SPIN:
                currentPlayer.awardFreeSpin();
                break;
            case LOOSE_TURN:
                // Nothing to do as the UI ends the turn with text when ready.
                break;
            case BANKRUPT:
                currentPlayer.getRoundScore().bankruptScore();
                break;
            case DOUBLE_SCORE:
                currentPlayer.getRoundScore().doubleScore();
                break;
        }
        return state;
    }
	
	public void loadQuestion(Category category){
	    // todo

		/*
		 * The question that needs to be answered needs to be retrieved and stored in currentQuestion.
		 * This should mostly be a pass through to the question board loadQuestion method. Something
		 * like:
		 * currentQuestion = questionBoard.getQuestion(category);
		 *
		 * The QuesitonBoard class hasn't been added yet. That needs to be filled. I'm not sure if
		 * there was a jeopardy program that we were going to use for this or not.
		 */
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
	    if (getRemainingSpins() == 0 || getRemainingQuestions() == 0) {
	        endRound();
        }

        /*
         * if changePlayer is true, end this turn and start the next turn for the other player
         * if changePlayer is false, end this turn and start the next turn for the current player
         */
        if (changePlayer) {
            players.add(currentPlayer);
            currentPlayer = players.poll();
        }
    }

    private void endRound() {
        for (Player player: players) {
            player.endRound();
        }
        wheel.reset();

	    if (currentRound == Round.ROUND2) {
            gameOver = true;
        }
    }
}