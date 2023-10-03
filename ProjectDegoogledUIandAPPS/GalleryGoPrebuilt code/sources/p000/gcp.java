package p000;

import android.view.View;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* renamed from: gcp */
/* compiled from: PG */
final class gcp implements Runnable {

    /* renamed from: a */
    private final CoordinatorLayout f10951a;

    /* renamed from: b */
    private final View f10952b;

    /* renamed from: c */
    private final /* synthetic */ gcq f10953c;

    public gcp(gcq gcq, CoordinatorLayout coordinatorLayout, View view) {
        this.f10953c = gcq;
        this.f10951a = coordinatorLayout;
        this.f10952b = view;
    }

    public final void run() {
        OverScroller overScroller;
        if (this.f10952b != null && (overScroller = this.f10953c.f10955b) != null) {
            if (overScroller.computeScrollOffset()) {
                gcq gcq = this.f10953c;
                gcq.mo6399b(this.f10951a, this.f10952b, gcq.f10955b.getCurrY());
                C0340mj.m14695a(this.f10952b, (Runnable) this);
                return;
            }
            this.f10953c.mo3600a(this.f10951a, this.f10952b);
        }
    }
}
