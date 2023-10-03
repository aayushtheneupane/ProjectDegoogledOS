package com.android.messaging.datamodel;

import android.telephony.SubscriptionManager;
import com.android.messaging.sms.C1024t;

/* renamed from: com.android.messaging.datamodel.i */
class C0948i extends SubscriptionManager.OnSubscriptionsChangedListener {
    final /* synthetic */ C0950k this$0;

    C0948i(C0950k kVar) {
        this.this$0 = kVar;
    }

    public void onSubscriptionsChanged() {
        C1024t.m2367Ci();
        ParticipantRefresh.m1288pe();
        this.this$0.m2128_n();
    }
}
