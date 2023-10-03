package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* renamed from: androidx.recyclerview.widget.i */
class C0569i extends AnimatorListenerAdapter {

    /* renamed from: o */
    final /* synthetic */ ViewPropertyAnimator f638o;
    final /* synthetic */ C0581o this$0;
    final /* synthetic */ C0586qa val$holder;
    final /* synthetic */ View val$view;

    C0569i(C0581o oVar, C0586qa qaVar, View view, ViewPropertyAnimator viewPropertyAnimator) {
        this.this$0 = oVar;
        this.val$holder = qaVar;
        this.val$view = view;
        this.f638o = viewPropertyAnimator;
    }

    public void onAnimationCancel(Animator animator) {
        this.val$view.setAlpha(1.0f);
    }

    public void onAnimationEnd(Animator animator) {
        this.f638o.setListener((Animator.AnimatorListener) null);
        this.this$0.mo5250q(this.val$holder);
        this.this$0.mAddAnimations.remove(this.val$holder);
        this.this$0.dispatchFinishedWhenDone();
    }

    public void onAnimationStart(Animator animator) {
        this.this$0.mo5251r(this.val$holder);
    }
}
