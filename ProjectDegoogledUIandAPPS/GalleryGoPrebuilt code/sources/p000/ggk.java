package p000;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

/* renamed from: ggk */
/* compiled from: PG */
public final class ggk {

    /* renamed from: a */
    public static final boolean f11254a = true;

    /* renamed from: b */
    private static final int[] f11255b = {16842910, 16842919};

    /* renamed from: c */
    private static final String f11256c = ggk.class.getSimpleName();

    static {
        int i = Build.VERSION.SDK_INT;
    }

    private ggk() {
    }

    /* renamed from: a */
    public static ColorStateList m10271a(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return ColorStateList.valueOf(0);
        }
        int i = Build.VERSION.SDK_INT;
        if (Build.VERSION.SDK_INT <= 27 && Color.alpha(colorStateList.getDefaultColor()) == 0 && Color.alpha(colorStateList.getColorForState(f11255b, 0)) != 0) {
            Log.w(f11256c, "Use a non-transparent color for the default color as it will be used to finish ripple animations.");
        }
        return colorStateList;
    }
}
