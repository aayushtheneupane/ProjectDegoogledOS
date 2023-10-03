package com.android.settings.development;

import android.content.Context;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;
import com.havoc.support.preferences.SwitchPreference;

public class WifiScanThrottlingPreferenceController extends DeveloperOptionsPreferenceController implements Preference.OnPreferenceChangeListener, PreferenceControllerMixin {
    static final int SETTING_THROTTLING_ENABLE_VALUE_OFF = 0;
    static final int SETTING_THROTTLING_ENABLE_VALUE_ON = 1;

    public String getPreferenceKey() {
        return "wifi_scan_throttling";
    }

    public WifiScanThrottlingPreferenceController(Context context) {
        super(context);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        Settings.Global.putInt(this.mContext.getContentResolver(), "wifi_scan_throttle_enabled", ((Boolean) obj).booleanValue() ? 1 : 0);
        return true;
    }

    public void updateState(Preference preference) {
        boolean z = true;
        int i = Settings.Global.getInt(this.mContext.getContentResolver(), "wifi_scan_throttle_enabled", 1);
        SwitchPreference switchPreference = (SwitchPreference) this.mPreference;
        if (i != 1) {
            z = false;
        }
        switchPreference.setChecked(z);
    }

    /* access modifiers changed from: protected */
    public void onDeveloperOptionsSwitchDisabled() {
        super.onDeveloperOptionsSwitchDisabled();
        Settings.Global.putInt(this.mContext.getContentResolver(), "wifi_scan_throttle_enabled", 1);
        ((SwitchPreference) this.mPreference).setChecked(true);
    }
}
