package p000;

import android.view.View;
import com.google.android.material.behavior.SwipeDismissBehavior;

/* renamed from: gcy */
/* compiled from: PG */
public final class gcy implements C0366ni {

    /* renamed from: a */
    private final /* synthetic */ SwipeDismissBehavior f10979a;

    public gcy(SwipeDismissBehavior swipeDismissBehavior) {
        this.f10979a = swipeDismissBehavior;
    }

    /* renamed from: a */
    public final boolean mo6416a(View view) {
        if (!this.f10979a.mo3609e(view)) {
            return false;
        }
        int f = C0340mj.m14714f(view);
        int i = this.f10979a.f5150c;
        C0340mj.m14711d(view, (i == 0 ? f != 1 : i != 1 || f == 1) ? view.getWidth() : -view.getWidth());
        view.setAlpha(0.0f);
        gcz gcz = this.f10979a.f5149b;
        if (gcz != null) {
            gcz.mo6417a(view);
        }
        return true;
    }
}
