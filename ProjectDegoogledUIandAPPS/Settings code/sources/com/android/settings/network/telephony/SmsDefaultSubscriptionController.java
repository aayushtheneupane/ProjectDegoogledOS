package com.android.settings.network.telephony;

import android.content.Context;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;

public class SmsDefaultSubscriptionController extends DefaultSubscriptionController {
    public SmsDefaultSubscriptionController(Context context, String str) {
        super(context, str);
    }

    /* access modifiers changed from: protected */
    public SubscriptionInfo getDefaultSubscriptionInfo() {
        return this.mManager.getDefaultSmsSubscriptionInfo();
    }

    /* access modifiers changed from: protected */
    public int getDefaultSubscriptionId() {
        return SubscriptionManager.getDefaultSmsSubscriptionId();
    }

    /* access modifiers changed from: protected */
    public void setDefaultSubscription(int i) {
        this.mManager.setDefaultSmsSubId(i);
    }
}
