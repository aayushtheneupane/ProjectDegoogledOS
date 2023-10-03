package p000;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.p002v7.widget.ActionBarOverlayLayout;

/* renamed from: si */
/* compiled from: PG */
public final class C0501si extends AnimatorListenerAdapter {

    /* renamed from: a */
    private final /* synthetic */ ActionBarOverlayLayout f15869a;

    public C0501si(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.f15869a = actionBarOverlayLayout;
    }

    public final void onAnimationCancel(Animator animator) {
        ActionBarOverlayLayout actionBarOverlayLayout = this.f15869a;
        actionBarOverlayLayout.f915h = null;
        actionBarOverlayLayout.f912e = false;
    }

    public final void onAnimationEnd(Animator animator) {
        ActionBarOverlayLayout actionBarOverlayLayout = this.f15869a;
        actionBarOverlayLayout.f915h = null;
        actionBarOverlayLayout.f912e = false;
    }
}
