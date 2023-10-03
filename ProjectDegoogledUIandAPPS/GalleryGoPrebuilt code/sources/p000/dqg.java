package p000;

import p003j$.util.Optional;

/* renamed from: dqg */
/* compiled from: PG */
final /* synthetic */ class dqg implements hpr {

    /* renamed from: a */
    private final dpz f7104a;

    public dqg(dpz dpz) {
        this.f7104a = dpz;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        dpz dpz = this.f7104a;
        Optional optional = (Optional) obj;
        if (!optional.isPresent()) {
            dpz.mo4337a(true);
        }
        if (optional != null) {
            dpz.f7044b = optional;
            return dpz;
        }
        throw new NullPointerException("Null initialMedia");
    }
}
