package com.nibble.wheelofjeopardy.game;

import com.nibble.wheelofjeopardy.questionBoard.Category;
import com.nibble.wheelofjeopardy.questionBoard.QuestionBoard;
import com.nibble.wheelofjeopardy.wheel.Sector;
import com.nibble.wheelofjeopardy.wheel.Wheel;
import com.nibble.wheelofjeopardy.questionBank.Question;

import java.util.LinkedList;
import java.util.Queue;

public class Game {


    private final Queue<Player> players = new LinkedList<>();
	private final Wheel wheel = new Wheel();
	private final int maxSpins = 50;
	private QuestionBoard questionBoard;

	private Round currentRound = Round.ROUND1;
	private Player currentPlayer = null;
	private Question currentQuestion = null;
	private boolean gameOver = false;

	public Game(int numPlayers, int questionGroup, String questionBankPath)
	{
	    questionBoard = new QuestionBoard(questionBankPath);
		for(int i =0 ; i < numPlayers; i += 1)
		{
			players.add(new Player(i+1));
		}
		currentPlayer = players.poll();
	}
	
	public int getNumPlayers(){
	    return players.size();
	}
	
	public int getRemainingSpins(){
	    System.out.println("remaing spins is: " + (maxSpins - wheel.getSpinCount()));
        return maxSpins - wheel.getSpinCount();
    }
	
	public int getRemainingQuestions(){
	    System.out.println("Remaining questions is: " + questionBoard.getRemainingQuesitons());
	    return questionBoard.getRemainingQuesitons();
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

    public boolean gameIsOver() {
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
		currentQuestion = questionBoard.getNextQuestion(category);
    }
	
	public void answerQuestion(boolean correct){
        int points = currentQuestion.getId() * 100 * (correct ? 1:-1);
        currentPlayer.getRoundScore().addToScore(points);
        currentQuestion = null;
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
	    System.out.println("ending turn");
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
        System.out.println("ending round");
        for (Player player: players) {
            player.endRound();
        }
        wheel.reset();

	    if (currentRound == Round.ROUND2) {
            System.out.println("ending game");
            gameOver = true;
        }
    }
}