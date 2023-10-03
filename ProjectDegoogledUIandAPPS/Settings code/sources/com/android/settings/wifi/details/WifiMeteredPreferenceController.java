package com.android.settings.wifi.details;

import android.app.backup.BackupManager;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import androidx.preference.DropDownPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.wifi.WifiDialog;

public class WifiMeteredPreferenceController extends BasePreferenceController implements Preference.OnPreferenceChangeListener, WifiDialog.WifiDialogListener {
    private static final String KEY_WIFI_METERED = "metered";
    private Preference mPreference;
    private WifiConfiguration mWifiConfiguration;
    private WifiManager mWifiManager;

    public int getAvailabilityStatus() {
        return 0;
    }

    public WifiMeteredPreferenceController(Context context, WifiConfiguration wifiConfiguration) {
        super(context, KEY_WIFI_METERED);
        this.mWifiConfiguration = wifiConfiguration;
        this.mWifiManager = (WifiManager) context.getSystemService("wifi");
    }

    public void updateState(Preference preference) {
        DropDownPreference dropDownPreference = (DropDownPreference) preference;
        int meteredOverride = getMeteredOverride();
        dropDownPreference.setValue(Integer.toString(meteredOverride));
        updateSummary(dropDownPreference, meteredOverride);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        WifiConfiguration wifiConfiguration = this.mWifiConfiguration;
        if (wifiConfiguration != null) {
            wifiConfiguration.meteredOverride = Integer.parseInt((String) obj);
        }
        this.mWifiManager.updateNetwork(this.mWifiConfiguration);
        BackupManager.dataChanged("com.android.providers.settings");
        updateSummary((DropDownPreference) preference, getMeteredOverride());
        return true;
    }

    /* access modifiers changed from: package-private */
    public int getMeteredOverride() {
        WifiConfiguration wifiConfiguration = this.mWifiConfiguration;
        if (wifiConfiguration != null) {
            return wifiConfiguration.meteredOverride;
        }
        return 0;
    }

    private void updateSummary(DropDownPreference dropDownPreference, int i) {
        dropDownPreference.setSummary(dropDownPreference.getEntries()[i]);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
    }

    public void onSubmit(WifiDialog wifiDialog) {
        WifiConfiguration config;
        WifiConfiguration wifiConfiguration;
        int i;
        if (wifiDialog.getController() != null && (config = wifiDialog.getController().getConfig()) != null && (wifiConfiguration = this.mWifiConfiguration) != null && (i = config.meteredOverride) != wifiConfiguration.meteredOverride) {
            this.mWifiConfiguration = config;
            onPreferenceChange(this.mPreference, String.valueOf(i));
        }
    }
}
