package p000;

import android.app.Dialog;

/* renamed from: els */
/* compiled from: PG */
final class els extends enr {

    /* renamed from: a */
    private final /* synthetic */ Dialog f8522a;

    /* renamed from: b */
    private final /* synthetic */ elt f8523b;

    public els(elt elt, Dialog dialog) {
        this.f8523b = elt;
        this.f8522a = dialog;
    }

    /* renamed from: a */
    public final void mo4982a() {
        this.f8523b.f8524a.mo4986b();
        if (this.f8522a.isShowing()) {
            this.f8522a.dismiss();
        }
    }
}
