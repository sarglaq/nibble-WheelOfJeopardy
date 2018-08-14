package com.nibble.wheelofjeopardy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nibble.wheelofjeopardy.R;
import com.nibble.wheelofjeopardy.game.GameManager;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView questionText = (TextView) findViewById(R.id.question_text);
        questionText.setText(GameManager.getInstance().getGame().getCurrentQuestion().getQuestion());

        Button submitButton = (Button) findViewById(R.id.submit_answer_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView answerText = (TextView) findViewById(R.id.answer_input);
                displayAnswer(answerText.getText());
            }
        });
    }

    public void displayAnswer(CharSequence userAnswer) {
        Intent askQuesiton = new Intent(this, QuestionActivity.class);
        askQuesiton.putExtra("USER_ANSWER", userAnswer);
        startActivity(askQuesiton);
    }
}
