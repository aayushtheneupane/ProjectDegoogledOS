package p000;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* renamed from: va */
/* compiled from: PG */
final class C0574va extends AnimatorListenerAdapter {

    /* renamed from: a */
    private final /* synthetic */ C0576vc f16056a;

    /* renamed from: b */
    private final /* synthetic */ ViewPropertyAnimator f16057b;

    /* renamed from: c */
    private final /* synthetic */ View f16058c;

    /* renamed from: d */
    private final /* synthetic */ C0578ve f16059d;

    public C0574va(C0578ve veVar, C0576vc vcVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.f16059d = veVar;
        this.f16056a = vcVar;
        this.f16057b = viewPropertyAnimator;
        this.f16058c = view;
    }

    public final void onAnimationStart(Animator animator) {
    }

    public final void onAnimationEnd(Animator animator) {
        this.f16057b.setListener((Animator.AnimatorListener) null);
        this.f16058c.setAlpha(1.0f);
        this.f16058c.setTranslationX(0.0f);
        this.f16058c.setTranslationY(0.0f);
        this.f16059d.mo10556d(this.f16056a.f16064a);
        this.f16059d.f16082g.remove(this.f16056a.f16064a);
        this.f16059d.mo10372c();
    }
}
