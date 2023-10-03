package com.android.systemui.biometrics;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Outline;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;

public class FaceDialogView extends BiometricDialogView {
    private boolean mDialogAnimatedIn;
    private final Runnable mErrorToIdleAnimationRunnable = new Runnable() {
        public final void run() {
            FaceDialogView.this.lambda$new$0$FaceDialogView();
        }
    };
    private IconController mIconController = new IconController();
    private float mIconOriginalY;
    private DialogOutlineProvider mOutlineProvider = new DialogOutlineProvider();
    /* access modifiers changed from: private */
    public int mSize;

    /* access modifiers changed from: protected */
    public int getDelayAfterAuthenticatedDurationMs() {
        return 500;
    }

    /* access modifiers changed from: protected */
    public int getHintStringResourceId() {
        return 0;
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
            FaceDialogView faceDialogView = FaceDialogView.this;
            faceDialogView.mBiometricIcon.setImageDrawable(faceDialogView.mContext.getDrawable(i));
        }

        public void startPulsing() {
            this.mLastPulseDirection = false;
            animateIcon(C1776R$drawable.face_dialog_pulse_dark_to_light, true);
        }

        public void showIcon(int i) {
            FaceDialogView.this.mBiometricIcon.setImageDrawable(FaceDialogView.this.mContext.getDrawable(i));
        }

        private void animateIcon(int i, boolean z) {
            AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) FaceDialogView.this.mContext.getDrawable(i);
            FaceDialogView.this.mBiometricIcon.setImageDrawable(animatedVectorDrawable);
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

    private final class DialogOutlineProvider extends ViewOutlineProvider {

        /* renamed from: mY */
        float f33mY;

