package p000;

import android.content.Context;
import android.view.MotionEvent;

/* renamed from: isp */
/* compiled from: PG */
public class isp extends iso {

    /* renamed from: h */
    public int f15047h = -1;

    /* renamed from: i */
    public int f15048i = 0;

    public isp(Context context) {
        super(context);
    }

    /* renamed from: a */
    public final float mo9102a(MotionEvent motionEvent) {
        try {
            return motionEvent.getX(this.f15048i);
        } catch (Exception e) {
            return motionEvent.getX();
        }
    }

    /* renamed from: b */
    public final float mo9104b(MotionEvent motionEvent) {
        try {
            return motionEvent.getY(this.f15048i);
        } catch (Exception e) {
            return motionEvent.getY();
        }
    }
}
