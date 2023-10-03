package p000;

import android.view.MotionEvent;
import android.view.View;

/* renamed from: dtp */
/* compiled from: PG */
final /* synthetic */ class dtp implements View.OnTouchListener {

    /* renamed from: a */
    public static final View.OnTouchListener f7355a = new dtp();

    private dtp() {
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        view.getParent().requestDisallowInterceptTouchEvent(true);
        return false;
    }
}
