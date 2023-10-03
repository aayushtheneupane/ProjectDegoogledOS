package com.android.messaging.datamodel.data;

import java.util.Comparator;

/* renamed from: com.android.messaging.datamodel.data.W */
class C0910W implements Comparator {
    C0910W(C0911X x) {
    }

    public int compare(Object obj, Object obj2) {
        return ((ParticipantData) obj).mo6354sh() > ((ParticipantData) obj2).mo6354sh() ? 1 : -1;
    }
}
