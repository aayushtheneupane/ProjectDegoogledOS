package com.android.settings.password;

import android.app.admin.DevicePolicyManager;
import android.app.trust.TrustManager;
import android.content.ComponentName;
import android.content.Intent;
import android.hardware.biometrics.BiometricManager;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.android.internal.widget.LockPatternUtils;
import com.android.settings.Utils;
import com.havoc.config.center.C1715R;
import java.util.concurrent.Executor;

public class ConfirmDeviceCredentialActivity extends FragmentActivity {
    public static final String TAG = "ConfirmDeviceCredentialActivity";
    private BiometricPrompt.AuthenticationCallback mAuthenticationCallback = new BiometricPrompt.AuthenticationCallback() {
        public void onAuthenticationError(int i, CharSequence charSequence) {
            if (ConfirmDeviceCredentialActivity.this.mGoingToBackground) {
                return;
            }
            if (i == 10 || i == 5) {
                if (ConfirmDeviceCredentialActivity.this.mIsFallback) {
                    ConfirmDeviceCredentialActivity.this.mBiometricManager.onConfirmDeviceCredentialError(i, ConfirmDeviceCredentialActivity.this.getStringForError(i));
                }
                ConfirmDeviceCredentialActivity.this.finish();
                return;
            }
            ConfirmDeviceCredentialActivity.this.showConfirmCredentials();
        }

        public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult authenticationResult) {
            ConfirmDeviceCredentialActivity.this.mTrustManager.setDeviceLockedForUser(ConfirmDeviceCredentialActivity.this.mUserId, false);
            ConfirmDeviceCredentialUtils.reportSuccessfulAttempt(ConfirmDeviceCredentialActivity.this.mLockPatternUtils, ConfirmDeviceCredentialActivity.this.mUserManager, ConfirmDeviceCredentialActivity.this.mUserId);
            ConfirmDeviceCredentialUtils.checkForPendingIntent(ConfirmDeviceCredentialActivity.this);
            if (ConfirmDeviceCredentialActivity.this.mIsFallback) {
                ConfirmDeviceCredentialActivity.this.mBiometricManager.onConfirmDeviceCredentialSuccess();
            }
            ConfirmDeviceCredentialActivity.this.setResult(-1);
            ConfirmDeviceCredentialActivity.this.finish();
        }
    };
    private BiometricFragment mBiometricFragment;
    /* access modifiers changed from: private */
    public BiometricManager mBiometricManager;
    private boolean mCCLaunched;
    private ChooseLockSettingsHelper mChooseLockSettingsHelper;
    private int mCredentialMode;
    private String mDetails;
    private DevicePolicyManager mDevicePolicyManager;
    private Executor mExecutor = new Executor() {
        public final void execute(Runnable runnable) {
            ConfirmDeviceCredentialActivity.this.lambda$new$0$ConfirmDeviceCredentialActivity(runnable);
        }
    };
    /* access modifiers changed from: private */
    public boolean mGoingToBackground;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public boolean mIsFallback;
    /* access modifiers changed from: private */
    public LockPatternUtils mLockPatternUtils;
    private String mTitle;
    /* access modifiers changed from: private */
    public TrustManager mTrustManager;
    /* access modifiers changed from: private */
    public int mUserId;
    /* access modifiers changed from: private */
    public UserManager mUserManager;

    public static class InternalActivity extends ConfirmDeviceCredentialActivity {
    }

    public static Intent createIntent(CharSequence charSequence, CharSequence charSequence2) {
        Intent intent = new Intent();
        intent.setClassName("com.android.settings", ConfirmDeviceCredentialActivity.class.getName());
        intent.putExtra("android.app.extra.TITLE", charSequence);
        intent.putExtra("android.app.extra.DESCRIPTION", charSequence2);
        return intent;
    }

    public /* synthetic */ void lambda$new$0$ConfirmDeviceCredentialActivity(Runnable runnable) {
        this.mHandler.post(runnable);
    }

    /* access modifiers changed from: private */
    public String getStringForError(int i) {
        if (i == 5) {
            return getString(17039642);
        }
        if (i != 10) {
            return null;
        }
        return getString(17039645);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mBiometricManager = (BiometricManager) getSystemService(BiometricManager.class);
        this.mDevicePolicyManager = (DevicePolicyManager) getSystemService(DevicePolicyManager.class);
        this.mUserManager = UserManager.get(this);
        this.mTrustManager = (TrustManager) getSystemService(TrustManager.class);
        this.mLockPatternUtils = new LockPatternUtils(this);
        Intent intent = getIntent();
        this.mTitle = intent.getStringExtra("android.app.extra.TITLE");
        this.mDetails = intent.getStringExtra("android.app.extra.DESCRIPTION");
        String stringExtra = intent.getStringExtra("android.app.extra.ALTERNATE_BUTTON_LABEL");
        boolean equals = "android.app.action.CONFIRM_FRP_CREDENTIAL".equals(intent.getAction());
        this.mUserId = UserHandle.myUserId();
        if (isInternalActivity()) {
            try {
                this.mUserId = Utils.getUserIdFromBundle(this, intent.getExtras());
            } catch (SecurityException e) {
                Log.e(TAG, "Invalid intent extra", e);
            }
        }
        int credentialOwnerProfile = this.mUserManager.getCredentialOwnerProfile(this.mUserId);
        boolean isManagedProfile = UserManager.get(this).isManagedProfile(this.mUserId);
        if (this.mTitle == null && isManagedProfile) {
            this.mTitle = getTitleFromOrganizationName(this.mUserId);
        }
        this.mChooseLockSettingsHelper = new ChooseLockSettingsHelper(this);
        LockPatternUtils lockPatternUtils = new LockPatternUtils(this);
        Bundle bundleExtra = intent.getBundleExtra("android.app.extra.BIOMETRIC_PROMPT_BUNDLE");
        boolean z = true;
        if (bundleExtra != null) {
            this.mIsFallback = true;
            this.mTitle = bundleExtra.getString("title");
            this.mDetails = bundleExtra.getString("subtitle");
        } else {
            bundleExtra = new Bundle();
            bundleExtra.putString("title", this.mTitle);
            bundleExtra.putString("description", this.mDetails);
        }
        boolean z2 = false;
        if (equals) {
            z = this.mChooseLockSettingsHelper.launchFrpConfirmationActivity(0, this.mTitle, this.mDetails, stringExtra);
        } else {
            if (!isManagedProfile || !isInternalActivity() || lockPatternUtils.isSeparateProfileChallengeEnabled(this.mUserId)) {
                this.mCredentialMode = 1;
                if (isBiometricAllowed(credentialOwnerProfile, this.mUserId)) {
                    showBiometricPrompt(bundleExtra);
                } else {
                    showConfirmCredentials();
                }
            } else {
                this.mCredentialMode = 2;
                if (isBiometricAllowed(credentialOwnerProfile, this.mUserId)) {
                    showBiometricPrompt(bundleExtra);
                } else {
                    showConfirmCredentials();
                }
            }
            z = false;
            z2 = true;
        }
        if (z) {
            finish();
        } else if (!z2) {
            Log.d(TAG, "No pattern, password or PIN set.");
            setResult(-1);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        setVisible(true);
    }

    public void onPause() {
        super.onPause();
        if (!isChangingConfigurations()) {
            this.mGoingToBackground = true;
            if (this.mBiometricFragment != null) {
                String str = TAG;
                Log.d(str, "Authenticating: " + this.mBiometricFragment.isAuthenticating());
                if (this.mBiometricFragment.isAuthenticating()) {
                    this.mBiometricFragment.cancel();
                }
            }
            if (this.mIsFallback && !this.mCCLaunched) {
                this.mBiometricManager.onConfirmDeviceCredentialError(5, getString(17039645));
            }
            finish();
            return;
        }
        this.mGoingToBackground = false;
    }

    private boolean isStrongAuthRequired(int i) {
        return !this.mLockPatternUtils.isBiometricAllowedForUser(i) || !this.mUserManager.isUserUnlocked(this.mUserId);
    }

    private boolean isBiometricDisabledByAdmin(int i) {
        return (this.mDevicePolicyManager.getKeyguardDisabledFeatures((ComponentName) null, i) & 416) != 0;
    }

    private boolean isBiometricAllowed(int i, int i2) {
        return !isStrongAuthRequired(i) && !isBiometricDisabledByAdmin(i) && !this.mLockPatternUtils.hasPendingEscrowToken(i2);
    }

    private void showBiometricPrompt(Bundle bundle) {
        boolean z;
        this.mBiometricManager.setActiveUser(this.mUserId);
        this.mBiometricFragment = (BiometricFragment) getSupportFragmentManager().findFragmentByTag("fragment");
        if (this.mBiometricFragment == null) {
            this.mBiometricFragment = BiometricFragment.newInstance(bundle);
            z = true;
        } else {
            z = false;
        }
        this.mBiometricFragment.setCallbacks(this.mExecutor, this.mAuthenticationCallback);
        this.mBiometricFragment.setUser(this.mUserId);
        if (z) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.add((Fragment) this.mBiometricFragment, "fragment");
            beginTransaction.commit();
        }
    }

    /* access modifiers changed from: private */
    public void showConfirmCredentials() {
        boolean z;
        this.mCCLaunched = true;
        int i = this.mCredentialMode;
        if (i == 2) {
            z = this.mChooseLockSettingsHelper.launchConfirmationActivityWithExternalAndChallenge(0, (CharSequence) null, this.mTitle, this.mDetails, true, 0, this.mUserId);
        } else {
            z = i == 1 ? this.mChooseLockSettingsHelper.launchConfirmationActivity(0, (CharSequence) null, (CharSequence) this.mTitle, (CharSequence) this.mDetails, false, true, this.mUserId) : false;
        }
        if (!z) {
            Log.d(TAG, "No pin/pattern/pass set");
            setResult(-1);
        }
        finish();
    }

    public void finish() {
        super.finish();
        overridePendingTransition(C1715R.anim.confirm_credential_biometric_transition_enter, 0);
    }

    private boolean isInternalActivity() {
        return this instanceof InternalActivity;
    }

    private String getTitleFromOrganizationName(int i) {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) getSystemService("device_policy");
        CharSequence organizationNameForUser = devicePolicyManager != null ? devicePolicyManager.getOrganizationNameForUser(i) : null;
        if (organizationNameForUser != null) {
            return organizationNameForUser.toString();
        }
        return null;
    }
}
