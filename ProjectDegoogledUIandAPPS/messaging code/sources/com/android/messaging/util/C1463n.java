package com.android.messaging.util;

import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;

/* renamed from: com.android.messaging.util.n */
class C1463n extends PhoneStateListener {
    final /* synthetic */ C1467p this$0;

    C1463n(C1467p pVar) {
        this.this$0 = pVar;
    }

    public void onDataConnectionStateChanged(int i) {
        int unused = this.this$0.f2333PJ = i == 0 ? 1 : 0;
    }

    public void onServiceStateChanged(ServiceState serviceState) {
        if (this.this$0.f2333PJ != serviceState.getState()) {
            int unused = this.this$0.f2333PJ = serviceState.getState();
            C1467p pVar = this.this$0;
            C1467p.m3766b(pVar, pVar.f2333PJ);
        }
    }
}
