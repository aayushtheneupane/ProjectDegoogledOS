package p000;

import java.lang.ref.WeakReference;

/* renamed from: emz */
/* compiled from: PG */
final class emz extends enr {

    /* renamed from: a */
    private final WeakReference f8598a;

    public emz(ena ena) {
        this.f8598a = new WeakReference(ena);
    }

    /* renamed from: a */
    public final void mo4982a() {
        ena ena = (ena) this.f8598a.get();
        if (ena != null) {
            ena.mo5032e();
        }
    }
}
