package p000;

import android.animation.ValueAnimator;
import android.os.Handler;

/* renamed from: ghu */
/* compiled from: PG */
final class ghu implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a */
    private final /* synthetic */ gik f11394a;

    public ghu(gik gik) {
        this.f11394a = gik;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        Handler handler = gik.f11413a;
        this.f11394a.f11418e.setTranslationY((float) intValue);
    }
}
