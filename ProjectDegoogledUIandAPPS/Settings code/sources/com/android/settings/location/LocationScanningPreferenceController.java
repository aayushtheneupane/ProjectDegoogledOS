package com.android.settings.location;

import android.content.Context;
import android.provider.Settings;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class LocationScanningPreferenceController extends BasePreferenceController {
    static final String KEY_LOCATION_SCANNING = "location_scanning";
    private final Context mContext;

    public LocationScanningPreferenceController(Context context) {
        super(context, KEY_LOCATION_SCANNING);
        this.mContext = context;
    }

    public CharSequence getSummary() {
        boolean z = false;
        boolean z2 = Settings.Global.getInt(this.mContext.getContentResolver(), "wifi_scan_always_enabled", 0) == 1;
        if (Settings.Global.getInt(this.mContext.getContentResolver(), "ble_scan_always_enabled", 0) == 1) {
            z = true;
        }
        return this.mContext.getString((!z2 || !z) ? (!z2 || z) ? (z2 || !z) ? C1715R.string.scanning_status_text_wifi_off_ble_off : C1715R.string.scanning_status_text_wifi_off_ble_on : C1715R.string.scanning_status_text_wifi_on_ble_off : C1715R.string.scanning_status_text_wifi_on_ble_on);
    }

    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(C1715R.bool.config_show_location_scanning) ? 0 : 3;
    }
}
