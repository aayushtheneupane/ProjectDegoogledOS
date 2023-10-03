package p000;

import android.view.MotionEvent;
import android.view.View;

/* renamed from: dpf */
/* compiled from: PG */
final /* synthetic */ class dpf implements View.OnTouchListener {

    /* renamed from: a */
    private final dpt f6985a;

    public dpf(dpt dpt) {
        this.f6985a = dpt;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        dpt dpt = this.f6985a;
        int action = motionEvent.getAction();
        if (action == 0) {
            dpt.mo4326a();
            return true;
        } else if (action != 1) {
            return false;
        } else {
            dpt.mo4328b();
            return true;
        }
    }
}
