package com.android.settings.development;

import android.content.Context;
import android.net.NetworkUtils;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.UserManager;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.TwoStatePreference;
import com.android.settingslib.core.ConfirmationDialogController;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;
import com.havoc.config.center.C1715R;

public class AdbNetworkPreferenceController extends DeveloperOptionsPreferenceController implements ConfirmationDialogController {
    private final DevelopmentSettingsDashboardFragment mFragment;

    public String getPreferenceKey() {
        return "enable_adb_network";
    }

    public AdbNetworkPreferenceController(Context context, DevelopmentSettingsDashboardFragment developmentSettingsDashboardFragment) {
        super(context);
        this.mFragment = developmentSettingsDashboardFragment;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        if (isAvailable()) {
            this.mPreference = (TwoStatePreference) preferenceScreen.findPreference("enable_adb_network");
        }
    }

    public boolean isAvailable() {
        UserManager userManager = (UserManager) this.mContext.getSystemService(UserManager.class);
        return userManager != null && (userManager.isAdminUser() || userManager.isDemoUser());
    }

    private boolean isAdbEnabled() {
        if (Settings.Global.getInt(this.mContext.getContentResolver(), "adb_port", 0) != 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void writeAdbSetting(boolean z) {
        Settings.Global.putInt(this.mContext.getContentResolver(), "adb_port", z ? 5555 : 0);
        updateState(this.mPreference);
    }

    public void updateState(Preference preference) {
        TwoStatePreference twoStatePreference = (TwoStatePreference) preference;
        twoStatePreference.setChecked(isAdbEnabled());
        if (isAdbEnabled()) {
            WifiInfo connectionInfo = ((WifiManager) this.mContext.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo != null) {
                twoStatePreference.setSummary((CharSequence) NetworkUtils.intToInetAddress(connectionInfo.getIpAddress()).getHostAddress());
                return;
            }
            return;
        }
        twoStatePreference.setSummary((CharSequence) this.mContext.getResources().getString(C1715R.string.adb_over_network_summary));
    }

    public void onAdbDialogConfirmed() {
        writeAdbSetting(true);
    }

    public void onAdbDialogDismissed() {
        updateState(this.mPreference);
    }

    public void showConfirmationDialog(Preference preference) {
        EnableAdbNetworkWarningDialog.show(this.mFragment);
    }

    /* access modifiers changed from: protected */
    public void onDeveloperOptionsSwitchDisabled() {
        super.onDeveloperOptionsSwitchDisabled();
        writeAdbSetting(false);
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals("enable_adb_network", preference.getKey())) {
            return false;
        }
        if (!isAdbEnabled()) {
            showConfirmationDialog(preference);
            return true;
        }
        writeAdbSetting(false);
        return true;
    }
}
