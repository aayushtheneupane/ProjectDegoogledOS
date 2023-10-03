package com.android.systemui.biometrics;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.UserManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.systemui.C1774R$color;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1779R$layout;
import com.android.systemui.C1784R$string;
import com.android.systemui.Interpolators;
import com.android.systemui.biometrics.BiometricDialogView;
import com.android.systemui.util.leak.RotationUtils;

public abstract class BiometricDialogView extends LinearLayout {
    private final AccessibilityManager mAccessibilityManager;
    /* access modifiers changed from: private */
    public boolean mAnimatingAway;
    /* access modifiers changed from: private */
    public final float mAnimationTranslationOffset;
    protected final ImageView mAppIcon;
    protected boolean mAppLockDialog;
    protected final ImageView mBiometricIcon;
    private Bundle mBundle;
    protected final DialogViewCallback mCallback;
    private boolean mCompletedAnimatingIn;
    protected final TextView mDescriptionText;
    private final DevicePolicyManager mDevicePolicyManager;
    protected final LinearLayout mDialog;
    private final float mDialogWidth;
    private final int mErrorColor;
    protected final TextView mErrorText;
    protected Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            if (message.what != 1) {
                Log.e("BiometricDialogView", "Unhandled message: " + message.what);
                return;
            }
            BiometricDialogView.this.handleResetMessage();
        }
    };
    private final boolean mHasFod;
    private boolean mIsFace;
    private boolean mIsFingerprint;
    protected final ViewGroup mLayout;
    /* access modifiers changed from: private */
    public final Interpolator mLinearOutSlowIn;
    protected final Button mNegativeButton;
    private final PackageManager mPackageManager;
    private boolean mPendingDismissDialog;
    protected final Button mPositiveButton;
    protected boolean mRequireConfirmation;
    private Bundle mRestoredState;
    private final Runnable mShowAnimationRunnable = new Runnable() {
        public void run() {
            BiometricDialogView.this.mLayout.animate().alpha(1.0f).setDuration(250).setInterpolator(BiometricDialogView.this.mLinearOutSlowIn).withLayer().start();
            BiometricDialogView.this.mDialog.animate().translationY(0.0f).setDuration(250).setInterpolator(BiometricDialogView.this.mLinearOutSlowIn).withEndAction(new Runnable() {
                public final void run() {
                    BiometricDialogView.C06681.this.lambda$run$0$BiometricDialogView$1();
                }
            }).start();
        }

        public /* synthetic */ void lambda$run$0$BiometricDialogView$1() {
            BiometricDialogView.this.onDialogAnimatedIn();
        }
    };
    private boolean mSkipIntro;
    private int mState = 0;
    protected final TextView mSubtitleText;
    protected final int mTextColor;
    protected final TextView mTitleText;
    protected final Button mTryAgainButton;
    private int mUserId;
    private final UserManager mUserManager;
    private boolean mWasForceRemoved;
    /* access modifiers changed from: private */
    public final WindowManager mWindowManager;
    private final IBinder mWindowToken = new Binder();

    /* access modifiers changed from: protected */
    public abstract int getAuthenticatedAccessibilityResourceId();

    /* access modifiers changed from: protected */
    public abstract int getDelayAfterAuthenticatedDurationMs();

    /* access modifiers changed from: protected */
    public abstract int getHintStringResourceId();

    /* access modifiers changed from: protected */
    public abstract int getIconDescriptionResourceId();

    /* access modifiers changed from: protected */
    public abstract void handleResetMessage();

    /* access modifiers changed from: protected */
    public abstract boolean shouldGrayAreaDismissDialog();

    public void showTryAgainButton(boolean z) {
    }

    /* access modifiers changed from: protected */
    public abstract void updateIcon(int i, int i2);

    public BiometricDialogView(Context context, DialogViewCallback dialogViewCallback) {
        super(context);
        this.mCallback = dialogViewCallback;
        this.mLinearOutSlowIn = Interpolators.LINEAR_OUT_SLOW_IN;
        this.mAccessibilityManager = (AccessibilityManager) this.mContext.getSystemService(AccessibilityManager.class);
        this.mWindowManager = (WindowManager) this.mContext.getSystemService(WindowManager.class);
        this.mUserManager = (UserManager) this.mContext.getSystemService(UserManager.class);
        this.mDevicePolicyManager = (DevicePolicyManager) this.mContext.getSystemService(DevicePolicyManager.class);
        this.mPackageManager = this.mContext.getPackageManager();
        this.mAnimationTranslationOffset = getResources().getDimension(C1775R$dimen.biometric_dialog_animation_translation_offset);
        this.mErrorColor = getResources().getColor(C1774R$color.biometric_dialog_error);
        this.mTextColor = getResources().getColor(C1774R$color.biometric_dialog_gray);
        this.mHasFod = this.mPackageManager.hasSystemFeature("vendor.lineage.biometrics.fingerprint.inscreen");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.mWindowManager.getDefaultDisplay().getMetrics(displayMetrics);
        this.mDialogWidth = (float) Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
        this.mLayout = (ViewGroup) LayoutInflater.from(getContext()).inflate(C1779R$layout.biometric_dialog, this, false);
        addView(this.mLayout);
        this.mLayout.setOnKeyListener(new View.OnKeyListener() {
            boolean downPressed = false;

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i != 4) {
                    return false;
                }
                if (keyEvent.getAction() == 0 && !this.downPressed) {
                    this.downPressed = true;
                } else if (keyEvent.getAction() == 0) {
                    this.downPressed = false;
                } else if (keyEvent.getAction() == 1 && this.downPressed) {
                    this.downPressed = false;
                    BiometricDialogView.this.mCallback.onUserCanceled();
                }
                return true;
            }
        });
        View findViewById = this.mLayout.findViewById(C1777R$id.space);
        View findViewById2 = this.mLayout.findViewById(C1777R$id.left_space);
        View findViewById3 = this.mLayout.findViewById(C1777R$id.right_space);
        this.mDialog = (LinearLayout) this.mLayout.findViewById(C1777R$id.dialog);
        this.mTitleText = (TextView) this.mLayout.findViewById(C1777R$id.title);
        this.mSubtitleText = (TextView) this.mLayout.findViewById(C1777R$id.subtitle);
        this.mDescriptionText = (TextView) this.mLayout.findViewById(C1777R$id.description);
        this.mBiometricIcon = (ImageView) this.mLayout.findViewById(C1777R$id.biometric_icon);
        this.mErrorText = (TextView) this.mLayout.findViewById(C1777R$id.error);
        this.mNegativeButton = (Button) this.mLayout.findViewById(C1777R$id.button2);
        this.mPositiveButton = (Button) this.mLayout.findViewById(C1777R$id.button1);
        this.mTryAgainButton = (Button) this.mLayout.findViewById(C1777R$id.button_try_again);
        this.mAppIcon = new ImageView(context);
        int dimensionPixelSize = getResources().getDimensionPixelSize(C1775R$dimen.applock_icon_dimension);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize);
        layoutParams.gravity = 17;
        layoutParams.topMargin = (-dimensionPixelSize) / 2;
        this.mAppIcon.setLayoutParams(layoutParams);
        this.mAppIcon.setVisibility(8);
        this.mDialog.addView(this.mAppIcon, 0);
        ((ViewGroup) this.mDialog.getParent()).setClipChildren(false);
        ((ViewGroup) this.mDialog.getParent().getParent()).setClipChildren(false);
        ((ViewGroup) this.mDialog.getParent().getParent().getParent()).setClipChildren(false);
        this.mBiometricIcon.setContentDescription(getResources().getString(getIconDescriptionResourceId()));
        setDismissesDialog(findViewById);
        setDismissesDialog(findViewById2);
        setDismissesDialog(findViewById3);
        this.mNegativeButton.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                BiometricDialogView.this.lambda$new$0$BiometricDialogView(view);
            }
        });
        this.mPositiveButton.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                BiometricDialogView.this.lambda$new$2$BiometricDialogView(view);
            }
        });
        this.mTryAgainButton.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                BiometricDialogView.this.lambda$new$3$BiometricDialogView(view);
            }
        });
        this.mLayout.setFocusableInTouchMode(true);
        this.mLayout.requestFocus();
    }

    public /* synthetic */ void lambda$new$0$BiometricDialogView(View view) {
        int i = this.mState;
        if (i == 3 || i == 4) {
            this.mCallback.onUserCanceled();
        } else {
            this.mCallback.onNegativePressed();
        }
    }

    public /* synthetic */ void lambda$new$2$BiometricDialogView(View view) {
        updateState(4);
        this.mHandler.postDelayed(new Runnable() {
            public final void run() {
                BiometricDialogView.this.lambda$new$1$BiometricDialogView();
            }
        }, (long) getDelayAfterAuthenticatedDurationMs());
    }

    public /* synthetic */ void lambda$new$1$BiometricDialogView() {
        this.mCallback.onPositivePressed();
    }

    public /* synthetic */ void lambda$new$3$BiometricDialogView(View view) {
        handleResetMessage();
        updateState(1);
        showTryAgainButton(false);
        this.mPositiveButton.setVisibility(0);
        this.mPositiveButton.setEnabled(false);
        this.mCallback.onTryAgainPressed();
    }

    public void onSaveState(Bundle bundle) {
        bundle.putInt("key_try_again_visibility", this.mTryAgainButton.getVisibility());
        bundle.putInt("key_confirm_visibility", this.mPositiveButton.getVisibility());
        bundle.putBoolean("key_confirm_enabled", this.mPositiveButton.isEnabled());
        bundle.putInt("key_state", this.mState);
        bundle.putInt("key_error_text_visibility", this.mErrorText.getVisibility());
        bundle.putCharSequence("key_error_text_string", this.mErrorText.getText());
        bundle.putBoolean("key_error_text_is_temporary", this.mHandler.hasMessages(1));
        bundle.putInt("key_error_text_color", this.mErrorText.getCurrentTextColor());
    }

    public void onAttachedToWindow() {
        ApplicationInfo applicationInfo;
        super.onAttachedToWindow();
        ImageView imageView = (ImageView) this.mLayout.findViewById(C1777R$id.background);
        Drawable drawable = null;
        if (this.mUserManager.isManagedProfile(this.mUserId)) {
            Drawable drawable2 = getResources().getDrawable(C1776R$drawable.work_challenge_background, this.mContext.getTheme());
            drawable2.setColorFilter(this.mDevicePolicyManager.getOrganizationColorForUser(this.mUserId), PorterDuff.Mode.DARKEN);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageDrawable(drawable2);
        } else {
            imageView.setImageDrawable((Drawable) null);
            imageView.setBackgroundColor(C1774R$color.biometric_dialog_dim_color);
        }
        this.mNegativeButton.setVisibility(0);
        if (RotationUtils.getRotation(this.mContext) != 0) {
            this.mDialog.getLayoutParams().width = (int) this.mDialogWidth;
        }
        if (this.mRestoredState == null) {
            updateState(1);
            this.mNegativeButton.setText(this.mBundle.getCharSequence("negative_text"));
            int hintStringResourceId = getHintStringResourceId();
            if (hintStringResourceId != 0) {
                this.mErrorText.setText(hintStringResourceId);
                this.mErrorText.setContentDescription(this.mContext.getString(hintStringResourceId));
                this.mErrorText.setVisibility(0);
            } else {
                this.mErrorText.setVisibility(4);
            }
            announceAccessibilityEvent();
        } else {
            updateState(this.mState);
        }
        CharSequence charSequence = this.mBundle.getCharSequence("applock_package_name");
        CharSequence charSequence2 = this.mBundle.getCharSequence("title");
        if (TextUtils.isEmpty(charSequence)) {
            this.mAppLockDialog = false;
            this.mTitleText.setText(charSequence2);
            this.mAppIcon.setVisibility(8);
        } else {
            this.mAppLockDialog = true;
            try {
                applicationInfo = this.mPackageManager.getApplicationInfoAsUser(charSequence.toString(), 0, this.mUserId);
            } catch (PackageManager.NameNotFoundException unused) {
                applicationInfo = null;
            }
            if (applicationInfo != null) {
                drawable = this.mPackageManager.getApplicationIcon(applicationInfo);
            }
            if (drawable == null) {
                this.mAppIcon.setVisibility(8);
            } else {
                this.mAppIcon.setVisibility(0);
                this.mAppIcon.setImageDrawable(drawable);
            }
            this.mTitleText.setText(getResources().getString(C1784R$string.accessibility_unlock_button) + " " + charSequence2.toString());
        }
        CharSequence charSequence3 = this.mBundle.getCharSequence("subtitle");
        if (TextUtils.isEmpty(charSequence3)) {
            this.mSubtitleText.setVisibility(8);
            announceAccessibilityEvent();
        } else {
            this.mSubtitleText.setVisibility(0);
            this.mSubtitleText.setText(charSequence3);
        }
        CharSequence charSequence4 = this.mBundle.getCharSequence("description");
        if (!TextUtils.isEmpty(charSequence4) || this.mAppLockDialog) {
            this.mDescriptionText.setVisibility(0);
            if (this.mAppLockDialog) {
                charSequence4 = this.mBundle.getCharSequence("negative_text") + getResources().getString(getDescriptionTextId());
            }
            this.mDescriptionText.setText(charSequence4);
        } else {
            this.mDescriptionText.setVisibility(8);
            announceAccessibilityEvent();
        }
        if (requiresConfirmation() && this.mRestoredState == null) {
            this.mPositiveButton.setVisibility(0);
            this.mPositiveButton.setEnabled(false);
        }
        if (this.mWasForceRemoved || this.mSkipIntro) {
            this.mLayout.animate().cancel();
            this.mDialog.animate().cancel();
            this.mDialog.setAlpha(1.0f);
            this.mDialog.setTranslationY(0.0f);
            this.mLayout.setAlpha(1.0f);
            onDialogAnimatedIn();
        } else {
            this.mDialog.setTranslationY(this.mAnimationTranslationOffset);
            this.mLayout.setAlpha(0.0f);
            postOnAnimation(this.mShowAnimationRunnable);
        }
        this.mWasForceRemoved = false;
        this.mSkipIntro = false;
    }

    private int getDescriptionTextId() {
        if (this.mIsFingerprint && this.mIsFace) {
            return C1784R$string.applock_fingerprint_face;
        }
        if (this.mIsFace) {
            return C1784R$string.applock_face;
        }
        return C1784R$string.applock_fingerprint;
    }

    /* access modifiers changed from: protected */
    public int getAnimatingAwayDuration() {
        return (int) ((this.mAppLockDialog ? 1.3f : 1.0f) * 350.0f);
    }

    public void setFaceAndFingerprint(boolean z, boolean z2) {
        this.mIsFace = z;
        this.mIsFingerprint = z2;
        if (this.mIsFingerprint) {
            this.mBiometricIcon.setVisibility(this.mHasFod ? 4 : 0);
            boolean z3 = true;
            boolean z4 = getResources().getConfiguration().orientation == 1;
            if (this.mHasFod && z4) {
                if (Integer.parseInt(Settings.Secure.getStringForUser(this.mContext.getContentResolver(), "navigation_mode", -2)) != 2) {
                    z3 = false;
                }
                int dimensionPixelSize = getResources().getDimensionPixelSize(17105291);
                int dimensionPixelSize2 = getResources().getDimensionPixelSize(C1775R$dimen.biometric_dialog_fod_margin);
                ((LinearLayout.LayoutParams) this.mBiometricIcon.getLayoutParams()).topMargin = z3 ? dimensionPixelSize2 : dimensionPixelSize2 > dimensionPixelSize ? dimensionPixelSize2 - dimensionPixelSize : 0;
                this.mDialog.removeView(this.mErrorText);
                LinearLayout linearLayout = this.mDialog;
                linearLayout.addView(this.mErrorText, linearLayout.indexOfChild(this.mBiometricIcon));
                ((LinearLayout.LayoutParams) this.mDescriptionText.getLayoutParams()).bottomMargin = this.mErrorText.getPaddingTop();
                this.mErrorText.setPadding(0, 0, 0, 0);
            }
        } else if (this.mIsFace) {
            this.mBiometricIcon.setVisibility(0);
        }
    }

    private void setDismissesDialog(View view) {
        view.setClickable(true);
        view.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                BiometricDialogView.this.lambda$setDismissesDialog$4$BiometricDialogView(view);
            }
        });
    }

    public /* synthetic */ void lambda$setDismissesDialog$4$BiometricDialogView(View view) {
        if (this.mState != 4 && shouldGrayAreaDismissDialog()) {
            this.mCallback.onUserCanceled();
        }
    }

    public void startDismiss() {
        if (!this.mCompletedAnimatingIn) {
            Log.w("BiometricDialogView", "startDismiss(): waiting for onDialogAnimatedIn");
            this.mPendingDismissDialog = true;
            return;
        }
        this.mAnimatingAway = true;
        final C06714 r0 = new Runnable() {
            public void run() {
                BiometricDialogView.this.mWindowManager.removeView(BiometricDialogView.this);
                boolean unused = BiometricDialogView.this.mAnimatingAway = false;
                BiometricDialogView.this.handleResetMessage();
                BiometricDialogView.this.showTryAgainButton(false);
                BiometricDialogView.this.updateState(0);
            }
        };
        postOnAnimation(new Runnable() {
            public void run() {
                BiometricDialogView.this.mLayout.animate().alpha(0.0f).setDuration((long) BiometricDialogView.this.getAnimatingAwayDuration()).setInterpolator(BiometricDialogView.this.mLinearOutSlowIn).withLayer().start();
                BiometricDialogView.this.mDialog.animate().translationY(BiometricDialogView.this.mAnimationTranslationOffset).setDuration((long) BiometricDialogView.this.getAnimatingAwayDuration()).setInterpolator(BiometricDialogView.this.mLinearOutSlowIn).withEndAction(r0).start();
            }
        });
    }

    public void forceRemove() {
        this.mLayout.animate().cancel();
        this.mDialog.animate().cancel();
        this.mWindowManager.removeView(this);
        this.mAnimatingAway = false;
        this.mWasForceRemoved = true;
    }

    public void setSkipIntro(boolean z) {
        this.mSkipIntro = z;
    }

    public void setBundle(Bundle bundle) {
        this.mBundle = bundle;
    }

    public void setRequireConfirmation(boolean z) {
        this.mRequireConfirmation = z;
    }

    public boolean requiresConfirmation() {
        return this.mRequireConfirmation;
    }

    public void setUserId(int i) {
        this.mUserId = i;
    }

    /* access modifiers changed from: protected */
    public void showTemporaryMessage(String str) {
        this.mHandler.removeMessages(1);
        this.mErrorText.setText(str);
        this.mErrorText.setTextColor(this.mErrorColor);
        this.mErrorText.setContentDescription(str);
        this.mErrorText.setVisibility(0);
        Handler handler = this.mHandler;
        handler.sendMessageDelayed(handler.obtainMessage(1), 2000);
    }

    public void onHelpReceived(String str) {
        updateState(2);
        showTemporaryMessage(str);
    }

    public void onAuthenticationFailed(String str) {
        updateState(2);
        showTemporaryMessage(str);
    }

    public void onErrorReceived(String str) {
        updateState(2);
        showTemporaryMessage(str);
        showTryAgainButton(false);
        this.mCallback.onErrorShown();
    }

    public void updateState(int i) {
        if (i == 3) {
            this.mHandler.removeMessages(1);
            this.mErrorText.setTextColor(this.mTextColor);
            this.mErrorText.setText(C1784R$string.biometric_dialog_tap_confirm);
            this.mErrorText.setContentDescription(getResources().getString(C1784R$string.biometric_dialog_tap_confirm));
            this.mErrorText.setVisibility(0);
            announceAccessibilityEvent();
            this.mPositiveButton.setVisibility(0);
            this.mPositiveButton.setEnabled(true);
        } else if (i == 4) {
            this.mPositiveButton.setVisibility(8);
            this.mNegativeButton.setVisibility(8);
            this.mErrorText.setVisibility(4);
            announceAccessibilityEvent();
        }
        if (i == 3 || i == 4) {
            this.mNegativeButton.setText(C1784R$string.cancel);
            this.mNegativeButton.setContentDescription(getResources().getString(C1784R$string.cancel));
        } else {
            this.mNegativeButton.setText(this.mBundle.getCharSequence("negative_text"));
        }
        updateIcon(this.mState, i);
        this.mState = i;
    }

    public void onDialogAnimatedIn() {
        this.mCompletedAnimatingIn = true;
        if (this.mPendingDismissDialog) {
            Log.d("BiometricDialogView", "onDialogAnimatedIn(): mPendingDismissDialog=true, dismissing now");
            startDismiss();
            this.mPendingDismissDialog = false;
        }
    }

    public void restoreState(Bundle bundle) {
        this.mRestoredState = bundle;
        int i = bundle.getInt("key_try_again_visibility");
        this.mTryAgainButton.setVisibility(i);
        int i2 = bundle.getInt("key_confirm_visibility");
        this.mPositiveButton.setVisibility(i2);
        this.mPositiveButton.setEnabled(bundle.getBoolean("key_confirm_enabled"));
        this.mState = bundle.getInt("key_state");
        this.mErrorText.setText(bundle.getCharSequence("key_error_text_string"));
        this.mErrorText.setContentDescription(bundle.getCharSequence("key_error_text_string"));
        int i3 = bundle.getInt("key_error_text_visibility");
        this.mErrorText.setVisibility(i3);
        if (i3 == 4 || i == 4 || i2 == 4) {
            announceAccessibilityEvent();
        }
        this.mErrorText.setTextColor(bundle.getInt("key_error_text_color"));
        if (bundle.getBoolean("key_error_text_is_temporary")) {
            Handler handler = this.mHandler;
            handler.sendMessageDelayed(handler.obtainMessage(1), 2000);
        }
    }

    public WindowManager.LayoutParams getLayoutParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 2008, 16777216, -3);
        layoutParams.privateFlags |= 524304;
        layoutParams.setTitle("BiometricDialogView");
        layoutParams.token = this.mWindowToken;
        if (this.mHasFod) {
            layoutParams.screenOrientation = 1;
        }
        return layoutParams;
    }

    /* access modifiers changed from: protected */
    public void announceAccessibilityEvent() {
        if (this.mAccessibilityManager.isEnabled()) {
            AccessibilityEvent obtain = AccessibilityEvent.obtain();
            obtain.setEventType(2048);
            obtain.setContentChangeTypes(1);
            this.mDialog.sendAccessibilityEventUnchecked(obtain);
            LinearLayout linearLayout = this.mDialog;
            linearLayout.notifySubtreeAccessibilityStateChanged(linearLayout, linearLayout, 1);
        }
    }
}
