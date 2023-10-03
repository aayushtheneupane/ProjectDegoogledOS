package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* renamed from: androidx.recyclerview.widget.j */
class C0571j extends AnimatorListenerAdapter {

    /* renamed from: o */
    final /* synthetic */ ViewPropertyAnimator f640o;

    /* renamed from: p */
    final /* synthetic */ int f641p;

    /* renamed from: q */
    final /* synthetic */ int f642q;
    final /* synthetic */ C0581o this$0;
    final /* synthetic */ C0586qa val$holder;
    final /* synthetic */ View val$view;

    C0571j(C0581o oVar, C0586qa qaVar, int i, View view, int i2, ViewPropertyAnimator viewPropertyAnimator) {
        this.this$0 = oVar;
        this.val$holder = qaVar;
        this.f641p = i;
        this.val$view = view;
        this.f642q = i2;
        this.f640o = viewPropertyAnimator;
    }

    public void onAnimationCancel(Animator animator) {
        if (this.f641p != 0) {
            this.val$view.setTranslationX(0.0f);
        }
        if (this.f642q != 0) {
            this.val$view.setTranslationY(0.0f);
        }
    }

    public void onAnimationEnd(Animator animator) {
        this.f640o.setListener((Animator.AnimatorListener) null);
        this.this$0.mo5252s(this.val$holder);
        this.this$0.mMoveAnimations.remove(this.val$holder);
        this.this$0.dispatchFinishedWhenDone();
    }

    public void onAnimationStart(Animator animator) {
        this.this$0.mo5253t(this.val$holder);
    }
}
