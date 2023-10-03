package p000;

import android.animation.ValueAnimator;

/* renamed from: giz */
/* compiled from: PG */
final class giz implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a */
    private final /* synthetic */ gja f11450a;

    public giz(gja gja) {
        this.f11450a = gja;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.f11450a.f11497m.setScaleX(floatValue);
        this.f11450a.f11497m.setScaleY(floatValue);
    }
}
