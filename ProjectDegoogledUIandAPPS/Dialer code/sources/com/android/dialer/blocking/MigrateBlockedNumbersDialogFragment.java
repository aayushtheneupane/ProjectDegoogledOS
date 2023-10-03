package com.android.dialer.blocking;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import com.android.dialer.R;
import com.android.dialer.blocking.BlockedNumbersMigrator;
import java.util.Objects;

@Deprecated
public class MigrateBlockedNumbersDialogFragment extends DialogFragment {
    /* access modifiers changed from: private */
    public BlockedNumbersMigrator blockedNumbersMigrator;
    /* access modifiers changed from: private */
    public BlockedNumbersMigrator.Listener migrationListener;

    public static DialogFragment newInstance(BlockedNumbersMigrator blockedNumbersMigrator2, BlockedNumbersMigrator.Listener listener) {
        MigrateBlockedNumbersDialogFragment migrateBlockedNumbersDialogFragment = new MigrateBlockedNumbersDialogFragment();
        migrateBlockedNumbersDialogFragment.blockedNumbersMigrator = (BlockedNumbersMigrator) Objects.requireNonNull(blockedNumbersMigrator2);
        migrateBlockedNumbersDialogFragment.migrationListener = (BlockedNumbersMigrator.Listener) Objects.requireNonNull(listener);
        return migrateBlockedNumbersDialogFragment;
    }

    /* access modifiers changed from: private */
    public View.OnClickListener newPositiveButtonOnClickListener(final AlertDialog alertDialog) {
        return new View.OnClickListener() {
            public void onClick(View view) {
                alertDialog.getButton(-1).setEnabled(false);
                alertDialog.getButton(-2).setEnabled(false);
                MigrateBlockedNumbersDialogFragment.this.blockedNumbersMigrator.migrate(new BlockedNumbersMigrator.Listener() {
                    public void onComplete() {
                        alertDialog.dismiss();
                        MigrateBlockedNumbersDialogFragment.this.migrationListener.onComplete();
                    }
                });
            }
        };
    }

    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreateDialog(bundle);
        AlertDialog create = new AlertDialog.Builder(getActivity()).setTitle(R.string.migrate_blocked_numbers_dialog_title).setMessage(R.string.migrate_blocked_numbers_dialog_message).setPositiveButton(R.string.migrate_blocked_numbers_dialog_allow_button, (DialogInterface.OnClickListener) null).setNegativeButton(R.string.migrate_blocked_numbers_dialog_cancel_button, (DialogInterface.OnClickListener) null).create();
        create.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                AlertDialog alertDialog = (AlertDialog) dialogInterface;
                alertDialog.getButton(-1).setOnClickListener(MigrateBlockedNumbersDialogFragment.this.newPositiveButtonOnClickListener(alertDialog));
            }
        });
        return create;
    }

    public void onPause() {
        dismiss();
        this.blockedNumbersMigrator = null;
        this.migrationListener = null;
        super.onPause();
    }
}
