package p000;

import java.util.concurrent.Callable;

/* renamed from: cuw */
/* compiled from: PG */
final /* synthetic */ class cuw implements Callable {

    /* renamed from: a */
    private final cva f5699a;

    public cuw(cva cva) {
        this.f5699a = cva;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002c, code lost:
        if (r2 != null) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002f, code lost:
        if (r2 != null) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001a, code lost:
        if (r2 == null) goto L_0x0032;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object call() {
        /*
            r5 = this;
            cva r0 = r5.f5699a
            blw r1 = r0.f5709d
            java.lang.String r2 = "BatteryStatus getExtras"
            hlj r2 = p000.hnb.m11765a((java.lang.String) r2)
            android.content.Context r1 = r1.f3123b     // Catch:{ all -> 0x004f }
            r3 = 0
            android.content.IntentFilter r4 = p000.blw.f3122a     // Catch:{ all -> 0x004f }
            android.content.Intent r1 = r1.registerReceiver(r3, r4)     // Catch:{ all -> 0x004f }
            if (r1 != 0) goto L_0x0021
            android.os.Bundle r1 = new android.os.Bundle     // Catch:{ all -> 0x004f }
            r1.<init>()     // Catch:{ all -> 0x004f }
            if (r2 != 0) goto L_0x001d
            goto L_0x0032
        L_0x001d:
            r2.close()
            goto L_0x0032
        L_0x0021:
            android.os.Bundle r1 = r1.getExtras()     // Catch:{ all -> 0x004f }
            if (r1 != 0) goto L_0x002f
            android.os.Bundle r1 = new android.os.Bundle     // Catch:{ all -> 0x004f }
            r1.<init>()     // Catch:{ all -> 0x004f }
            if (r2 == 0) goto L_0x0032
            goto L_0x001d
        L_0x002f:
            if (r2 == 0) goto L_0x0032
            goto L_0x001d
        L_0x0032:
            r2 = -1
            java.lang.String r3 = "status"
            int r1 = r1.getInt(r3, r2)
            r2 = 2
            r3 = 1
            if (r1 == r2) goto L_0x0042
            r2 = 5
            if (r1 != r2) goto L_0x0041
            goto L_0x0042
        L_0x0041:
            r3 = 0
        L_0x0042:
            r1 = 20
            java.lang.String r2 = "PluggedInWorker ended early because phone was unplugged."
            boolean r0 = r0.mo3845a(r3, r1, r2)
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            return r0
        L_0x004f:
            r0 = move-exception
            if (r2 == 0) goto L_0x005a
            r2.close()     // Catch:{ all -> 0x0056 }
            goto L_0x005a
        L_0x0056:
            r1 = move-exception
            p000.ifn.m12935a((java.lang.Throwable) r0, (java.lang.Throwable) r1)
        L_0x005a:
            goto L_0x005c
        L_0x005b:
            throw r0
        L_0x005c:
            goto L_0x005b
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.cuw.call():java.lang.Object");
    }
}
