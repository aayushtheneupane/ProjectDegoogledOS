package p000;

import android.animation.ValueAnimator;

/* renamed from: vm */
/* compiled from: PG */
final class C0586vm implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a */
    private final /* synthetic */ C0587vn f16108a;

    public C0586vm(C0587vn vnVar) {
        this.f16108a = vnVar;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int floatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
        this.f16108a.f16117b.setAlpha(floatValue);
        this.f16108a.f16118c.setAlpha(floatValue);
        this.f16108a.mo10392a();
    }
}
