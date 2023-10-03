package com.android.settings.security.screenlock;

import android.content.Context;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.internal.widget.LockPatternUtils;
import com.android.settings.Utils;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.security.trustagent.TrustAgentManager;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.support.preferences.SwitchPreference;

public class QuickUnlockPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    private final LockPatternUtils mLockPatternUtils;
    private final TrustAgentManager mTrustAgentManager;
    private final int mUserId;

    public String getPreferenceKey() {
        return "lockscreen_quick_unlock_control";
    }

    public QuickUnlockPreferenceController(Context context, int i, LockPatternUtils lockPatternUtils) {
        super(context);
        this.mUserId = i;
        this.mLockPatternUtils = lockPatternUtils;
        this.mTrustAgentManager = FeatureFactory.getFactory(context).getSecurityFeatureProvider().getTrustAgentManager();
    }

    public boolean isAvailable() {
        if (!this.mLockPatternUtils.isSecure(this.mUserId) || Utils.getPINPasswordLength(this.mLockPatternUtils, this.mUserId) <= 0) {
            return false;
        }
        int keyguardStoredPasswordQuality = this.mLockPatternUtils.getKeyguardStoredPasswordQuality(this.mUserId);
        if (keyguardStoredPasswordQuality == 131072 || keyguardStoredPasswordQuality == 196608 || keyguardStoredPasswordQuality == 262144 || keyguardStoredPasswordQuality == 393216) {
            return true;
        }
        return false;
    }

    public void updateState(Preference preference) {
        boolean z = false;
        SwitchPreference switchPreference = (SwitchPreference) preference;
        if (Settings.System.getInt(this.mContext.getContentResolver(), "lockscreen_quick_unlock_control", 0) != 0) {
            z = true;
        }
        switchPreference.setChecked(z);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        Settings.System.putInt(this.mContext.getContentResolver(), "lockscreen_quick_unlock_control", ((Boolean) obj).booleanValue() ? 1 : 0);
        return true;
    }
}
