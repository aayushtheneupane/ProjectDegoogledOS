package com.android.contacts.editor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import com.android.contacts.ContactSaveService;
import com.android.contacts.R;

public class SplitContactConfirmationDialogFragment extends DialogFragment {
    /* access modifiers changed from: private */
    public boolean mHasPendingChanges;

    public interface Listener {
        void onSplitContactCanceled();

        void onSplitContactConfirmed(boolean z);
    }

    public static void show(ContactEditorFragment contactEditorFragment, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("hasPendingChanges", z);
        SplitContactConfirmationDialogFragment splitContactConfirmationDialogFragment = new SplitContactConfirmationDialogFragment();
        splitContactConfirmationDialogFragment.setTargetFragment(contactEditorFragment, 0);
        splitContactConfirmationDialogFragment.setArguments(bundle);
        splitContactConfirmationDialogFragment.show(contactEditorFragment.getFragmentManager(), ContactSaveService.ACTION_SPLIT_CONTACT);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mHasPendingChanges = getArguments() != null && getArguments().getBoolean("hasPendingChanges");
    }

    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(this.mHasPendingChanges ? R.string.splitConfirmationWithPendingChanges : R.string.splitConfirmation);
        builder.setPositiveButton(this.mHasPendingChanges ? R.string.splitConfirmationWithPendingChanges_positive_button : R.string.splitConfirmation_positive_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                SplitContactConfirmationDialogFragment.this.getListener().onSplitContactConfirmed(SplitContactConfirmationDialogFragment.this.mHasPendingChanges);
            }
        });
        builder.setNegativeButton(17039360, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                SplitContactConfirmationDialogFragment.this.onCancel(dialogInterface);
            }
        });
        builder.setCancelable(false);
        return builder.create();
    }

    /* access modifiers changed from: private */
    public Listener getListener() {
        if (getTargetFragment() == null) {
            return (Listener) getActivity();
        }
        return (Listener) getTargetFragment();
    }

    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        getListener().onSplitContactCanceled();
    }
}
