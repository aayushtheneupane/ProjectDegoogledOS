package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* renamed from: androidx.recyclerview.widget.l */
class C0575l extends AnimatorListenerAdapter {

    /* renamed from: A */
    final /* synthetic */ ViewPropertyAnimator f647A;

    /* renamed from: B */
    final /* synthetic */ View f648B;
    final /* synthetic */ C0581o this$0;

    /* renamed from: u */
    final /* synthetic */ C0577m f649u;

    C0575l(C0581o oVar, C0577m mVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.this$0 = oVar;
        this.f649u = mVar;
        this.f647A = viewPropertyAnimator;
        this.f648B = view;
    }

    public void onAnimationEnd(Animator animator) {
        this.f647A.setListener((Animator.AnimatorListener) null);
        this.f648B.setAlpha(1.0f);
        this.f648B.setTranslationX(0.0f);
        this.f648B.setTranslationY(0.0f);
        this.this$0.mo5240b(this.f649u.newHolder, false);
        this.this$0.mChangeAnimations.remove(this.f649u.newHolder);
        this.this$0.dispatchFinishedWhenDone();
    }

    public void onAnimationStart(Animator animator) {
        this.this$0.mo5242c(this.f649u.newHolder, false);
    }
}
