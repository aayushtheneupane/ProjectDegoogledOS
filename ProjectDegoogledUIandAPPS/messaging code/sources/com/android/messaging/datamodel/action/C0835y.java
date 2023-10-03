package com.android.messaging.datamodel.action;

import com.android.messaging.util.C1472ra;

/* renamed from: com.android.messaging.datamodel.action.y */
class C0835y implements C1472ra {
    C0835y() {
    }

    /* renamed from: c */
    public void mo6074c(int i) {
        ProcessPendingMessagesAction.m1416Cb(i);
        ProcessPendingMessagesAction.m1418W(0, i);
        ProcessPendingMessagesAction processPendingMessagesAction = new ProcessPendingMessagesAction((C0835y) null);
        processPendingMessagesAction.f1057Oy.putInt("sub_id", i);
        processPendingMessagesAction.start();
    }
}
