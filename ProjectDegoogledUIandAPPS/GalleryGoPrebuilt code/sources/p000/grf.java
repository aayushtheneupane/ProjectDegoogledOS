package p000;

import android.support.p002v7.widget.RecyclerView;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: grf */
/* compiled from: PG */
public final class grf {

    /* renamed from: a */
    public final grc f11887a;

    /* renamed from: b */
    public final AtomicLong f11888b = new AtomicLong(m10657a(RecyclerView.UNDEFINED_DURATION, RecyclerView.UNDEFINED_DURATION));

    /* renamed from: c */
    public final AtomicReference f11889c = new AtomicReference((Object) null);

    /* renamed from: d */
    public final iev f11890d = iev.m12774f();

    /* renamed from: e */
    private final AtomicReference f11891e = new AtomicReference((Object) null);

    /* renamed from: f */
    private final Executor f11892f = ife.m12837a((Executor) idh.f13918a);

    public grf(ice ice, Executor executor) {
        grc grc = new grc(ice, executor);
        this.f11887a = grc;
        this.f11890d.mo53a((Runnable) grc, (Executor) idh.f13918a);
    }

    /* renamed from: a */
    public static int m10656a(long j) {
        return (int) (j >>> 32);
    }

    /* renamed from: a */
    public static long m10657a(int i, int i2) {
        return (((long) i2) & 4294967295L) | (((long) i) << 32);
    }

    /* renamed from: a */
    public final ieh mo6948a() {
        long j;
        int a;
        ieh ieh;
        if (this.f11890d.isDone()) {
            return this.f11890d;
        }
        do {
            j = this.f11888b.get();
            a = m10656a(j);
        } while (!this.f11888b.compareAndSet(j, m10657a(a, ((int) j) + 1)));
        iev f = iev.m12774f();
        ieh ieh2 = (ieh) this.f11891e.getAndSet(f);
        if (ieh2 == null) {
            ieh = ife.m12816a(hmq.m11743a((ice) new gra(this, a)), (Executor) idh.f13918a);
        } else {
            ieh = ibd.m12604a(ieh2, Throwable.class, hmq.m11744a((icf) new grb(this, a)), this.f11892f);
        }
        f.mo6946a(ieh);
        grd grd = new grd(this, a);
        f.mo53a((Runnable) new gqz(this, f, grd), (Executor) idh.f13918a);
        return grd;
    }

    /* renamed from: b */
    public final boolean mo6950b() {
        return this.f11890d.isDone();
    }

    /* renamed from: a */
    public final ieh mo6949a(int i) {
        gre gre;
        if (m10656a(this.f11888b.get()) > i) {
            return ife.m12868b();
        }
        gre gre2 = new gre(i);
        do {
            gre = (gre) this.f11889c.get();
            if (gre != null && gre.f11886a > i) {
                return ife.m12868b();
            }
        } while (!this.f11889c.compareAndSet(gre, gre2));
        if (m10656a(this.f11888b.get()) > i) {
            gre2.cancel(true);
            this.f11889c.compareAndSet(gre2, (Object) null);
            return gre2;
        }
        grc grc = this.f11887a;
        ice ice = grc.f11882a;
        Executor executor = grc.f11883b;
        if (ice == null || executor == null) {
            gre2.mo6946a(this.f11890d);
        } else {
            gre2.mo6946a(ife.m12816a(hmq.m11743a(ice), executor));
        }
        return gre2;
    }
}
