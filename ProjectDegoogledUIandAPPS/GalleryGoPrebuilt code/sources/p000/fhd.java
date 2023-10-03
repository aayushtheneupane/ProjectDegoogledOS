package p000;

import android.animation.Animator;

/* renamed from: fhd */
/* compiled from: PG */
final class fhd extends fgg {

    /* renamed from: a */
    private final /* synthetic */ fhf f9639a;

    public fhd(fhf fhf) {
        this.f9639a = fhf;
    }

    public final void onAnimationEnd(Animator animator) {
        if (mo5583a(animator)) {
            this.f9639a.f9643c = null;
            return;
        }
        fhf fhf = this.f9639a;
        fhd.super.setVisible(fhf.f9641a, false);
        this.f9639a.mo5692b();
        Runnable runnable = this.f9639a.f9643c;
        if (runnable != null) {
            runnable.run();
            this.f9639a.f9643c = null;
        }
    }
}
