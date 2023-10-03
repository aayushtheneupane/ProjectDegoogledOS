package p000;

import android.app.ActivityManager;
import android.os.Build;

/* renamed from: avs */
/* compiled from: PG */
public final class avs {

    /* renamed from: a */
    public final int f1772a;

    /* renamed from: b */
    public final int f1773b;

    /* renamed from: c */
    public final int f1774c;

    public avs(avp avp) {
        int i;
        float f;
        if (!m1771a(avp.f1768a)) {
            i = 4194304;
        } else {
            i = 2097152;
        }
        this.f1774c = i;
        ActivityManager activityManager = avp.f1768a;
        float memoryClass = (float) (activityManager.getMemoryClass() * 1048576);
        if (!m1771a(activityManager)) {
            f = 0.4f;
        } else {
            f = 0.33f;
        }
        int round = Math.round(memoryClass * f);
        float f2 = (float) ((((avq) avp.f1769b).f1771a.widthPixels * ((avq) avp.f1769b).f1771a.heightPixels) << 2);
        int round2 = Math.round(avp.f1770c * f2);
        int round3 = Math.round(f2 + f2);
        int i2 = round - this.f1774c;
        if (round3 + round2 <= i2) {
            this.f1773b = round3;
            this.f1772a = round2;
            return;
        }
        float f3 = ((float) i2) / (avp.f1770c + 2.0f);
        this.f1773b = Math.round(f3 + f3);
        this.f1772a = Math.round(f3 * avp.f1770c);
    }

    /* renamed from: a */
    static boolean m1771a(ActivityManager activityManager) {
        int i = Build.VERSION.SDK_INT;
        return activityManager.isLowRamDevice();
    }
}
