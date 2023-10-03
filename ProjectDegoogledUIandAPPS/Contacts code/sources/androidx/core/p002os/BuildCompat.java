package androidx.core.p002os;

import android.os.Build;

/* renamed from: androidx.core.os.BuildCompat */
public class BuildCompat {
    @Deprecated
    public static boolean isAtLeastO() {
        return Build.VERSION.SDK_INT >= 26;
    }
}
