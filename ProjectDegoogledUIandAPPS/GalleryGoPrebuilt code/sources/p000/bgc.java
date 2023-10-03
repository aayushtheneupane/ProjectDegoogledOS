package p000;

import android.content.Context;
import android.graphics.PointF;
import android.view.GestureDetector;
import android.view.MotionEvent;

/* renamed from: bgc */
/* compiled from: PG */
final class bgc extends GestureDetector.SimpleOnGestureListener {

    /* renamed from: a */
    private final /* synthetic */ Context f2234a;

    /* renamed from: b */
    private final /* synthetic */ bgo f2235b;

    public bgc(bgo bgo, Context context) {
        this.f2235b = bgo;
        this.f2234a = context;
    }

    public final boolean onDoubleTap(MotionEvent motionEvent) {
        bgo bgo = this.f2235b;
        String str = bgo.f2287a;
        if (!bgo.f2330f || !bgo.f2292C || bgo.f2336l == null) {
            return super.onDoubleTapEvent(motionEvent);
        }
        bgo.mo1994a(this.f2234a);
        bgo bgo2 = this.f2235b;
        if (bgo2.f2331g) {
            bgo2.f2346v = new PointF(motionEvent.getX(), motionEvent.getY());
            bgo bgo3 = this.f2235b;
            bgo3.f2337m = new PointF(bgo3.f2336l.x, this.f2235b.f2336l.y);
            bgo bgo4 = this.f2235b;
            bgo4.f2335k = bgo4.f2334j;
            bgo4.f2343s = true;
            bgo4.f2342r = true;
            bgo4.f2347w = -1.0f;
            bgo4.f2350z = bgo4.mo1991a(bgo4.f2346v);
            this.f2235b.f2290A = new PointF(motionEvent.getX(), motionEvent.getY());
            bgo bgo5 = this.f2235b;
            bgo5.f2349y = new PointF(bgo5.f2350z.x, this.f2235b.f2350z.y);
            this.f2235b.f2348x = false;
            return false;
        }
        bgo2.mo1997a(bgo2.mo1991a(new PointF(motionEvent.getX(), motionEvent.getY())), new PointF(motionEvent.getX(), motionEvent.getY()));
        return true;
    }

    public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        bgo bgo = this.f2235b;
        String str = bgo.f2287a;
        if (bgo.f2329e && bgo.f2292C && bgo.f2336l != null && motionEvent != null && motionEvent2 != null && ((Math.abs(motionEvent.getX() - motionEvent2.getX()) > 50.0f || Math.abs(motionEvent.getY() - motionEvent2.getY()) > 50.0f) && (Math.abs(f) > 500.0f || Math.abs(f2) > 500.0f))) {
            bgo bgo2 = this.f2235b;
            if (!bgo2.f2342r) {
                PointF pointF = new PointF(bgo2.f2336l.x + (f * 0.25f), this.f2235b.f2336l.y + (f2 * 0.25f));
                int width = this.f2235b.getWidth();
                float f3 = pointF.x;
                bgo bgo3 = this.f2235b;
                float f4 = bgo3.f2334j;
                int height = bgo3.getHeight();
                float f5 = pointF.y;
                bgo bgo4 = this.f2235b;
                bgf bgf = new bgf(bgo4, new PointF((((float) (width / 2)) - f3) / f4, (((float) (height / 2)) - f5) / bgo4.f2334j));
                if (bgo.f2289c.contains(1)) {
                    bgf.f2251b = 1;
                    bgf.f2253d = false;
                    bgf.f2252c = 3;
                    bgf.mo1980a();
                    return true;
                }
                StringBuilder sb = new StringBuilder(32);
                sb.append("Unknown easing type: 1");
                throw new IllegalArgumentException(sb.toString());
            }
        }
        return super.onFling(motionEvent, motionEvent2, f, f2);
    }

    public final boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        this.f2235b.performClick();
        return true;
    }
}
