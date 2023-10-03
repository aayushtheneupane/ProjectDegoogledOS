package com.android.settings.development;

import android.content.Context;
import android.net.wifi.WifiManager;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;
import com.havoc.support.preferences.SwitchPreference;

public class WifiVerboseLoggingPreferenceController extends DeveloperOptionsPreferenceController implements Preference.OnPreferenceChangeListener, PreferenceControllerMixin {
    static final int SETTING_VALUE_OFF = 0;
    static final int SETTING_VALUE_ON = 1;
    private final WifiManager mWifiManager;

    public String getPreferenceKey() {
        return "wifi_verbose_logging";
    }

    public WifiVerboseLoggingPreferenceController(Context context) {
        super(context);
        this.mWifiManager = (WifiManager) context.getSystemService("wifi");
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        this.mWifiManager.enableVerboseLogging(((Boolean) obj).booleanValue() ? 1 : 0);
        return true;
    }

    public void updateState(Preference preference) {
        ((SwitchPreference) this.mPreference).setChecked(this.mWifiManager.getVerboseLoggingLevel() > 0);
    }

    /* access modifiers changed from: protected */
    public void onDeveloperOptionsSwitchDisabled() {
        super.onDeveloperOptionsSwitchDisabled();
        this.mWifiManager.enableVerboseLogging(0);
        ((SwitchPreference) this.mPreference).setChecked(false);
    }
}
