package com.nibble.wheelofjeopardy.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nibble.wheelofjeopardy.R;
import com.nibble.wheelofjeopardy.game.Game;
import com.nibble.wheelofjeopardy.game.GameManager;
import com.nibble.wheelofjeopardy.game.Player;

import java.util.Queue;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Game game = GameManager.getGame();

        TextView playerScore = (TextView) findViewById(R.id.player_scores);
        StringBuilder playerScoreText = new StringBuilder();
        Queue<Player> players = game.getPlayers();
        for (Player p: players) {
            playerScoreText.append(p.getName());
            playerScoreText.append(": ");
            playerScoreText.append(p.getTotalScore().getScore());
            playerScoreText.append('\n');
        }
        playerScore.setText(playerScoreText.toString());

        Button newGame = (Button) findViewById(R.id.new_game_button);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewGameActivity();
            }
        });
    }

    /* There is no context inside of onClick, so call a method that does the work with context. */
    private void goToNewGameActivity() {
        Intent returnToBeginning = new Intent(this, NewGameActivity.class);
        startActivity(returnToBeginning);
    }
}
