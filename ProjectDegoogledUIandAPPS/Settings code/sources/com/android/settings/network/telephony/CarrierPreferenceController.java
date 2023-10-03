package com.android.settings.network.telephony;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.telephony.CarrierConfigManager;
import androidx.preference.Preference;

public class CarrierPreferenceController extends TelephonyBasePreferenceController {
    CarrierConfigManager mCarrierConfigManager;

    public CarrierPreferenceController(Context context, String str) {
        super(context, str);
        this.mCarrierConfigManager = new CarrierConfigManager(context);
    }

    public void init(int i) {
        this.mSubId = i;
    }

    public int getAvailabilityStatus(int i) {
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(i);
        return (configForSubId == null || !configForSubId.getBoolean("carrier_settings_enable_bool") || (!MobileNetworkUtils.isCdmaOptions(this.mContext, i) && !MobileNetworkUtils.isGsmOptions(this.mContext, i))) ? 2 : 0;
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!getPreferenceKey().equals(preference.getKey())) {
            return false;
        }
        Intent carrierSettingsActivityIntent = getCarrierSettingsActivityIntent(this.mSubId);
        if (carrierSettingsActivityIntent == null) {
            return true;
        }
        this.mContext.startActivity(carrierSettingsActivityIntent);
        return true;
    }

    private Intent getCarrierSettingsActivityIntent(int i) {
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(i);
        String str = "";
        if (configForSubId != null) {
            str = configForSubId.getString("carrier_settings_activity_component_name_string", str);
        }
        ComponentName unflattenFromString = ComponentName.unflattenFromString(str);
        if (unflattenFromString == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setComponent(unflattenFromString);
        intent.setFlags(268435456);
        intent.putExtra("android.telephony.extra.SUBSCRIPTION_INDEX", i);
        if (this.mContext.getPackageManager().resolveActivity(intent, 0) != null) {
            return intent;
        }
        return null;
    }
}
