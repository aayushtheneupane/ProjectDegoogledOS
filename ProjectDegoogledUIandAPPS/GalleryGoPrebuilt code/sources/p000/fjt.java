package p000;

import android.app.Application;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import p003j$.util.concurrent.ConcurrentHashMap;

/* renamed from: fjt */
/* compiled from: PG */
final class fjt extends fhn {

    /* renamed from: d */
    public final boolean f9839d;

    /* renamed from: e */
    public final fjk f9840e;

    /* renamed from: f */
    public final boolean f9841f;

    /* renamed from: g */
    public final boolean f9842g;

    /* renamed from: h */
    private fjq f9843h;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public fjt(iqk iqk, Application application, hqk hqk, hqk hqk2, int i, boolean z, fjk fjk, boolean z2, boolean z3) {
        super(iqk, application, hqk, hqk2, 1, i);
        new AtomicReference(fjj.f9819a);
        new ConcurrentHashMap();
        this.f9839d = z;
        this.f9840e = fjk;
        this.f9841f = z2;
        this.f9842g = z3;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final ieh mo5884a(String str, int i, String str2) {
        if (mo5731b()) {
            return ife.m12816a((ice) new fjs(this, str, i, str2), (Executor) mo5732c());
        }
        return ife.m12820a((Object) null);
    }

    /* renamed from: d */
    public final synchronized void mo5733d() {
        fjq fjq = this.f9843h;
        if (fjq != null) {
            fjq.f9831f.mo5748b(fjq.f9832g);
            fjq.f9831f.mo5748b(fjq.f9833h);
            this.f9843h = null;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002d, code lost:
        return;
     */
    /* renamed from: e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void mo5885e() {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = r4.f9687c     // Catch:{ all -> 0x003e }
            if (r0 != 0) goto L_0x002c
            fjq r0 = r4.f9843h     // Catch:{ all -> 0x003e }
            if (r0 != 0) goto L_0x002c
            fjr r0 = new fjr     // Catch:{ all -> 0x003e }
            r0.<init>(r4)     // Catch:{ all -> 0x003e }
            fjq r1 = new fjq     // Catch:{ all -> 0x003e }
            android.app.Application r2 = r4.f9685a     // Catch:{ all -> 0x003e }
            hqk r3 = r4.f9686b     // Catch:{ all -> 0x003e }
            r1.<init>(r0, r2, r3)     // Catch:{ all -> 0x003e }
            r4.f9843h = r1     // Catch:{ all -> 0x003e }
            java.util.concurrent.atomic.AtomicBoolean r0 = r1.f9826a     // Catch:{ all -> 0x003e }
            r2 = 1
            boolean r0 = r0.getAndSet(r2)     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x002e
            java.lang.String r0 = "MemoryMetricMonitor"
            java.lang.String r1 = "Memory Monitor has already started. This MemoryMetricMonitor.start() is ignored."
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x003e }
            p000.flw.m9202d(r0, r1, r2)     // Catch:{ all -> 0x003e }
        L_0x002c:
            monitor-exit(r4)
            return
        L_0x002e:
            fid r0 = r1.f9831f     // Catch:{ all -> 0x003e }
            fhz r2 = r1.f9832g     // Catch:{ all -> 0x003e }
            r0.mo5747a((p000.fic) r2)     // Catch:{ all -> 0x003e }
            fid r0 = r1.f9831f     // Catch:{ all -> 0x003e }
            fia r1 = r1.f9833h     // Catch:{ all -> 0x003e }
            r0.mo5747a((p000.fic) r1)     // Catch:{ all -> 0x003e }
            monitor-exit(r4)
            return
        L_0x003e:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.fjt.mo5885e():void");
    }
}
