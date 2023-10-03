package com.android.messaging;

import android.os.Debug;
import com.android.messaging.util.C1410N;
import com.android.messaging.util.C1430e;
import java.io.File;

/* renamed from: com.android.messaging.d */
class C0759d implements Runnable {

    /* renamed from: ax */
    final /* synthetic */ File f981ax;

    C0759d(BugleApplication bugleApplication, File file) {
        this.f981ax = file;
    }

    public void run() {
        Debug.stopMethodTracing();
        C1410N.m3548b(this.f981ax);
        C1430e.m3620d("MessagingAppProf", "Tracing complete - " + this.f981ax.getAbsolutePath());
    }
}
