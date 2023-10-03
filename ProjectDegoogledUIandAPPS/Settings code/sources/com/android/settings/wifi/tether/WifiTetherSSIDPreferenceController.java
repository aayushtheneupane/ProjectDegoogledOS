package com.android.settings.wifi.tether;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.util.Log;
import android.view.View;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.widget.ValidatedEditTextPreference;
import com.android.settings.wifi.dpp.WifiDppUtils;
import com.android.settings.wifi.tether.WifiTetherBasePreferenceController;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;

public class WifiTetherSSIDPreferenceController extends WifiTetherBasePreferenceController implements ValidatedEditTextPreference.Validator {
    static final String DEFAULT_SSID = "AndroidAP";
    private final MetricsFeatureProvider mMetricsFeatureProvider;
    private String mSSID;
    private WifiDeviceNameTextValidator mWifiDeviceNameTextValidator = new WifiDeviceNameTextValidator();

    public String getPreferenceKey() {
        return "wifi_tether_network_name";
    }

    public WifiTetherSSIDPreferenceController(Context context, WifiTetherBasePreferenceController.OnTetherConfigUpdateListener onTetherConfigUpdateListener) {
        super(context, onTetherConfigUpdateListener);
        this.mMetricsFeatureProvider = FeatureFactory.getFactory(context).getMetricsFeatureProvider();
    }

    public void updateDisplay() {
        WifiConfiguration wifiApConfiguration = this.mWifiManager.getWifiApConfiguration();
        if (wifiApConfiguration != null) {
            this.mSSID = wifiApConfiguration.SSID;
        } else {
            this.mSSID = DEFAULT_SSID;
        }
        ((ValidatedEditTextPreference) this.mPreference).setValidator(this);
        if (!this.mWifiManager.isWifiApEnabled() || wifiApConfiguration == null) {
            ((WifiTetherSsidPreference) this.mPreference).setButtonVisible(false);
        } else {
            Intent hotspotConfiguratorIntentOrNull = WifiDppUtils.getHotspotConfiguratorIntentOrNull(this.mContext, this.mWifiManager, wifiApConfiguration);
            if (hotspotConfiguratorIntentOrNull == null) {
                Log.e("WifiTetherSsidPref", "Invalid security to share hotspot");
                ((WifiTetherSsidPreference) this.mPreference).setButtonVisible(false);
            } else {
                ((WifiTetherSsidPreference) this.mPreference).setButtonOnClickListener(new View.OnClickListener(hotspotConfiguratorIntentOrNull) {
                    private final /* synthetic */ Intent f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(View view) {
                        WifiTetherSSIDPreferenceController.this.lambda$updateDisplay$0$WifiTetherSSIDPreferenceController(this.f$1, view);
                    }
                });
                ((WifiTetherSsidPreference) this.mPreference).setButtonVisible(true);
            }
        }
        updateSsidDisplay((EditTextPreference) this.mPreference);
    }

    public /* synthetic */ void lambda$updateDisplay$0$WifiTetherSSIDPreferenceController(Intent intent, View view) {
        shareHotspotNetwork(intent);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        this.mSSID = (String) obj;
        updateSsidDisplay((EditTextPreference) preference);
        this.mListener.onTetherConfigUpdated();
        return true;
    }

    public boolean isTextValid(String str) {
        return this.mWifiDeviceNameTextValidator.isTextValid(str);
    }

    public String getSSID() {
        return this.mSSID;
    }

    private void updateSsidDisplay(EditTextPreference editTextPreference) {
        editTextPreference.setText(this.mSSID);
        editTextPreference.setSummary((CharSequence) this.mSSID);
    }

    private void shareHotspotNetwork(Intent intent) {
        WifiDppUtils.showLockScreen(this.mContext, new Runnable(intent) {
            private final /* synthetic */ Intent f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                WifiTetherSSIDPreferenceController.this.lambda$shareHotspotNetwork$1$WifiTetherSSIDPreferenceController(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$shareHotspotNetwork$1$WifiTetherSSIDPreferenceController(Intent intent) {
        this.mMetricsFeatureProvider.action(0, 1712, 1595, (String) null, Integer.MIN_VALUE);
        this.mContext.startActivity(intent);
    }

    /* access modifiers changed from: package-private */
    public boolean isQrCodeButtonAvailable() {
        return ((WifiTetherSsidPreference) this.mPreference).isQrCodeButtonAvailable();
    }
}
