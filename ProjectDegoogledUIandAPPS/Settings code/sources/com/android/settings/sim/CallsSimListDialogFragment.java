package com.android.settings.sim;

import android.content.Context;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import java.util.ArrayList;
import java.util.List;

public class CallsSimListDialogFragment extends SimListDialogFragment {
    public int getMetricsCategory() {
        return 1708;
    }

    /* access modifiers changed from: protected */
    public List<SubscriptionInfo> getCurrentSubscriptions() {
        Context context = getContext();
        SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        TelecomManager telecomManager = (TelecomManager) context.getSystemService(TelecomManager.class);
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        List<PhoneAccountHandle> callCapablePhoneAccounts = telecomManager.getCallCapablePhoneAccounts();
        ArrayList arrayList = new ArrayList();
        if (callCapablePhoneAccounts == null) {
            return arrayList;
        }
        for (PhoneAccountHandle phoneAccount : callCapablePhoneAccounts) {
            int subIdForPhoneAccount = telephonyManager.getSubIdForPhoneAccount(telecomManager.getPhoneAccount(phoneAccount));
            if (subIdForPhoneAccount != -1) {
                arrayList.add(subscriptionManager.getActiveSubscriptionInfo(subIdForPhoneAccount));
            }
        }
        return arrayList;
    }
}
