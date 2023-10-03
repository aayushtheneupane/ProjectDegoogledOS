package com.android.settings.security;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.internal.widget.LockPatternUtils;
import com.android.settings.Utils;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.password.ChooseLockGeneric;
import com.android.settings.password.ChooseLockSettingsHelper;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.RestrictedSwitchPreference;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;

public class LockUnificationPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    private static final int MY_USER_ID = UserHandle.myUserId();
    private byte[] mCurrentDevicePassword;
    private byte[] mCurrentProfilePassword;
    private final DevicePolicyManager mDpm;
    private final SecuritySettings mHost;
    private boolean mKeepDeviceLock;
    private final LockPatternUtils mLockPatternUtils;
    private final int mProfileUserId = Utils.getManagedProfileId(this.mUm, MY_USER_ID);
    private final UserManager mUm;
    private RestrictedSwitchPreference mUnifyProfile;

    public String getPreferenceKey() {
        return "unification";
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mUnifyProfile = (RestrictedSwitchPreference) preferenceScreen.findPreference("unification");
    }

    public LockUnificationPreferenceController(Context context, SecuritySettings securitySettings) {
        super(context);
        this.mHost = securitySettings;
        this.mUm = (UserManager) context.getSystemService(UserManager.class);
        this.mDpm = (DevicePolicyManager) context.getSystemService(DevicePolicyManager.class);
        this.mLockPatternUtils = FeatureFactory.getFactory(context).getSecurityFeatureProvider().getLockPatternUtils(context);
    }

    public boolean isAvailable() {
        int i = this.mProfileUserId;
        return i != -10000 && this.mLockPatternUtils.isSeparateProfileChallengeAllowed(i);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        boolean z = false;
        if (Utils.startQuietModeDialogIfNecessary(this.mContext, this.mUm, this.mProfileUserId)) {
            return false;
        }
        if (((Boolean) obj).booleanValue()) {
            if (this.mLockPatternUtils.getKeyguardStoredPasswordQuality(this.mProfileUserId) < 65536 || !this.mDpm.isProfileActivePasswordSufficientForParent(this.mProfileUserId)) {
                z = true;
            }
            this.mKeepDeviceLock = z;
            UnificationConfirmationDialog.newInstance(!this.mKeepDeviceLock).show(this.mHost);
        } else {
            if (!new ChooseLockSettingsHelper(this.mHost.getActivity(), this.mHost).launchConfirmationActivity(130, this.mContext.getString(C1715R.string.unlock_set_unlock_launch_picker_title), true, MY_USER_ID)) {
                ununifyLocks();
            }
        }
        return true;
    }

    public void updateState(Preference preference) {
        if (this.mUnifyProfile != null) {
            boolean isSeparateProfileChallengeEnabled = this.mLockPatternUtils.isSeparateProfileChallengeEnabled(this.mProfileUserId);
            this.mUnifyProfile.setChecked(!isSeparateProfileChallengeEnabled);
            if (isSeparateProfileChallengeEnabled) {
                this.mUnifyProfile.setDisabledByAdmin(RestrictedLockUtilsInternal.checkIfRestrictionEnforced(this.mContext, "no_unified_password", this.mProfileUserId));
            }
        }
    }

    public boolean handleActivityResult(int i, int i2, Intent intent) {
        if (i == 130 && i2 == -1) {
            ununifyLocks();
            return true;
        } else if (i == 128 && i2 == -1) {
            this.mCurrentDevicePassword = intent.getByteArrayExtra("password");
            launchConfirmProfileLock();
            return true;
        } else if (i != 129 || i2 != -1) {
            return false;
        } else {
            this.mCurrentProfilePassword = intent.getByteArrayExtra("password");
            unifyLocks();
            return true;
        }
    }

    private void ununifyLocks() {
        Bundle bundle = new Bundle();
        bundle.putInt("android.intent.extra.USER_ID", this.mProfileUserId);
        new SubSettingLauncher(this.mContext).setDestination(ChooseLockGeneric.ChooseLockGenericFragment.class.getName()).setTitleRes(C1715R.string.lock_settings_picker_title_profile).setSourceMetricsCategory(this.mHost.getMetricsCategory()).setArguments(bundle).launch();
    }

    private void launchConfirmDeviceAndProfileLock() {
        if (!new ChooseLockSettingsHelper(this.mHost.getActivity(), this.mHost).launchConfirmationActivity(128, this.mContext.getString(C1715R.string.unlock_set_unlock_launch_picker_title), true, MY_USER_ID)) {
            launchConfirmProfileLock();
        }
    }

    private void launchConfirmProfileLock() {
        if (!new ChooseLockSettingsHelper(this.mHost.getActivity(), this.mHost).launchConfirmationActivity(129, this.mContext.getString(C1715R.string.unlock_set_unlock_launch_picker_title_profile), true, this.mProfileUserId)) {
            unifyLocks();
        }
    }

    /* access modifiers changed from: package-private */
    public void startUnification() {
        if (this.mKeepDeviceLock) {
            launchConfirmProfileLock();
        } else {
            launchConfirmDeviceAndProfileLock();
        }
    }

    private void unifyLocks() {
        if (this.mKeepDeviceLock) {
            unifyKeepingDeviceLock();
            promptForNewDeviceLock();
        } else {
            unifyKeepingWorkLock();
        }
        this.mCurrentDevicePassword = null;
        this.mCurrentProfilePassword = null;
    }

    private void unifyKeepingWorkLock() {
        int keyguardStoredPasswordQuality = this.mLockPatternUtils.getKeyguardStoredPasswordQuality(this.mProfileUserId);
        if (keyguardStoredPasswordQuality == 65536) {
            this.mLockPatternUtils.saveLockPattern(LockPatternUtils.byteArrayToPattern(this.mCurrentProfilePassword, this.mLockPatternUtils.getLockPatternSize(MY_USER_ID)), this.mCurrentDevicePassword, MY_USER_ID);
        } else {
            this.mLockPatternUtils.saveLockPassword(this.mCurrentProfilePassword, this.mCurrentDevicePassword, keyguardStoredPasswordQuality, MY_USER_ID);
        }
        this.mLockPatternUtils.setSeparateProfileChallengeEnabled(this.mProfileUserId, false, this.mCurrentProfilePassword);
        this.mLockPatternUtils.setVisiblePatternEnabled(this.mLockPatternUtils.isVisiblePatternEnabled(this.mProfileUserId), MY_USER_ID);
    }

    private void unifyKeepingDeviceLock() {
        this.mLockPatternUtils.setSeparateProfileChallengeEnabled(this.mProfileUserId, false, this.mCurrentProfilePassword);
    }

    private void promptForNewDeviceLock() {
        new SubSettingLauncher(this.mContext).setDestination(ChooseLockGeneric.ChooseLockGenericFragment.class.getName()).setTitleRes(C1715R.string.lock_settings_picker_title).setSourceMetricsCategory(this.mHost.getMetricsCategory()).launch();
    }
}
