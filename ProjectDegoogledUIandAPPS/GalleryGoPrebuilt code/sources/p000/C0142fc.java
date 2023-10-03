package p000;

import android.view.View;

/* renamed from: fc */
/* compiled from: PG */
final class C0142fc implements C0627x {

    /* renamed from: a */
    private final /* synthetic */ C0147fh f9260a;

    public C0142fc(C0147fh fhVar) {
        this.f9260a = fhVar;
    }

    /* renamed from: a */
    public final void mo3a(C0681z zVar, C0546u uVar) {
        View view;
        if (uVar == C0546u.ON_STOP && (view = this.f9260a.f9573L) != null) {
            view.cancelPendingInputEvents();
        }
    }
}
