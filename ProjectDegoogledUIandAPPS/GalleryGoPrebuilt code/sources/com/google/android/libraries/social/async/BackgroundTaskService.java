package com.google.android.libraries.social.async;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/* compiled from: PG */
public final class BackgroundTaskService extends Service {
    public final IBinder onBind(Intent intent) {
        return null;
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }

    public final void onCreate() {
        m4979a();
    }

    public final void onDestroy() {
        m4979a();
    }

    /* renamed from: a */
    private final void m4979a() {
        fsa fsa = (fsa) fua.m9628a(this, fsa.class);
        fxk.m9830b();
    }
}
