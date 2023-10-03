package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* renamed from: androidx.recyclerview.widget.h */
class C0567h extends AnimatorListenerAdapter {

    /* renamed from: o */
    final /* synthetic */ ViewPropertyAnimator f637o;
    final /* synthetic */ C0581o this$0;
    final /* synthetic */ C0586qa val$holder;
    final /* synthetic */ View val$view;

    C0567h(C0581o oVar, C0586qa qaVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.this$0 = oVar;
        this.val$holder = qaVar;
        this.f637o = viewPropertyAnimator;
        this.val$view = view;
    }

    public void onAnimationEnd(Animator animator) {
        this.f637o.setListener((Animator.AnimatorListener) null);
        this.val$view.setAlpha(1.0f);
        this.this$0.mo5254u(this.val$holder);
        this.this$0.mRemoveAnimations.remove(this.val$holder);
        this.this$0.dispatchFinishedWhenDone();
    }

    public void onAnimationStart(Animator animator) {
        this.this$0.mo5255v(this.val$holder);
    }
}
