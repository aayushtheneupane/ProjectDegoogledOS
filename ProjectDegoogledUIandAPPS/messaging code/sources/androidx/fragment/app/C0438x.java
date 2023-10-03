package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: androidx.fragment.app.x */
class C0438x extends AnimatorListenerAdapter {

    /* renamed from: j */
    final /* synthetic */ ViewGroup f421j;

    /* renamed from: k */
    final /* synthetic */ View f422k;

    /* renamed from: m */
    final /* synthetic */ C0424j f423m;
    final /* synthetic */ C0389H this$0;

    C0438x(C0389H h, ViewGroup viewGroup, View view, C0424j jVar) {
        this.this$0 = h;
        this.f421j = viewGroup;
        this.f422k = view;
        this.f423m = jVar;
    }

    public void onAnimationEnd(Animator animator) {
        this.f421j.endViewTransition(this.f422k);
        Animator animator2 = this.f423m.getAnimator();
        this.f423m.setAnimator((Animator) null);
        if (animator2 != null && this.f421j.indexOfChild(this.f422k) < 0) {
            C0389H h = this.this$0;
            C0424j jVar = this.f423m;
            h.mo4079a(jVar, jVar.getStateAfterAnimating(), 0, 0, false);
        }
    }
}
