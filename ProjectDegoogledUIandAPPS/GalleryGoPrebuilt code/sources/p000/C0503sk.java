package p000;

import android.support.p002v7.widget.ActionBarOverlayLayout;

/* renamed from: sk */
/* compiled from: PG */
public final class C0503sk implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ ActionBarOverlayLayout f15871a;

    public C0503sk(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.f15871a = actionBarOverlayLayout;
    }

    public final void run() {
        this.f15871a.mo809a();
        ActionBarOverlayLayout actionBarOverlayLayout = this.f15871a;
        actionBarOverlayLayout.f915h = actionBarOverlayLayout.f909b.animate().translationY((float) (-this.f15871a.f909b.getHeight())).setListener(this.f15871a.f916i);
    }
}
