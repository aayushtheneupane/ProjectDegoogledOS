package p000;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: afh */
/* compiled from: PG */
final class afh extends AnimatorListenerAdapter {

    /* renamed from: a */
    private final /* synthetic */ C0290kn f311a;

    /* renamed from: b */
    private final /* synthetic */ afl f312b;

    public afh(afl afl, C0290kn knVar) {
        this.f312b = afl;
        this.f311a = knVar;
    }

    public final void onAnimationEnd(Animator animator) {
        this.f311a.remove(animator);
        this.f312b.f332k.remove(animator);
    }

    public final void onAnimationStart(Animator animator) {
        this.f312b.f332k.add(animator);
    }
}
