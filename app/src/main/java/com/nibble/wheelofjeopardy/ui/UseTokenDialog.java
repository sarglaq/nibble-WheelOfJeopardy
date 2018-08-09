package com.nibble.wheelofjeopardy.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.nibble.wheelofjeopardy.R;
import com.nibble.wheelofjeopardy.game.GameManager;

public class UseTokenDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("You have ");
        messageBuilder.append(GameManager.getInstance().getGame().getCurrentPlayer().getFreeSpins());
        messageBuilder.append(" tokens, would you like to use one and take another turn?");
        dialogBuilder.setMessage(messageBuilder.toString());
        dialogBuilder.setPositiveButton(R.string.yes_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                GameManager.getInstance().getGame().endTurn(false);
            }
        });
        dialogBuilder.setPositiveButton(R.string.no_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                GameManager.getInstance().getGame().endTurn(true);
            }
        });
        return dialogBuilder.create();
    }
}
