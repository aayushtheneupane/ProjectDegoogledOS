package android.support.p000v4.view.animation;

import android.os.Build;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

/* renamed from: android.support.v4.view.animation.PathInterpolatorCompat */
public final class PathInterpolatorCompat {
    public static Interpolator create(float f, float f2, float f3, float f4) {
        int i = Build.VERSION.SDK_INT;
        return new PathInterpolator(f, f2, f3, f4);
    }
}
