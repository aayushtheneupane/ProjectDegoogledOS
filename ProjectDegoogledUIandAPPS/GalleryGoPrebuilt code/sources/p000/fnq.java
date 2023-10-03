package p000;

import android.os.health.HealthStats;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* renamed from: fnq */
/* compiled from: PG */
public final class fnq extends fns {

    /* renamed from: a */
    public static final fnq f10109a = new fnq();

    private fnq() {
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ ikf mo5985a(String str, Object obj) {
        HealthStats healthStats = (HealthStats) obj;
        iir g = iqr.f14686e.mo8793g();
        g.mo8756c((Iterable) fnt.f10111a.mo5988a(fpt.m9385d(healthStats, 40001)));
        g.mo8761d((Iterable) fnp.f10108a.mo5988a((Map) (healthStats != null && healthStats.hasMeasurements(40002)) ? healthStats.getMeasurements(40002) : Collections.emptyMap()));
        if (str != null) {
            iqq a = fpt.m9374a(str);
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqr iqr = (iqr) g.f14318b;
            a.getClass();
            iqr.f14691d = a;
            iqr.f14688a |= 1;
        }
        iqr iqr2 = (iqr) g.mo8770g();
        if (!fpt.m9379a(iqr2)) {
            return iqr2;
        }
        return null;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ String mo5986a(ikf ikf) {
        iqq iqq = ((iqr) ikf).f14691d;
        if (iqq == null) {
            iqq = iqq.f14681d;
        }
        return iqq.f14685c;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ ikf mo5984a(ikf ikf, ikf ikf2) {
        iqr iqr = (iqr) ikf;
        iqr iqr2 = (iqr) ikf2;
        if (iqr == null || iqr2 == null) {
            return iqr;
        }
        iir g = iqr.f14686e.mo8793g();
        g.mo8756c((Iterable) fnt.f10111a.mo5987a((List) iqr.f14689b, (List) iqr2.f14689b));
        g.mo8761d((Iterable) fnp.f10108a.mo5987a((List) iqr.f14690c, (List) iqr2.f14690c));
        iqq iqq = iqr.f14691d;
        if (iqq == null) {
            iqq = iqq.f14681d;
        }
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        iqr iqr3 = (iqr) g.f14318b;
        iqq.getClass();
        iqr3.f14691d = iqq;
        iqr3.f14688a |= 1;
        iqr iqr4 = (iqr) g.mo8770g();
        if (fpt.m9379a(iqr4)) {
            return null;
        }
        return iqr4;
    }
}
