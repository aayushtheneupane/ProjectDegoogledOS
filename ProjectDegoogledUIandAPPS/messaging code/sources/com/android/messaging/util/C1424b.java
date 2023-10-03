package com.android.messaging.util;

import android.os.Looper;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.util.b */
public final class C1424b {

    /* renamed from: DJ */
    private static boolean f2235DJ;

    /* renamed from: EJ */
    private static boolean f2236EJ;

    /* renamed from: Fj */
    public static void m3583Fj() {
        if (!f2235DJ) {
            m3590h("Expected condition to be true", true);
        }
    }

    /* renamed from: Gj */
    public static void m3584Gj() {
        if (Looper.myLooper() == Looper.getMainLooper() && !Thread.currentThread().getName().contains("test")) {
            m3590h("Not expected to run on main thread", false);
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m3586a(C1449g gVar) {
        f2236EJ = f2235DJ;
        if (!f2236EJ) {
            gVar.getBoolean("bugle_asserts_fatal", false);
            f2236EJ = false;
        }
    }

    /* renamed from: b */
    public static void m3589b(C1449g gVar) {
        gVar.mo8172i(new C1422a(gVar));
        f2236EJ = f2235DJ;
        if (!f2236EJ) {
            gVar.getBoolean("bugle_asserts_fatal", false);
            f2236EJ = false;
        }
    }

    public static void equals(int i, int i2) {
        if (i != i2) {
            m3590h("Expected " + i + " but got " + i2, false);
        }
    }

    public static void fail(String str) {
        m3590h(C0632a.m1025k("Assert.fail() called: ", str), false);
    }

    /* renamed from: h */
    private static void m3590h(String str, boolean z) {
        C1430e.m3622e("MessagingApp", str);
        if (z || f2236EJ) {
            throw new AssertionError(str);
        }
        StackTraceElement caller = C1410N.getCaller(2);
        if (caller != null) {
            StringBuilder Pa = C0632a.m1011Pa("\tat ");
            Pa.append(caller.toString());
            C1430e.m3622e("MessagingApp", Pa.toString());
        }
    }

    /* renamed from: ha */
    public static void m3591ha(boolean z) {
        if (z) {
            m3590h("Expected condition to be false", false);
        }
    }

    /* renamed from: ia */
    public static void m3592ia(boolean z) {
        if (!z) {
            m3590h("Expected condition to be true", false);
        }
    }

    public static void isNull(Object obj) {
        if (obj != null) {
            m3590h("Expected object to be null", false);
        }
    }

    /* renamed from: oc */
    public static void m3593oc() {
        if (Looper.myLooper() != Looper.getMainLooper() && !Thread.currentThread().getName().contains("test")) {
            m3590h("Expected to run on main thread", false);
        }
    }

    /* renamed from: t */
    public static void m3594t(Object obj) {
        if (obj == null) {
            m3590h("Expected value to be non-null", false);
        }
    }

    public static void equals(Object obj, Object obj2) {
        if (obj == obj2) {
            return;
        }
        if (obj == null || obj2 == null || !obj.equals(obj2)) {
            m3590h("Expected " + obj + " but got " + obj2, false);
        }
    }

    /* renamed from: a */
    public static void m3585a(long j, long j2, long j3) {
        if (j < j2 || j > j3) {
            m3590h("Expected value in range [" + j2 + ", " + j3 + "], but was " + j, false);
        }
    }

    /* renamed from: a */
    public static void m3587a(Object obj, String str) {
        if (obj != null) {
            m3590h(str, false);
        }
    }

    /* renamed from: b */
    public static void m3588b(int i, int i2, int i3) {
        if (i < i2 || i > i3) {
            m3590h("Expected value in range [" + i2 + ", " + i3 + "], but was " + i, false);
        }
    }
}
