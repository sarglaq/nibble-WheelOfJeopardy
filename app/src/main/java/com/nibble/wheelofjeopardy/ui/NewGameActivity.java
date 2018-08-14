package com.nibble.wheelofjeopardy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.nibble.wheelofjeopardy.R;
import com.nibble.wheelofjeopardy.game.Game;
import com.nibble.wheelofjeopardy.game.GameManager;

public class NewGameActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        Button startGameButton = (Button) findViewById(R.id.start_game_button);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchNewGame();
            }
        });
    }

    private void launchNewGame() {
        RadioGroup playerSelection = (RadioGroup) findViewById(R.id.selectPlayers);
        int radioButtonID = playerSelection.getCheckedRadioButtonId();
        int numPlayers = radioButtonID == R.id.radio_one_player ? 1:2;
        Game newGame = new Game(numPlayers, 0);
        GameManager.newGame(newGame);
        Intent wheelIntent = new Intent(this, WheelActivity.class);
        startActivity(wheelIntent);
    }
}
