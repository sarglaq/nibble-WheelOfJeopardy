package com.nibble.wheelofjeopardy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nibble.wheelofjeopardy.R;
import com.nibble.wheelofjeopardy.game.Game;
import com.nibble.wheelofjeopardy.game.GameManager;


public class QuestionActivity extends AppCompatActivity {
    private static int timeLimit = 60;
    private static TextView timmerView;

    private final Handler timerHandler = new Handler();

    private CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            System.out.println("A second has passed");
            timeLimit--; //decrement every sec
            timerHandler.post(updateUI);
        }

        @Override
        public void onFinish() {
            timeExpired();

        }
    };

    private final Runnable updateUI = new Runnable() {
        @Override
        public void run() {
            System.out.println("Updating timer UI");
            String newTime = "" + timeLimit;
            timmerView.setText(newTime);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        timeLimit = 60;
        timmerView = findViewById(R.id.timmer);

        TextView questionText = (TextView) findViewById(R.id.question_text);
        questionText.setText(GameManager.getGame().getCurrentQuestion().getQuestion());

        Button submitButton = (Button) findViewById(R.id.submit_answer_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView answerText = (TextView) findViewById(R.id.answer_input);
                displayAnswer(answerText.getText());
            }
        });

        timer.start();
    }

    public void displayAnswer(CharSequence userAnswer) {
        timer.cancel();
        Intent askQuesiton = new Intent(this, AnswerActivity.class);
        askQuesiton.putExtra("USER_ANSWER", userAnswer);
        startActivity(askQuesiton);
    }

    private void timeExpired() {
        Game game = GameManager.getGame();
        game.answerQuestion(Game.AnswerOptions.TIME_EXPIRED);

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
