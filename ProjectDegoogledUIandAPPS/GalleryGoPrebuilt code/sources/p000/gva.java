package p000;

import android.support.p002v7.widget.RecyclerView;

/* renamed from: gva */
/* compiled from: PG */
abstract class gva {
    /* renamed from: a */
    public abstract gud mo7060a();

    /* renamed from: b */
    public abstract long mo7061b();

    /* renamed from: c */
    public abstract gul mo7062c();

    /* renamed from: d */
    public abstract gup mo7063d();

    /* renamed from: e */
    public abstract int mo7064e();

    /* renamed from: f */
    public abstract long mo7066f();

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo7106a(gva gva) {
        ife.m12896d(mo7061b() != Long.MIN_VALUE);
        ife.m12896d(!equals(gva) || this == gva);
        gty gty = (gty) gva;
        if (mo7061b() >= gty.f12054b) {
            if (mo7061b() != gty.f12054b) {
                return false;
            }
            if (mo7062c().mo7044a() >= gty.f12055c.mo7044a()) {
                if (mo7063d().mo7048a() < gty.f12056d.mo7048a()) {
                    return true;
                }
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final gva mo7105a(gud gud, long j) {
        boolean z;
        if (mo7061b() != RecyclerView.FOREVER_NS) {
            z = true;
        } else {
            z = false;
        }
        ife.m12876b(z, (Object) "You've just overflowed a long. Consider upgrading to a BigDecimal, if this happens more than once.");
        long b = mo7061b() + 1;
        return new gty(gud, b, gul.m10830b(), gup.m10836b(), 0, j);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public final boolean mo7107g() {
        return mo7064e() > 5;
    }
}
