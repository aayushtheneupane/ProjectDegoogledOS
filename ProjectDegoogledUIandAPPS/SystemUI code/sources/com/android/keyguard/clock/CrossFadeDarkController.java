package com.android.keyguard.clock;

import android.view.View;

final class CrossFadeDarkController {
    private final View mFadeInView;
    private final View mFadeOutView;

    CrossFadeDarkController(View view, View view2) {
        this.mFadeInView = view;
        this.mFadeOutView = view2;
    }

    /* access modifiers changed from: package-private */
    public void setDarkAmount(float f) {
        float f2 = 2.0f * f;
        this.mFadeInView.setAlpha(Math.max(0.0f, f2 - 1.0f));
        if (f == 0.0f) {
            this.mFadeInView.setVisibility(8);
        } else if (this.mFadeInView.getVisibility() == 8) {
            this.mFadeInView.setVisibility(0);
        }
        this.mFadeOutView.setAlpha(Math.max(0.0f, 1.0f - f2));
        if (f == 1.0f) {
            this.mFadeOutView.setVisibility(8);
        } else if (this.mFadeOutView.getVisibility() == 8) {
            this.mFadeOutView.setVisibility(0);
        }
    }
}
