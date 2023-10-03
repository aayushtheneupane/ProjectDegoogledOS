package com.android.settings.network.telephony;

import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.preference.Preference;

public class EuiccPreferenceController extends TelephonyBasePreferenceController {
    private TelephonyManager mTelephonyManager;

    public EuiccPreferenceController(Context context, String str) {
        super(context, str);
        this.mTelephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
    }

    public int getAvailabilityStatus(int i) {
        return MobileNetworkUtils.showEuiccSettings(this.mContext) ? 0 : 2;
    }

    public CharSequence getSummary() {
        return this.mTelephonyManager.getSimOperatorName();
    }

    public void init(int i) {
        this.mSubId = i;
        this.mTelephonyManager = TelephonyManager.from(this.mContext).createForSubscriptionId(this.mSubId);
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(preference.getKey(), getPreferenceKey())) {
            return false;
        }
        this.mContext.startActivity(new Intent("android.telephony.euicc.action.MANAGE_EMBEDDED_SUBSCRIPTIONS"));
        return true;
    }
}
