package com.android.settingslib.graph;

import android.animation.ValueAnimator;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FullCircleBatteryDrawable.kt */
final class FullCircleBatteryDrawable$startChargingAnimation$1 implements ValueAnimator.AnimatorUpdateListener {
    final /* synthetic */ FullCircleBatteryDrawable this$0;

    FullCircleBatteryDrawable$startChargingAnimation$1(FullCircleBatteryDrawable fullCircleBatteryDrawable) {
        this.this$0 = fullCircleBatteryDrawable;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        FullCircleBatteryDrawable fullCircleBatteryDrawable = this.this$0;
        Intrinsics.checkExpressionValueIsNotNull(valueAnimator, "it");
        Object animatedValue = valueAnimator.getAnimatedValue();
        if (animatedValue != null) {
            fullCircleBatteryDrawable.batteryAlpha = ((Integer) animatedValue).intValue();
            this.this$0.postInvalidate();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
    }
}
