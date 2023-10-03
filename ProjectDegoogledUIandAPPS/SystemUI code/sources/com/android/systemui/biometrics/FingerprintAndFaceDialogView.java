package com.android.systemui.biometrics;

import android.content.Context;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;

public class FingerprintAndFaceDialogView extends BiometricDialogView {
    private boolean mDialogAnimatedIn;
    private final Runnable mErrorToIdleAnimationRunnable = new Runnable() {
        public final void run() {
            FingerprintAndFaceDialogView.this.lambda$new$0$FingerprintAndFaceDialogView();
        }
    };
    /* access modifiers changed from: private */
    public ImageView mFaceIcon;
    private IconController mIconController = new IconController();

    /* access modifiers changed from: protected */
    public int getAuthenticatedAccessibilityResourceId() {
        return 17040061;
    }

    /* access modifiers changed from: protected */
    public int getDelayAfterAuthenticatedDurationMs() {
        return 200;
    }

    /* access modifiers changed from: protected */
    public boolean shouldGrayAreaDismissDialog() {
        return true;
    }

    private final class IconController extends Animatable2.AnimationCallback {
        private boolean mLastPulseDirection;
        int mState = 0;

        IconController() {
        }

        public void animateOnce(int i) {
            animateIcon(i, false);
        }

        public void showStatic(int i) {
            FingerprintAndFaceDialogView.this.mFaceIcon.setImageDrawable(FingerprintAndFaceDialogView.this.mContext.getDrawable(i));
        }

        public void startPulsing() {
            this.mLastPulseDirection = false;
            animateIcon(C1776R$drawable.face_dialog_pulse_dark_to_light, true);
        }

        public void showIcon(int i) {
            FingerprintAndFaceDialogView.this.mFaceIcon.setImageDrawable(FingerprintAndFaceDialogView.this.mContext.getDrawable(i));
        }

        private void animateIcon(int i, boolean z) {
            AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) FingerprintAndFaceDialogView.this.mContext.getDrawable(i);
            FingerprintAndFaceDialogView.this.mFaceIcon.setImageDrawable(animatedVectorDrawable);
            animatedVectorDrawable.forceAnimationOnUI();
            if (z) {
                animatedVectorDrawable.registerAnimationCallback(this);
            }
            animatedVectorDrawable.start();
        }

        private void pulseInNextDirection() {
            int i;
            if (this.mLastPulseDirection) {
                i = C1776R$drawable.face_dialog_pulse_dark_to_light;
            } else {
                i = C1776R$drawable.face_dialog_pulse_light_to_dark;
            }
            animateIcon(i, true);
            this.mLastPulseDirection = !this.mLastPulseDirection;
        }

