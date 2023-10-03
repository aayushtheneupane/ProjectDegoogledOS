package p000;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import java.util.concurrent.ScheduledExecutorService;

/* renamed from: hge */
/* compiled from: PG */
public final class hge implements ComponentCallbacks2 {

    /* renamed from: a */
    private final gay f12691a;

    public hge(Context context, ScheduledExecutorService scheduledExecutorService, hgd hgd, String str, hgj hgj) {
        this.f12691a = new gay(context, scheduledExecutorService, hgd.f12690a, str, hgj.f12696a);
    }

    public final void onConfigurationChanged(Configuration configuration) {
    }

    public final void onLowMemory() {
        this.f12691a.onLowMemory();
    }

    public final void onTrimMemory(int i) {
        this.f12691a.onTrimMemory(i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r2 = p000.ife.m12817a(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0060, code lost:
        if (r1 != null) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0063, code lost:
        r2 = r1.mo7548a(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0067, code lost:
        r0 = p000.gay.m9964a(r2, new p000.gai(r0)).mo8396a(p000.hmq.m11745a((p000.ico) new p000.gaj(r0)), (java.util.concurrent.Executor) p000.idh.f13918a);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0083, code lost:
        if (r1 != null) goto L_0x0086;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0086, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0095, code lost:
        return p000.gpc.m10576a(r0.mo8397a(p000.hgc.f12689a, (java.util.concurrent.Executor) p000.idh.f13918a));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0096, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a0, code lost:
        r0 = th;
     */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a8  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.gpc mo7394a() {
        /*
            r8 = this;
            gay r0 = r8.f12691a
            java.util.List r1 = p000.hnb.f13076a
            r1 = 0
            java.lang.Object r2 = r0.f10811k     // Catch:{ all -> 0x00a4 }
            monitor-enter(r2)     // Catch:{ all -> 0x00a4 }
            int r3 = r0.f10816p     // Catch:{ all -> 0x009d }
            r4 = 1
            int r3 = r3 + r4
            r0.f10816p = r3     // Catch:{ all -> 0x009d }
            ieh r5 = r0.f10815o     // Catch:{ all -> 0x009d }
            r6 = 0
            if (r5 == 0) goto L_0x0014
            goto L_0x0050
        L_0x0014:
            if (r3 != r4) goto L_0x0019
            r3 = 1
            goto L_0x001b
        L_0x0019:
            r3 = 0
        L_0x001b:
            java.lang.String r5 = "DB was null with nonzero refcount"
            p000.ife.m12876b((boolean) r3, (java.lang.Object) r5)     // Catch:{ all -> 0x009d }
            java.lang.String r3 = "Opening database"
            hlj r1 = p000.hnb.m11765a((java.lang.String) r3)     // Catch:{ all -> 0x009d }
            ice r3 = r0.f10809i     // Catch:{ Exception -> 0x0047 }
            java.util.concurrent.Executor r5 = r0.f10814n     // Catch:{ Exception -> 0x0047 }
            ieh r3 = p000.ife.m12816a((p000.ice) r3, (java.util.concurrent.Executor) r5)     // Catch:{ Exception -> 0x0047 }
            idw r5 = r0.f10813m     // Catch:{ Exception -> 0x0047 }
            java.util.concurrent.ScheduledExecutorService r7 = r0.f10803c     // Catch:{ Exception -> 0x0047 }
            p000.ife.m12841a((p000.ieh) r3, (p000.idw) r5, (java.util.concurrent.Executor) r7)     // Catch:{ Exception -> 0x0047 }
            gam r5 = new gam     // Catch:{ Exception -> 0x0047 }
            r5.<init>(r0)     // Catch:{ Exception -> 0x0047 }
            hpr r5 = p000.hmq.m11742a((p000.hpr) r5)     // Catch:{ Exception -> 0x0047 }
            java.util.concurrent.Executor r7 = r0.f10814n     // Catch:{ Exception -> 0x0047 }
            ieh r3 = p000.ibv.m12657a((p000.ieh) r3, (p000.hpr) r5, (java.util.concurrent.Executor) r7)     // Catch:{ Exception -> 0x0047 }
            goto L_0x004c
        L_0x0045:
            r0 = move-exception
            goto L_0x009c
        L_0x0047:
            r3 = move-exception
            ieh r3 = p000.ife.m12822a((java.lang.Throwable) r3)     // Catch:{ all -> 0x0045 }
        L_0x004c:
            r0.f10815o = r3     // Catch:{ all -> 0x0045 }
        L_0x0050:
            ieh r3 = r0.f10815o     // Catch:{ all -> 0x0099 }
            java.util.concurrent.ScheduledFuture r5 = r0.f10817q     // Catch:{ all -> 0x0099 }
            if (r5 != 0) goto L_0x0057
            goto L_0x005b
        L_0x0057:
            r5.cancel(r4)     // Catch:{ all -> 0x0099 }
        L_0x005b:
            monitor-exit(r2)     // Catch:{ all -> 0x0099 }
            ieh r2 = p000.ife.m12817a((p000.ieh) r3)     // Catch:{ all -> 0x0096 }
            if (r1 != 0) goto L_0x0063
            goto L_0x0067
        L_0x0063:
            ieh r2 = r1.mo7548a(r2)     // Catch:{ all -> 0x0096 }
        L_0x0067:
            java.io.Closeable[] r3 = new java.io.Closeable[r4]     // Catch:{ all -> 0x0096 }
            gai r4 = new gai     // Catch:{ all -> 0x0096 }
            r4.<init>(r0)     // Catch:{ all -> 0x0096 }
            r3[r6] = r4     // Catch:{ all -> 0x0096 }
            idb r2 = p000.gay.m9964a((p000.ieh) r2, (java.io.Closeable[]) r3)     // Catch:{ all -> 0x0096 }
            gaj r3 = new gaj     // Catch:{ all -> 0x0096 }
            r3.<init>(r0)     // Catch:{ all -> 0x0096 }
            ico r0 = p000.hmq.m11745a((p000.ico) r3)     // Catch:{ all -> 0x0096 }
            idh r3 = p000.idh.f13918a     // Catch:{ all -> 0x0096 }
            idb r0 = r2.mo8396a((p000.ico) r0, (java.util.concurrent.Executor) r3)     // Catch:{ all -> 0x0096 }
            if (r1 != 0) goto L_0x0086
            goto L_0x0089
        L_0x0086:
            r1.close()
        L_0x0089:
            icr r1 = p000.hgc.f12689a
            idh r2 = p000.idh.f13918a
            idb r0 = r0.mo8397a((p000.icr) r1, (java.util.concurrent.Executor) r2)
            gpc r0 = p000.gpc.m10576a((p000.idb) r0)
            return r0
        L_0x0096:
            r0 = move-exception
            goto L_0x00a5
        L_0x0099:
            r0 = move-exception
            goto L_0x009e
        L_0x009c:
            goto L_0x00a3
        L_0x009d:
            r0 = move-exception
        L_0x009e:
            monitor-exit(r2)     // Catch:{ all -> 0x00a2 }
            throw r0     // Catch:{ all -> 0x00a0 }
        L_0x00a0:
            r0 = move-exception
            goto L_0x00a5
        L_0x00a2:
            r0 = move-exception
        L_0x00a3:
            goto L_0x009e
        L_0x00a4:
            r0 = move-exception
        L_0x00a5:
            if (r1 != 0) goto L_0x00a8
            goto L_0x00ab
        L_0x00a8:
            r1.close()
        L_0x00ab:
            goto L_0x00ad
        L_0x00ac:
            throw r0
        L_0x00ad:
            goto L_0x00ac
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.hge.mo7394a():gpc");
    }
}
