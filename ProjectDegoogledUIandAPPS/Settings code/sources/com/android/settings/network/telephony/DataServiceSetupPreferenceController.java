package com.android.settings.network.telephony;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.telephony.CarrierConfigManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.preference.Preference;

public class DataServiceSetupPreferenceController extends TelephonyBasePreferenceController {
    private CarrierConfigManager mCarrierConfigManager;
    private String mSetupUrl = Settings.Global.getString(this.mContext.getContentResolver(), "setup_prepaid_data_service_url");
    private TelephonyManager mTelephonyManager;

    public DataServiceSetupPreferenceController(Context context, String str) {
        super(context, str);
        this.mCarrierConfigManager = (CarrierConfigManager) context.getSystemService(CarrierConfigManager.class);
        this.mTelephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
    }

    public int getAvailabilityStatus(int i) {
        boolean z = true;
        if (this.mTelephonyManager.getLteOnCdmaMode() != 1) {
            z = false;
        }
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(i);
        if (i == -1 || configForSubId == null || configForSubId.getBoolean("hide_carrier_network_settings_bool") || !z || TextUtils.isEmpty(this.mSetupUrl)) {
            return 2;
        }
        return 0;
    }

    public void init(int i) {
        this.mSubId = i;
        this.mTelephonyManager = TelephonyManager.from(this.mContext).createForSubscriptionId(this.mSubId);
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!getPreferenceKey().equals(preference.getKey())) {
            return false;
        }
        if (!TextUtils.isEmpty(this.mSetupUrl)) {
            String subscriberId = this.mTelephonyManager.getSubscriberId();
            if (subscriberId == null) {
                subscriberId = "";
            }
            this.mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(TextUtils.expandTemplate(this.mSetupUrl, new CharSequence[]{subscriberId}).toString())));
        }
        return true;
    }
}
