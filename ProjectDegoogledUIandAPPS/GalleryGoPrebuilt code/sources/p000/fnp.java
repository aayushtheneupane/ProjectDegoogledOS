package p000;

/* renamed from: fnp */
/* compiled from: PG */
final class fnp extends fns {

    /* renamed from: a */
    public static final fnp f10108a = new fnp();

    private fnp() {
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ ikf mo5985a(String str, Object obj) {
        int intValue = ((Long) obj).intValue();
        if (intValue == 0) {
            return null;
        }
        iir g = iqp.f14676d.mo8793g();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        iqp iqp = (iqp) g.f14318b;
        iqp.f14678a |= 1;
        iqp.f14679b = intValue;
        if (str != null) {
            iqq a = fpt.m9374a(str);
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqp iqp2 = (iqp) g.f14318b;
            a.getClass();
            iqp2.f14680c = a;
            iqp2.f14678a |= 2;
        }
        return (iqp) g.mo8770g();
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ String mo5986a(ikf ikf) {
        iqq iqq = ((iqp) ikf).f14680c;
        if (iqq == null) {
            iqq = iqq.f14681d;
        }
        return iqq.f14685c;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ ikf mo5984a(ikf ikf, ikf ikf2) {
        iqp iqp = (iqp) ikf;
        iqp iqp2 = (iqp) ikf2;
        if (iqp == null || iqp2 == null) {
            return iqp;
        }
        if ((iqp.f14678a & 1) != 0) {
            iir g = iqp.f14676d.mo8793g();
            iqq iqq = iqp.f14680c;
            if (iqq == null) {
                iqq = iqq.f14681d;
            }
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqp iqp3 = (iqp) g.f14318b;
            iqq.getClass();
            iqp3.f14680c = iqq;
            int i = iqp3.f14678a | 2;
            iqp3.f14678a = i;
            int i2 = iqp.f14679b - iqp2.f14679b;
            if (i2 != 0) {
                iqp3.f14678a = i | 1;
                iqp3.f14679b = i2;
                return (iqp) g.mo8770g();
            }
        }
        return null;
    }
}