        public void onAnimationEnd(Drawable drawable) {
            super.onAnimationEnd(drawable);
            if (this.mState == 1) {
                pulseInNextDirection();
            }
        }
    }

    public /* synthetic */ void lambda$new$0$FingerprintAndFaceDialogView() {
        updateState(0);
        announceAccessibilityEvent();
    }

    public FingerprintAndFaceDialogView(Context context, DialogViewCallback dialogViewCallback) {
        super(context, dialogViewCallback);
        this.mFaceIcon = new ImageView(context);
        int dimensionPixelSize = getResources().getDimensionPixelSize(C1775R$dimen.biometric_dialog_biometric_icon_size);
        this.mFaceIcon.setVisibility(0);
        this.mLayout.addView(this.mFaceIcon);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mFaceIcon.getLayoutParams();
        layoutParams.gravity = 49;
        layoutParams.width = dimensionPixelSize;
        layoutParams.height = dimensionPixelSize;
        layoutParams.topMargin = dimensionPixelSize;
    }

    public void onSaveState(Bundle bundle) {
        super.onSaveState(bundle);
        bundle.putBoolean("key_dialog_animated_in", this.mDialogAnimatedIn);
    }

    /* access modifiers changed from: protected */
    public void handleResetMessage() {
        this.mErrorText.setText(getHintStringResourceId());
        this.mErrorText.setTextColor(this.mTextColor);
        announceAccessibilityEvent();
    }

    public void restoreState(Bundle bundle) {
        super.restoreState(bundle);
        this.mDialogAnimatedIn = bundle.getBoolean("key_dialog_animated_in");
    }

    public void onAuthenticationFailed(String str) {
        super.onAuthenticationFailed(str);
        showTryAgainButton(true);
    }

    public void showTryAgainButton(boolean z) {
        if (z) {
            this.mTryAgainButton.setVisibility(0);
            this.mPositiveButton.setVisibility(8);
            announceAccessibilityEvent();
            return;
        }
        this.mTryAgainButton.setVisibility(8);
        announceAccessibilityEvent();
    }

    /* access modifiers changed from: protected */
    public int getHintStringResourceId() {
        return C1784R$string.fingerprint_dialog_touch_sensor;
    }

    /* access modifiers changed from: protected */
    public int getIconDescriptionResourceId() {
        return C1784R$string.accessibility_fingerprint_dialog_fingerprint_icon;
    }

    /* access modifiers changed from: protected */
    public void updateIcon(int i, int i2) {
        IconController iconController = this.mIconController;
        iconController.mState = i2;
        if (i2 == 1) {
            this.mHandler.removeCallbacks(this.mErrorToIdleAnimationRunnable);
            if (this.mDialogAnimatedIn) {
                this.mIconController.startPulsing();
            } else {
                this.mIconController.showIcon(C1776R$drawable.face_dialog_pulse_dark_to_light);
            }
            this.mFaceIcon.setContentDescription(this.mContext.getString(C1784R$string.biometric_dialog_face_icon_description_authenticating));
        } else if (i == 3 && i2 == 4) {
            iconController.animateOnce(C1776R$drawable.face_dialog_dark_to_checkmark);
            this.mFaceIcon.setContentDescription(this.mContext.getString(C1784R$string.biometric_dialog_face_icon_description_confirmed));
        } else if (i == 2 && i2 == 0) {
            this.mIconController.animateOnce(C1776R$drawable.face_dialog_error_to_idle);
            this.mFaceIcon.setContentDescription(this.mContext.getString(C1784R$string.biometric_dialog_face_icon_description_idle));
        } else if (i == 2 && i2 == 4) {
            this.mHandler.removeCallbacks(this.mErrorToIdleAnimationRunnable);
            this.mIconController.animateOnce(C1776R$drawable.face_dialog_dark_to_checkmark);
            this.mFaceIcon.setContentDescription(this.mContext.getString(C1784R$string.biometric_dialog_face_icon_description_authenticated));
        } else if (i2 == 2) {
            if (!this.mHandler.hasCallbacks(this.mErrorToIdleAnimationRunnable)) {
                this.mIconController.animateOnce(C1776R$drawable.face_dialog_dark_to_error);
                this.mHandler.postDelayed(this.mErrorToIdleAnimationRunnable, 2000);
            }
        } else if (i == 1 && i2 == 4) {
            this.mIconController.animateOnce(C1776R$drawable.face_dialog_dark_to_checkmark);
            this.mFaceIcon.setContentDescription(this.mContext.getString(C1784R$string.biometric_dialog_face_icon_description_authenticated));
        } else if (i2 == 3) {
            this.mHandler.removeCallbacks(this.mErrorToIdleAnimationRunnable);
            this.mIconController.animateOnce(C1776R$drawable.face_dialog_wink_from_dark);
            this.mFaceIcon.setContentDescription(this.mContext.getString(C1784R$string.biometric_dialog_face_icon_description_authenticated));
        } else if (i2 == 0) {
            this.mIconController.showStatic(C1776R$drawable.face_dialog_idle_static);
            this.mFaceIcon.setContentDescription(this.mContext.getString(C1784R$string.biometric_dialog_face_icon_description_idle));
        } else {
            Log.w("FingerprintAndFaceDialogView", "Unknown animation from " + i + " -> " + i2);
        }
        this.mBiometricIcon.setImageDrawable(this.mContext.getDrawable(C1776R$drawable.fingerprint_dialog_fp_to_error));
        if (i == 2 && i2 == 2) {
            this.mHandler.removeCallbacks(this.mErrorToIdleAnimationRunnable);
            this.mHandler.postDelayed(this.mErrorToIdleAnimationRunnable, 2000);
        }
    }

    public void onDialogAnimatedIn() {
        super.onDialogAnimatedIn();
        this.mDialogAnimatedIn = true;
        this.mIconController.startPulsing();
    }
}
