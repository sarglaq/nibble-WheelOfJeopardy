package com.nibble.wheelofjeopardy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCurrentGame = GameManager.getInstance().getGame();

        Button startGameButton = (Button) findViewById(R.id.spin_wheel_button);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinWheel();
            }
        });
    }

    private void spinWheel() {
        if (mCurrentGame == null) {
            Intent newGame = new Intent(this, NewGameActivity.class);
            startActivity(newGame);
        }

        TextView wheelMessage = (TextView) findViewById(R.id.wheel_message);

        Sector state = mCurrentGame.spinWheel();
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
            case OPONENTS_CHOICE:
                ChooseCategoryDialog opponentChooseCategoryDialog = new ChooseCategoryDialog();
                opponentChooseCategoryDialog.setPlayersChoice(false);
                opponentChooseCategoryDialog.show(getSupportFragmentManager(), "OpponentChoosingCategory");
                break;
            case FREE_SPIN:
                mCurrentGame.getCurrentPlayer().awardFreeSpin();
                StringBuilder messageBuilder = new StringBuilder();
                messageBuilder.append("You got a Free Spin token! You now have ");
                messageBuilder.append(mCurrentGame.getCurrentPlayer().getFreeSpins());
                messageBuilder.append(" Free Spin tokens to use.");
                wheelMessage.setText(messageBuilder.toString());
                mCurrentGame.endTurn(false);
                break;
            case LOOSE_TURN:
                int freeSpins = mCurrentGame.getCurrentPlayer().getFreeSpins();
                wheelMessage.setText("Oh teh nose! You lost your turn!");
                if (freeSpins <= 0) {
                    mCurrentGame.endTurn(true);
                }
                UseTokenDialog askUseToken = new UseTokenDialog();
                askUseToken.show(getSupportFragmentManager(), "AskToUseTokenDialog");
                break;
            case BANKRUPT:
                mCurrentGame.getCurrentPlayer().getRoundScore().bankruptScore();
                wheelMessage.setText("Oh teh nose! You've been bankrupt!");
                mCurrentGame.endTurn(true);
                break;
            case DOUBLE_SCORE:
                mCurrentGame.getCurrentPlayer().getRoundScore().doubleScore();
                wheelMessage.setText("Your score just doubled! Sweet!");
                mCurrentGame.endTurn(false);
                break;
        }
    }

    public void askQuesiton(Category category) {
        Intent askQuesiton = new Intent(this, QuestionBoardActivity.class);
        askQuesiton.getExtras().putString("category", category.getName());
        startActivity(askQuesiton);
    }
}
