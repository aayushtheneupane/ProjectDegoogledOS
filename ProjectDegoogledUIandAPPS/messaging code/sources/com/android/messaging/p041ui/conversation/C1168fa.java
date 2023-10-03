package com.android.messaging.p041ui.conversation;

import com.android.messaging.datamodel.data.MessagePartData;
import java.util.Comparator;

/* renamed from: com.android.messaging.ui.conversation.fa */
class C1168fa implements Comparator {
    C1168fa() {
    }

    public int compare(Object obj, Object obj2) {
        return ((MessagePartData) obj).mo6298ch().compareTo(((MessagePartData) obj2).mo6298ch());
    }
}
