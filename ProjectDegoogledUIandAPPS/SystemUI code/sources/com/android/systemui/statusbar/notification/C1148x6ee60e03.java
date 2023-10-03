package com.android.systemui.statusbar.notification;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.ViewGroup;
import java.util.Set;

/* renamed from: com.android.systemui.statusbar.notification.ViewGroupFadeHelper$Companion$fadeOutAllChildrenExcept$$inlined$apply$lambda$2 */
/* compiled from: ViewGroupFadeHelper.kt */
public final class C1148x6ee60e03 extends AnimatorListenerAdapter {
    final /* synthetic */ long $duration$inlined;
    final /* synthetic */ Runnable $endRunnable$inlined;
    final /* synthetic */ ViewGroup $root$inlined;
    final /* synthetic */ Set $viewsToFadeOut$inlined;

    C1148x6ee60e03(long j, ViewGroup viewGroup, Set set, Runnable runnable) {
        this.$duration$inlined = j;
        this.$root$inlined = viewGroup;
        this.$viewsToFadeOut$inlined = set;
        this.$endRunnable$inlined = runnable;
    }

    public void onAnimationEnd(Animator animator) {
        Runnable runnable = this.$endRunnable$inlined;
        if (runnable != null) {
            runnable.run();
        }
    }
}
