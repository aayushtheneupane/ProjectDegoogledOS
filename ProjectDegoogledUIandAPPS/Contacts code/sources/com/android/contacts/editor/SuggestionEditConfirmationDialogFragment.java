package com.android.contacts.editor;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import com.android.contacts.R;

public class SuggestionEditConfirmationDialogFragment extends DialogFragment {
    public static void show(ContactEditorFragment contactEditorFragment, Uri uri, long j) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("contactUri", uri);
        bundle.putLong("rawContactId", j);
        SuggestionEditConfirmationDialogFragment suggestionEditConfirmationDialogFragment = new SuggestionEditConfirmationDialogFragment();
        suggestionEditConfirmationDialogFragment.setArguments(bundle);
        suggestionEditConfirmationDialogFragment.setTargetFragment(contactEditorFragment, 0);
        suggestionEditConfirmationDialogFragment.show(contactEditorFragment.getFragmentManager(), "edit");
    }

    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIconAttribute(16843605);
        builder.setMessage(R.string.aggregation_suggestion_edit_dialog_message);
        builder.setPositiveButton(17039379, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ((ContactEditorFragment) SuggestionEditConfirmationDialogFragment.this.getTargetFragment()).doEditSuggestedContact((Uri) SuggestionEditConfirmationDialogFragment.this.getArguments().getParcelable("contactUri"), SuggestionEditConfirmationDialogFragment.this.getArguments().getLong("rawContactId"));
            }
        });
        builder.setNegativeButton(17039369, (DialogInterface.OnClickListener) null);
        return builder.create();
    }
}
