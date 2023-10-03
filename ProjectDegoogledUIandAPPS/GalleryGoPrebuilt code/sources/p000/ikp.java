package p000;

import java.util.concurrent.ConcurrentMap;
import p003j$.util.concurrent.ConcurrentHashMap;

/* renamed from: ikp */
/* compiled from: PG */
public final class ikp {

    /* renamed from: a */
    public static final ikp f14397a = new ikp();

    /* renamed from: b */
    private final ikz f14398b = new ijv();

    /* renamed from: c */
    private final ConcurrentMap f14399c = new ConcurrentHashMap();

    private ikp() {
    }

    /* renamed from: a */
    public final iky mo8875a(Class cls) {
        ijf.m13648a((Object) cls, "messageType");
        iky iky = (iky) this.f14399c.get(cls);
        if (iky == null) {
            ikz ikz = this.f14398b;
            ila.m13917a(cls);
            ikc b = ((ijv) ikz).f14362a.mo8742b(cls);
            if (b.mo8859a()) {
                if (iix.class.isAssignableFrom(cls)) {
                    iky = iki.m13798a(ila.f14430c, iik.f14252a, b.mo8860b());
                } else {
                    iky = iki.m13798a(ila.f14428a, iik.m13504a(), b.mo8860b());
                }
            } else if (iix.class.isAssignableFrom(cls)) {
                if (ijv.m13698a(b)) {
                    iky = ikh.m13747a(b, ikm.f14396b, ijr.f14356b, ila.f14430c, iik.f14252a, ikb.f14374b);
                } else {
                    iky = ikh.m13747a(b, ikm.f14396b, ijr.f14356b, ila.f14430c, (imi) null, ikb.f14374b);
                }
            } else if (ijv.m13698a(b)) {
                iky = ikh.m13747a(b, ikm.f14395a, ijr.f14355a, ila.f14428a, iik.m13504a(), ikb.f14373a);
            } else {
                iky = ikh.m13747a(b, ikm.f14395a, ijr.f14355a, ila.f14429b, (imi) null, ikb.f14373a);
            }
            ijf.m13648a((Object) cls, "messageType");
            ijf.m13648a((Object) iky, "schema");
            iky iky2 = (iky) this.f14399c.putIfAbsent(cls, iky);
            return iky2 == null ? iky : iky2;
        }
    }

    /* renamed from: a */
    public final iky mo8876a(Object obj) {
        return mo8875a((Class) obj.getClass());
    }
}
