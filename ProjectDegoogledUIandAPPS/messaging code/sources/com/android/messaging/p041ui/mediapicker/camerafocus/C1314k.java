package com.android.messaging.p041ui.mediapicker.camerafocus;

import android.view.animation.Animation;

/* renamed from: com.android.messaging.ui.mediapicker.camerafocus.k */
class C1314k implements Animation.AnimationListener {
    final /* synthetic */ C1317n this$0;

    /* synthetic */ C1314k(C1317n nVar, C1309f fVar) {
        this.this$0 = nVar;
    }

    public void onAnimationEnd(Animation animation) {
        if (!this.this$0.f2115yJ) {
            C1317n nVar = this.this$0;
            nVar.mOverlay.postDelayed(nVar.f2085UI, 200);
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }
}
