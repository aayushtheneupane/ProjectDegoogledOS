package com.android.messaging.util;

import com.android.messaging.datamodel.action.SyncMessagesAction;

/* renamed from: com.android.messaging.util.E */
class C1401E extends C1407K {
    C1401E(String str) {
        super(str);
    }

    public void run() {
        C1451h.m3724Hd().putLong("last_full_sync_time_millis", -1);
        SyncMessagesAction.m1447Je();
    }
}
