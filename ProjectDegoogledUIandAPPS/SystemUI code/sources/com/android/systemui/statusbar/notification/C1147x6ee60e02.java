package com.android.systemui.statusbar.notification;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.C1777R$id;
import java.util.Set;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.android.systemui.statusbar.notification.ViewGroupFadeHelper$Companion$fadeOutAllChildrenExcept$$inlined$apply$lambda$1 */
/* compiled from: ViewGroupFadeHelper.kt */
final class C1147x6ee60e02 implements ValueAnimator.AnimatorUpdateListener {
    final /* synthetic */ long $duration$inlined;
    final /* synthetic */ Runnable $endRunnable$inlined;
    final /* synthetic */ ViewGroup $root$inlined;
    final /* synthetic */ Set $viewsToFadeOut$inlined;

    C1147x6ee60e02(long j, ViewGroup viewGroup, Set set, Runnable runnable) {
        this.$duration$inlined = j;
        this.$root$inlined = viewGroup;
        this.$viewsToFadeOut$inlined = set;
        this.$endRunnable$inlined = runnable;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        Float f = (Float) this.$root$inlined.getTag(C1777R$id.view_group_fade_helper_previous_value_tag);
        Intrinsics.checkExpressionValueIsNotNull(valueAnimator, "animation");
        Object animatedValue = valueAnimator.getAnimatedValue();
        if (animatedValue != null) {
            float floatValue = ((Float) animatedValue).floatValue();
            for (View view : this.$viewsToFadeOut$inlined) {
                if (!Intrinsics.areEqual(view.getAlpha(), f)) {
                    view.setTag(C1777R$id.view_group_fade_helper_restore_tag, Float.valueOf(view.getAlpha()));
                }
                view.setAlpha(floatValue);
            }
            this.$root$inlined.setTag(C1777R$id.view_group_fade_helper_previous_value_tag, Float.valueOf(floatValue));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Float");
    }
}
