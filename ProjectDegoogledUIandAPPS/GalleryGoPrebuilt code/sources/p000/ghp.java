package p000;

import android.animation.ValueAnimator;

/* renamed from: ghp */
/* compiled from: PG */
final class ghp implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a */
    private final /* synthetic */ gik f11389a;

    public ghp(gik gik) {
        this.f11389a = gik;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f11389a.f11418e.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }
}
