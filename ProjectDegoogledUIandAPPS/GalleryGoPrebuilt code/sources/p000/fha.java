package p000;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: fha */
/* compiled from: PG */
final class fha extends AnimatorListenerAdapter {

    /* renamed from: a */
    private final /* synthetic */ fhc f9608a;

    public fha(fhc fhc) {
        this.f9608a = fhc;
    }

    public final void onAnimationStart(Animator animator) {
        fhc fhc = this.f9608a;
        int i = fhc.f9610j;
        if (!fhc.f9615a.isStarted()) {
            this.f9608a.f9615a.start();
        }
    }
}
