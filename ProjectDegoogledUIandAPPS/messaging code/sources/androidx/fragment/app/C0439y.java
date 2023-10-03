package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: androidx.fragment.app.y */
class C0439y extends AnimatorListenerAdapter {

    /* renamed from: j */
    final /* synthetic */ ViewGroup f424j;

    /* renamed from: m */
    final /* synthetic */ C0424j f425m;

    /* renamed from: n */
    final /* synthetic */ View f426n;

    C0439y(C0389H h, ViewGroup viewGroup, View view, C0424j jVar) {
        this.f424j = viewGroup;
        this.f426n = view;
        this.f425m = jVar;
    }

    public void onAnimationEnd(Animator animator) {
        this.f424j.endViewTransition(this.f426n);
        animator.removeListener(this);
        View view = this.f425m.mView;
        if (view != null) {
            view.setVisibility(8);
        }
    }
}
