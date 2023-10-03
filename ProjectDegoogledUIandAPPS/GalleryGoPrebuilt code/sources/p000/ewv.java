package p000;

import java.util.concurrent.Executor;

/* renamed from: ewv */
/* compiled from: PG */
final class ewv implements exc {

    /* renamed from: a */
    public final Object f9149a = new Object();

    /* renamed from: b */
    public final eww f9150b;

    /* renamed from: c */
    private final Executor f9151c;

    public ewv(Executor executor, eww eww) {
        this.f9151c = executor;
        this.f9150b = eww;
    }

    /* renamed from: a */
    public final void mo5353a(exb exb) {
        if (!exb.mo5359a()) {
            boolean z = exb.f9161d;
            synchronized (this.f9149a) {
            }
            this.f9151c.execute(new ewu(this, exb));
        }
    }
}
