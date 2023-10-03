package p000;

import android.animation.Animator;

/* renamed from: fhi */
/* compiled from: PG */
final class fhi extends fgg {

    /* renamed from: a */
    private final /* synthetic */ fhj f9659a;

    public fhi(fhj fhj) {
        this.f9659a = fhj;
    }

    public final void onAnimationEnd(Animator animator) {
        if (mo5583a(animator)) {
            this.f9659a.f9662c = null;
            return;
        }
        fhj fhj = this.f9659a;
        fhi.super.setVisible(fhj.f9661b, false);
        this.f9659a.f9660a.cancel();
        this.f9659a.mo5705b();
        Runnable runnable = this.f9659a.f9662c;
        if (runnable != null) {
            runnable.run();
            this.f9659a.f9662c = null;
        }
    }
}
