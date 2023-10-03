package p000;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* renamed from: vb */
/* compiled from: PG */
final class C0575vb extends AnimatorListenerAdapter {

    /* renamed from: a */
    private final /* synthetic */ C0576vc f16060a;

    /* renamed from: b */
    private final /* synthetic */ ViewPropertyAnimator f16061b;

    /* renamed from: c */
    private final /* synthetic */ View f16062c;

    /* renamed from: d */
    private final /* synthetic */ C0578ve f16063d;

    public C0575vb(C0578ve veVar, C0576vc vcVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.f16063d = veVar;
        this.f16060a = vcVar;
        this.f16061b = viewPropertyAnimator;
        this.f16062c = view;
    }

    public final void onAnimationStart(Animator animator) {
    }

    public final void onAnimationEnd(Animator animator) {
        this.f16061b.setListener((Animator.AnimatorListener) null);
        this.f16062c.setAlpha(1.0f);
        this.f16062c.setTranslationX(0.0f);
        this.f16062c.setTranslationY(0.0f);
        this.f16063d.mo10556d(this.f16060a.f16065b);
        this.f16063d.f16082g.remove(this.f16060a.f16065b);
        this.f16063d.mo10372c();
    }
}
