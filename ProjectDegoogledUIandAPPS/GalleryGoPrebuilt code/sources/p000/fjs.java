package p000;

import android.app.ActivityManager;
import android.os.Build;
import java.util.List;

/* renamed from: fjs */
/* compiled from: PG */
final /* synthetic */ class fjs implements ice {

    /* renamed from: a */
    private final fjt f9835a;

    /* renamed from: b */
    private final String f9836b;

    /* renamed from: c */
    private final String f9837c;

    /* renamed from: d */
    private final int f9838d;

    public fjs(fjt fjt, String str, int i, String str2) {
        this.f9835a = fjt;
        this.f9836b = str;
        this.f9838d = i;
        this.f9837c = str2;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        iqx iqx;
        fjt fjt = this.f9835a;
        String str = this.f9836b;
        int i = this.f9838d;
        String str2 = this.f9837c;
        if (fjt.f9841f) {
            System.gc();
            System.runFinalization();
            System.gc();
        }
        fjk fjk = fjt.f9840e;
        if (fjk != null) {
            try {
                iqx = fjk.mo5879a();
            } catch (RuntimeException e) {
                flw.m9203e("MemoryMetricService", "Metric extension provider failed.", new Object[0]);
                iqx = null;
            }
        } else {
            iqx = null;
        }
        if (!fjt.f9839d) {
            iir g = isc.f14974r.mo8793g();
            irc a = fok.m9318a(i, fjt.f9685a, str2, fjt.f9842g);
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            isc isc = (isc) g.f14318b;
            a.getClass();
            isc.f14977b = a;
            isc.f14976a |= 1;
            fjt.mo5729a(str, false, (isc) g.mo8770g(), iqx);
        } else {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = fom.m9322a(fjt.f9685a).getRunningAppProcesses();
            if (runningAppProcesses != null) {
                fjt.f9685a.getPackageName();
                for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                    int i2 = Build.VERSION.SDK_INT;
                    iir g2 = isc.f14974r.mo8793g();
                    irc a2 = fok.m9317a(i, next.pid, next.processName, fjt.f9685a, str2, fjt.f9842g);
                    if (g2.f14319c) {
                        g2.mo8751b();
                        g2.f14319c = false;
                    }
                    isc isc2 = (isc) g2.f14318b;
                    a2.getClass();
                    isc2.f14977b = a2;
                    isc2.f14976a |= 1;
                    fjt.mo5729a(str, false, (isc) g2.mo8770g(), iqx);
                }
            }
        }
        return ife.m12820a((Object) null);
    }
}
