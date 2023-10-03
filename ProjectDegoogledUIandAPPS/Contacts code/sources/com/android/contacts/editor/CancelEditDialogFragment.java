package com.android.contacts.editor;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import com.android.contacts.R;

public class CancelEditDialogFragment extends DialogFragment {

    public interface Listener {
        void onCancelEditConfirmed();
    }

    public static void show(ContactEditorFragment contactEditorFragment) {
        CancelEditDialogFragment cancelEditDialogFragment = new CancelEditDialogFragment();
        cancelEditDialogFragment.setTargetFragment(contactEditorFragment, 0);
        cancelEditDialogFragment.show(contactEditorFragment.getFragmentManager(), "cancelEditor");
    }

    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIconAttribute(16843605);
        builder.setMessage(R.string.cancel_confirmation_dialog_message);
        builder.setPositiveButton(R.string.cancel_confirmation_dialog_cancel_editing_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ((Listener) CancelEditDialogFragment.this.getTargetFragment()).onCancelEditConfirmed();
            }
        });
        builder.setNegativeButton(R.string.cancel_confirmation_dialog_keep_editing_button, (DialogInterface.OnClickListener) null);
        return builder.create();
    }
}
