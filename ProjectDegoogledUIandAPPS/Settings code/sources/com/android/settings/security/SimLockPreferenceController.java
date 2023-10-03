package com.android.settings.security;

import android.content.Context;
import android.os.UserManager;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import java.util.List;

public class SimLockPreferenceController extends BasePreferenceController {
    private static final String KEY_SIM_LOCK = "sim_lock_settings";
    private final CarrierConfigManager mCarrierConfigManager = ((CarrierConfigManager) this.mContext.getSystemService("carrier_config"));
    private final SubscriptionManager mSubscriptionManager;
    private final TelephonyManager mTelephonyManager;
    private final UserManager mUserManager;

    public SimLockPreferenceController(Context context) {
        super(context, KEY_SIM_LOCK);
        this.mUserManager = (UserManager) context.getSystemService("user");
        this.mSubscriptionManager = (SubscriptionManager) context.getSystemService("telephony_subscription_service");
        this.mTelephonyManager = (TelephonyManager) context.getSystemService("phone");
    }

    public int getAvailabilityStatus() {
        return (!this.mUserManager.isAdminUser() || !isSimIccReady() || this.mCarrierConfigManager.getConfig().getBoolean("hide_sim_lock_settings_bool")) ? 4 : 0;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Preference findPreference = preferenceScreen.findPreference(getPreferenceKey());
        if (findPreference != null) {
            findPreference.setEnabled(isSimReady());
        }
    }

    private boolean isSimReady() {
        List<SubscriptionInfo> activeSubscriptionInfoList = this.mSubscriptionManager.getActiveSubscriptionInfoList(true);
        if (activeSubscriptionInfoList == null) {
            return false;
        }
        for (SubscriptionInfo simSlotIndex : activeSubscriptionInfoList) {
            int simState = this.mTelephonyManager.getSimState(simSlotIndex.getSimSlotIndex());
            if (simState != 1 && simState != 0) {
                return true;
            }
        }
        return false;
    }

    private boolean isSimIccReady() {
        List<SubscriptionInfo> activeSubscriptionInfoList = this.mSubscriptionManager.getActiveSubscriptionInfoList(true);
        if (activeSubscriptionInfoList == null) {
            return false;
        }
        for (SubscriptionInfo simSlotIndex : activeSubscriptionInfoList) {
            if (this.mTelephonyManager.hasIccCard(simSlotIndex.getSimSlotIndex())) {
                return true;
            }
        }
        return false;
    }
}
