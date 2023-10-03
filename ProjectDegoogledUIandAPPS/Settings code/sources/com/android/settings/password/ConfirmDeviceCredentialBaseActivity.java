package com.android.settings.password;

import android.app.KeyguardManager;
import android.hardware.biometrics.BiometricManager;
import android.hardware.biometrics.IBiometricConfirmDeviceCredentialCallback;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import com.android.settings.SettingsActivity;
import com.android.settings.SetupWizardUtils;
import com.android.settings.Utils;
import com.android.settings.password.ConfirmLockPassword;
import com.android.settings.password.ConfirmLockPattern;
import com.havoc.config.center.C1715R;

public abstract class ConfirmDeviceCredentialBaseActivity extends SettingsActivity {
    private BiometricManager mBiometricManager;
    private final IBiometricConfirmDeviceCredentialCallback mCancelCallback = new IBiometricConfirmDeviceCredentialCallback.Stub() {
        public void cancel() {
            ConfirmDeviceCredentialBaseActivity.this.finish();
        }
    };
    private ConfirmCredentialTheme mConfirmCredentialTheme;
    private boolean mEnterAnimationPending;
    private boolean mFirstTimeVisible = true;
    private boolean mIsKeyguardLocked = false;
    private boolean mRestoring;

    enum ConfirmCredentialTheme {
        NORMAL,
        DARK,
        WORK
    }

    public boolean isLaunchableInTaskModePinned() {
        return true;
    }

    private boolean isInternalActivity() {
        return (this instanceof ConfirmLockPassword.InternalActivity) || (this instanceof ConfirmLockPattern.InternalActivity);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        boolean z;
        try {
            boolean z2 = false;
            if (UserManager.get(this).isManagedProfile(Utils.getCredentialOwnerUserId(this, Utils.getUserIdFromBundle(this, getIntent().getExtras(), isInternalActivity())))) {
                setTheme(2131952251);
                this.mConfirmCredentialTheme = ConfirmCredentialTheme.WORK;
            } else if (getIntent().getBooleanExtra("com.android.settings.ConfirmCredentials.darkTheme", false)) {
                setTheme(2131952250);
                this.mConfirmCredentialTheme = ConfirmCredentialTheme.DARK;
            } else {
                setTheme(SetupWizardUtils.getTheme(getIntent()));
                this.mConfirmCredentialTheme = ConfirmCredentialTheme.NORMAL;
            }
            super.onCreate(bundle);
            this.mBiometricManager = (BiometricManager) getSystemService(BiometricManager.class);
            this.mBiometricManager.registerCancellationCallback(this.mCancelCallback);
            if (this.mConfirmCredentialTheme == ConfirmCredentialTheme.NORMAL) {
                findViewById(C1715R.C1718id.content_parent).setFitsSystemWindows(false);
            }
            getWindow().addFlags(8192);
            if (bundle == null) {
                z = ((KeyguardManager) getSystemService(KeyguardManager.class)).isKeyguardLocked();
            } else {
                z = bundle.getBoolean("STATE_IS_KEYGUARD_LOCKED", false);
            }
            this.mIsKeyguardLocked = z;
            if (this.mIsKeyguardLocked && getIntent().getBooleanExtra("com.android.settings.ConfirmCredentials.showWhenLocked", false)) {
                getWindow().addFlags(524288);
            }
            setTitle(getIntent().getStringExtra("com.android.settings.ConfirmCredentials.title"));
            if (getActionBar() != null) {
                getActionBar().setDisplayHomeAsUpEnabled(true);
                getActionBar().setHomeButtonEnabled(true);
            }
            if (bundle != null) {
                z2 = true;
            }
            this.mRestoring = z2;
        } catch (SecurityException e) {
            Log.e("ConfirmDeviceCredentialBaseActivity", "Invalid user Id supplied", e);
            finish();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("STATE_IS_KEYGUARD_LOCKED", this.mIsKeyguardLocked);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }

    public void onResume() {
        super.onResume();
        if (!isChangingConfigurations() && !this.mRestoring && this.mConfirmCredentialTheme == ConfirmCredentialTheme.DARK && this.mFirstTimeVisible) {
            this.mFirstTimeVisible = false;
            prepareEnterAnimation();
            this.mEnterAnimationPending = true;
        }
    }

    private ConfirmDeviceCredentialBaseFragment getFragment() {
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(C1715R.C1718id.main_content);
        if (findFragmentById == null || !(findFragmentById instanceof ConfirmDeviceCredentialBaseFragment)) {
            return null;
        }
        return (ConfirmDeviceCredentialBaseFragment) findFragmentById;
    }

    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
        if (this.mEnterAnimationPending) {
            startEnterAnimation();
            this.mEnterAnimationPending = false;
        }
    }

    public void onStop() {
        super.onStop();
        if (!isChangingConfigurations()) {
            this.mBiometricManager.onConfirmDeviceCredentialError(10, getString(17039645));
            if (getIntent().getBooleanExtra("foreground_only", false)) {
                finish();
            }
        }
    }

    public void finish() {
        super.finish();
        if (getIntent().getBooleanExtra("com.android.settings.ConfirmCredentials.useFadeAnimation", false)) {
            overridePendingTransition(0, C1715R.anim.confirm_credential_biometric_transition_exit);
        }
    }

    public void prepareEnterAnimation() {
        getFragment().prepareEnterAnimation();
    }

    public void startEnterAnimation() {
        getFragment().startEnterAnimation();
    }

    public ConfirmCredentialTheme getConfirmCredentialTheme() {
        return this.mConfirmCredentialTheme;
    }
}
