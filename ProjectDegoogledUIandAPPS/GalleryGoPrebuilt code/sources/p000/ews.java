package p000;

import java.util.concurrent.Executor;

/* renamed from: ews */
/* compiled from: PG */
public final class ews implements exc {

    /* renamed from: a */
    public final Object f9144a = new Object();

    /* renamed from: b */
    public final ewt f9145b;

    /* renamed from: c */
    private final Executor f9146c;

    public ews(Executor executor, ewt ewt) {
        this.f9146c = executor;
        this.f9145b = ewt;
    }

    /* renamed from: a */
    public final void mo5353a(exb exb) {
        synchronized (this.f9144a) {
        }
        this.f9146c.execute(new ewr(this));
    }
}
