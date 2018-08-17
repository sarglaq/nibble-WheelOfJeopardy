package com.nibble.wheelofjeopardy.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.nibble.wheelofjeopardy.R;
import com.nibble.wheelofjeopardy.game.GameManager;
import com.nibble.wheelofjeopardy.questionBoard.Category;

import java.util.Vector;

public class ChooseCategoryDialog extends DialogFragment {

    private boolean playersChoice = true;

    public void setPlayersChoice(boolean playersChoice) {
        this.playersChoice = playersChoice;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle(playersChoice ? R.string.players_choice:R.string.opponents_choice);

        final Vector<Category> categories = new Vector<>();
        for (Category category: Category.values()) {
            if (GameManager.getGame().anyQuestionsRemaining(category)) {
                categories.add(category);
            }
        }

        final String[] categoryNames = new String[categories.size()];
        for (int i = 0; i < categories.size(); i++) {
            categoryNames[i] = categories.get(i).getName();
        }

        dialogBuilder.setItems(categoryNames, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Category selectedCategory = categories.get(i);
                if (getActivity() instanceof WheelActivity) {
                    System.out.println("Selected " + selectedCategory.name());
                    ((WheelActivity) getActivity()).askQuesiton(selectedCategory);
                }
            }
        });
        return dialogBuilder.create();
    }
}
