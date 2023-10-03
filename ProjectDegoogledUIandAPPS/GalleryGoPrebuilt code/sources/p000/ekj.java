package p000;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

/* renamed from: ekj */
/* compiled from: PG */
public final class ekj extends C0140fa {

    /* renamed from: Z */
    public Dialog f8473Z;

    /* renamed from: aa */
    public DialogInterface.OnCancelListener f8474aa;

    public final void onCancel(DialogInterface dialogInterface) {
        DialogInterface.OnCancelListener onCancelListener = this.f8474aa;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialogInterface);
        }
    }

    /* renamed from: c */
    public final Dialog mo193c(Bundle bundle) {
        Dialog dialog = this.f8473Z;
        if (dialog == null) {
            this.f9239c = false;
        }
        return dialog;
    }
}
