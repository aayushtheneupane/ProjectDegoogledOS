package p000;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* renamed from: uy */
/* compiled from: PG */
final class C0571uy extends AnimatorListenerAdapter {

    /* renamed from: a */
    private final /* synthetic */ C0667ym f16040a;

    /* renamed from: b */
    private final /* synthetic */ View f16041b;

    /* renamed from: c */
    private final /* synthetic */ ViewPropertyAnimator f16042c;

    /* renamed from: d */
    private final /* synthetic */ C0578ve f16043d;

    public C0571uy(C0578ve veVar, C0667ym ymVar, View view, ViewPropertyAnimator viewPropertyAnimator) {
        this.f16043d = veVar;
        this.f16040a = ymVar;
        this.f16041b = view;
        this.f16042c = viewPropertyAnimator;
    }

    public final void onAnimationStart(Animator animator) {
    }

    public final void onAnimationCancel(Animator animator) {
        this.f16041b.setAlpha(1.0f);
    }

    public final void onAnimationEnd(Animator animator) {
        this.f16042c.setListener((Animator.AnimatorListener) null);
        this.f16043d.mo10556d(this.f16040a);
        this.f16043d.f16079d.remove(this.f16040a);
        this.f16043d.mo10372c();
    }
}
