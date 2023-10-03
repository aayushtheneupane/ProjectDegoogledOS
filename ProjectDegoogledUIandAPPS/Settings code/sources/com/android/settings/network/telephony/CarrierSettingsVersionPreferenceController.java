package com.android.settings.network.telephony;

import android.content.Context;
import android.os.PersistableBundle;
import android.telephony.CarrierConfigManager;
import android.text.TextUtils;
import com.android.settings.core.BasePreferenceController;

public class CarrierSettingsVersionPreferenceController extends BasePreferenceController {
    private CarrierConfigManager mManager;
    private int mSubscriptionId = -1;

    public CarrierSettingsVersionPreferenceController(Context context, String str) {
        super(context, str);
        this.mManager = (CarrierConfigManager) context.getSystemService(CarrierConfigManager.class);
    }

    public void init(int i) {
        this.mSubscriptionId = i;
    }

    public CharSequence getSummary() {
        PersistableBundle configForSubId = this.mManager.getConfigForSubId(this.mSubscriptionId);
        if (configForSubId == null) {
            return null;
        }
        return configForSubId.getString("carrier_config_version_string");
    }

    public int getAvailabilityStatus() {
        return TextUtils.isEmpty(getSummary()) ? 3 : 0;
    }
}
