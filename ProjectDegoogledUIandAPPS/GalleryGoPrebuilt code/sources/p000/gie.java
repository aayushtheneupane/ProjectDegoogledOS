package p000;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;

/* renamed from: gie */
/* compiled from: PG */
final class gie implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ gik f11403a;

    public gie(gik gik) {
        this.f11403a = gik;
    }

    public final void run() {
        this.f11403a.f11418e.setVisibility(0);
        gik gik = this.f11403a;
        if (gik.f11418e.f11408c == 1) {
            ValueAnimator a = gik.mo6711a(0.0f, 1.0f);
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.8f, 1.0f});
            ofFloat.setInterpolator(gci.f10939d);
            ofFloat.addUpdateListener(new ghq(gik));
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{a, ofFloat});
            animatorSet.setDuration(150);
            animatorSet.addListener(new ghn(gik));
            animatorSet.start();
            return;
        }
        int e = gik.mo6717e();
        gik.f11418e.setTranslationY((float) e);
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(new int[]{e, 0});
        valueAnimator.setInterpolator(gci.f10937b);
        valueAnimator.setDuration(250);
        valueAnimator.addListener(new ghr(gik));
        valueAnimator.addUpdateListener(new ghs(gik));
        valueAnimator.start();
    }
}
