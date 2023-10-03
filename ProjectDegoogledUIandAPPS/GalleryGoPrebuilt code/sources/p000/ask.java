package p000;

import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: ask */
/* compiled from: PG */
public final class ask {

    /* renamed from: a */
    public final ReferenceQueue f1519a = new ReferenceQueue();

    /* renamed from: b */
    public volatile boolean f1520b;

    /* renamed from: c */
    public volatile asi f1521c;

    /* renamed from: d */
    private final Map f1522d = new HashMap();

    public ask() {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor(new asg());
        newSingleThreadExecutor.execute(new ash(this));
    }

    /* renamed from: a */
    public final synchronized void mo1547a(aqu aqu, ats ats) {
        asj asj = (asj) this.f1522d.put(aqu, new asj(aqu, ats, this.f1519a));
        if (asj != null) {
            asj.clear();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo1548a(asj asj) {
        synchronized (this) {
            this.f1522d.remove(asj.f1518a);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized void mo1546a(aqu aqu) {
        asj asj = (asj) this.f1522d.remove(aqu);
        if (asj != null) {
            asj.clear();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
        return r0;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized p000.ats mo1549b(p000.aqu r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            java.util.Map r0 = r1.f1522d     // Catch:{ all -> 0x001b }
            java.lang.Object r2 = r0.get(r2)     // Catch:{ all -> 0x001b }
            asj r2 = (p000.asj) r2     // Catch:{ all -> 0x001b }
            if (r2 == 0) goto L_0x0018
            java.lang.Object r0 = r2.get()     // Catch:{ all -> 0x001b }
            ats r0 = (p000.ats) r0     // Catch:{ all -> 0x001b }
            if (r0 != 0) goto L_0x0016
            r1.mo1548a((p000.asj) r2)     // Catch:{ all -> 0x001b }
        L_0x0016:
            monitor-exit(r1)
            return r0
        L_0x0018:
            r2 = 0
            monitor-exit(r1)
            return r2
        L_0x001b:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ask.mo1549b(aqu):ats");
    }
}
