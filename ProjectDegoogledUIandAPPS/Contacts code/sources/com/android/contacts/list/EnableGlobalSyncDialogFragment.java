package com.android.contacts.list;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import com.android.contacts.R;

public class EnableGlobalSyncDialogFragment extends DialogFragment {
    /* access modifiers changed from: private */
    public ContactListFilter mFilter;

    public interface Listener {
        void onEnableAutoSync(ContactListFilter contactListFilter);
    }

    public static void show(DefaultContactBrowseListFragment defaultContactBrowseListFragment, ContactListFilter contactListFilter) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("filter", contactListFilter);
        EnableGlobalSyncDialogFragment enableGlobalSyncDialogFragment = new EnableGlobalSyncDialogFragment();
        enableGlobalSyncDialogFragment.setTargetFragment(defaultContactBrowseListFragment, 0);
        enableGlobalSyncDialogFragment.setArguments(bundle);
        enableGlobalSyncDialogFragment.show(defaultContactBrowseListFragment.getFragmentManager(), "globalSync");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFilter = (ContactListFilter) getArguments().getParcelable("filter");
    }

    public Dialog onCreateDialog(Bundle bundle) {
        final Listener listener = (Listener) getTargetFragment();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.turn_auto_sync_on_dialog_title).setMessage(R.string.turn_auto_sync_on_dialog_body).setPositiveButton(R.string.turn_auto_sync_on_dialog_confirm_btn, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Listener listener = listener;
                if (listener != null) {
                    listener.onEnableAutoSync(EnableGlobalSyncDialogFragment.this.mFilter);
                }
            }
        });
        builder.setNegativeButton(17039360, (DialogInterface.OnClickListener) null);
        builder.setCancelable(false);
        return builder.create();
    }
}
