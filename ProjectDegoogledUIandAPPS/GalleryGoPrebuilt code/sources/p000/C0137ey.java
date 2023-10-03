package p000;

import android.app.Dialog;
import android.content.DialogInterface;

/* renamed from: ey */
/* compiled from: PG */
final class C0137ey implements DialogInterface.OnCancelListener {

    /* renamed from: a */
    private final /* synthetic */ C0140fa f9197a;

    public C0137ey(C0140fa faVar) {
        this.f9197a = faVar;
    }

    public final void onCancel(DialogInterface dialogInterface) {
        C0140fa faVar = this.f9197a;
        Dialog dialog = faVar.f9240d;
        if (dialog != null) {
            faVar.onCancel(dialog);
        }
    }
}
