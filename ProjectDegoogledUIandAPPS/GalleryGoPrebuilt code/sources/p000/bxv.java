package p000;

import android.animation.Animator;
import android.animation.TimeInterpolator;

/* renamed from: bxv */
/* compiled from: PG */
final /* synthetic */ class bxv implements Runnable {

    /* renamed from: a */
    private final byc f3874a;

    /* renamed from: b */
    private final Animator.AnimatorListener f3875b;

    /* renamed from: c */
    private final TimeInterpolator f3876c;

    public bxv(byc byc, Animator.AnimatorListener animatorListener, TimeInterpolator timeInterpolator) {
        this.f3874a = byc;
        this.f3875b = animatorListener;
        this.f3876c = timeInterpolator;
    }

    public final void run() {
        this.f3874a.mo2885a(this.f3875b, this.f3876c);
    }
}
