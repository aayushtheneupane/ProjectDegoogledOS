package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* renamed from: androidx.recyclerview.widget.k */
class C0573k extends AnimatorListenerAdapter {
    final /* synthetic */ C0581o this$0;

    /* renamed from: u */
    final /* synthetic */ C0577m f645u;

    /* renamed from: v */
    final /* synthetic */ ViewPropertyAnimator f646v;
    final /* synthetic */ View val$view;

    C0573k(C0581o oVar, C0577m mVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.this$0 = oVar;
        this.f645u = mVar;
        this.f646v = viewPropertyAnimator;
        this.val$view = view;
    }

    public void onAnimationEnd(Animator animator) {
        this.f646v.setListener((Animator.AnimatorListener) null);
        this.val$view.setAlpha(1.0f);
        this.val$view.setTranslationX(0.0f);
        this.val$view.setTranslationY(0.0f);
        this.this$0.mo5240b(this.f645u.oldHolder, true);
        this.this$0.mChangeAnimations.remove(this.f645u.oldHolder);
        this.this$0.dispatchFinishedWhenDone();
    }

    public void onAnimationStart(Animator animator) {
        this.this$0.mo5242c(this.f645u.oldHolder, true);
    }
}
