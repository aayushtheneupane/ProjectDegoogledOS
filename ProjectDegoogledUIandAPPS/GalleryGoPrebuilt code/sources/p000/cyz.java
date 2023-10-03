package p000;

import java.util.List;

/* renamed from: cyz */
/* compiled from: PG */
final /* synthetic */ class cyz implements ice {

    /* renamed from: a */
    private final cze f6076a;

    /* renamed from: b */
    private final int f6077b;

    /* renamed from: c */
    private final List f6078c;

    /* renamed from: d */
    private final int f6079d;

    /* renamed from: e */
    private final List f6080e;

    public cyz(cze cze, int i, List list, int i2, List list2) {
        this.f6076a = cze;
        this.f6077b = i;
        this.f6078c = list;
        this.f6079d = i2;
        this.f6080e = list2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0087, code lost:
        if (r5 == null) goto L_0x0092;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x008f, code lost:
        if (r5 != null) goto L_0x0089;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0092, code lost:
        return r0;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.ieh mo2539a() {
        /*
            r10 = this;
            cze r0 = r10.f6076a
            int r1 = r10.f6077b
            java.util.List r2 = r10.f6078c
            int r3 = r10.f6079d
            java.util.List r4 = r10.f6080e
            java.lang.String r5 = "delete batch"
            hlj r5 = p000.hnb.m11765a((java.lang.String) r5)
            java.util.concurrent.atomic.AtomicBoolean r6 = r0.f6095g     // Catch:{ all -> 0x0093 }
            boolean r6 = r6.get()     // Catch:{ all -> 0x0093 }
            if (r6 != 0) goto L_0x008d
            bqq r6 = r0.f6093e     // Catch:{ all -> 0x0093 }
            r7 = 0
            r8 = 1
            if (r1 >= 0) goto L_0x001f
            goto L_0x003a
        L_0x001f:
            r9 = 100
            if (r1 > r9) goto L_0x003a
            r6.f3388c = r1     // Catch:{ all -> 0x0093 }
            java.lang.Object[] r9 = new java.lang.Object[r8]     // Catch:{ all -> 0x0093 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0093 }
            r9[r7] = r1     // Catch:{ all -> 0x0093 }
            gus r1 = r6.f3387b     // Catch:{ all -> 0x0093 }
            r6 = 0
            ieh r6 = p000.ife.m12820a((java.lang.Object) r6)     // Catch:{ all -> 0x0093 }
            java.lang.String r9 = "DELETION_PROGRESS_KEY"
            r1.mo7096a((p000.ieh) r6, (java.lang.Object) r9)     // Catch:{ all -> 0x0093 }
            goto L_0x0047
        L_0x003a:
            java.lang.String r6 = "DeletionProgressDataService: Attempted to set completion percent to %d"
            java.lang.Object[] r9 = new java.lang.Object[r8]     // Catch:{ all -> 0x0093 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0093 }
            r9[r7] = r1     // Catch:{ all -> 0x0093 }
            p000.cwn.m5514b(r6, r9)     // Catch:{ all -> 0x0093 }
        L_0x0047:
            iqk r1 = r0.f6089a     // Catch:{ all -> 0x0093 }
            java.lang.Object r1 = r1.mo2097a()     // Catch:{ all -> 0x0093 }
            czf r1 = (p000.czf) r1     // Catch:{ all -> 0x0093 }
            imp r0 = r0.f6090b     // Catch:{ all -> 0x0093 }
            java.lang.String r6 = "Deleter"
            imq r0 = r0.mo8999a(r6)     // Catch:{ all -> 0x0093 }
            czf r0 = r1.mo2094a((p000.imq) r0)     // Catch:{ all -> 0x0093 }
            czf r0 = r0.mo2095a((java.util.List) r2)     // Catch:{ all -> 0x0093 }
            czg r0 = r0.mo2096a()     // Catch:{ all -> 0x0093 }
            ieh r0 = r0.mo2102d()     // Catch:{ all -> 0x0093 }
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0093 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0093 }
            r1[r7] = r2     // Catch:{ all -> 0x0093 }
            int r2 = r4.size()     // Catch:{ all -> 0x0093 }
            int r2 = r2 + 9
            int r2 = r2 / 10
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x0093 }
            r1[r8] = r2     // Catch:{ all -> 0x0093 }
            java.lang.String r2 = "Deleter: Failed to delete batch %d of %d"
            p000.cwn.m5509a((p000.ieh) r0, (java.lang.String) r2, (java.lang.Object[]) r1)     // Catch:{ all -> 0x0093 }
            ieh r0 = r5.mo7548a(r0)     // Catch:{ all -> 0x0093 }
            if (r5 == 0) goto L_0x0092
        L_0x0089:
            r5.close()
            return r0
        L_0x008d:
            ieh r0 = p000.bip.f2458b     // Catch:{ all -> 0x0093 }
            if (r5 == 0) goto L_0x0092
            goto L_0x0089
        L_0x0092:
            return r0
        L_0x0093:
            r0 = move-exception
            if (r5 == 0) goto L_0x009e
            r5.close()     // Catch:{ all -> 0x009a }
            goto L_0x009e
        L_0x009a:
            r1 = move-exception
            p000.ifn.m12935a((java.lang.Throwable) r0, (java.lang.Throwable) r1)
        L_0x009e:
            goto L_0x00a0
        L_0x009f:
            throw r0
        L_0x00a0:
            goto L_0x009f
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.cyz.mo2539a():ieh");
    }
}
