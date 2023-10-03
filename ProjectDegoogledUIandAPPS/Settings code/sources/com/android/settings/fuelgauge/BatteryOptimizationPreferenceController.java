package com.android.settings.fuelgauge;

import android.os.Bundle;
import androidx.preference.Preference;
import com.android.settings.Settings;
import com.android.settings.SettingsActivity;
import com.android.settings.applications.manageapplications.ManageApplications;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.fuelgauge.PowerWhitelistBackend;
import com.havoc.config.center.C1715R;

public class BatteryOptimizationPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    private PowerWhitelistBackend mBackend;
    private DashboardFragment mFragment;
    private String mPackageName;
    private SettingsActivity mSettingsActivity;

    public String getPreferenceKey() {
        return "battery_optimization";
    }

    public boolean isAvailable() {
        return true;
    }

    public BatteryOptimizationPreferenceController(SettingsActivity settingsActivity, DashboardFragment dashboardFragment, String str) {
        super(settingsActivity);
        this.mFragment = dashboardFragment;
        this.mSettingsActivity = settingsActivity;
        this.mPackageName = str;
        this.mBackend = PowerWhitelistBackend.getInstance(this.mSettingsActivity);
    }

    BatteryOptimizationPreferenceController(SettingsActivity settingsActivity, DashboardFragment dashboardFragment, String str, PowerWhitelistBackend powerWhitelistBackend) {
        super(settingsActivity);
        this.mFragment = dashboardFragment;
        this.mSettingsActivity = settingsActivity;
        this.mPackageName = str;
        this.mBackend = powerWhitelistBackend;
    }

    public void updateState(Preference preference) {
        preference.setSummary(this.mBackend.isWhitelisted(this.mPackageName) ? C1715R.string.high_power_on : C1715R.string.high_power_off);
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!"battery_optimization".equals(preference.getKey())) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("classname", Settings.HighPowerApplicationsActivity.class.getName());
        new SubSettingLauncher(this.mSettingsActivity).setDestination(ManageApplications.class.getName()).setArguments(bundle).setTitleRes(C1715R.string.high_power_apps).setSourceMetricsCategory(this.mFragment.getMetricsCategory()).launch();
        return true;
    }
}
