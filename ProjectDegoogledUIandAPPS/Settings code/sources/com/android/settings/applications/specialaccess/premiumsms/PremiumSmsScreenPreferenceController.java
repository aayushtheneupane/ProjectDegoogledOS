package com.android.settings.applications.specialaccess.premiumsms;

import android.content.Context;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class PremiumSmsScreenPreferenceController extends BasePreferenceController {
    public PremiumSmsScreenPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(C1715R.bool.config_show_premium_sms) ? 0 : 3;
    }
}
