package com.nibble.wheelofjeopardy.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.nibble.wheelofjeopardy.R;
import com.nibble.wheelofjeopardy.game.GameManager;
import com.nibble.wheelofjeopardy.questionBoard.Category;
import com.nibble.wheelofjeopardy.wheel.Sector;

public class WheelResultDialog extends DialogFragment {

    private Context context = null;
    private String message = "";
    private Sector sector = null;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
        String messageRes;

        switch (sector) {
            case CATEGORY_ONE:
                messageRes = context.getString(R.string.answer_question);
                message = String.format(messageRes, Category.CATEGORY_ONE.getName());
                break;
            case CATEGORY_TWO:
                messageRes = context.getString(R.string.answer_question);
                message = String.format(messageRes, Category.CATEGORY_TWO.getName());
                break;
            case CATEGORY_THREE:
                messageRes = context.getString(R.string.answer_question);
                message = String.format(messageRes, Category.CATEGORY_THREE.getName());
                break;
            case CATEGORY_FOUR:
                messageRes = context.getString(R.string.answer_question);
                message = String.format(messageRes, Category.CATEGORY_FOUR.getName());
                break;
            case CATEGORY_FIVE:
                messageRes = context.getString(R.string.answer_question);
                message = String.format(messageRes, Category.CATEGORY_FIVE.getName());
                break;
            case CATEGORY_SIX:
                messageRes = context.getString(R.string.answer_question);
                message = String.format(messageRes, Category.CATEGORY_SIX.getName());
                break;
            case PLAYERS_CHOICE:
                message = context.getString(R.string.you_pick);
                break;
            case OPPONENTS_CHOICE:
                message = context.getString(R.string.they_pick);
                break;
            case FREE_SPIN:
                messageRes = context.getString(R.string.won_free_spin);
                message = String.format(messageRes, GameManager.getGame().getCurrentPlayer().getFreeSpins());
                break;
            case LOOSE_TURN:
                message = context.getString(R.string.lose_turn);
                break;
            case BANKRUPT:
                message = context.getString(R.string.score_bankrupt);
                break;
            case DOUBLE_SCORE:
                message = context.getString(R.string.score_doubled);
                break;
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setMessage(message);
        dialogBuilder.setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (getActivity() instanceof WheelActivity) {
                    ((WheelActivity) getActivity()).performWheelAction(sector);
                }
            }
        });
        return dialogBuilder.create();
    }
}
