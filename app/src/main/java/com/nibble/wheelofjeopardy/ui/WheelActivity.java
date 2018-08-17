package com.nibble.wheelofjeopardy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nibble.wheelofjeopardy.R;
import com.nibble.wheelofjeopardy.game.Game;
import com.nibble.wheelofjeopardy.game.GameManager;
import com.nibble.wheelofjeopardy.questionBank.Question;
import com.nibble.wheelofjeopardy.questionBoard.Category;
import com.nibble.wheelofjeopardy.wheel.Sector;

import org.w3c.dom.Text;

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

        TextView round = (TextView) findViewById(R.id.current_round);
        round.setText(mCurrentGame.getCurrentRound().getName());

        TextView player = (TextView) findViewById(R.id.player_name);
        player.setText(mCurrentGame.getCurrentPlayer().getName());

        TextView score = (TextView) findViewById(R.id.score);
        String scoreString = new StringBuilder().append(mCurrentGame.getCurrentPlayer().getRoundScore().getScore()).toString();
        score.setText(scoreString);

        TextView spins = (TextView) findViewById(R.id.remaining_spins);
        String spinsAsString = new StringBuilder().append(mCurrentGame.getRemainingSpins()).toString();
        spins.setText(spinsAsString);

        TextView questions = (TextView) findViewById(R.id.remaining_questions);
        String questionsAsString = new StringBuilder().append(mCurrentGame.getRemainingQuestions()).toString();
        questions.setText(questionsAsString);
    }

    private void spinWheel() {
        if (mCurrentGame == null) {
            Intent newGame = new Intent(this, NewGameActivity.class);
            startActivity(newGame);
        }

        Sector state = mCurrentGame.spinWheel();

        TextView wheelMessage = (TextView) findViewById(R.id.wheel_message);
        wheelMessage.setText(R.string.wheel_greeting);

        WheelResultDialog wheelDialog = new WheelResultDialog();
        wheelDialog.setContext(getApplicationContext());
        wheelDialog.setSector(state);
        wheelDialog.show(getSupportFragmentManager(), "WheelResult");
    }
    public void performWheelAction(Sector state) {
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
                endTurn(false);
                break;
            case LOOSE_TURN:
                int freeSpins = mCurrentGame.getCurrentPlayer().getFreeSpins();
                if (freeSpins > 0) {
                    UseTokenDialog askUseToken = new UseTokenDialog();
                    askUseToken.show(getSupportFragmentManager(), "AskToUseTokenDialog");
                    break;
                }
                endTurn(true);
                break;
            case BANKRUPT:
                endTurn(true);
                break;
            case DOUBLE_SCORE:
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
        System.out.println("Game over? " + mCurrentGame.gameIsOver());
        if (mCurrentGame.gameIsOver()) {
            Intent showEndGame = new Intent(this, GameOverActivity.class);
            startActivity(showEndGame);
        }
    }

    public void askQuesiton(Category category) {
        mCurrentGame.loadQuestion(category);

        Question question = mCurrentGame.getCurrentQuestion();
        if (question == null) {
            // all the questions have been answered or something is wrong and should be handled gracefully
            TextView wheelMessage = (TextView) findViewById(R.id.wheel_message);
            wheelMessage.setText(R.string.no_more_questions);
            endTurn(false);
        } else {
            Intent askQuestion = new Intent(this, QuestionActivity.class);
            startActivity(askQuestion);
        }
    }
}
