package p000;

import android.animation.ValueAnimator;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

/* renamed from: gde */
/* compiled from: PG */
public final class gde implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a */
    private final /* synthetic */ BottomSheetBehavior f11015a;

    public gde(BottomSheetBehavior bottomSheetBehavior) {
        this.f11015a = bottomSheetBehavior;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        ggu ggu = this.f11015a.f5168c;
        if (ggu != null) {
            ggu.mo6631a(floatValue);
        }
    }
}
