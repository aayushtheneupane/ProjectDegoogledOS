package androidx.core.p024os;

import android.os.Build;
import android.os.Trace;

/* renamed from: androidx.core.os.TraceCompat */
public final class TraceCompat {
    private TraceCompat() {
    }

    public static void beginSection(String str) {
        int i = Build.VERSION.SDK_INT;
        Trace.beginSection(str);
    }

    public static void endSection() {
        int i = Build.VERSION.SDK_INT;
        Trace.endSection();
    }
}
