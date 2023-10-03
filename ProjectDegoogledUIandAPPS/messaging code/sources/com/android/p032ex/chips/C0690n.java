package com.android.p032ex.chips;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.os.Bundle;

/* renamed from: com.android.ex.chips.n */
public class C0690n extends DialogFragment implements DialogInterface.OnClickListener {
    private String mText;

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            ((ClipboardManager) getActivity().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText((CharSequence) null, this.mText));
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        this.mText = getArguments().getString("text");
        return new AlertDialog.Builder(getActivity()).setMessage(this.mText).setPositiveButton(C0642J.chips_action_copy, this).setNegativeButton(C0642J.chips_action_cancel, (DialogInterface.OnClickListener) null).create();
    }
}
