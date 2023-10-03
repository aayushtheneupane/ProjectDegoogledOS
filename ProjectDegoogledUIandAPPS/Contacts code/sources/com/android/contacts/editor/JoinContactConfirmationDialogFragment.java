package com.android.contacts.editor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import com.android.contacts.R;

public class JoinContactConfirmationDialogFragment extends DialogFragment {
    /* access modifiers changed from: private */
    public long mContactId;

    public interface Listener {
        void onJoinContactConfirmed(long j);
    }

    public static void show(ContactEditorFragment contactEditorFragment, long j) {
        Bundle bundle = new Bundle();
        bundle.putLong("joinContactId", j);
        JoinContactConfirmationDialogFragment joinContactConfirmationDialogFragment = new JoinContactConfirmationDialogFragment();
        joinContactConfirmationDialogFragment.setTargetFragment(contactEditorFragment, 0);
        joinContactConfirmationDialogFragment.setArguments(bundle);
        joinContactConfirmationDialogFragment.show(contactEditorFragment.getFragmentManager(), "joinContactConfirmationDialog");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContactId = getArguments().getLong("joinContactId");
    }

    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.joinConfirmation);
        builder.setPositiveButton(R.string.joinConfirmation_positive_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ((Listener) JoinContactConfirmationDialogFragment.this.getTargetFragment()).onJoinContactConfirmed(JoinContactConfirmationDialogFragment.this.mContactId);
            }
        });
        builder.setNegativeButton(17039360, (DialogInterface.OnClickListener) null);
        builder.setCancelable(false);
        return builder.create();
    }
}
