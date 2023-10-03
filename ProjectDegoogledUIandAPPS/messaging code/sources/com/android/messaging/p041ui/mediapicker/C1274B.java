package com.android.messaging.p041ui.mediapicker;

import android.view.View;
import android.view.animation.Animation;

/* renamed from: com.android.messaging.ui.mediapicker.B */
class C1274B implements Animation.AnimationListener {

    /* renamed from: kI */
    final /* synthetic */ View f1998kI;

    C1274B(C1275C c, View view) {
        this.f1998kI = view;
    }

    public void onAnimationEnd(Animation animation) {
        this.f1998kI.setVisibility(8);
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
        this.f1998kI.setVisibility(0);
    }
}
