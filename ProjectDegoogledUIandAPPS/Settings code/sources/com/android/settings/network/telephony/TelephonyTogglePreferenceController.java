package com.android.settings.network.telephony;

import android.content.Context;
import com.android.settings.core.TogglePreferenceController;

public abstract class TelephonyTogglePreferenceController extends TogglePreferenceController implements TelephonyAvailabilityCallback {
    protected int mSubId = -1;

    public TelephonyTogglePreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return MobileNetworkUtils.getAvailability(this.mContext, this.mSubId, new TelephonyAvailabilityCallback() {
            public final int getAvailabilityStatus(int i) {
                return TelephonyTogglePreferenceController.this.getAvailabilityStatus(i);
            }
        });
    }
}
