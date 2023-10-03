package com.android.systemui.statusbar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

public class AODDimView extends View {
    private static int ANIMATION_DURATION = 200;
    private boolean mIsEnabled;

    public AODDimView(Context context) {
        this(context, (AttributeSet) null);
        init();
    }

    public AODDimView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        init();
    }

    public AODDimView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
        init();
    }

    public AODDimView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }

    private void init() {
        setBackgroundColor(Color.parseColor("#000000"));
        setVisibility(8);
    }

    public void setEnabled(boolean z) {
        this.mIsEnabled = z;
    }

    public void setVisible(boolean z) {
        setVisible(z, false);
    }

    public void setVisible(boolean z, boolean z2) {
        if (this.mIsEnabled) {
            if (z && z2) {
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setDuration((long) ANIMATION_DURATION);
                alphaAnimation.setFillAfter(false);
                startAnimation(alphaAnimation);
                setVisibility(0);
            } else if (z) {
                setVisibility(0);
            }
            if (!z && z2) {
                AlphaAnimation alphaAnimation2 = new AlphaAnimation(1.0f, 0.0f);
                alphaAnimation2.setDuration((long) ANIMATION_DURATION);
                alphaAnimation2.setFillAfter(false);
                startAnimation(alphaAnimation2);
                alphaAnimation2.setAnimationListener(new Animation.AnimationListener() {
                    public void onAnimationRepeat(Animation animation) {
                    }

                    public void onAnimationStart(Animation animation) {
                    }

                    public void onAnimationEnd(Animation animation) {
                        AODDimView.this.setHidden();
                    }
                });
            } else if (!z && !z2) {
                setVisibility(8);
            }
        }
    }

    /* access modifiers changed from: private */
    public void setHidden() {
        setVisibility(8);
    }
}
