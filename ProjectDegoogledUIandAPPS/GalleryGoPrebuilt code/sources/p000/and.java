package p000;

import androidx.work.impl.workers.ConstraintTrackingWorker;

/* renamed from: and */
/* compiled from: PG */
final class and implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ ieh f813a;

    /* renamed from: b */
    private final /* synthetic */ ConstraintTrackingWorker f814b;

    public and(ConstraintTrackingWorker constraintTrackingWorker, ieh ieh) {
        this.f814b = constraintTrackingWorker;
        this.f813a = ieh;
    }

    public final void run() {
        synchronized (this.f814b.f1190f) {
            if (!this.f814b.f1191g) {
                this.f814b.f1193i.mo658b(this.f813a);
            } else {
                this.f814b.mo1245h();
            }
        }
    }
}
