package p000;

import java.util.concurrent.Callable;

/* renamed from: app */
/* compiled from: PG */
final class C0018app implements Callable {

    /* renamed from: a */
    private final /* synthetic */ apu f1367a;

    public C0018app(apu apu) {
        this.f1367a = apu;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object call() {
        /*
            r3 = this;
            apu r0 = r3.f1367a
            monitor-enter(r0)
            apu r1 = r3.f1367a     // Catch:{ all -> 0x0023 }
            java.io.Writer r2 = r1.f1385f     // Catch:{ all -> 0x0023 }
            if (r2 == 0) goto L_0x0020
            r1.mo1468d()     // Catch:{ all -> 0x0023 }
            apu r1 = r3.f1367a     // Catch:{ all -> 0x0023 }
            boolean r1 = r1.mo1466c()     // Catch:{ all -> 0x0023 }
            if (r1 == 0) goto L_0x001e
            apu r1 = r3.f1367a     // Catch:{ all -> 0x0023 }
            r1.mo1465b()     // Catch:{ all -> 0x0023 }
            apu r1 = r3.f1367a     // Catch:{ all -> 0x0023 }
            r2 = 0
            r1.f1387h = r2     // Catch:{ all -> 0x0023 }
        L_0x001e:
            monitor-exit(r0)     // Catch:{ all -> 0x0023 }
            goto L_0x0021
        L_0x0020:
            monitor-exit(r0)     // Catch:{ all -> 0x0023 }
        L_0x0021:
            r0 = 0
            return r0
        L_0x0023:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0023 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0018app.call():java.lang.Object");
    }
}
