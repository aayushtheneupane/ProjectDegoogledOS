package androidx.core.graphics.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;

/* renamed from: androidx.core.graphics.drawable.a */
public final class C0322a {
    /* renamed from: a */
    public static void m261a(Drawable drawable, Resources.Theme theme) {
        int i = Build.VERSION.SDK_INT;
        drawable.applyTheme(theme);
    }

    /* renamed from: b */
    public static int m262b(Drawable drawable) {
        int i = Build.VERSION.SDK_INT;
        return drawable.getLayoutDirection();
    }

    /* renamed from: c */
    public static boolean m263c(Drawable drawable) {
        int i = Build.VERSION.SDK_INT;
        return drawable.isAutoMirrored();
    }

    /* renamed from: d */
    public static Drawable m264d(Drawable drawable) {
        return drawable instanceof C0324c ? ((C0325d) drawable).mDrawable : drawable;
    }
}
