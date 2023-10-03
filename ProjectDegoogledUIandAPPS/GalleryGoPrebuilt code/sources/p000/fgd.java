package p000;

import android.content.DialogInterface;

/* renamed from: fgd */
/* compiled from: PG */
public final /* synthetic */ class fgd implements DialogInterface.OnShowListener {

    /* renamed from: a */
    private final C0140fa f9503a;

    /* renamed from: b */
    private final DialogInterface.OnShowListener f9504b;

    public fgd(C0140fa faVar, DialogInterface.OnShowListener onShowListener) {
        this.f9503a = faVar;
        this.f9504b = onShowListener;
    }

    public final void onShow(DialogInterface dialogInterface) {
        C0140fa faVar = this.f9503a;
        DialogInterface.OnShowListener onShowListener = this.f9504b;
        if (dialogInterface != null && faVar.f9240d != null) {
            onShowListener.onShow(dialogInterface);
        }
    }
}
