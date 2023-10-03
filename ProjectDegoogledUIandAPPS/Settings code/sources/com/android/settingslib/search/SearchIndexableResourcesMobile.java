package com.android.settingslib.search;

import com.android.settings.DisplaySettings;
import com.android.settings.applications.defaultapps.AutofillPicker;
import com.android.settings.applications.managedomainurls.ManageDomainUrls;
import com.android.settings.connecteddevice.AdvancedConnectedDeviceDashboardFragment;
import com.android.settings.connecteddevice.ConnectedDeviceDashboardFragment;
import com.android.settings.connecteddevice.PreviouslyConnectedDeviceDashboardFragment;
import com.android.settings.connecteddevice.usb.UsbDetailsFragment;
import com.android.settings.development.DevelopmentSettingsDashboardFragment;
import com.android.settings.display.AdaptiveSleepSettings;
import com.android.settings.display.AutoBrightnessSettings;
import com.android.settings.display.DcDimmingSettings;
import com.android.settings.display.NightDisplaySettings;
import com.android.settings.display.ScreenZoomSettings;
import com.android.settings.display.darkmode.DarkModeSettingsFragment;
import com.android.settings.flashlight.FlashlightHandleActivity;
import com.android.settings.fuelgauge.PowerUsageAdvanced;
import com.android.settings.fuelgauge.PowerUsageSummary;
import com.android.settings.fuelgauge.SmartBatterySettings;
import com.android.settings.fuelgauge.batterysaver.BatterySaverSettings;
import com.android.settings.homepage.TopLevelSettings;
import com.android.settings.location.ScanningSettings;
import com.android.settings.network.MobileNetworkListFragment;
import com.android.settings.network.telephony.MobileNetworkSettings;
import com.android.settings.notification.ZenModeRestrictNotificationsSettings;
import com.android.settings.wfd.WifiDisplaySettings;

public class SearchIndexableResourcesMobile extends SearchIndexableResourcesBase {
    public SearchIndexableResourcesMobile() {
        addIndex(DisplaySettings.class);
        addIndex(AutofillPicker.class);
        addIndex(ManageDomainUrls.class);
        addIndex(AdvancedConnectedDeviceDashboardFragment.class);
        addIndex(ConnectedDeviceDashboardFragment.class);
        addIndex(PreviouslyConnectedDeviceDashboardFragment.class);
        addIndex(UsbDetailsFragment.class);
        addIndex(DevelopmentSettingsDashboardFragment.class);
        addIndex(AdaptiveSleepSettings.class);
        addIndex(AutoBrightnessSettings.class);
        addIndex(DcDimmingSettings.class);
        addIndex(NightDisplaySettings.class);
        addIndex(ScreenZoomSettings.class);
        addIndex(DarkModeSettingsFragment.class);
        addIndex(FlashlightHandleActivity.class);
        addIndex(PowerUsageAdvanced.class);
        addIndex(PowerUsageSummary.class);
        addIndex(SmartBatterySettings.class);
        addIndex(BatterySaverSettings.class);
        addIndex(TopLevelSettings.class);
        addIndex(ScanningSettings.class);
        addIndex(MobileNetworkListFragment.class);
        addIndex(MobileNetworkSettings.class);
        addIndex(ZenModeRestrictNotificationsSettings.class);
        addIndex(WifiDisplaySettings.class);
    }
}
