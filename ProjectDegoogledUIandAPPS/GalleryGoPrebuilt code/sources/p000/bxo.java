package p000;

import android.animation.Animator;
import android.animation.TimeInterpolator;

/* renamed from: bxo */
/* compiled from: PG */
public final class bxo implements bxn {

    /* renamed from: c */
    private static final TimeInterpolator f3859c = new acc();

    /* renamed from: a */
    public TimeInterpolator f3860a = f3859c;

    /* renamed from: b */
    public Animator.AnimatorListener f3861b;

    /* renamed from: d */
    private final bxq f3862d;

    public bxo(bxq bxq) {
        this.f3862d = bxq;
    }

    /* renamed from: a */
    public final void mo2883a() {
        bxq bxq = this.f3862d;
        if (bxq != null) {
            bxq.mo2885a(this.f3861b, this.f3860a);
        }
    }
}
