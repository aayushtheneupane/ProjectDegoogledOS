package p000;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* renamed from: uz */
/* compiled from: PG */
final class C0572uz extends AnimatorListenerAdapter {

    /* renamed from: a */
    private final /* synthetic */ C0667ym f16044a;

    /* renamed from: b */
    private final /* synthetic */ int f16045b;

    /* renamed from: c */
    private final /* synthetic */ View f16046c;

    /* renamed from: d */
    private final /* synthetic */ int f16047d;

    /* renamed from: e */
    private final /* synthetic */ ViewPropertyAnimator f16048e;

    /* renamed from: f */
    private final /* synthetic */ C0578ve f16049f;

    public C0572uz(C0578ve veVar, C0667ym ymVar, int i, View view, int i2, ViewPropertyAnimator viewPropertyAnimator) {
        this.f16049f = veVar;
        this.f16044a = ymVar;
        this.f16045b = i;
        this.f16046c = view;
        this.f16047d = i2;
        this.f16048e = viewPropertyAnimator;
    }

    public final void onAnimationStart(Animator animator) {
    }

    public final void onAnimationCancel(Animator animator) {
        if (this.f16045b != 0) {
            this.f16046c.setTranslationX(0.0f);
        }
        if (this.f16047d != 0) {
            this.f16046c.setTranslationY(0.0f);
        }
    }

    public final void onAnimationEnd(Animator animator) {
        this.f16048e.setListener((Animator.AnimatorListener) null);
        this.f16049f.mo10556d(this.f16044a);
        this.f16049f.f16080e.remove(this.f16044a);
        this.f16049f.mo10372c();
    }
}
