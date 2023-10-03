package p000;

import android.animation.Animator;

/* renamed from: fgx */
/* compiled from: PG */
final class fgx extends fgg {

    /* renamed from: a */
    private final /* synthetic */ fgy f9541a;

    public fgx(fgy fgy) {
        this.f9541a = fgy;
    }

    public final void onAnimationEnd(Animator animator) {
        if (mo5583a(animator)) {
            this.f9541a.f9546c = null;
            return;
        }
        fgy fgy = this.f9541a;
        int i = fgy.f9542d;
        fgx.super.setVisible(fgy.f9544a, false);
        Runnable runnable = this.f9541a.f9546c;
        if (runnable != null) {
            runnable.run();
            this.f9541a.f9546c = null;
        }
        this.f9541a.mo5608b();
    }
}
