package p000;

import android.animation.ValueAnimator;
import android.os.Handler;

/* renamed from: ghs */
/* compiled from: PG */
final class ghs implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a */
    private final /* synthetic */ gik f11392a;

    public ghs(gik gik) {
        this.f11392a = gik;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        Handler handler = gik.f11413a;
        this.f11392a.f11418e.setTranslationY((float) intValue);
    }
}
