package com.android.messaging.datamodel;

import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.datamodel.M */
class C0772M implements Runnable {
    C0772M() {
    }

    public void run() {
        C1424b.m3592ia(ParticipantRefresh.f1034yy.getAndSet(false));
        ParticipantRefresh.refreshParticipants(0);
    }
}
