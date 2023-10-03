package com.android.messaging.util;

import android.view.animation.Animation;

/* renamed from: com.android.messaging.util.xa */
class C1484xa implements Animation.AnimationListener {

    /* renamed from: XK */
    final /* synthetic */ Runnable f2352XK;

    C1484xa(Runnable runnable) {
        this.f2352XK = runnable;
    }

    public void onAnimationEnd(Animation animation) {
        if (this.f2352XK != null) {
            C1480va.getMainThreadHandler().post(this.f2352XK);
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }
}
