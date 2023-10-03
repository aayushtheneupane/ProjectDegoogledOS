package p000;

import android.view.View;
import com.google.android.material.behavior.SwipeDismissBehavior;

/* renamed from: gda */
/* compiled from: PG */
final class gda implements Runnable {

    /* renamed from: a */
    private final View f11008a;

    /* renamed from: b */
    private final boolean f11009b;

    /* renamed from: c */
    private final /* synthetic */ SwipeDismissBehavior f11010c;

    public gda(SwipeDismissBehavior swipeDismissBehavior, View view, boolean z) {
        this.f11010c = swipeDismissBehavior;
        this.f11008a = view;
        this.f11009b = z;
    }

    public final void run() {
        gcz gcz;
        C0380nw nwVar = this.f11010c.f5148a;
        if (nwVar != null && nwVar.mo9479a()) {
            C0340mj.m14695a(this.f11008a, (Runnable) this);
        } else if (this.f11009b && (gcz = this.f11010c.f5149b) != null) {
            gcz.mo6417a(this.f11008a);
        }
    }
}
