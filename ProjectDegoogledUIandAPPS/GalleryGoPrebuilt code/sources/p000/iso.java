package p000;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

/* renamed from: iso */
/* compiled from: PG */
public class iso implements iss {

    /* renamed from: a */
    public ist f15040a;

    /* renamed from: b */
    public float f15041b;

    /* renamed from: c */
    public float f15042c;

    /* renamed from: d */
    public final float f15043d;

    /* renamed from: e */
    public final float f15044e;

    /* renamed from: f */
    public VelocityTracker f15045f;

    /* renamed from: g */
    public boolean f15046g;

    public iso(Context context) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f15044e = (float) viewConfiguration.getScaledMinimumFlingVelocity();
        this.f15043d = (float) viewConfiguration.getScaledTouchSlop();
    }

    /* renamed from: a */
    public float mo9102a(MotionEvent motionEvent) {
        throw null;
    }

    /* renamed from: a */
    public boolean mo9103a() {
        throw null;
    }

    /* renamed from: b */
    public float mo9104b(MotionEvent motionEvent) {
        throw null;
    }

    /* renamed from: c */
    public boolean mo9105c(MotionEvent motionEvent) {
        throw null;
    }
}
