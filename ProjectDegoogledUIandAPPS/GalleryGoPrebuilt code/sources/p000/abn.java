package p000;

import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* renamed from: abn */
/* compiled from: PG */
public final class abn implements ViewTreeObserver.OnPreDrawListener {

    /* renamed from: a */
    private final /* synthetic */ CoordinatorLayout f97a;

    public abn(CoordinatorLayout coordinatorLayout) {
        this.f97a = coordinatorLayout;
    }

    public final boolean onPreDraw() {
        this.f97a.mo1120a(0);
        return true;
    }
}
