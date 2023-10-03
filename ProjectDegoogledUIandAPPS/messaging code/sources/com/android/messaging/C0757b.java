package com.android.messaging;

import androidx.appcompat.mms.MmsManager;
import com.android.messaging.util.C1449g;

/* renamed from: com.android.messaging.b */
class C0757b implements Runnable {

    /* renamed from: Yw */
    final /* synthetic */ C1449g f978Yw;

    C0757b(C1449g gVar) {
        this.f978Yw = gVar;
    }

    public void run() {
        this.f978Yw.getBoolean("bugle_use_mms_api", true);
        MmsManager.setForceLegacyMms(false);
    }
}
