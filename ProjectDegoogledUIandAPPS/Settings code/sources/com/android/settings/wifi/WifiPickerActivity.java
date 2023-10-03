package com.android.settings.wifi;

import android.content.Intent;
import androidx.preference.PreferenceFragmentCompat;
import com.android.settings.ButtonBarHandler;
import com.android.settings.SettingsActivity;
import com.android.settings.wifi.p2p.WifiP2pSettings;
import com.android.settings.wifi.savedaccesspoints.SavedAccessPointsWifiSettings;
import com.havoc.config.center.C1715R;

public class WifiPickerActivity extends SettingsActivity implements ButtonBarHandler {
    public Intent getIntent() {
        Intent intent = new Intent(super.getIntent());
        if (!intent.hasExtra(":settings:show_fragment")) {
            intent.putExtra(":settings:show_fragment", getWifiSettingsClass().getName());
            intent.putExtra(":settings:show_fragment_title_resid", C1715R.string.wifi_select_network);
        }
        return intent;
    }

    /* access modifiers changed from: protected */
    public boolean isValidFragment(String str) {
        return WifiSettings.class.getName().equals(str) || WifiP2pSettings.class.getName().equals(str) || SavedAccessPointsWifiSettings.class.getName().equals(str);
    }

    /* access modifiers changed from: package-private */
    public Class<? extends PreferenceFragmentCompat> getWifiSettingsClass() {
        return WifiSettings.class;
    }
}
