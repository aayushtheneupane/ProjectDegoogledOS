package com.android.contacts.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import com.android.contacts.R;

public class DialogManager {
    /* access modifiers changed from: private */
    public final Activity mActivity;
    private boolean mUseDialogId2 = false;

    public interface DialogShowingView {
        Dialog createDialog(Bundle bundle);
    }

    public interface DialogShowingViewActivity {
        DialogManager getDialogManager();
    }

    public static final boolean isManagedId(int i) {
        return i == R.id.dialog_manager_id_1 || i == R.id.dialog_manager_id_2;
    }

    public DialogManager(Activity activity) {
        if (activity != null) {
            this.mActivity = activity;
            return;
        }
        throw new IllegalArgumentException("activity must not be null");
    }

    public void showDialogInView(View view, Bundle bundle) {
        int id = view.getId();
        if (bundle.containsKey("view_id")) {
            throw new IllegalArgumentException("Bundle already contains a view_id");
        } else if (id != -1) {
            bundle.putInt("view_id", id);
            this.mActivity.showDialog(this.mUseDialogId2 ? R.id.dialog_manager_id_2 : R.id.dialog_manager_id_1, bundle);
        } else {
            throw new IllegalArgumentException("View does not have a proper ViewId");
        }
    }

    public Dialog onCreateDialog(final int i, Bundle bundle) {
        if (i == R.id.dialog_manager_id_1) {
            this.mUseDialogId2 = true;
        } else if (i != R.id.dialog_manager_id_2) {
            return null;
        } else {
            this.mUseDialogId2 = false;
        }
        if (bundle.containsKey("view_id")) {
            View findViewById = this.mActivity.findViewById(bundle.getInt("view_id"));
            if (findViewById == null || !(findViewById instanceof DialogShowingView)) {
                return null;
            }
            Dialog createDialog = ((DialogShowingView) findViewById).createDialog(bundle);
            if (createDialog == null) {
                return createDialog;
            }
            createDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                public void onDismiss(DialogInterface dialogInterface) {
                    DialogManager.this.mActivity.removeDialog(i);
                }
            });
            return createDialog;
        }
        throw new IllegalArgumentException("Bundle does not contain a ViewId");
    }
}
