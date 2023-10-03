package p000;

import java.util.concurrent.Executor;

/* renamed from: ewy */
/* compiled from: PG */
final class ewy implements exc {

    /* renamed from: a */
    public final Object f9154a = new Object();

    /* renamed from: b */
    public final ewz f9155b;

    /* renamed from: c */
    private final Executor f9156c;

    public ewy(Executor executor, ewz ewz) {
        this.f9156c = executor;
        this.f9155b = ewz;
    }

    /* renamed from: a */
    public final void mo5353a(exb exb) {
        if (exb.mo5359a()) {
            synchronized (this.f9154a) {
            }
            this.f9156c.execute(new ewx(this, exb));
        }
    }
}
