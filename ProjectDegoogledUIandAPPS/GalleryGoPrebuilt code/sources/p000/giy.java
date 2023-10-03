package p000;

import android.animation.ValueAnimator;

/* renamed from: giy */
/* compiled from: PG */
final class giy implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a */
    private final /* synthetic */ gja f11449a;

    public giy(gja gja) {
        this.f11449a = gja;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f11449a.f11497m.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }
}
