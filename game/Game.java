package game

public class Game {

	enum class Round {
		ROUND1(1, "Round1"),
		ROUND2(2, "Round2")

		private int mID;
		private String mName;

		Round(int id, String name) {
			mID = id;
			mName = name;
		}

		int getID() { return mID; }
		String getName() { return mName; }
	}


	private Player[numPlayers] players;
	private Round currentRound;
	private Player currentPlayer;
	private Wheel wheel;
	private QuestionBoard questionBoard;
	private Question currentQuestion;

	public Game(int, int){}
	
	public int getNumPlayers(){ return players.size(); }
	
	public int getRemainingSpins(){}
	
	public int getRemainingQuestions(){}
	
	public int getCurrentRound(){ return currentRound; }
	
	public int getCurrentPlayer(){return currentPlayer; }
	
	public int spinWheel(){}
	
	public Question getQuestion(int, int){}
	
	public void answerQuestion(bool){}
	
	public int[2] getPlayerRoundScores(){}
	
	public int[2] getPlayerRTotalScores(){}
	
}