package p000;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* renamed from: ux */
/* compiled from: PG */
final class C0570ux extends AnimatorListenerAdapter {

    /* renamed from: a */
    private final /* synthetic */ C0667ym f16036a;

    /* renamed from: b */
    private final /* synthetic */ ViewPropertyAnimator f16037b;

    /* renamed from: c */
    private final /* synthetic */ View f16038c;

    /* renamed from: d */
    private final /* synthetic */ C0578ve f16039d;

    public C0570ux(C0578ve veVar, C0667ym ymVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.f16039d = veVar;
        this.f16036a = ymVar;
        this.f16037b = viewPropertyAnimator;
        this.f16038c = view;
    }

    public final void onAnimationStart(Animator animator) {
    }

    public final void onAnimationEnd(Animator animator) {
        this.f16037b.setListener((Animator.AnimatorListener) null);
        this.f16038c.setAlpha(1.0f);
        this.f16039d.mo10556d(this.f16036a);
        this.f16039d.f16081f.remove(this.f16036a);
        this.f16039d.mo10372c();
    }
}
