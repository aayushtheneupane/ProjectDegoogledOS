package com.android.settingslib.graph;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FullCircleBatteryDrawable.kt */
public final class FullCircleBatteryDrawable$startChargingAnimation$2 extends AnimatorListenerAdapter {
    final /* synthetic */ int $alpha;
    final /* synthetic */ FullCircleBatteryDrawable this$0;

    FullCircleBatteryDrawable$startChargingAnimation$2(FullCircleBatteryDrawable fullCircleBatteryDrawable, int i) {
        this.this$0 = fullCircleBatteryDrawable;
        this.$alpha = i;
    }

    public void onAnimationCancel(Animator animator) {
        Intrinsics.checkParameterIsNotNull(animator, "animation");
        super.onAnimationCancel(animator);
        this.this$0.batteryAlpha = this.$alpha;
        this.this$0.postInvalidate();
        onAnimationEnd(animator);
    }

    public void onAnimationEnd(Animator animator) {
        Intrinsics.checkParameterIsNotNull(animator, "animation");
        super.onAnimationEnd(animator);
        this.this$0.chargingAnimator = null;
    }
}
