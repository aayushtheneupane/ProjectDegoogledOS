package androidx.core.p024os;

import android.os.Build;

/* renamed from: androidx.core.os.BuildCompat */
public class BuildCompat {
    private BuildCompat() {
    }

    @Deprecated
    public static boolean isAtLeastN() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @Deprecated
    public static boolean isAtLeastNMR1() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @Deprecated
    public static boolean isAtLeastO() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @Deprecated
    public static boolean isAtLeastOMR1() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @Deprecated
    public static boolean isAtLeastP() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @Deprecated
    public static boolean isAtLeastQ() {
        return Build.VERSION.SDK_INT >= 29;
    }
}
