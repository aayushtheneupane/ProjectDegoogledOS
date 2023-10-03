package p000;

import android.animation.Animator;

/* renamed from: fgi */
/* compiled from: PG */
public final class fgi extends fgg {

    /* renamed from: a */
    public final Animator f9509a;

    /* renamed from: b */
    public final Runnable f9510b;

    /* renamed from: c */
    public final int f9511c;

    /* renamed from: d */
    public int f9512d;

    /* renamed from: e */
    private final fgl f9513e = new fgh(this);

    private fgi(Animator animator, Runnable runnable) {
        this.f9509a = animator;
        this.f9511c = -1;
        this.f9510b = runnable;
    }

    /* renamed from: a */
    public static void m8780a(Animator animator, Runnable runnable) {
        animator.addListener(new fgi(animator, runnable));
    }

    public final void onAnimationEnd(Animator animator) {
        if (!mo5583a(animator)) {
            fgn.m8785a().mo5591a(this.f9513e);
        }
    }
}
