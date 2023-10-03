package com.android.settings.connecteddevice;

import android.content.Context;
import android.provider.Settings;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.nfc.NfcPreferenceController;
import com.havoc.config.center.C1715R;

public class AdvancedConnectedDeviceController extends BasePreferenceController {
    private static final String DRIVING_MODE_SETTINGS_ENABLED = "gearhead:driving_mode_settings_enabled";

    public int getAvailabilityStatus() {
        return 0;
    }

    public AdvancedConnectedDeviceController(Context context, String str) {
        super(context, str);
    }

    public CharSequence getSummary() {
        Context context = this.mContext;
        return context.getText(getConnectedDevicesSummaryResourceId(context));
    }

    public static int getConnectedDevicesSummaryResourceId(Context context) {
        return getConnectedDevicesSummaryResourceId(new NfcPreferenceController(context, NfcPreferenceController.KEY_TOGGLE_NFC), isDrivingModeAvailable(context));
    }

    static boolean isDrivingModeAvailable(Context context) {
        return Settings.System.getInt(context.getContentResolver(), DRIVING_MODE_SETTINGS_ENABLED, 0) == 1;
    }

    static int getConnectedDevicesSummaryResourceId(NfcPreferenceController nfcPreferenceController, boolean z) {
        return nfcPreferenceController.isAvailable() ? z ? C1715R.string.connected_devices_dashboard_summary : C1715R.string.connected_devices_dashboard_no_driving_mode_summary : z ? C1715R.string.connected_devices_dashboard_no_nfc_summary : C1715R.string.connected_devices_dashboard_no_driving_mode_no_nfc_summary;
    }
}
