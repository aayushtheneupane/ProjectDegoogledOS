package p000;

import android.app.Dialog;
import android.content.DialogInterface;

/* renamed from: ez */
/* compiled from: PG */
final class C0138ez implements DialogInterface.OnDismissListener {

    /* renamed from: a */
    private final /* synthetic */ C0140fa f9208a;

    public C0138ez(C0140fa faVar) {
        this.f9208a = faVar;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        C0140fa faVar = this.f9208a;
        Dialog dialog = faVar.f9240d;
        if (dialog != null) {
            faVar.onDismiss(dialog);
        }
    }
}
