package com.android.messaging.p041ui.mediapicker.camerafocus;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: com.android.messaging.ui.mediapicker.camerafocus.h */
class C1311h extends AnimatorListenerAdapter {
    final /* synthetic */ C1317n this$0;

    C1311h(C1317n nVar) {
        this.this$0 = nVar;
    }

    public void onAnimationEnd(Animator animator) {
        this.this$0.m3335Ko();
        this.this$0.show(false);
        this.this$0.mOverlay.setAlpha(1.0f);
        super.onAnimationEnd(animator);
    }
}
