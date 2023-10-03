package com.android.settings.wifi.details;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.wifi.dpp.WifiDppUtils;
import com.android.settingslib.wifi.AccessPoint;

public class AddDevicePreferenceController extends BasePreferenceController {
    private static final String KEY_ADD_DEVICE = "add_device_to_network";
    private static final String TAG = "AddDevicePreferenceController";
    private AccessPoint mAccessPoint;
    private WifiManager mWifiManager;

    public AddDevicePreferenceController(Context context) {
        super(context, KEY_ADD_DEVICE);
        this.mWifiManager = (WifiManager) context.getSystemService("wifi");
    }

    public AddDevicePreferenceController init(AccessPoint accessPoint) {
        this.mAccessPoint = accessPoint;
        return this;
    }

    public int getAvailabilityStatus() {
        return WifiDppUtils.isSupportConfiguratorQrCodeScanner(this.mContext, this.mAccessPoint) ? 0 : 2;
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!KEY_ADD_DEVICE.equals(preference.getKey())) {
            return false;
        }
        WifiDppUtils.showLockScreen(this.mContext, new Runnable() {
            public final void run() {
                AddDevicePreferenceController.this.lambda$handlePreferenceTreeClick$0$AddDevicePreferenceController();
            }
        });
        return true;
    }

    /* access modifiers changed from: private */
    /* renamed from: launchWifiDppConfiguratorQrCodeScanner */
    public void lambda$handlePreferenceTreeClick$0$AddDevicePreferenceController() {
        Intent configuratorQrCodeScannerIntentOrNull = WifiDppUtils.getConfiguratorQrCodeScannerIntentOrNull(this.mContext, this.mWifiManager, this.mAccessPoint);
        if (configuratorQrCodeScannerIntentOrNull == null) {
            Log.e(TAG, "Launch Wi-Fi QR code scanner with a wrong Wi-Fi network!");
        } else {
            this.mContext.startActivity(configuratorQrCodeScannerIntentOrNull);
        }
    }
}
