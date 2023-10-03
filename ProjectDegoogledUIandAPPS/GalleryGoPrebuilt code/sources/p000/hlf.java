package p000;

import android.os.Build;
import android.support.p002v7.widget.RecyclerView;
import java.lang.ref.Reference;
import java.util.UUID;

/* renamed from: hlf */
/* compiled from: PG */
final class hlf extends hku {

    /* renamed from: a */
    private final hng f12960a;

    /* renamed from: b */
    private final hle f12961b;

    /* renamed from: c */
    private final exm f12962c;

    /* renamed from: d */
    private final int f12963d;

    /* renamed from: e */
    private final boolean f12964e;

    /* renamed from: f */
    private final boolean f12965f;

    private hlf(hng hng, hlf hlf) {
        super(hng.f13087b, (hlp) hlf, hng.f13089d);
        this.f12960a = hng;
        this.f12961b = hlf.f12961b;
        this.f12962c = hlf.f12962c;
        this.f12963d = hlf.f12963d;
        this.f12965f = hlf.f12965f;
        this.f12964e = hlf.f12964e;
    }

    /* renamed from: e */
    public final boolean mo7546e() {
        return this.f12965f;
    }

    public hlf(hng hng, UUID uuid, hle hle, exm exm, long j, boolean z, boolean z2) {
        super(hng.f13087b, uuid, hng.f13089d);
        this.f12960a = hng;
        this.f12961b = hle;
        this.f12962c = exm;
        this.f12963d = (int) j;
        this.f12965f = z;
        this.f12964e = z2;
    }

    /* renamed from: a */
    public final void mo7544a(int i) {
        this.f12960a.addAndGet(i);
        m11685f();
    }

    /* renamed from: a */
    public final hlp mo7543a(String str, hln hln) {
        hle hle = this.f12961b;
        hng hng = new hng(this.f12960a, str, m11684d(), hln);
        while (true) {
            hni hni = (hni) hle;
            hng hng2 = (hng) hni.f13099a.get();
            int i = hng2.f13090e + 1;
            if (i >= 500) {
                hng.mo7604a(-1, (hng) null);
                synchronized (hle) {
                    ((hni) hle).f13101c++;
                    break;
                }
            }
            hng.mo7604a(i, hng2);
            if (hni.f13099a.compareAndSet(hng2, hng)) {
                break;
            }
        }
        return new hlf(hng, this);
    }

    /* renamed from: f */
    private final void m11685f() {
        if (Build.VERSION.SDK_INT >= 28) {
            Reference.reachabilityFence(this);
        }
    }

    /* renamed from: d */
    private final int m11684d() {
        return ((int) (this.f12964e ? this.f12962c.mo5374e() : this.f12962c.mo5372c())) - this.f12963d;
    }

    /* renamed from: a */
    public final void mo7545a(boolean z) {
        int i;
        int d = m11684d();
        hng hng = this.f12960a;
        int i2 = d - hng.f13088c;
        ife.m12896d(!hng.mo7605a());
        if (!z) {
            i = 0;
        } else {
            i = 1073741824;
        }
        hng.f13092g = i | RecyclerView.UNDEFINED_DURATION | (i2 & 1073741823);
        m11685f();
    }
}
