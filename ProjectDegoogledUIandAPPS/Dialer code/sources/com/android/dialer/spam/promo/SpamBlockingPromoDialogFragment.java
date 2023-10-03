package com.android.dialer.spam.promo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.p000v4.app.DialogFragment;
import android.support.p002v7.app.AlertDialog;
import com.android.dialer.R;

public class SpamBlockingPromoDialogFragment extends DialogFragment {
    protected DialogInterface.OnDismissListener dismissListener;
    protected OnEnableListener positiveListener;

    public interface OnEnableListener {
        void onClick();
    }

    public /* synthetic */ void lambda$onCreateDialog$0$SpamBlockingPromoDialogFragment(DialogInterface dialogInterface, int i) {
        dismiss();
    }

    public /* synthetic */ void lambda$onCreateDialog$1$SpamBlockingPromoDialogFragment(DialogInterface dialogInterface, int i) {
        dismiss();
        this.positiveListener.onClick();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreateDialog(bundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle((int) R.string.spam_blocking_promo_title);
        builder.setMessage((int) R.string.spam_blocking_promo_text);
        builder.setNegativeButton((int) R.string.spam_blocking_promo_action_dismiss, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                SpamBlockingPromoDialogFragment.this.lambda$onCreateDialog$0$SpamBlockingPromoDialogFragment(dialogInterface, i);
            }
        });
        builder.setPositiveButton((int) R.string.spam_blocking_promo_action_filter_spam, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                SpamBlockingPromoDialogFragment.this.lambda$onCreateDialog$1$SpamBlockingPromoDialogFragment(dialogInterface, i);
            }
        });
        return builder.create();
    }

    public void onDismiss(DialogInterface dialogInterface) {
        DialogInterface.OnDismissListener onDismissListener = this.dismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialogInterface);
        }
        super.onDismiss(dialogInterface);
    }

    public void onPause() {
        dismiss();
        this.dismissListener = null;
        this.positiveListener = null;
        super.onPause();
    }
}
