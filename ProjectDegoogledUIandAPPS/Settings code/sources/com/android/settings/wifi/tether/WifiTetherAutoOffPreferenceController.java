package com.android.settings.wifi.tether;

import android.content.Context;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;
import com.havoc.support.preferences.SwitchPreference;

public class WifiTetherAutoOffPreferenceController extends BasePreferenceController implements Preference.OnPreferenceChangeListener {
    public int getAvailabilityStatus() {
        return 0;
    }

    public WifiTetherAutoOffPreferenceController(Context context, String str) {
        super(context, str);
    }

    public void updateState(Preference preference) {
        boolean z = true;
        if (Settings.Global.getInt(this.mContext.getContentResolver(), "soft_ap_timeout_enabled", 1) == 0) {
            z = false;
        }
        ((SwitchPreference) preference).setChecked(z);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        Settings.Global.putInt(this.mContext.getContentResolver(), "soft_ap_timeout_enabled", ((Boolean) obj).booleanValue() ? 1 : 0);
        return true;
    }
}
