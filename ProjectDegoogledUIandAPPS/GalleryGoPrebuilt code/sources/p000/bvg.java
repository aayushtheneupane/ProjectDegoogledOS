package p000;

import android.animation.TimeInterpolator;
import android.support.p002v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

/* renamed from: bvg */
/* compiled from: PG */
final /* synthetic */ class bvg implements View.OnHoverListener {

    /* renamed from: a */
    private final C0372no f3672a;

    public bvg(C0372no noVar) {
        this.f3672a = noVar;
    }

    public final boolean onHover(View view, MotionEvent motionEvent) {
        C0372no noVar = this.f3672a;
        TimeInterpolator timeInterpolator = bvv.f3687a;
        if (!noVar.f15296b.isEnabled() || !noVar.f15296b.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 7 || action == 9) {
            bxf bxf = (bxf) noVar;
            int a = ihg.m13018a(bxf.f3819g.mo2874a(), bxf.f3819g.f3826g, motionEvent.getX(), motionEvent.getY());
            if (a == 0) {
                a = -1;
            }
            noVar.mo9462a(a);
            return true;
        } else if (action != 10 || noVar.f15300f == Integer.MIN_VALUE) {
            return false;
        } else {
            noVar.mo9462a((int) RecyclerView.UNDEFINED_DURATION);
            return true;
        }
    }
}
