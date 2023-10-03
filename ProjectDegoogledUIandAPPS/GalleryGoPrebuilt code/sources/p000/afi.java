package p000;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: afi */
/* compiled from: PG */
final class afi extends AnimatorListenerAdapter {

    /* renamed from: a */
    private final /* synthetic */ afl f313a;

    public afi(afl afl) {
        this.f313a = afl;
    }

    public final void onAnimationEnd(Animator animator) {
        this.f313a.mo320e();
        animator.removeListener(this);
    }
}
