package com.android.messaging.datamodel;

import com.android.messaging.util.C1462ma;

/* renamed from: com.android.messaging.datamodel.d */
class C0888d implements Runnable {

    /* renamed from: vx */
    final /* synthetic */ C1462ma f1134vx;

    C0888d(C1462ma maVar) {
        this.f1134vx = maVar;
    }

    public void run() {
        this.f1134vx.stop(true);
    }
}
