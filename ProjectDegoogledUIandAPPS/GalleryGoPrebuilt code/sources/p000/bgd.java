package p000;

import android.view.GestureDetector;
import android.view.MotionEvent;

/* renamed from: bgd */
/* compiled from: PG */
final class bgd extends GestureDetector.SimpleOnGestureListener {

    /* renamed from: a */
    private final /* synthetic */ bgo f2236a;

    public bgd(bgo bgo) {
        this.f2236a = bgo;
    }

    public final boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        this.f2236a.performClick();
        return true;
    }
}
