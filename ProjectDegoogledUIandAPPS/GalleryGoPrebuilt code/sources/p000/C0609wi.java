package p000;

import android.view.View;

/* renamed from: wi */
/* compiled from: PG */
final class C0609wi implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ C0616wp f16235a;

    public C0609wi(C0616wp wpVar) {
        this.f16235a = wpVar;
    }

    public final void run() {
        View view = this.f16235a.f16254l;
        if (view != null && view.getWindowToken() != null) {
            this.f16235a.mo9805ab();
        }
    }
}
