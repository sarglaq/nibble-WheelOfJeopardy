package com.nibble.wheelofjeopardy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nibble.wheelofjeopardy.R;
import com.nibble.wheelofjeopardy.game.Game;
import com.nibble.wheelofjeopardy.game.GameManager;
import com.nibble.wheelofjeopardy.questionBoard.Category;
import com.nibble.wheelofjeopardy.wheel.Sector;

public class WheelActivity extends AppCompatActivity {

    private Game mCurrentGame = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel);

        mCurrentGame = GameManager.getGame();

        TextView wheelMessage = (TextView) findViewById(R.id.wheel_message);
        wheelMessage.setText(R.string.wheel_greeting);

        updateUiInfo();

        Button startGameButton = (Button) findViewById(R.id.spin_wheel_button);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinWheel();
            }
        });
    }

    private void updateUiInfo() {
        System.out.println("Updating UI info");

        TextView round = (TextView) findViewById(R.id.current_round);
        round.setText(mCurrentGame.getCurrentRound().getName());

        TextView player = (TextView) findViewById(R.id.player_name);
        player.setText(mCurrentGame.getCurrentPlayer().getName());

        TextView score = (TextView) findViewById(R.id.score);
        String scoreAsString = "" + mCurrentGame.getCurrentPlayer().getRoundScore().getScore();
        score.setText(scoreAsString);

        TextView spins = (TextView) findViewById(R.id.remaining_spins);
        String spinsAsString = "" + mCurrentGame.getRemainingSpins();
        score.setText(spinsAsString);

        TextView questions = (TextView) findViewById(R.id.remaining_questions);
        String questionsAsString = "" + mCurrentGame.getRemainingQuestions();
        score.setText(questionsAsString);
    }

    private void spinWheel() {
        if (mCurrentGame == null) {
            Intent newGame = new Intent(this, NewGameActivity.class);
            startActivity(newGame);
        }

        TextView wheelMessage = (TextView) findViewById(R.id.wheel_message);

        Sector state = mCurrentGame.spinWheel();
        System.out.println("Just spun " + state);
        switch (state) {
            case CATEGORY_ONE:
                askQuesiton(Category.CATEGORY_ONE);
                break;
            case CATEGORY_TWO:
                askQuesiton(Category.CATEGORY_TWO);
                break;
            case CATEGORY_THREE:
                askQuesiton(Category.CATEGORY_THREE);
                break;
            case CATEGORY_FOUR:
                askQuesiton(Category.CATEGORY_FOUR);
                break;
            case CATEGORY_FIVE:
                askQuesiton(Category.CATEGORY_FIVE);
                break;
            case CATEGORY_SIX:
                askQuesiton(Category.CATEGORY_SIX);
                break;
            case PLAYERS_CHOICE:
                ChooseCategoryDialog playerChooseCategoryDialog = new ChooseCategoryDialog();
                playerChooseCategoryDialog.setPlayersChoice(true);
                playerChooseCategoryDialog.show(getSupportFragmentManager(), "PlayerChoosingCategory");
                break;
            case OPPONENTS_CHOICE:
                ChooseCategoryDialog opponentChooseCategoryDialog = new ChooseCategoryDialog();
                opponentChooseCategoryDialog.setPlayersChoice(false);
                opponentChooseCategoryDialog.show(getSupportFragmentManager(), "OpponentChoosingCategory");
                break;
            case FREE_SPIN:
                StringBuilder messageBuilder = new StringBuilder();
                messageBuilder.append("You got a Free Spin token! You now have ");
                messageBuilder.append(mCurrentGame.getCurrentPlayer().getFreeSpins());
                messageBuilder.append(" Free Spin tokens to use.");
                wheelMessage.setText(messageBuilder.toString());
                endTurn(false);
                break;
            case LOOSE_TURN:
                int freeSpins = mCurrentGame.getCurrentPlayer().getFreeSpins();
                wheelMessage.setText("Oh teh nose! You lost your turn!");
                if (freeSpins < 0) {
                    endTurn(true);
                }
                UseTokenDialog askUseToken = new UseTokenDialog();
                askUseToken.show(getSupportFragmentManager(), "AskToUseTokenDialog");
                break;
            case BANKRUPT:
                mCurrentGame.getCurrentPlayer().getRoundScore().bankruptScore();
                wheelMessage.setText("Oh teh nose! You've been bankrupt!");
                endTurn(true);
                break;
            case DOUBLE_SCORE:
                mCurrentGame.getCurrentPlayer().getRoundScore().doubleScore();
                wheelMessage.setText("Your score just doubled! Sweet!");
                endTurn(false);
                break;
        }
    }

    public void endTurn(boolean changePlayer) {
        mCurrentGame.endTurn(changePlayer);
        updateUiInfo();
        checkIfGameIsOver();
    }

    public void checkIfGameIsOver() {
        if (mCurrentGame.gameIsOver()) {
            Intent showEndGame = new Intent(this, GameOverActivity.class);
            startActivity(showEndGame);
        }
    }

    public void askQuesiton(Category category) {
        mCurrentGame.loadQuestion(category);
        Intent askQuesiton = new Intent(this, QuestionActivity.class);
        startActivity(askQuesiton);
    }
}
