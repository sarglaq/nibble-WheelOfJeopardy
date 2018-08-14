package com.nibble.wheelofjeopardy.ui;

import android.content.Intent;
import android.os.Bundle;
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

public class AnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CharSequence usersTextAnswer = savedInstanceState.getCharSequence("USER_ANSWER");
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
                answerQuestion(true);
            }
        });

        Button wrongButton = (Button) findViewById(R.id.wrong_button);
        wrongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerQuestion(false);
            }
        });
    }

    private void answerQuestion(boolean correct) {
        Game game = GameManager.getGame();
        game.answerQuestion(correct);
        /* If the player gets the question right (correct = true) than it remains their turn
         * (changePlayer = false) and vice versa. So pass the inverted value of correct to endTurn.
         */
        game.endTurn(!correct);
        Intent returnToWheel = new Intent(this, WheelActivity.class);
        startActivity(returnToWheel);
    }
}
