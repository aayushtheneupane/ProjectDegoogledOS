package p000;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/* renamed from: ced */
/* compiled from: PG */
final class ced extends GestureDetector.SimpleOnGestureListener {

    /* renamed from: a */
    private final View f4158a;

    /* renamed from: b */
    private final float f4159b;

    /* renamed from: c */
    private final float f4160c;

    /* renamed from: d */
    private float f4161d;

    /* renamed from: e */
    private final /* synthetic */ cee f4162e;

    public /* synthetic */ ced(cee cee, View view) {
        this.f4162e = cee;
        this.f4158a = view;
        this.f4159b = (float) cee.f4163a.f4168c.getWidth();
        cef cef = cee.f4163a;
        this.f4160c = cef.f4167b.getX() + cef.f4168c.getX();
    }

    public final boolean onDown(MotionEvent motionEvent) {
        float f;
        View view = this.f4158a;
        cef cef = this.f4162e.f4163a;
        if (view == cef.f4170e) {
            cef.mo3068a(true);
            f = motionEvent.getX();
            this.f4161d = f;
        } else {
            f = view.getX();
            this.f4161d = f;
        }
        this.f4162e.f4163a.mo3066a(this.f4158a, f, this.f4159b, this.f4160c);
        cef cef2 = this.f4162e.f4163a;
        cef2.mo3070b(cef2.mo3064a(cef2.mo3063a(this.f4158a, this.f4159b)));
        return true;
    }

    public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        float f3 = this.f4161d - f;
        this.f4161d = f3;
        this.f4162e.f4163a.mo3066a(this.f4158a, f3, this.f4159b, this.f4160c);
        cef cef = this.f4162e.f4163a;
        cef.mo3070b(cef.mo3064a(cef.mo3063a(this.f4158a, this.f4159b)));
        return true;
    }
}
