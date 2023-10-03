package androidx.fragment.app;

import android.app.Dialog;

/* renamed from: androidx.fragment.app.c */
class C0411c implements Runnable {
    final /* synthetic */ DialogFragment this$0;

    C0411c(DialogFragment dialogFragment) {
        this.this$0 = dialogFragment;
    }

    public void run() {
        DialogFragment dialogFragment = this.this$0;
        Dialog dialog = dialogFragment.mDialog;
        if (dialog != null) {
            dialogFragment.onDismiss(dialog);
        }
    }
}
