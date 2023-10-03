package p000;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;

/* renamed from: bxk */
/* compiled from: PG */
final class bxk extends AnimatorListenerAdapter {

    /* renamed from: a */
    private final /* synthetic */ bxl f3831a;

    public bxk(bxl bxl) {
        this.f3831a = bxl;
    }

    public final void onAnimationEnd(Animator animator) {
        bxl bxl = this.f3831a;
        TimeInterpolator timeInterpolator = bxl.f3832a;
        bxl.f3848p = false;
    }

    public final void onAnimationStart(Animator animator) {
        bxl bxl = this.f3831a;
        TimeInterpolator timeInterpolator = bxl.f3832a;
        bxl.f3848p = true;
    }
}
