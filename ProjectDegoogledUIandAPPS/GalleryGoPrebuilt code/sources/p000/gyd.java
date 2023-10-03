package p000;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

/* renamed from: gyd */
/* compiled from: PG */
public final class gyd {

    /* renamed from: a */
    private final iqk f12292a;

    /* renamed from: b */
    private final iqk f12293b;

    /* renamed from: c */
    private final iqk f12294c;

    /* renamed from: d */
    private final iqk f12295d;

    /* renamed from: e */
    private final iqk f12296e;

    /* renamed from: f */
    private final iqk f12297f;

    /* renamed from: g */
    private final iqk f12298g;

    /* renamed from: h */
    private final iqk f12299h;

    public gyd(iqk iqk, iqk iqk2, iqk iqk3, iqk iqk4, iqk iqk5, iqk iqk6, iqk iqk7, iqk iqk8) {
        this.f12292a = (iqk) m11039a(iqk, 1);
        this.f12293b = (iqk) m11039a(iqk2, 2);
        this.f12294c = (iqk) m11039a(iqk3, 3);
        this.f12295d = (iqk) m11039a(iqk4, 4);
        this.f12296e = (iqk) m11039a(iqk5, 5);
        this.f12297f = (iqk) m11039a(iqk6, 6);
        this.f12298g = (iqk) m11039a(iqk7, 7);
        this.f12299h = (iqk) m11039a(iqk8, 8);
    }

    /* renamed from: a */
    private static Object m11039a(Object obj, int i) {
        if (obj != null) {
            return obj;
        }
        StringBuilder sb = new StringBuilder(93);
        sb.append("@AutoFactory method argument is null but is not marked @Nullable. Argument index: ");
        sb.append(i);
        throw new NullPointerException(sb.toString());
    }

    /* renamed from: a */
    public final gyc mo7209a(gww gww, icf icf, Executor executor, Map map, gzm gzm, gzm gzm2, gxs gxs, icf icf2) {
        Map map2 = (Map) m11039a((Map) this.f12299h.mo2097a(), 8);
        return new gyc(((Integer) m11039a((Integer) this.f12292a.mo2097a(), 1)).intValue(), (Map) m11039a((Map) this.f12293b.mo2097a(), 2), (gzy) m11039a((gzy) this.f12294c.mo2097a(), 3), (iek) m11039a((iek) this.f12295d.mo2097a(), 4), (exm) m11039a((exm) this.f12296e.mo2097a(), 5), (Set) m11039a((Set) this.f12297f.mo2097a(), 6), (fxr) m11039a((fxr) this.f12298g.mo2097a(), 7), (gww) m11039a(gww, 9), (icf) m11039a(icf, 10), (Executor) m11039a(executor, 11), (Map) m11039a(map, 12), (gzm) m11039a(gzm, 13), (gzm) m11039a(gzm2, 14), (gxs) m11039a(gxs, 15), (icf) m11039a(icf2, 16));
    }
}
