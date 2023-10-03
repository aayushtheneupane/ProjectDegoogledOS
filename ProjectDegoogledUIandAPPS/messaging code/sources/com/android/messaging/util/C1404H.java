package com.android.messaging.util;

import android.app.Activity;
import androidx.core.app.NotificationCompat;

/* renamed from: com.android.messaging.util.H */
class C1404H extends C1407K {

    /* renamed from: XJ */
    final /* synthetic */ Activity f2209XJ;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C1404H(String str, Activity activity) {
        super(str);
        this.f2209XJ = activity;
    }

    public void run() {
        new C1409M(this.f2209XJ, NotificationCompat.CATEGORY_EMAIL).mo8233b(new Void[0]);
    }
}
