package com.android.systemui.statusbar.phone;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1777R$id;

public final class PhoneStatusBarTransitions extends BarTransitions {
    private View mBattery;
    private View[] mBatteryBars = new View[2];
    private View mCenterClock;
    private Animator mCurrentAnimation;
    private View mHavocLogo;
    private View mHavocLogoRight;
    private final float mIconAlphaWhenOpaque;
    private View mLeftSide;
    private View mStatusIcons;

    private boolean isOpaque(int i) {
        return (i == 1 || i == 2 || i == 4 || i == 6) ? false : true;
    }

    public PhoneStatusBarTransitions(PhoneStatusBarView phoneStatusBarView, View view) {
        super(view, C1776R$drawable.status_background);
        this.mIconAlphaWhenOpaque = phoneStatusBarView.getContext().getResources().getFraction(C1775R$dimen.status_bar_icon_drawing_alpha, 1, 1);
        this.mLeftSide = phoneStatusBarView.findViewById(C1777R$id.status_bar_left_side);
        this.mStatusIcons = phoneStatusBarView.findViewById(C1777R$id.statusIcons);
        this.mBattery = phoneStatusBarView.findViewById(C1777R$id.battery);
        this.mBatteryBars[0] = phoneStatusBarView.findViewById(C1777R$id.battery_bar);
        this.mBatteryBars[1] = phoneStatusBarView.findViewById(C1777R$id.battery_bar_1);
        this.mCenterClock = phoneStatusBarView.findViewById(C1777R$id.center_clock);
        this.mHavocLogo = phoneStatusBarView.findViewById(C1777R$id.havoc_logo);
        this.mHavocLogoRight = phoneStatusBarView.findViewById(C1777R$id.havoc_logo_right);
        applyModeBackground(-1, getMode(), false);
        applyMode(getMode(), false);
    }

    public ObjectAnimator animateTransitionTo(View view, float f) {
        return ObjectAnimator.ofFloat(view, "alpha", new float[]{view.getAlpha(), f});
    }

    private float getNonBatteryClockAlphaFor(int i) {
        if (isLightsOut(i)) {
            return 0.0f;
        }
        if (!isOpaque(i)) {
            return 1.0f;
        }
        return this.mIconAlphaWhenOpaque;
    }

    private float getBatteryClockAlpha(int i) {
        if (isLightsOut(i)) {
            return 0.5f;
        }
        return getNonBatteryClockAlphaFor(i);
    }

    /* access modifiers changed from: protected */
    public void onTransition(int i, int i2, boolean z) {
        super.onTransition(i, i2, z);
        applyMode(i2, z);
    }

    private void applyMode(int i, boolean z) {
        if (this.mLeftSide != null) {
            float nonBatteryClockAlphaFor = getNonBatteryClockAlphaFor(i);
            float batteryClockAlpha = getBatteryClockAlpha(i);
            Animator animator = this.mCurrentAnimation;
            if (animator != null) {
                animator.cancel();
            }
            if (z) {
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(new Animator[]{animateTransitionTo(this.mLeftSide, nonBatteryClockAlphaFor), animateTransitionTo(this.mStatusIcons, nonBatteryClockAlphaFor), animateTransitionTo(this.mBattery, batteryClockAlpha), animateTransitionTo(this.mBatteryBars[0], batteryClockAlpha), animateTransitionTo(this.mBatteryBars[1], batteryClockAlpha), animateTransitionTo(this.mCenterClock, batteryClockAlpha), animateTransitionTo(this.mHavocLogo, nonBatteryClockAlphaFor), animateTransitionTo(this.mHavocLogoRight, nonBatteryClockAlphaFor)});
                if (isLightsOut(i)) {
                    animatorSet.setDuration(1500);
                }
                animatorSet.start();
                this.mCurrentAnimation = animatorSet;
                return;
            }
            this.mLeftSide.setAlpha(nonBatteryClockAlphaFor);
            this.mStatusIcons.setAlpha(nonBatteryClockAlphaFor);
            this.mBattery.setAlpha(batteryClockAlpha);
            this.mBatteryBars[0].setAlpha(batteryClockAlpha);
            this.mBatteryBars[1].setAlpha(batteryClockAlpha);
            this.mCenterClock.setAlpha(batteryClockAlpha);
            this.mHavocLogo.setAlpha(nonBatteryClockAlphaFor);
            this.mHavocLogoRight.setAlpha(nonBatteryClockAlphaFor);
        }
    }
}
