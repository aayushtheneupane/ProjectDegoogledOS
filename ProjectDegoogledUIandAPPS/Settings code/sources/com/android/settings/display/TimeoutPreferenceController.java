package com.android.settings.display;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;

public class TimeoutPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    private final String mScreenTimeoutKey;

    public boolean isAvailable() {
        return true;
    }

    public TimeoutPreferenceController(Context context, String str) {
        super(context);
        this.mScreenTimeoutKey = str;
    }

    public String getPreferenceKey() {
        return this.mScreenTimeoutKey;
    }

    public void updateState(Preference preference) {
        TimeoutListPreference timeoutListPreference = (TimeoutListPreference) preference;
        timeoutListPreference.setValue(String.valueOf(Settings.System.getLong(this.mContext.getContentResolver(), "screen_off_timeout", 30000)));
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) this.mContext.getSystemService("device_policy");
        if (devicePolicyManager != null) {
            timeoutListPreference.removeUnusableTimeouts(devicePolicyManager.getMaximumTimeToLock((ComponentName) null, UserHandle.myUserId()), RestrictedLockUtilsInternal.checkIfMaximumTimeToLockIsSet(this.mContext));
        }
        updateTimeoutPreferenceDescription(timeoutListPreference, Long.parseLong(timeoutListPreference.getValue()));
        RestrictedLockUtils.EnforcedAdmin checkIfRestrictionEnforced = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(this.mContext, "no_config_screen_timeout", UserHandle.myUserId());
        if (checkIfRestrictionEnforced != null) {
            timeoutListPreference.removeUnusableTimeouts(0, checkIfRestrictionEnforced);
        }
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        try {
            int parseInt = Integer.parseInt((String) obj);
            Settings.System.putInt(this.mContext.getContentResolver(), "screen_off_timeout", parseInt);
            updateTimeoutPreferenceDescription((TimeoutListPreference) preference, (long) parseInt);
            return true;
        } catch (NumberFormatException e) {
            Log.e("TimeoutPrefContr", "could not persist screen timeout setting", e);
            return true;
        }
    }

    public static CharSequence getTimeoutDescription(long j, CharSequence[] charSequenceArr, CharSequence[] charSequenceArr2) {
        if (j >= 0 && charSequenceArr != null && charSequenceArr2 != null && charSequenceArr2.length == charSequenceArr.length) {
            for (int i = 0; i < charSequenceArr2.length; i++) {
                if (j == Long.parseLong(charSequenceArr2[i].toString())) {
                    return charSequenceArr[i];
                }
            }
        }
        return null;
    }

    private void updateTimeoutPreferenceDescription(TimeoutListPreference timeoutListPreference, long j) {
        String str;
        CharSequence[] entries = timeoutListPreference.getEntries();
        CharSequence[] entryValues = timeoutListPreference.getEntryValues();
        if (timeoutListPreference.isDisabledByAdmin()) {
            str = this.mContext.getString(C1715R.string.disabled_by_policy_title);
        } else {
            CharSequence timeoutDescription = getTimeoutDescription(j, entries, entryValues);
            if (timeoutDescription == null) {
                str = "";
            } else {
                str = this.mContext.getString(C1715R.string.screen_timeout_summary, new Object[]{timeoutDescription});
            }
        }
        timeoutListPreference.setSummary(str);
    }
}
