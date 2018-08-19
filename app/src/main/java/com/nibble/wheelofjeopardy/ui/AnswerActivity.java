package com.nibble.wheelofjeopardy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nibble.wheelofjeopardy.R;
import com.nibble.wheelofjeopardy.game.Game;
import com.nibble.wheelofjeopardy.game.GameManager;

import java.util.TimerTask;

public class AnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        CharSequence usersTextAnswer = this.getIntent().getCharSequenceExtra("USER_ANSWER");
        TextView usersAnswer = (TextView) findViewById(R.id.user_answer);
        if (usersTextAnswer != null && usersTextAnswer.length() != 0) {
            usersAnswer.setText(usersTextAnswer);
        } else {
            usersAnswer.setVisibility(View.GONE);
        }

        TextView actualAnswer = (TextView) findViewById(R.id.actual_answer);
        actualAnswer.setText(GameManager.getGame().getCurrentQuestion().getAnswer());

        Button correctButton = (Button) findViewById(R.id.correct_button);
        correctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerQuestion(Game.AnswerOptions.CORRET);
            }
        });

        Button wrongButton = (Button) findViewById(R.id.wrong_button);
        wrongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerQuestion(Game.AnswerOptions.WRONG);
            }
        });
    }

    private void answerQuestion(Game.AnswerOptions result) {
        Game game = GameManager.getGame();
        game.answerQuestion(result);

        if (result == Game.AnswerOptions.CORRET) {
            // Same player spins again if they got it right
            game.endTurn(false);
            Intent returnToWheel = new Intent(this, WheelActivity.class);
            startActivity(returnToWheel);
        } else {
            // Give the user a chance to use a free token if they get the question wrong
            if (game.getCurrentPlayer().getFreeSpins() > 0) {
                UseTokenDialog askUseToken = new UseTokenDialog();
                askUseToken.show(getSupportFragmentManager(), "AskToUseTokenDialog");
            } else {
                game.endTurn(true);
                Intent returnToWheel = new Intent(this, WheelActivity.class);
                startActivity(returnToWheel);
            }
        }
    }

    public void useFreeSpin(boolean useToken) {
        Game game = GameManager.getGame();
        if (useToken) {
            game.endTurn(false);
        } else {
            game.endTurn(true);
        }
        Intent returnToWheel = new Intent(this, WheelActivity.class);
        startActivity(returnToWheel);
    }
}
