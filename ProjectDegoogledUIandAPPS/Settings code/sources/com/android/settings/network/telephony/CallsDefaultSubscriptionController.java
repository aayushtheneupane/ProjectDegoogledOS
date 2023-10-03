package com.android.settings.network.telephony;

import android.content.Context;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;

public class CallsDefaultSubscriptionController extends DefaultSubscriptionController {
    public CallsDefaultSubscriptionController(Context context, String str) {
        super(context, str);
    }

    /* access modifiers changed from: protected */
    public SubscriptionInfo getDefaultSubscriptionInfo() {
        return this.mManager.getDefaultVoiceSubscriptionInfo();
    }

    /* access modifiers changed from: protected */
    public int getDefaultSubscriptionId() {
        return SubscriptionManager.getDefaultVoiceSubscriptionId();
    }

    /* access modifiers changed from: protected */
    public void setDefaultSubscription(int i) {
        this.mManager.setDefaultVoiceSubId(i);
    }
}
