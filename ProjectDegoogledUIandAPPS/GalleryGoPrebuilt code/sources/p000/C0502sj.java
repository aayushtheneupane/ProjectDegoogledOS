package p000;

import android.support.p002v7.widget.ActionBarOverlayLayout;

/* renamed from: sj */
/* compiled from: PG */
public final class C0502sj implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ ActionBarOverlayLayout f15870a;

    public C0502sj(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.f15870a = actionBarOverlayLayout;
    }

    public final void run() {
        this.f15870a.mo809a();
        ActionBarOverlayLayout actionBarOverlayLayout = this.f15870a;
        actionBarOverlayLayout.f915h = actionBarOverlayLayout.f909b.animate().translationY(0.0f).setListener(this.f15870a.f916i);
    }
}
