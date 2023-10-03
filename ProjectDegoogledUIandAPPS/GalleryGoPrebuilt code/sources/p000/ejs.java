package p000;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/* renamed from: ejs */
/* compiled from: PG */
public final class ejs extends DialogFragment {

    /* renamed from: a */
    public Dialog f8447a;

    /* renamed from: b */
    public DialogInterface.OnCancelListener f8448b;

    public final void onCancel(DialogInterface dialogInterface) {
        DialogInterface.OnCancelListener onCancelListener = this.f8448b;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialogInterface);
        }
    }

    public final Dialog onCreateDialog(Bundle bundle) {
        if (this.f8447a == null) {
            setShowsDialog(false);
        }
        return this.f8447a;
    }
}
