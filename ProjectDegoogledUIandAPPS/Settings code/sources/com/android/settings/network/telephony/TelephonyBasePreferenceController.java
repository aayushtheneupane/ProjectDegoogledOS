package com.android.settings.network.telephony;

import android.content.Context;
import com.android.settings.core.BasePreferenceController;

public abstract class TelephonyBasePreferenceController extends BasePreferenceController implements TelephonyAvailabilityCallback {
    protected int mSubId = -1;

    public TelephonyBasePreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return MobileNetworkUtils.getAvailability(this.mContext, this.mSubId, new TelephonyAvailabilityCallback() {
            public final int getAvailabilityStatus(int i) {
                return TelephonyBasePreferenceController.this.getAvailabilityStatus(i);
            }
        });
    }
}
