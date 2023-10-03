package p000;

import android.os.Build;
import android.os.Trace;

/* renamed from: jo */
/* compiled from: PG */
public final class C0264jo {
    static {
        int i = Build.VERSION.SDK_INT;
        if (Build.VERSION.SDK_INT < 29) {
            try {
                Trace.class.getField("TRACE_TAG_APP").getLong((Object) null);
                Trace.class.getMethod("isTagEnabled", new Class[]{Long.TYPE});
                Trace.class.getMethod("asyncTraceBegin", new Class[]{Long.TYPE, String.class, Integer.TYPE});
                Trace.class.getMethod("asyncTraceEnd", new Class[]{Long.TYPE, String.class, Integer.TYPE});
                Trace.class.getMethod("traceCounter", new Class[]{Long.TYPE, String.class, Integer.TYPE});
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: a */
    public static void m14493a(String str) {
        int i = Build.VERSION.SDK_INT;
        Trace.beginSection(str);
    }

    /* renamed from: a */
    public static void m14492a() {
        int i = Build.VERSION.SDK_INT;
        Trace.endSection();
    }
}
