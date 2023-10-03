package p000;

import android.animation.ValueAnimator;

/* renamed from: ghq */
/* compiled from: PG */
final class ghq implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a */
    private final /* synthetic */ gik f11390a;

    public ghq(gik gik) {
        this.f11390a = gik;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.f11390a.f11418e.setScaleX(floatValue);
        this.f11390a.f11418e.setScaleY(floatValue);
    }
}
