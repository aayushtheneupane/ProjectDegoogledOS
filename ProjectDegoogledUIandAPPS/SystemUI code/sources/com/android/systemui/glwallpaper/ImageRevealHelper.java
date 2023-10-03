package com.android.systemui.glwallpaper;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.Log;
import com.android.systemui.Interpolators;

class ImageRevealHelper {
    /* access modifiers changed from: private */
    public static final String TAG = "ImageRevealHelper";
    private final ValueAnimator mAnimator;
    private boolean mAwake = false;
    /* access modifiers changed from: private */
    public float mReveal = 0.0f;
    /* access modifiers changed from: private */
    public final RevealStateListener mRevealListener;

    public interface RevealStateListener {
        void onRevealEnd();

        void onRevealStart(boolean z);

        void onRevealStateChanged();
    }

    ImageRevealHelper(RevealStateListener revealStateListener) {
        this.mRevealListener = revealStateListener;
        this.mAnimator = ValueAnimator.ofFloat(new float[0]);
        this.mAnimator.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
        this.mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ImageRevealHelper.this.lambda$new$0$ImageRevealHelper(valueAnimator);
            }
        });
        this.mAnimator.addListener(new AnimatorListenerAdapter() {
            private boolean mIsCanceled;

            public void onAnimationCancel(Animator animator) {
                this.mIsCanceled = true;
            }

            public void onAnimationEnd(Animator animator) {
                if (ImageRevealHelper.this.mRevealListener != null) {
                    String access$100 = ImageRevealHelper.TAG;
                    Log.d(access$100, "transition end, cancel=" + this.mIsCanceled + ", reveal=" + ImageRevealHelper.this.mReveal);
                    if (!this.mIsCanceled) {
                        ImageRevealHelper.this.mRevealListener.onRevealEnd();
                    }
                }
                this.mIsCanceled = false;
            }

            public void onAnimationStart(Animator animator) {
                if (ImageRevealHelper.this.mRevealListener != null) {
                    Log.d(ImageRevealHelper.TAG, "transition start");
                    ImageRevealHelper.this.mRevealListener.onRevealStart(true);
                }
            }
        });
    }

    public /* synthetic */ void lambda$new$0$ImageRevealHelper(ValueAnimator valueAnimator) {
        this.mReveal = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        RevealStateListener revealStateListener = this.mRevealListener;
        if (revealStateListener != null) {
            revealStateListener.onRevealStateChanged();
        }
    }

    public float getReveal() {
        return this.mReveal;
    }

    /* access modifiers changed from: package-private */
    public void updateAwake(boolean z, long j) {
        Log.d(TAG, "updateAwake: awake=" + z + ", duration=" + j);
        this.mAnimator.cancel();
        this.mAwake = z;
        float f = 0.0f;
        if (j == 0) {
            if (!this.mAwake) {
                f = 1.0f;
            }
            this.mReveal = f;
            this.mRevealListener.onRevealStart(false);
            this.mRevealListener.onRevealStateChanged();
            this.mRevealListener.onRevealEnd();
            return;
        }
        this.mAnimator.setDuration(j);
        ValueAnimator valueAnimator = this.mAnimator;
        float[] fArr = new float[2];
        fArr[0] = this.mReveal;
        if (!this.mAwake) {
            f = 1.0f;
        }
        fArr[1] = f;
        valueAnimator.setFloatValues(fArr);
        this.mAnimator.start();
    }
}
