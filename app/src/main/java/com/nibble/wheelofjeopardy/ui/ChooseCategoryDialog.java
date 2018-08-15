package com.nibble.wheelofjeopardy.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.nibble.wheelofjeopardy.R;
import com.nibble.wheelofjeopardy.questionBoard.Category;

public class ChooseCategoryDialog extends DialogFragment {

    private boolean playersChoice = true;

    public void setPlayersChoice(boolean playersChoice) {
        this.playersChoice = playersChoice;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle(playersChoice ? R.string.players_choice:R.string.opponents_choice);

        final String[] categories = {
                Category.CATEGORY_ONE.getName(),
                Category.CATEGORY_TWO.getName(),
                Category.CATEGORY_THREE.getName(),
                Category.CATEGORY_FOUR.getName(),
                Category.CATEGORY_FIVE.getName(),
                Category.CATEGORY_SIX.getName(),
        };

        dialogBuilder.setItems(categories, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Category selectedCategory;
                switch (i) {
                    case 0:
                        selectedCategory = Category.CATEGORY_ONE;
                        break;
                    case 1:
                        selectedCategory = Category.CATEGORY_TWO;
                        break;
                    case 2:
                        selectedCategory = Category.CATEGORY_THREE;
                        break;
                    case 3:
                        selectedCategory = Category.CATEGORY_FOUR;
                        break;
                    case 4:
                        selectedCategory = Category.CATEGORY_FIVE;
                        break;
                    case 5:
                    default:
                        selectedCategory = Category.CATEGORY_SIX;
                        break;
                }
                if (getActivity() instanceof WheelActivity) {
                    System.out.println("Selected " + selectedCategory.name());
                    ((WheelActivity) getActivity()).askQuesiton(selectedCategory);
                }
            }
        });
        return dialogBuilder.create();
    }
}
