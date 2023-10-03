package p000;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;
import p003j$.util.concurrent.ConcurrentHashMap;

/* renamed from: fkk */
/* compiled from: PG */
final class fkk implements fkm {

    /* renamed from: a */
    public volatile fit f9887a;

    /* renamed from: b */
    public final Queue f9888b = new ConcurrentLinkedQueue();

    /* renamed from: c */
    public final hpy f9889c;

    /* renamed from: d */
    private final AtomicReference f9890d = new AtomicReference();

    /* renamed from: e */
    private final AtomicReference f9891e = new AtomicReference();

    public fkk(boolean z) {
        hpy hpy;
        if (z) {
            hpy = hpy.m11893b(new ConcurrentHashMap());
        } else {
            hpy = hph.f13219a;
        }
        this.f9889c = hpy;
    }

    /* renamed from: a */
    public final void mo5836a(fnb fnb, iri iri) {
    }

    /* renamed from: a */
    public final void mo5899a(fit fit) {
        fkj fkj = (fkj) this.f9888b.poll();
        while (fkj != null) {
            fkj.mo5896a(fit);
            fkj = (fkj) this.f9888b.poll();
        }
    }

    /* renamed from: a */
    public final void mo5837a(fnj fnj, String str, long j, long j2, iqx iqx) {
        m9074a((fkj) new fkg(fnj, str, j, j2, iqx));
    }

    /* renamed from: a */
    public final void mo5838a(String str) {
        m9074a((fkj) new fkf(str));
    }

    /* renamed from: a */
    private final void m9074a(fkj fkj) {
        synchronized (this.f9888b) {
            if (this.f9887a == null) {
                this.f9888b.add(fkj);
            } else {
                fkj.mo5896a(this.f9887a);
            }
        }
    }

    /* renamed from: a */
    public final void mo5835a() {
        this.f9888b.clear();
    }

    /* renamed from: c */
    public final void mo5841c() {
        fki fki = new fki(Thread.getDefaultUncaughtExceptionHandler(), this.f9890d, this.f9891e);
        m9074a((fkj) fki);
        Thread.setDefaultUncaughtExceptionHandler(fki);
    }

    /* renamed from: b */
    public final void mo5839b() {
        m9074a((fkj) new fke());
    }
}
