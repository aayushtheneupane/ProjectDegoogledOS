package com.google.apps.tiktok.concurrent;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

/* compiled from: PG */
public final class InternalForegroundService extends Service {

    /* renamed from: a */
    public gqw f5306a;

    public final IBinder onBind(Intent intent) {
        return null;
    }

    public final void onCreate() {
        super.onCreate();
        ((gqx) hgh.m11442a((Context) this, gqx.class)).mo2178a(this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return 2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int onStartCommand(android.content.Intent r3, int r4, int r5) {
        /*
            r2 = this;
            gqw r4 = r2.f5306a
            java.lang.Object r0 = r4.f11858d
            monitor-enter(r0)
            if (r3 != 0) goto L_0x0012
            gqv r3 = r4.f11861g     // Catch:{ all -> 0x0048 }
            gqv r4 = p000.gqv.STOPPED     // Catch:{ all -> 0x0048 }
            if (r3 != r4) goto L_0x0010
            r2.stopSelf(r5)     // Catch:{ all -> 0x0048 }
        L_0x0010:
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            goto L_0x0046
        L_0x0012:
            r4.f11860f = r2     // Catch:{ all -> 0x0048 }
            r4.f11862h = r5     // Catch:{ all -> 0x0048 }
            gqv r5 = p000.gqv.STARTED     // Catch:{ all -> 0x0048 }
            r4.f11861g = r5     // Catch:{ all -> 0x0048 }
            java.util.Map r5 = r4.f11859e     // Catch:{ all -> 0x0048 }
            boolean r5 = r5.isEmpty()     // Catch:{ all -> 0x0048 }
            r1 = 174344743(0xa644a27, float:1.0991747E-32)
            if (r5 == 0) goto L_0x0036
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0048 }
            java.lang.String r5 = "fallback_notification"
            android.os.Parcelable r3 = r3.getParcelableExtra(r5)     // Catch:{ all -> 0x0048 }
            android.app.Notification r3 = (android.app.Notification) r3     // Catch:{ all -> 0x0048 }
            r2.startForeground(r1, r3)     // Catch:{ all -> 0x0048 }
            r4.mo6939a()     // Catch:{ all -> 0x0048 }
            goto L_0x0045
        L_0x0036:
            gqy r3 = r4.f11863i     // Catch:{ all -> 0x0048 }
            gqy r3 = r4.mo6942b(r3)     // Catch:{ all -> 0x0048 }
            r4.f11863i = r3     // Catch:{ all -> 0x0048 }
            gqy r3 = r4.f11863i     // Catch:{ all -> 0x0048 }
            android.app.Notification r3 = r3.f11865a     // Catch:{ all -> 0x0048 }
            r2.startForeground(r1, r3)     // Catch:{ all -> 0x0048 }
        L_0x0045:
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
        L_0x0046:
            r3 = 2
            return r3
        L_0x0048:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.apps.tiktok.concurrent.InternalForegroundService.onStartCommand(android.content.Intent, int, int):int");
    }
}
