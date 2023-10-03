package p000;

import android.os.Build;
import android.os.SystemClock;

/* renamed from: bfk */
/* compiled from: PG */
public final class bfk {

    /* renamed from: a */
    public static final double f2210a = (1.0d / Math.pow(10.0d, 6.0d));

    static {
        int i = Build.VERSION.SDK_INT;
    }

    /* renamed from: a */
    public static long m2412a() {
        int i = Build.VERSION.SDK_INT;
        return SystemClock.elapsedRealtimeNanos();
    }
}
