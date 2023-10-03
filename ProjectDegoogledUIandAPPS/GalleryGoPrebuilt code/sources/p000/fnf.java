package p000;

import android.app.Application;
import java.util.concurrent.Executor;
import p003j$.util.concurrent.ConcurrentHashMap;

/* renamed from: fnf */
/* compiled from: PG */
final class fnf extends fhn implements fnd {

    /* renamed from: d */
    public final hpy f10085d;

    /* renamed from: e */
    private final foq f10086e;

    /* renamed from: f */
    private final ConcurrentHashMap f10087f;

    static {
        hto.m12124a("Cold startup", "Cold startup interactive", "Cold startup interactive before onDraw", "Warm startup", "Warm startup interactive", "Warm startup interactive before onDraw", "Warm startup activity onStart");
    }

    public fnf(iqk iqk, Application application, hqk hqk, hqk hqk2, foq foq, int i, hpy hpy, ConcurrentHashMap concurrentHashMap) {
        super(iqk, application, hqk, hqk2, 1, i);
        this.f10086e = foq;
        this.f10087f = concurrentHashMap;
        this.f10085d = hpy;
    }

    /* renamed from: a */
    private static isc m9259a(fnc fnc, String str) {
        iir g = isc.f14974r.mo8793g();
        iir g2 = isd.f14994d.mo8793g();
        long j = fnc.f10080b - fnc.f10079a;
        if (g2.f14319c) {
            g2.mo8751b();
            g2.f14319c = false;
        }
        isd isd = (isd) g2.f14318b;
        int i = isd.f14996a | 1;
        isd.f14996a = i;
        isd.f14997b = j;
        isd.f14998c = 0;
        isd.f14996a = i | 2;
        isd isd2 = (isd) g2.mo8770g();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        isc isc = (isc) g.f14318b;
        isd2.getClass();
        isc.f14980e = isd2;
        isc.f14976a |= 8;
        if (str != null) {
            iir g3 = irp.f14897c.mo8793g();
            if (g3.f14319c) {
                g3.mo8751b();
                g3.f14319c = false;
            }
            irp irp = (irp) g3.f14318b;
            str.getClass();
            irp.f14899a |= 1;
            irp.f14900b = str;
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            isc isc2 = (isc) g.f14318b;
            irp irp2 = (irp) g3.mo8770g();
            irp2.getClass();
            isc2.f14991p = irp2;
            isc2.f14976a |= 1048576;
        }
        return (isc) g.mo8770g();
    }

    /* renamed from: a */
    public final ieh mo5893a(String str, long j, long j2, iqx iqx) {
        if (this.f10086e.mo6000a() && mo5731b()) {
            if (j <= j2) {
                return m9258a(str, m9259a(new fnc(j, j2), (String) null), iqx);
            }
            flw.m9202d("TimerMetricService", "Skip timer event: end time %d is before start time %d", Long.valueOf(j2), Long.valueOf(j));
        }
        return ife.m12820a((Object) null);
    }

    /* renamed from: a */
    public final ieh mo5892a(fnc fnc, String str, String str2) {
        if (!this.f10086e.mo6000a() || !mo5731b()) {
            return ife.m12820a((Object) null);
        }
        return m9258a(str, m9259a(fnc, str2), (iqx) null);
    }

    /* renamed from: a */
    private final ieh m9258a(String str, isc isc, iqx iqx) {
        return ife.m12816a((ice) new fne(this, str, isc, iqx), (Executor) mo5732c());
    }

    /* renamed from: d */
    public final void mo5733d() {
        this.f10087f.clear();
    }
}
