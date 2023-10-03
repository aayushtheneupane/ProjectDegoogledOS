package com.android.messaging.util;

import com.android.messaging.datamodel.action.SyncMessagesAction;

/* renamed from: com.android.messaging.util.F */
class C1402F extends C1407K {
    C1402F(String str) {
        super(str);
    }

    public void run() {
        SyncMessagesAction.sync();
    }
}
