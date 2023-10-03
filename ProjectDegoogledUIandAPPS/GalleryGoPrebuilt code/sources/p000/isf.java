package p000;

import android.graphics.RectF;
import android.view.GestureDetector;
import android.view.MotionEvent;

/* renamed from: isf */
/* compiled from: PG */
public final class isf implements GestureDetector.OnDoubleTapListener {

    /* renamed from: a */
    private final isn f15000a;

    public isf(isn isn) {
        this.f15000a = isn;
    }

    public final boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    public final boolean onDoubleTap(MotionEvent motionEvent) {
        try {
            float d = this.f15000a.mo9096d();
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            isn isn = this.f15000a;
            float f = isn.f15018d;
            if (d >= f) {
                if (d >= f) {
                    float f2 = isn.f15019e;
                    if (d < f2) {
                        isn.mo9094b(f2, x, y);
                        return true;
                    }
                }
                isn.mo9094b(isn.f15017c, x, y);
                return true;
            }
            isn.mo9094b(f, x, y);
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }
    }

    public final boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        RectF b;
        this.f15000a.mo9095c();
        isn isn = this.f15000a;
        if (isn.f15024j == null || (b = isn.mo9093b()) == null) {
            return false;
        }
        if (b.contains(motionEvent.getX(), motionEvent.getY())) {
            float f = b.left;
            b.width();
            float f2 = b.top;
            b.height();
            this.f15000a.f15024j.mo4363b();
            return true;
        }
        this.f15000a.f15024j.mo4362a();
        return false;
    }
}
