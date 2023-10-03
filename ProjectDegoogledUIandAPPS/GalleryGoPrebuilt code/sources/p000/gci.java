package p000;

import android.animation.TimeInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

/* renamed from: gci */
/* compiled from: PG */
public final class gci {

    /* renamed from: a */
    public static final TimeInterpolator f10936a = new LinearInterpolator();

    /* renamed from: b */
    public static final TimeInterpolator f10937b = new acb();

    /* renamed from: c */
    public static final TimeInterpolator f10938c = new aca();

    /* renamed from: d */
    public static final TimeInterpolator f10939d = new acc();

    /* renamed from: e */
    public static final TimeInterpolator f10940e = new DecelerateInterpolator();

    /* renamed from: a */
    public static float m10013a(float f, float f2, float f3) {
        return f + (f3 * (f2 - f));
    }
}
