package com.android.systemui.biometrics;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.WindowManager;
import android.widget.ImageView;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1776R$drawable;

public class FODAnimation extends ImageView {
    private final WindowManager.LayoutParams mAnimParams = new WindowManager.LayoutParams();
    private int mAnimationOffset;
    private int mAnimationSize;
    private Context mContext;
    private boolean mIsKeyguard;
    private boolean mShowing = false;
    private WindowManager mWindowManager;
    private AnimationDrawable recognizingAnim;

    public FODAnimation(Context context, int i, int i2) {
        super(context);
        this.mContext = context;
        this.mWindowManager = (WindowManager) this.mContext.getSystemService(WindowManager.class);
        this.mAnimationSize = this.mContext.getResources().getDimensionPixelSize(C1775R$dimen.fod_animation_size);
        this.mAnimationOffset = this.mContext.getResources().getDimensionPixelSize(C1775R$dimen.fod_animation_offset);
        WindowManager.LayoutParams layoutParams = this.mAnimParams;
        int i3 = this.mAnimationSize;
        layoutParams.height = i3;
        layoutParams.width = i3;
        layoutParams.format = -3;
        layoutParams.type = 2020;
        layoutParams.flags = 552;
        layoutParams.gravity = 49;
        layoutParams.y = (i2 - (i3 / 2)) + this.mAnimationOffset;
        setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        setBackgroundResource(C1776R$drawable.fod_default_touch_animation);
        this.recognizingAnim = (AnimationDrawable) getBackground();
    }

    public void updateParams(int i) {
        this.mAnimParams.y = (i - (this.mAnimationSize / 2)) + this.mAnimationOffset;
    }

    public void setAnimationKeyguard(boolean z) {
        this.mIsKeyguard = z;
    }

    public void showFODanimation() {
        if (this.mAnimParams != null && !this.mShowing && this.mIsKeyguard) {
            this.mShowing = true;
            if (getWindowToken() == null) {
                this.mWindowManager.addView(this, this.mAnimParams);
                this.mWindowManager.updateViewLayout(this, this.mAnimParams);
            }
            this.recognizingAnim.start();
        }
    }

    public void hideFODanimation() {
        if (this.mShowing) {
            this.mShowing = false;
            if (this.recognizingAnim != null) {
                clearAnimation();
                this.recognizingAnim.stop();
                this.recognizingAnim.selectDrawable(0);
            }
            if (getWindowToken() != null) {
                this.mWindowManager.removeViewImmediate(this);
            }
        }
    }
}
