package p000;

import android.view.MotionEvent;
import android.view.View;

/* renamed from: cee */
/* compiled from: PG */
public final class cee {

    /* renamed from: a */
    public final /* synthetic */ cef f4163a;

    /* renamed from: b */
    private final ced f4164b;

    /* renamed from: c */
    private final C0320lq f4165c;

    public /* synthetic */ cee(cef cef, View view) {
        this.f4163a = cef;
        this.f4164b = new ced(this, view);
        this.f4165c = new C0320lq(view.getContext(), this.f4164b);
    }

    /* renamed from: a */
    public final boolean mo3060a(MotionEvent motionEvent) {
        return ((C0319lp) this.f4165c.f15208a).f15207a.onTouchEvent(motionEvent);
    }
}
