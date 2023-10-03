package p000;

import android.app.Application;

/* renamed from: fni */
/* compiled from: PG */
final class fni extends fhn {
    private fni(iqk iqk, Application application, hqk hqk, hqk hqk2, int i, float f) {
        super(iqk, application, hqk, hqk2, 2, i);
        foq.m9328a(f);
    }

    /* renamed from: b */
    static synchronized fni m9269b(iqk iqk, Application application, hqk hqk, hqk hqk2, hpy hpy) {
        fni fni;
        synchronized (fni.class) {
            fmu fmu = new fmu((byte[]) null);
            boolean z = false;
            fmu.f10066a = false;
            fmu.f10067b = Float.valueOf(0.5f);
            fmu.f10068c = 5;
            fmu.f10069d = 1000;
            String str = "";
            if (fmu.f10066a == null) {
                str = " enabled";
            }
            if (fmu.f10067b == null) {
                str = str.concat(" samplingProbability");
            }
            if (fmu.f10068c == null) {
                str = String.valueOf(str).concat(" minSpanDurationMs");
            }
            if (fmu.f10069d == null) {
                str = String.valueOf(str).concat(" maxTracingBufferSize");
            }
            if (!str.isEmpty()) {
                String valueOf = String.valueOf(str);
                throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
            }
            fip fip = new fip(fmu.f10066a.booleanValue(), fmu.f10067b.floatValue(), fmu.f10068c.intValue(), fmu.f10069d.intValue());
            float f = fip.f9746a;
            if (f >= 0.0f) {
                if (f <= 1.0f) {
                    z = true;
                }
            }
            ife.m12876b(z, (Object) "Probability shall be between 0 and 1.");
            fmv fmv = (fmv) hpy.mo7645a(fip);
            float b = fmv.mo5826b();
            fmv.mo5827c();
            fmv.mo5828d();
            fni = new fni(iqk, application, hqk, hqk2, 10, b);
        }
        return fni;
    }

    /* renamed from: a */
    static synchronized fni m9268a(iqk iqk, Application application, hqk hqk, hqk hqk2, hpy hpy) {
        fni fni;
        synchronized (fni.class) {
            fni = new fni(iqk, application, hqk, hqk2, ((fmq) hpy.mo7645a(fmq.m9233e().mo5972a())).mo5812b(), 1.0f);
        }
        return fni;
    }

    /* renamed from: d */
    public final void mo5733d() {
        fou.m9333b(fmt.f10065a);
    }
}
