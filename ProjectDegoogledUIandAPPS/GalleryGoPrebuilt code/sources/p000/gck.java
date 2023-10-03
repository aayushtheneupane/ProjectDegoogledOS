package p000;

import android.animation.ValueAnimator;

/* renamed from: gck */
/* compiled from: PG */
public final class gck implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a */
    private final /* synthetic */ ggu f10942a;

    public gck(ggu ggu) {
        this.f10942a = ggu;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f10942a.mo6637b(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }
}
