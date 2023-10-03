package com.android.messaging.p041ui.appsettings;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.android.messaging.sms.C1006b;

/* renamed from: com.android.messaging.ui.appsettings.h */
class C1092h extends Handler {

    /* renamed from: Vd */
    private Handler f1735Vd;

    public C1092h(C1094j jVar, Looper looper, Handler handler) {
        super(looper);
        this.f1735Vd = handler;
    }

    public void handleMessage(Message message) {
        if (message.what == 1) {
            C1006b.m2349ob();
            this.f1735Vd.sendEmptyMessage(2);
        }
    }
}
