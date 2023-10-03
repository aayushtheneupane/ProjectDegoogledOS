package com.android.settings.biometrics.fingerprint;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.media.AudioAttributes;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.android.settings.biometrics.BiometricEnrollSidecar;
import com.android.settings.biometrics.BiometricErrorDialog;
import com.android.settings.biometrics.BiometricsEnrollEnrolling;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;
import com.google.android.setupdesign.util.DescriptionStyler;
import com.havoc.config.center.C1715R;

public class FingerprintEnrollEnrolling extends BiometricsEnrollEnrolling {
    private static final AudioAttributes FINGERPRINT_ENROLLING_SONFICATION_ATTRIBUTES = new AudioAttributes.Builder().setContentType(4).setUsage(13).build();
    private static final VibrationEffect VIBRATE_EFFECT_ERROR = VibrationEffect.createWaveform(new long[]{0, 5, 55, 60}, -1);
    /* access modifiers changed from: private */
    public boolean mAnimationCancelled;
    /* access modifiers changed from: private */
    public final Runnable mDelayedFinishRunnable = new Runnable() {
        public void run() {
            FingerprintEnrollEnrolling fingerprintEnrollEnrolling = FingerprintEnrollEnrolling.this;
            fingerprintEnrollEnrolling.launchFinish(fingerprintEnrollEnrolling.mToken);
        }
    };
    private TextView mErrorText;
    private Interpolator mFastOutLinearInInterpolator;
    private Interpolator mFastOutSlowInInterpolator;
    private final Animatable2.AnimationCallback mIconAnimationCallback = new Animatable2.AnimationCallback() {
        public void onAnimationEnd(Drawable drawable) {
            if (!FingerprintEnrollEnrolling.this.mAnimationCancelled) {
                FingerprintEnrollEnrolling.this.mProgressBar.post(new Runnable() {
                    public void run() {
                        FingerprintEnrollEnrolling.this.startIconAnimation();
                    }
                });
            }
        }
    };
    private AnimatedVectorDrawable mIconAnimationDrawable;
    private AnimatedVectorDrawable mIconBackgroundBlinksDrawable;
    /* access modifiers changed from: private */
    public int mIconTouchCount;
    private Interpolator mLinearOutSlowInInterpolator;
    private ObjectAnimator mProgressAnim;
    private final Animator.AnimatorListener mProgressAnimationListener = new Animator.AnimatorListener() {
        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }

        public void onAnimationStart(Animator animator) {
        }

