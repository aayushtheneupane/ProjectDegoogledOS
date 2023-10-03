package com.android.contacts.vcard;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import com.android.contacts.R;

public class ImportVCardDialogFragment extends DialogFragment {

    public interface Listener {
        void onImportVCardConfirmed(Uri uri, String str);

        void onImportVCardDenied();
    }

    public static void show(Activity activity, Uri uri, String str) {
        if (activity instanceof Listener) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("sourceUri", uri);
            bundle.putString("sourceDisplayName", str);
            ImportVCardDialogFragment importVCardDialogFragment = new ImportVCardDialogFragment();
            importVCardDialogFragment.setArguments(bundle);
            importVCardDialogFragment.show(activity.getFragmentManager(), "importVCardDialog");
            return;
        }
        throw new IllegalArgumentException("Activity must implement " + Listener.class.getName());
    }

    public Dialog onCreateDialog(Bundle bundle) {
        final Uri uri = (Uri) getArguments().getParcelable("sourceUri");
        final String string = getArguments().getString("sourceDisplayName");
        return new AlertDialog.Builder(getActivity()).setIconAttribute(16843605).setMessage(R.string.import_from_vcf_file_confirmation_message).setPositiveButton(17039379, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Listener listener = (Listener) ImportVCardDialogFragment.this.getActivity();
                if (listener != null) {
                    listener.onImportVCardConfirmed(uri, string);
                }
            }
        }).setNegativeButton(17039369, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Listener listener = (Listener) ImportVCardDialogFragment.this.getActivity();
                if (listener != null) {
                    listener.onImportVCardDenied();
                }
            }
        }).create();
    }
}
