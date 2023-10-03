package com.android.settingslib.deviceinfo;

import android.os.PersistableBundle;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settingslib.R$string;

public abstract class AbstractImsStatusPreferenceController extends AbstractConnectivityPreferenceController {
    private static final String[] CONNECTIVITY_INTENTS = {"android.bluetooth.adapter.action.STATE_CHANGED", "android.net.conn.CONNECTIVITY_CHANGE", "android.net.wifi.LINK_CONFIGURATION_CHANGED", "android.net.wifi.STATE_CHANGE"};
    static final String KEY_IMS_REGISTRATION_STATE = "ims_reg_state";
    private Preference mImsStatus;

    public String getPreferenceKey() {
        return KEY_IMS_REGISTRATION_STATE;
    }

    public boolean isAvailable() {
        CarrierConfigManager carrierConfigManager = (CarrierConfigManager) this.mContext.getSystemService(CarrierConfigManager.class);
        PersistableBundle configForSubId = carrierConfigManager != null ? carrierConfigManager.getConfigForSubId(SubscriptionManager.getDefaultDataSubscriptionId()) : null;
        return configForSubId != null && configForSubId.getBoolean("show_ims_registration_status_bool");
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mImsStatus = preferenceScreen.findPreference(KEY_IMS_REGISTRATION_STATE);
        updateConnectivity();
    }

    /* access modifiers changed from: protected */
    public String[] getConnectivityIntents() {
        return CONNECTIVITY_INTENTS;
    }

    /* access modifiers changed from: protected */
    public void updateConnectivity() {
        int defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
        if (this.mImsStatus != null) {
            TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService(TelephonyManager.class);
            this.mImsStatus.setSummary((telephonyManager == null || !telephonyManager.isImsRegistered(defaultDataSubscriptionId)) ? R$string.ims_reg_status_not_registered : R$string.ims_reg_status_registered);
        }
    }
}
