package p000;

import android.animation.ValueAnimator;
import com.google.android.material.textfield.TextInputLayout;

/* renamed from: gkb */
/* compiled from: PG */
public final class gkb implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a */
    private final /* synthetic */ TextInputLayout f11536a;

    public gkb(TextInputLayout textInputLayout) {
        this.f11536a = textInputLayout;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f11536a.f5292n.mo6592a(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }
}
