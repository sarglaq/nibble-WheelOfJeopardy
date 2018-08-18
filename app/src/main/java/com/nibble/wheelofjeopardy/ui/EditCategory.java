package com.nibble.wheelofjeopardy.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nibble.wheelofjeopardy.R;
import com.nibble.wheelofjeopardy.questionBoard.Category;

public class EditCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);

        Button category1Button = (Button) findViewById(R.id.category_one);
        category1Button.setText(Category.CATEGORY_ONE.getName());
        category1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editQuestions(Category.CATEGORY_ONE);
            }
        });

        Button category2Button = (Button) findViewById(R.id.category_two);
        category2Button.setText(Category.CATEGORY_TWO.getName());
        category2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editQuestions(Category.CATEGORY_TWO);
            }
        });

        Button category3Button = (Button) findViewById(R.id.category_three);
        category3Button.setText(Category.CATEGORY_THREE.getName());
        category3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editQuestions(Category.CATEGORY_THREE);
            }
        });

        Button category4Button = (Button) findViewById(R.id.category_four);
        category4Button.setText(Category.CATEGORY_FOUR.getName());
        category4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editQuestions(Category.CATEGORY_FOUR);
            }
        });

        Button category5Button = (Button) findViewById(R.id.category_five);
        category5Button.setText(Category.CATEGORY_FIVE.getName());
        category5Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editQuestions(Category.CATEGORY_FIVE);
            }
        });

        Button category6Button = (Button) findViewById(R.id.category_six);
        category6Button.setText(Category.CATEGORY_SIX.getName());
        category6Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editQuestions(Category.CATEGORY_SIX);
            }
        });

        final Button newGameButton = (Button) findViewById(R.id.new_game_button);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNewGameActivity();
            }
        });
    }

    private void editQuestions(Category category) {
        Intent editQuestions = new Intent(this, EditQuestions.class);
        editQuestions.putExtra("CATEGORY", category.getValue());
        startActivity(editQuestions);
    }

    private void goToNewGameActivity() {
        Intent returnToBeginning = new Intent(this, NewGameActivity.class);
        startActivity(returnToBeginning);
    }
}
