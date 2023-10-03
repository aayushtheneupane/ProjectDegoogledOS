package com.android.settings.password;

import android.app.Dialog;
import android.app.admin.DevicePolicyManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.biometrics.BiometricManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.android.internal.widget.LockPatternUtils;
import com.android.settings.Utils;
import com.android.settings.core.InstrumentedFragment;
import com.android.settings.password.ConfirmLockPassword;
import com.android.settings.password.ConfirmLockPattern;
import com.havoc.config.center.C1715R;

public abstract class ConfirmDeviceCredentialBaseFragment extends InstrumentedFragment {
    protected BiometricManager mBiometricManager;
    protected Button mCancelButton;
    protected DevicePolicyManager mDevicePolicyManager;
    protected int mEffectiveUserId;
    protected TextView mErrorTextView;
    protected boolean mFrp;
    private CharSequence mFrpAlternateButtonText;
    protected final Handler mHandler = new Handler();
    protected LockPatternUtils mLockPatternUtils;
    private final Runnable mResetErrorRunnable = new Runnable() {
        public void run() {
            ConfirmDeviceCredentialBaseFragment.this.mErrorTextView.setText("");
        }
    };
    protected boolean mReturnCredentials = false;
    protected int mUserId;
    protected UserManager mUserManager;

    /* access modifiers changed from: protected */
    public abstract int getLastTryErrorMessage(int i);

    /* access modifiers changed from: protected */
    public abstract void onShowError();

    public void prepareEnterAnimation() {
    }

    public void startEnterAnimation() {
    }

