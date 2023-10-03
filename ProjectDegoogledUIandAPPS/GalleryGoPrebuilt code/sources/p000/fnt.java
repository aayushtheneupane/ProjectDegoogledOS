package p000;

import android.os.health.HealthStats;

/* renamed from: fnt */
/* compiled from: PG */
final class fnt extends fns {

    /* renamed from: a */
    public static final fnt f10111a = new fnt();

    private fnt() {
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ ikf mo5985a(String str, Object obj) {
        HealthStats healthStats = (HealthStats) obj;
        iir g = iqu.f14704e.mo8793g();
        int a = (int) fpt.m9373a(healthStats, 50001);
        if (a != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqu iqu = (iqu) g.f14318b;
            iqu.f14706a |= 1;
            iqu.f14707b = a;
        }
        int a2 = (int) fpt.m9373a(healthStats, 50002);
        if (a2 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqu iqu2 = (iqu) g.f14318b;
            iqu2.f14706a |= 2;
            iqu2.f14708c = a2;
        }
        if (str != null) {
            iqq a3 = fpt.m9374a(str);
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqu iqu3 = (iqu) g.f14318b;
            a3.getClass();
            iqu3.f14709d = a3;
            iqu3.f14706a |= 4;
        }
        iqu iqu4 = (iqu) g.mo8770g();
        if (!fpt.m9381a(iqu4)) {
            return iqu4;
        }
        return null;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ String mo5986a(ikf ikf) {
        iqq iqq = ((iqu) ikf).f14709d;
        if (iqq == null) {
            iqq = iqq.f14681d;
        }
        return iqq.f14685c;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ ikf mo5984a(ikf ikf, ikf ikf2) {
        int i;
        int i2;
        iqu iqu = (iqu) ikf;
        iqu iqu2 = (iqu) ikf2;
        if (iqu == null || iqu2 == null) {
            return iqu;
        }
        iir g = iqu.f14704e.mo8793g();
        if (!((iqu.f14706a & 1) == 0 || (i2 = iqu.f14707b - iqu2.f14707b) == 0)) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqu iqu3 = (iqu) g.f14318b;
            iqu3.f14706a |= 1;
            iqu3.f14707b = i2;
        }
        if (!((iqu.f14706a & 2) == 0 || (i = iqu.f14708c - iqu2.f14708c) == 0)) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqu iqu4 = (iqu) g.f14318b;
            iqu4.f14706a |= 2;
            iqu4.f14708c = i;
        }
        iqq iqq = iqu.f14709d;
        if (iqq == null) {
            iqq = iqq.f14681d;
        }
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        iqu iqu5 = (iqu) g.f14318b;
        iqq.getClass();
        iqu5.f14709d = iqq;
        iqu5.f14706a |= 4;
        iqu iqu6 = (iqu) g.mo8770g();
        if (fpt.m9381a(iqu6)) {
            return null;
        }
        return iqu6;
    }
}
