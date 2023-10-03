package p000;

import android.animation.ValueAnimator;

/* renamed from: gjm */
/* compiled from: PG */
final class gjm implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a */
    private final /* synthetic */ gjn f11482a;

    public gjm(gjn gjn) {
        this.f11482a = gjn;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f11482a.f11497m.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }
}