    private boolean isInternalActivity() {
        return (getActivity() instanceof ConfirmLockPassword.InternalActivity) || (getActivity() instanceof ConfirmLockPattern.InternalActivity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFrpAlternateButtonText = getActivity().getIntent().getCharSequenceExtra("android.app.extra.ALTERNATE_BUTTON_LABEL");
        boolean z = false;
        this.mReturnCredentials = getActivity().getIntent().getBooleanExtra("return_credentials", false);
        this.mUserId = Utils.getUserIdFromBundle(getActivity(), getActivity().getIntent().getExtras(), isInternalActivity());
        if (this.mUserId == -9999) {
            z = true;
        }
        this.mFrp = z;
        this.mUserManager = UserManager.get(getActivity());
        this.mEffectiveUserId = this.mUserManager.getCredentialOwnerProfile(this.mUserId);
        this.mLockPatternUtils = new LockPatternUtils(getActivity());
        this.mDevicePolicyManager = (DevicePolicyManager) getActivity().getSystemService("device_policy");
        this.mBiometricManager = (BiometricManager) getActivity().getSystemService(BiometricManager.class);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mCancelButton = (Button) view.findViewById(C1715R.C1718id.cancelButton);
        int i = 0;
        boolean booleanExtra = getActivity().getIntent().getBooleanExtra("com.android.settings.ConfirmCredentials.showCancelButton", false);
        final boolean z = this.mFrp && !TextUtils.isEmpty(this.mFrpAlternateButtonText);
        Button button = this.mCancelButton;
        if (!booleanExtra && !z) {
            i = 8;
        }
        button.setVisibility(i);
        if (z) {
            this.mCancelButton.setText(this.mFrpAlternateButtonText);
        }
        this.mCancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (z) {
                    ConfirmDeviceCredentialBaseFragment.this.getActivity().setResult(1);
                }
                ConfirmDeviceCredentialBaseFragment.this.getActivity().finish();
            }
        });
        int credentialOwnerUserId = Utils.getCredentialOwnerUserId(getActivity(), Utils.getUserIdFromBundle(getActivity(), getActivity().getIntent().getExtras(), isInternalActivity()));
        if (this.mUserManager.isManagedProfile(credentialOwnerUserId)) {
            setWorkChallengeBackground(view, credentialOwnerUserId);
        }
    }

    /* access modifiers changed from: protected */
    public boolean isStrongAuthRequired() {
        return this.mFrp || !this.mLockPatternUtils.isBiometricAllowedForUser(this.mEffectiveUserId) || !this.mUserManager.isUserUnlocked(this.mUserId);
    }

    public void onResume() {
        super.onResume();
        refreshLockScreen();
    }

    /* access modifiers changed from: protected */
    public void refreshLockScreen() {
        updateErrorMessage(this.mLockPatternUtils.getCurrentFailedPasswordAttempts(this.mEffectiveUserId));
    }

    /* access modifiers changed from: protected */
    public void setAccessibilityTitle(CharSequence charSequence) {
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            CharSequence charSequenceExtra = intent.getCharSequenceExtra("com.android.settings.ConfirmCredentials.title");
            if (charSequence != null) {
                if (charSequenceExtra == null) {
                    getActivity().setTitle(charSequence);
                    return;
                }
                getActivity().setTitle(Utils.createAccessibleSequence(charSequenceExtra, charSequenceExtra + "," + charSequence));
            }
        }
    }

    public void onPause() {
        super.onPause();
    }

    private void setWorkChallengeBackground(View view, int i) {
        View findViewById = getActivity().findViewById(C1715R.C1718id.main_content);
        if (findViewById != null) {
            findViewById.setPadding(0, 0, 0, 0);
        }
        view.setBackground(new ColorDrawable(this.mDevicePolicyManager.getOrganizationColorForUser(i)));
        ImageView imageView = (ImageView) view.findViewById(C1715R.C1718id.background_image);
        if (imageView != null) {
            Drawable drawable = getResources().getDrawable(C1715R.C1717drawable.work_challenge_background);
            drawable.setColorFilter(getResources().getColor(C1715R.C1716color.confirm_device_credential_transparent_black), PorterDuff.Mode.DARKEN);
            imageView.setImageDrawable(drawable);
            Point point = new Point();
            getActivity().getWindowManager().getDefaultDisplay().getSize(point);
            imageView.setLayoutParams(new FrameLayout.LayoutParams(-1, point.y));
        }
    }

    /* access modifiers changed from: protected */
    public void reportFailedAttempt() {
        updateErrorMessage(this.mLockPatternUtils.getCurrentFailedPasswordAttempts(this.mEffectiveUserId) + 1);
        this.mLockPatternUtils.reportFailedPasswordAttempt(this.mEffectiveUserId);
    }

    /* access modifiers changed from: protected */
    public void updateErrorMessage(int i) {
        int maximumFailedPasswordsForWipe = this.mLockPatternUtils.getMaximumFailedPasswordsForWipe(this.mEffectiveUserId);
        if (maximumFailedPasswordsForWipe > 0 && i > 0) {
            if (this.mErrorTextView != null) {
                showError((CharSequence) getActivity().getString(C1715R.string.lock_failed_attempts_before_wipe, new Object[]{Integer.valueOf(i), Integer.valueOf(maximumFailedPasswordsForWipe)}), 0);
            }
            int i2 = maximumFailedPasswordsForWipe - i;
            if (i2 <= 1) {
                FragmentManager childFragmentManager = getChildFragmentManager();
                int userTypeForWipe = getUserTypeForWipe();
                if (i2 == 1) {
                    LastTryDialog.show(childFragmentManager, getActivity().getString(C1715R.string.lock_last_attempt_before_wipe_warning_title), getLastTryErrorMessage(userTypeForWipe), 17039370, false);
                } else {
                    LastTryDialog.show(childFragmentManager, (String) null, getWipeMessage(userTypeForWipe), C1715R.string.lock_failed_attempts_now_wiping_dialog_dismiss, true);
                }
            }
        }
    }

    private int getUserTypeForWipe() {
        UserInfo userInfo = this.mUserManager.getUserInfo(this.mDevicePolicyManager.getProfileWithMinimumFailedPasswordsForWipe(this.mEffectiveUserId));
        if (userInfo == null || userInfo.isPrimary()) {
            return 1;
        }
        return userInfo.isManagedProfile() ? 2 : 3;
    }

    private int getWipeMessage(int i) {
        if (i == 1) {
            return C1715R.string.lock_failed_attempts_now_wiping_device;
        }
        if (i == 2) {
            return C1715R.string.lock_failed_attempts_now_wiping_profile;
        }
        if (i == 3) {
            return C1715R.string.lock_failed_attempts_now_wiping_user;
        }
        throw new IllegalArgumentException("Unrecognized user type:" + i);
    }

    /* access modifiers changed from: protected */
    public void showError(CharSequence charSequence, long j) {
        this.mErrorTextView.setText(charSequence);
        onShowError();
        this.mHandler.removeCallbacks(this.mResetErrorRunnable);
        if (j != 0) {
            this.mHandler.postDelayed(this.mResetErrorRunnable, j);
        }
    }

    /* access modifiers changed from: protected */
    public void showError(int i, long j) {
        showError(getText(i), j);
    }

    public static class LastTryDialog extends DialogFragment {
        private static final String TAG = "LastTryDialog";

        static boolean show(FragmentManager fragmentManager, String str, int i, int i2, boolean z) {
            LastTryDialog lastTryDialog = (LastTryDialog) fragmentManager.findFragmentByTag(TAG);
            if (lastTryDialog != null && !lastTryDialog.isRemoving()) {
                return false;
            }
            Bundle bundle = new Bundle();
            bundle.putString("title", str);
            bundle.putInt("message", i);
            bundle.putInt("button", i2);
            bundle.putBoolean("dismiss", z);
            LastTryDialog lastTryDialog2 = new LastTryDialog();
            lastTryDialog2.setArguments(bundle);
            lastTryDialog2.show(fragmentManager, TAG);
            fragmentManager.executePendingTransactions();
            return true;
        }

        public Dialog onCreateDialog(Bundle bundle) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle((CharSequence) getArguments().getString("title"));
            builder.setMessage(getArguments().getInt("message"));
            builder.setPositiveButton(getArguments().getInt("button"), (DialogInterface.OnClickListener) null);
            AlertDialog create = builder.create();
            create.setCanceledOnTouchOutside(false);
            return create;
        }

        public void onDismiss(DialogInterface dialogInterface) {
            super.onDismiss(dialogInterface);
            if (getActivity() != null && getArguments().getBoolean("dismiss")) {
                getActivity().finish();
            }
        }
    }
}
