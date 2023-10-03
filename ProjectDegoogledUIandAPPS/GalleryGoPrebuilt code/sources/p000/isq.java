package p000;

import android.view.ScaleGestureDetector;

/* renamed from: isq */
/* compiled from: PG */
final class isq implements ScaleGestureDetector.OnScaleGestureListener {

    /* renamed from: a */
    private final /* synthetic */ isr f15049a;

    public isq(isr isr) {
        this.f15049a = isr;
    }

    public final boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        return true;
    }

    public final void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
    }

    public final boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        float scaleFactor = scaleGestureDetector.getScaleFactor();
        if (Float.isNaN(scaleFactor) || Float.isInfinite(scaleFactor)) {
            return false;
        }
        this.f15049a.f15040a.mo9090a(scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
        return true;
    }
}
