package androidx.core.p002os;

import android.os.Build;
import android.os.Trace;

/* renamed from: androidx.core.os.TraceCompat */
public final class TraceCompat {
    public static void beginSection(String str) {
        if (Build.VERSION.SDK_INT >= 18) {
            Trace.beginSection(str);
        }
    }

    public static void endSection() {
        if (Build.VERSION.SDK_INT >= 18) {
            Trace.endSection();
        }
    }
}
