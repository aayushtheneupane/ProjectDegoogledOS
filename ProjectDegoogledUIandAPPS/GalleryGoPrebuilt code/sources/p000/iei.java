package p000;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

/* renamed from: iei */
/* compiled from: PG */
public final class iei extends FutureTask implements ieh {

    /* renamed from: a */
    private final idj f13957a = new idj();

    private iei(Runnable runnable) {
        super(runnable, (Object) null);
    }

    private iei(Callable callable) {
        super(callable);
    }

    /* renamed from: a */
    public final void mo53a(Runnable runnable, Executor executor) {
        idj idj = this.f13957a;
        ife.m12869b((Object) runnable, (Object) "Runnable was null.");
        ife.m12869b((Object) executor, (Object) "Executor was null.");
        synchronized (idj) {
            if (!idj.f13925b) {
                idj.f13924a = new idi(runnable, executor, idj.f13924a);
            } else {
                idj.m12727a(runnable, executor);
            }
        }
    }

    /* renamed from: a */
    public static iei m12760a(Runnable runnable) {
        return new iei(runnable);
    }

    /* renamed from: a */
    public static iei m12761a(Callable callable) {
        return new iei(callable);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001d, code lost:
        p000.idj.m12727a(r2.f13920a, r2.f13921b);
        r2 = r2.f13922c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0027, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0010, code lost:
        if (r1 == null) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0012, code lost:
        r0 = r1.f13922c;
        r1.f13922c = r2;
        r2 = r1;
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        if (r2 == null) goto L_0x0027;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void done() {
        /*
            r3 = this;
            idj r0 = r3.f13957a
            monitor-enter(r0)
            boolean r1 = r0.f13925b     // Catch:{ all -> 0x002a }
            if (r1 != 0) goto L_0x0028
            r1 = 1
            r0.f13925b = r1     // Catch:{ all -> 0x002a }
            idi r1 = r0.f13924a     // Catch:{ all -> 0x002a }
            r2 = 0
            r0.f13924a = r2     // Catch:{ all -> 0x002a }
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
        L_0x0010:
            if (r1 == 0) goto L_0x001a
            idi r0 = r1.f13922c
            r1.f13922c = r2
            r2 = r1
            r1 = r0
            goto L_0x0010
        L_0x001a:
        L_0x001b:
            if (r2 == 0) goto L_0x0027
            java.lang.Runnable r0 = r2.f13920a
            java.util.concurrent.Executor r1 = r2.f13921b
            p000.idj.m12727a(r0, r1)
            idi r2 = r2.f13922c
            goto L_0x001b
        L_0x0027:
            return
        L_0x0028:
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            return
        L_0x002a:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            goto L_0x002e
        L_0x002d:
            throw r1
        L_0x002e:
            goto L_0x002d
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.iei.done():void");
    }
}
