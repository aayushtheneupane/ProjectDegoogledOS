package p000;

import p003j$.util.Optional;

/* renamed from: dqd */
/* compiled from: PG */
final /* synthetic */ class dqd implements hpr {

    /* renamed from: a */
    public static final hpr f7101a = new dqd();

    private dqd() {
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        Optional optional = (Optional) obj;
        if (!optional.isPresent()) {
            return cxd.f5884h;
        }
        iir g = cxd.f5884h.mo8793g();
        String str = (String) optional.get();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        cxd cxd = (cxd) g.f14318b;
        str.getClass();
        cxd.f5887b = 1;
        cxd.f5888c = str;
        cxd.m5587a(cxd);
        return (cxd) g.mo8770g();
    }
}
