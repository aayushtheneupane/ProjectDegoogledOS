package p000;

import android.animation.Animator;

/* renamed from: fhb */
/* compiled from: PG */
final class fhb extends fgg {

    /* renamed from: a */
    private final /* synthetic */ fhc f9609a;

    public fhb(fhc fhc) {
        this.f9609a = fhc;
    }

    public final void onAnimationEnd(Animator animator) {
        if (mo5583a(animator)) {
            this.f9609a.f9623i = null;
            return;
        }
        fhc fhc = this.f9609a;
        int i = fhc.f9610j;
        fhb.super.setVisible(fhc.f9622h, false);
        Runnable runnable = this.f9609a.f9623i;
        if (runnable != null) {
            runnable.run();
            this.f9609a.f9623i = null;
        }
        this.f9609a.mo5669c();
    }
}
