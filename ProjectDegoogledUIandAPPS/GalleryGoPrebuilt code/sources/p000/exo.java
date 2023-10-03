package p000;

import android.os.Build;
import android.os.SystemClock;

/* renamed from: exo */
/* compiled from: PG */
public final class exo implements exm {

    /* renamed from: a */
    private static final boolean f9186a;

    static {
        boolean z;
        try {
            int i = Build.VERSION.SDK_INT;
            SystemClock.elapsedRealtimeNanos();
            z = true;
        } catch (Throwable th) {
            z = false;
        }
        f9186a = z;
    }

    /* renamed from: a */
    public final long mo5370a() {
        return System.currentTimeMillis();
    }

    /* renamed from: c */
    public final long mo5372c() {
        return SystemClock.elapsedRealtime();
    }

    /* renamed from: d */
    public final long mo5373d() {
        if (!f9186a) {
            return SystemClock.elapsedRealtime() * 1000000;
        }
        return SystemClock.elapsedRealtimeNanos();
    }

    /* renamed from: b */
    public final long mo5371b() {
        return System.nanoTime();
    }

    /* renamed from: e */
    public final long mo5374e() {
        return SystemClock.uptimeMillis();
    }
}
