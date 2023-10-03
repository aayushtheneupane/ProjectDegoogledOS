package com.android.settings.display;

import android.content.Context;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SystemSettingListPreference;

public class TimeoutLockscreenPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    private final String mLockScreenTimeoutKey;

    public boolean isAvailable() {
        return true;
    }

    public TimeoutLockscreenPreferenceController(Context context, String str) {
        super(context);
        this.mLockScreenTimeoutKey = str;
    }

    public String getPreferenceKey() {
        return this.mLockScreenTimeoutKey;
    }

    public void updateState(Preference preference) {
        SystemSettingListPreference systemSettingListPreference = (SystemSettingListPreference) preference;
        systemSettingListPreference.setValue(String.valueOf(Settings.System.getLong(this.mContext.getContentResolver(), "lockscreen_timeout", 15000)));
        updateTimeoutPreferenceDescription(systemSettingListPreference, Long.parseLong(systemSettingListPreference.getValue()));
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        try {
            int parseInt = Integer.parseInt((String) obj);
            Settings.System.putInt(this.mContext.getContentResolver(), "lockscreen_timeout", parseInt);
            updateTimeoutPreferenceDescription((SystemSettingListPreference) preference, (long) parseInt);
            return true;
        } catch (NumberFormatException unused) {
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

    private void updateTimeoutPreferenceDescription(SystemSettingListPreference systemSettingListPreference, long j) {
        String str;
        CharSequence timeoutDescription = getTimeoutDescription(j, systemSettingListPreference.getEntries(), systemSettingListPreference.getEntryValues());
        if (timeoutDescription == null) {
            str = "";
        } else {
            str = this.mContext.getString(C1715R.string.screen_timeout_summary, new Object[]{timeoutDescription});
        }
        systemSettingListPreference.setSummary(str);
    }
}
