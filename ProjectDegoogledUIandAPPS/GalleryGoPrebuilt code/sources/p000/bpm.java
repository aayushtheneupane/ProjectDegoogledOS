package p000;

import java.util.concurrent.Executor;

/* renamed from: bpm */
/* compiled from: PG */
public final class bpm implements ice {

    /* renamed from: a */
    public final Object f3310a = new Object();

    /* renamed from: b */
    public ieh f3311b = null;

    /* renamed from: c */
    public ieh f3312c = null;

    /* renamed from: d */
    private final ice f3313d;

    /* renamed from: e */
    private final Executor f3314e;

    public bpm(ice ice, Executor executor) {
        this.f3313d = ice;
        this.f3314e = executor;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        synchronized (this.f3310a) {
            ieh ieh = this.f3311b;
            if (ieh == null) {
                ieh b = mo2648b();
                this.f3311b = b;
                return b;
            }
            if (this.f3312c == null) {
                this.f3312c = gte.m10769a(ieh).mo7611a((ice) new bpk(this), this.f3314e);
            }
            ieh ieh2 = this.f3312c;
            return ieh2;
        }
    }

    /* renamed from: b */
    public final ieh mo2648b() {
        ieh a;
        synchronized (this.f3310a) {
            try {
                ieh a2 = this.f3313d.mo2539a();
                a = gte.m10769a(a2).mo7611a((ice) new bpl(this, a2), this.f3314e);
            } catch (Exception e) {
                return ife.m12822a((Throwable) e);
            } catch (Throwable th) {
                throw th;
            }
        }
        return a;
    }
}
