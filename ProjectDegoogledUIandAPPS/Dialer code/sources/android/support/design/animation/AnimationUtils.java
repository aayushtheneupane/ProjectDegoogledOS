package android.support.design.animation;

import android.animation.TimeInterpolator;
import android.support.p000v4.view.animation.FastOutLinearInInterpolator;
import android.support.p000v4.view.animation.FastOutSlowInInterpolator;
import android.support.p000v4.view.animation.LinearOutSlowInInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class AnimationUtils {
    public static final TimeInterpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator();
    public static final TimeInterpolator FAST_OUT_LINEAR_IN_INTERPOLATOR = new FastOutLinearInInterpolator();
    public static final TimeInterpolator FAST_OUT_SLOW_IN_INTERPOLATOR = new FastOutSlowInInterpolator();
    public static final TimeInterpolator LINEAR_OUT_SLOW_IN_INTERPOLATOR = new LinearOutSlowInInterpolator();

    static {
        new LinearInterpolator();
    }

    public static float lerp(float f, float f2, float f3) {
        return GeneratedOutlineSupport.outline0(f2, f, f3, f);
    }
}
