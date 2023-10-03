package android.support.p000v4.view;

import android.content.Context;
import android.os.Build;
import android.view.ViewConfiguration;

/* renamed from: android.support.v4.view.ViewConfigurationCompat */
public final class ViewConfigurationCompat {
    static {
        int i = Build.VERSION.SDK_INT;
    }

    public static float getScaledHorizontalScrollFactor(ViewConfiguration viewConfiguration, Context context) {
        int i = Build.VERSION.SDK_INT;
        return viewConfiguration.getScaledHorizontalScrollFactor();
    }

    public static boolean shouldShowMenuShortcutsWhenKeyboardPresent(ViewConfiguration viewConfiguration, Context context) {
        int i = Build.VERSION.SDK_INT;
        return viewConfiguration.shouldShowMenuShortcutsWhenKeyboardPresent();
    }
}