        private DialogOutlineProvider() {
        }

        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, (int) this.f33mY, FaceDialogView.this.mDialog.getWidth(), FaceDialogView.this.mDialog.getBottom(), FaceDialogView.this.getResources().getDimension(C1775R$dimen.biometric_dialog_corner_size));
        }

        /* access modifiers changed from: package-private */
        public int calculateSmall() {
            return (FaceDialogView.this.mDialog.getHeight() - FaceDialogView.this.mBiometricIcon.getHeight()) - (((int) FaceDialogView.this.dpToPixels(16.0f)) * 2);
        }

        /* access modifiers changed from: package-private */
        public void setOutlineY(float f) {
            this.f33mY = f;
        }
    }

    public /* synthetic */ void lambda$new$0$FaceDialogView() {
        updateState(0);
        this.mErrorText.setVisibility(4);
        announceAccessibilityEvent();
    }

    public FaceDialogView(Context context, DialogViewCallback dialogViewCallback) {
        super(context, dialogViewCallback);
    }

    /* access modifiers changed from: private */
    public void updateSize(int i) {
        float height = ((float) (this.mDialog.getHeight() - this.mBiometricIcon.getHeight())) - dpToPixels(16.0f);
        if (i == 1) {
            this.mTitleText.setVisibility(this.mAppLockDialog ? 8 : 4);
            this.mErrorText.setVisibility(4);
            this.mNegativeButton.setVisibility(4);
            if (!TextUtils.isEmpty(this.mSubtitleText.getText())) {
                this.mSubtitleText.setVisibility(4);
            }
            if (!TextUtils.isEmpty(this.mDescriptionText.getText())) {
                this.mDescriptionText.setVisibility(4);
            }
            this.mBiometricIcon.setY(height);
            this.mDialog.setOutlineProvider(this.mOutlineProvider);
            DialogOutlineProvider dialogOutlineProvider = this.mOutlineProvider;
            dialogOutlineProvider.setOutlineY((float) dialogOutlineProvider.calculateSmall());
            this.mDialog.setClipToOutline(true);
            this.mDialog.invalidateOutline();
            this.mSize = i;
            announceAccessibilityEvent();
        } else if (this.mSize == 1 && i == 3) {
            this.mSize = 2;
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{(float) this.mOutlineProvider.calculateSmall(), 0.0f});
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    FaceDialogView.this.lambda$updateSize$1$FaceDialogView(valueAnimator);
                }
            });
            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{height, this.mIconOriginalY});
            ofFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    FaceDialogView.this.lambda$updateSize$2$FaceDialogView(valueAnimator);
                }
            });
            ValueAnimator ofFloat3 = ValueAnimator.ofFloat(new float[]{dpToPixels(32.0f), 0.0f});
            ofFloat3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    FaceDialogView.this.lambda$updateSize$3$FaceDialogView(valueAnimator);
                }
            });
            ValueAnimator ofFloat4 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            ofFloat4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    FaceDialogView.this.lambda$updateSize$4$FaceDialogView(valueAnimator);
                }
            });
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(150);
            animatorSet.addListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                    super.onAnimationStart(animator);
                    FaceDialogView faceDialogView = FaceDialogView.this;
                    if (!faceDialogView.mAppLockDialog) {
                        faceDialogView.mTitleText.setVisibility(0);
                    }
                    FaceDialogView.this.mErrorText.setVisibility(0);
                    FaceDialogView.this.mNegativeButton.setVisibility(0);
                    FaceDialogView.this.mTryAgainButton.setVisibility(0);
                    if (!TextUtils.isEmpty(FaceDialogView.this.mSubtitleText.getText())) {
                        FaceDialogView.this.mSubtitleText.setVisibility(0);
                    }
                    if (!TextUtils.isEmpty(FaceDialogView.this.mDescriptionText.getText())) {
                        FaceDialogView.this.mDescriptionText.setVisibility(0);
                    }
                }

                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    int unused = FaceDialogView.this.mSize = 3;
                    FaceDialogView faceDialogView = FaceDialogView.this;
                    faceDialogView.updateSize(faceDialogView.mSize);
                }
            });
            animatorSet.play(ofFloat).with(ofFloat2).with(ofFloat4).with(ofFloat3);
            animatorSet.start();
        } else if (this.mSize == 3) {
            this.mDialog.setClipToOutline(false);
            this.mDialog.invalidateOutline();
            this.mBiometricIcon.setY(this.mIconOriginalY);
            this.mSize = i;
        }
    }

    public /* synthetic */ void lambda$updateSize$1$FaceDialogView(ValueAnimator valueAnimator) {
        this.mOutlineProvider.setOutlineY(((Float) valueAnimator.getAnimatedValue()).floatValue());
        this.mDialog.invalidateOutline();
    }

    public /* synthetic */ void lambda$updateSize$2$FaceDialogView(ValueAnimator valueAnimator) {
        this.mBiometricIcon.setY(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    public /* synthetic */ void lambda$updateSize$3$FaceDialogView(ValueAnimator valueAnimator) {
        this.mErrorText.setTranslationY(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    public /* synthetic */ void lambda$updateSize$4$FaceDialogView(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.mTitleText.setAlpha(floatValue);
        this.mErrorText.setAlpha(floatValue);
        this.mNegativeButton.setAlpha(floatValue);
        this.mTryAgainButton.setAlpha(floatValue);
        if (!TextUtils.isEmpty(this.mSubtitleText.getText())) {
            this.mSubtitleText.setAlpha(floatValue);
        }
        if (!TextUtils.isEmpty(this.mDescriptionText.getText())) {
            this.mDescriptionText.setAlpha(floatValue);
        }
    }

    public void onSaveState(Bundle bundle) {
        super.onSaveState(bundle);
        bundle.putInt("key_dialog_size", this.mSize);
        bundle.putBoolean("key_dialog_animated_in", this.mDialogAnimatedIn);
    }

    /* access modifiers changed from: protected */
    public void handleResetMessage() {
        this.mErrorText.setTextColor(this.mTextColor);
        this.mErrorText.setVisibility(4);
        announceAccessibilityEvent();
    }

    public void restoreState(Bundle bundle) {
        super.restoreState(bundle);
        this.mSize = bundle.getInt("key_dialog_size");
        this.mDialogAnimatedIn = bundle.getBoolean("key_dialog_animated_in");
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.mIconOriginalY == 0.0f) {
            this.mIconOriginalY = this.mBiometricIcon.getY();
        }
        int i5 = this.mSize;
        if (i5 != 0) {
            if (i5 == 1) {
                updateSize(1);
            }
        } else if (!requiresConfirmation()) {
            updateSize(1);
        } else {
            updateSize(3);
        }
    }

    public void onErrorReceived(String str) {
        super.onErrorReceived(str);
        if (this.mSize == 1) {
            updateSize(3);
        }
    }

    public void onAuthenticationFailed(String str) {
        super.onAuthenticationFailed(str);
        showTryAgainButton(true);
    }

    public void showTryAgainButton(boolean z) {
        if (z && this.mSize == 1) {
            updateSize(3);
        } else if (z) {
            this.mTryAgainButton.setVisibility(0);
        } else {
            this.mTryAgainButton.setVisibility(8);
            announceAccessibilityEvent();
        }
        if (z) {
            this.mPositiveButton.setVisibility(8);
            announceAccessibilityEvent();
        }
    }

    /* access modifiers changed from: protected */
    public int getAuthenticatedAccessibilityResourceId() {
        return this.mRequireConfirmation ? 17040022 : 17040023;
    }

    /* access modifiers changed from: protected */
    public int getIconDescriptionResourceId() {
        return C1784R$string.accessibility_face_dialog_face_icon;
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
            this.mBiometricIcon.setContentDescription(this.mContext.getString(C1784R$string.biometric_dialog_face_icon_description_authenticating));
        } else if (i == 3 && i2 == 4) {
            iconController.animateOnce(C1776R$drawable.face_dialog_dark_to_checkmark);
            this.mBiometricIcon.setContentDescription(this.mContext.getString(C1784R$string.biometric_dialog_face_icon_description_confirmed));
        } else if (i == 2 && i2 == 0) {
            this.mIconController.animateOnce(C1776R$drawable.face_dialog_error_to_idle);
            this.mBiometricIcon.setContentDescription(this.mContext.getString(C1784R$string.biometric_dialog_face_icon_description_idle));
        } else if (i == 2 && i2 == 4) {
            this.mHandler.removeCallbacks(this.mErrorToIdleAnimationRunnable);
            this.mIconController.animateOnce(C1776R$drawable.face_dialog_dark_to_checkmark);
            this.mBiometricIcon.setContentDescription(this.mContext.getString(C1784R$string.biometric_dialog_face_icon_description_authenticated));
        } else if (i2 == 2) {
            if (!this.mHandler.hasCallbacks(this.mErrorToIdleAnimationRunnable)) {
                this.mIconController.animateOnce(C1776R$drawable.face_dialog_dark_to_error);
                this.mHandler.postDelayed(this.mErrorToIdleAnimationRunnable, 2000);
            }
        } else if (i == 1 && i2 == 4) {
            this.mIconController.animateOnce(C1776R$drawable.face_dialog_dark_to_checkmark);
            this.mBiometricIcon.setContentDescription(this.mContext.getString(C1784R$string.biometric_dialog_face_icon_description_authenticated));
        } else if (i2 == 3) {
            this.mHandler.removeCallbacks(this.mErrorToIdleAnimationRunnable);
            this.mIconController.animateOnce(C1776R$drawable.face_dialog_wink_from_dark);
            this.mBiometricIcon.setContentDescription(this.mContext.getString(C1784R$string.biometric_dialog_face_icon_description_authenticated));
        } else if (i2 == 0) {
            this.mIconController.showStatic(C1776R$drawable.face_dialog_idle_static);
            this.mBiometricIcon.setContentDescription(this.mContext.getString(C1784R$string.biometric_dialog_face_icon_description_idle));
        } else {
            Log.w("FaceDialogView", "Unknown animation from " + i + " -> " + i2);
        }
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

    /* access modifiers changed from: protected */
    public boolean shouldGrayAreaDismissDialog() {
        return this.mSize != 1;
    }

    /* access modifiers changed from: private */
    public float dpToPixels(float f) {
        return f * (((float) this.mContext.getResources().getDisplayMetrics().densityDpi) / 160.0f);
    }
}
