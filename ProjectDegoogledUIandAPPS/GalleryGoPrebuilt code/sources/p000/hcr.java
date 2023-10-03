package p000;

import java.util.Set;

/* renamed from: hcr */
/* compiled from: PG */
public final class hcr implements hxp {

    /* renamed from: a */
    private final iqk f12485a;

    public hcr(iqk iqk) {
        this.f12485a = iqk;
    }

    /* renamed from: a */
    public final hxa mo7281a(String str) {
        hsj j = hso.m12048j();
        for (hxp a : (Set) this.f12485a.mo2097a()) {
            j.mo7908c(a.mo7281a(str));
        }
        return new ifv(str, j.mo7905a());
    }
}
