package p000;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: bxz */
/* compiled from: PG */
final class bxz extends AnimatorListenerAdapter {

    /* renamed from: a */
    private final /* synthetic */ bxu f3880a;

    /* renamed from: b */
    private final /* synthetic */ byc f3881b;

    public bxz(byc byc, bxu bxu) {
        this.f3881b = byc;
        this.f3880a = bxu;
    }

    public final void onAnimationEnd(Animator animator) {
        this.f3881b.f3889d.remove(this.f3880a);
    }
}
