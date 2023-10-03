package com.android.dialer.simulator.impl;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.p000v4.app.DialogFragment;
import android.widget.EditText;
import com.android.dialer.R;

public final class SimulatorDialogFragment extends DialogFragment {
    private int callerIdPresentationChoice = 1;
    private final String[] callerIdPresentationItems = {"ALLOWED", "PAYPHONE", "RESTRICTED", "UNKNOWN"};
    private DialogCallback dialogCallback;

    public interface DialogCallback {
        void createCustomizedCall(String str, int i);
    }

    static SimulatorDialogFragment newInstance(DialogCallback dialogCallback2) {
        SimulatorDialogFragment simulatorDialogFragment = new SimulatorDialogFragment();
        simulatorDialogFragment.dialogCallback = dialogCallback2;
        return simulatorDialogFragment;
    }

    public /* synthetic */ void lambda$onCreateDialog$0$SimulatorDialogFragment(DialogInterface dialogInterface, int i) {
        if (i == 0) {
            this.callerIdPresentationChoice = 1;
        } else if (i == 1) {
            this.callerIdPresentationChoice = 4;
        } else if (i == 2) {
            this.callerIdPresentationChoice = 2;
        } else if (i == 3) {
            this.callerIdPresentationChoice = 3;
        } else {
            throw new IllegalStateException("Unknown presentation choice selected!");
        }
    }

    public /* synthetic */ void lambda$onCreateDialog$1$SimulatorDialogFragment(EditText editText, DialogInterface dialogInterface, int i) {
        this.dialogCallback.createCustomizedCall(editText.getText().toString(), this.callerIdPresentationChoice);
        dialogInterface.cancel();
        dismiss();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        EditText editText = new EditText(getActivity());
        editText.setHint("Please input phone number");
        builder.setTitle("Phone Number:").setView(editText).setSingleChoiceItems(this.callerIdPresentationItems, 0, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                SimulatorDialogFragment.this.lambda$onCreateDialog$0$SimulatorDialogFragment(dialogInterface, i);
            }
        }).setPositiveButton(R.string.call, new DialogInterface.OnClickListener(editText) {
            private final /* synthetic */ EditText f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                SimulatorDialogFragment.this.lambda$onCreateDialog$1$SimulatorDialogFragment(this.f$1, dialogInterface, i);
            }
        });
        AlertDialog create = builder.create();
        create.show();
        return create;
    }
}