        public void onAnimationEnd(Animator animator) {
            if (FingerprintEnrollEnrolling.this.mProgressBar.getProgress() >= 10000) {
                FingerprintEnrollEnrolling.this.mProgressBar.postDelayed(FingerprintEnrollEnrolling.this.mDelayedFinishRunnable, 250);
            }
        }
    };
    /* access modifiers changed from: private */
    public ProgressBar mProgressBar;
    private TextView mRepeatMessage;
    private boolean mRestoring;
    /* access modifiers changed from: private */
    public final Runnable mShowDialogRunnable = new Runnable() {
        public void run() {
            FingerprintEnrollEnrolling.this.showIconTouchDialog();
        }
    };
    private TextView mStartMessage;
    private final Runnable mTouchAgainRunnable = new Runnable() {
        public void run() {
            FingerprintEnrollEnrolling fingerprintEnrollEnrolling = FingerprintEnrollEnrolling.this;
            fingerprintEnrollEnrolling.showError(fingerprintEnrollEnrolling.getString(C1715R.string.security_settings_fingerprint_enroll_lift_touch_again));
        }
    };
    private Vibrator mVibrator;

    public int getMetricsCategory() {
        return 240;
    }

    /* access modifiers changed from: protected */
    public boolean shouldStartAutomatically() {
        return true;
    }

    static /* synthetic */ int access$008(FingerprintEnrollEnrolling fingerprintEnrollEnrolling) {
        int i = fingerprintEnrollEnrolling.mIconTouchCount;
        fingerprintEnrollEnrolling.mIconTouchCount = i + 1;
        return i;
    }

    public static class FingerprintErrorDialog extends BiometricErrorDialog {
        public int getMetricsCategory() {
            return 569;
        }

        public int getOkButtonTextResId() {
            return C1715R.string.security_settings_fingerprint_enroll_dialog_ok;
        }

        public int getTitleResId() {
            return C1715R.string.security_settings_fingerprint_enroll_error_dialog_title;
        }

        static FingerprintErrorDialog newInstance(CharSequence charSequence, int i) {
            FingerprintErrorDialog fingerprintErrorDialog = new FingerprintErrorDialog();
            Bundle bundle = new Bundle();
            bundle.putCharSequence("error_msg", charSequence);
            bundle.putInt("error_id", i);
            fingerprintErrorDialog.setArguments(bundle);
            return fingerprintErrorDialog;
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1715R.layout.fingerprint_enroll_enrolling);
        setHeaderText(C1715R.string.security_settings_fingerprint_enroll_repeat_title);
        this.mStartMessage = (TextView) findViewById(C1715R.C1718id.sud_layout_description);
        this.mRepeatMessage = (TextView) findViewById(C1715R.C1718id.repeat_message);
        this.mErrorText = (TextView) findViewById(C1715R.C1718id.error_text);
        this.mProgressBar = (ProgressBar) findViewById(C1715R.C1718id.fingerprint_progress_bar);
        this.mVibrator = (Vibrator) getSystemService(Vibrator.class);
        if (getLayout().shouldApplyPartnerHeavyThemeResource()) {
            DescriptionStyler.applyPartnerCustomizationStyle(this.mRepeatMessage);
        }
        this.mFooterBarMixin = (FooterBarMixin) getLayout().getMixin(FooterBarMixin.class);
        FooterBarMixin footerBarMixin = this.mFooterBarMixin;
        FooterButton.Builder builder = new FooterButton.Builder(this);
        builder.setText(C1715R.string.security_settings_fingerprint_enroll_enrolling_skip);
        builder.setListener(new View.OnClickListener() {
            public final void onClick(View view) {
                FingerprintEnrollEnrolling.this.onSkipButtonClick(view);
            }
        });
        builder.setButtonType(7);
        builder.setTheme(2131952051);
        footerBarMixin.setSecondaryButton(builder.build());
        LayerDrawable layerDrawable = (LayerDrawable) this.mProgressBar.getBackground();
        this.mIconAnimationDrawable = (AnimatedVectorDrawable) layerDrawable.findDrawableByLayerId(C1715R.C1718id.fingerprint_animation);
        this.mIconBackgroundBlinksDrawable = (AnimatedVectorDrawable) layerDrawable.findDrawableByLayerId(C1715R.C1718id.fingerprint_background);
        this.mIconAnimationDrawable.registerAnimationCallback(this.mIconAnimationCallback);
        this.mFastOutSlowInInterpolator = AnimationUtils.loadInterpolator(this, 17563661);
        this.mLinearOutSlowInInterpolator = AnimationUtils.loadInterpolator(this, 17563662);
        this.mFastOutLinearInInterpolator = AnimationUtils.loadInterpolator(this, 17563663);
        this.mProgressBar.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getActionMasked() == 0) {
                    FingerprintEnrollEnrolling.access$008(FingerprintEnrollEnrolling.this);
                    if (FingerprintEnrollEnrolling.this.mIconTouchCount == 3) {
                        FingerprintEnrollEnrolling.this.showIconTouchDialog();
                    } else {
                        FingerprintEnrollEnrolling.this.mProgressBar.postDelayed(FingerprintEnrollEnrolling.this.mShowDialogRunnable, 500);
                    }
                } else if (motionEvent.getActionMasked() == 3 || motionEvent.getActionMasked() == 1) {
                    FingerprintEnrollEnrolling.this.mProgressBar.removeCallbacks(FingerprintEnrollEnrolling.this.mShowDialogRunnable);
                }
                return true;
            }
        });
        this.mRestoring = bundle != null;
    }

    /* access modifiers changed from: protected */
    public BiometricEnrollSidecar getSidecar() {
        return new FingerprintEnrollSidecar();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        updateProgress(false);
        updateDescription();
        if (this.mRestoring) {
            startIconAnimation();
        }
    }

    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
        this.mAnimationCancelled = false;
        startIconAnimation();
    }

    /* access modifiers changed from: private */
    public void startIconAnimation() {
        this.mIconAnimationDrawable.start();
    }

    private void stopIconAnimation() {
        this.mAnimationCancelled = true;
        this.mIconAnimationDrawable.stop();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        stopIconAnimation();
    }

    private void animateProgress(int i) {
        ObjectAnimator objectAnimator = this.mProgressAnim;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        ProgressBar progressBar = this.mProgressBar;
        ObjectAnimator ofInt = ObjectAnimator.ofInt(progressBar, "progress", new int[]{progressBar.getProgress(), i});
        ofInt.addListener(this.mProgressAnimationListener);
        ofInt.setInterpolator(this.mFastOutSlowInInterpolator);
        ofInt.setDuration(250);
        ofInt.start();
        this.mProgressAnim = ofInt;
    }

    private void animateFlash() {
        this.mIconBackgroundBlinksDrawable.start();
    }

    /* access modifiers changed from: protected */
    public Intent getFinishIntent() {
        return new Intent(this, FingerprintEnrollFinish.class);
    }

    private void updateDescription() {
        if (this.mSidecar.getEnrollmentSteps() == -1) {
            this.mStartMessage.setVisibility(0);
            this.mRepeatMessage.setVisibility(4);
            return;
        }
        this.mStartMessage.setVisibility(4);
        this.mRepeatMessage.setVisibility(0);
    }

    public void onEnrollmentHelp(int i, CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            this.mErrorText.removeCallbacks(this.mTouchAgainRunnable);
            showError(charSequence);
        }
    }

    public void onEnrollmentError(int i, CharSequence charSequence) {
        showErrorDialog(getText(i != 3 ? C1715R.string.f99x537c56b0 : C1715R.string.f100xe7f94de6), i);
        stopIconAnimation();
        this.mErrorText.removeCallbacks(this.mTouchAgainRunnable);
    }

    public void onEnrollmentProgressChange(int i, int i2) {
        updateProgress(true);
        updateDescription();
        clearError();
        animateFlash();
        this.mErrorText.removeCallbacks(this.mTouchAgainRunnable);
        this.mErrorText.postDelayed(this.mTouchAgainRunnable, 2500);
    }

    private void updateProgress(boolean z) {
        int progress = getProgress(this.mSidecar.getEnrollmentSteps(), this.mSidecar.getEnrollmentRemaining());
        if (z) {
            animateProgress(progress);
            return;
        }
        this.mProgressBar.setProgress(progress);
        if (progress >= 10000) {
            this.mDelayedFinishRunnable.run();
        }
    }

    private int getProgress(int i, int i2) {
        if (i == -1) {
            return 0;
        }
        int i3 = i + 1;
        return (Math.max(0, i3 - i2) * 10000) / i3;
    }

    private void showErrorDialog(CharSequence charSequence, int i) {
        FingerprintErrorDialog.newInstance(charSequence, i).show(getSupportFragmentManager(), FingerprintErrorDialog.class.getName());
    }

    /* access modifiers changed from: private */
    public void showIconTouchDialog() {
        this.mIconTouchCount = 0;
        new IconTouchDialog().show(getSupportFragmentManager(), (String) null);
    }

    /* access modifiers changed from: private */
    public void showError(CharSequence charSequence) {
        this.mErrorText.setText(charSequence);
        if (this.mErrorText.getVisibility() == 4) {
            this.mErrorText.setVisibility(0);
            this.mErrorText.setTranslationY((float) getResources().getDimensionPixelSize(C1715R.dimen.fingerprint_error_text_appear_distance));
            this.mErrorText.setAlpha(0.0f);
            this.mErrorText.animate().alpha(1.0f).translationY(0.0f).setDuration(200).setInterpolator(this.mLinearOutSlowInInterpolator).start();
        } else {
            this.mErrorText.animate().cancel();
            this.mErrorText.setAlpha(1.0f);
            this.mErrorText.setTranslationY(0.0f);
        }
        if (isResumed()) {
            this.mVibrator.vibrate(VIBRATE_EFFECT_ERROR, FINGERPRINT_ENROLLING_SONFICATION_ATTRIBUTES);
        }
    }

    private void clearError() {
        if (this.mErrorText.getVisibility() == 0) {
            this.mErrorText.animate().alpha(0.0f).translationY((float) getResources().getDimensionPixelSize(C1715R.dimen.fingerprint_error_text_disappear_distance)).setDuration(100).setInterpolator(this.mFastOutLinearInInterpolator).withEndAction(new Runnable() {
                public final void run() {
                    FingerprintEnrollEnrolling.this.lambda$clearError$0$FingerprintEnrollEnrolling();
                }
            }).start();
        }
    }

    public /* synthetic */ void lambda$clearError$0$FingerprintEnrollEnrolling() {
        this.mErrorText.setVisibility(4);
    }

    public static class IconTouchDialog extends InstrumentedDialogFragment {
        public int getMetricsCategory() {
            return 568;
        }

        public Dialog onCreateDialog(Bundle bundle) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle((int) C1715R.string.security_settings_fingerprint_enroll_touch_dialog_title);
            builder.setMessage((int) C1715R.string.security_settings_fingerprint_enroll_touch_dialog_message);
            builder.setPositiveButton((int) C1715R.string.security_settings_fingerprint_enroll_dialog_ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            return builder.create();
        }
    }
}
