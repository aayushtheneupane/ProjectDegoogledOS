package p000;

/* renamed from: js */
/* compiled from: PG */
final class C0268js implements C0279kc {

    /* renamed from: a */
    private final /* synthetic */ String f15094a;

    public C0268js(String str) {
        this.f15094a = str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
        ((p000.C0279kc) r1.get(r0)).mo9171a(r5);
        r0 = r0 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002a, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
        r0 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r0 >= r1.size()) goto L_0x002a;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ void mo9171a(java.lang.Object r5) {
        /*
            r4 = this;
            java.lang.Object r0 = p000.C0273jx.f15105b
            monitor-enter(r0)
            lf r1 = p000.C0273jx.f15106c     // Catch:{ all -> 0x002d }
            java.lang.String r2 = r4.f15094a     // Catch:{ all -> 0x002d }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x002d }
            java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch:{ all -> 0x002d }
            if (r1 == 0) goto L_0x002b
            lf r2 = p000.C0273jx.f15106c     // Catch:{ all -> 0x002d }
            java.lang.String r3 = r4.f15094a     // Catch:{ all -> 0x002d }
            r2.remove(r3)     // Catch:{ all -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            r0 = 0
        L_0x0018:
            int r2 = r1.size()
            if (r0 >= r2) goto L_0x002a
            java.lang.Object r2 = r1.get(r0)
            kc r2 = (p000.C0279kc) r2
            r2.mo9171a(r5)
            int r0 = r0 + 1
            goto L_0x0018
        L_0x002a:
            return
        L_0x002b:
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x002d:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            goto L_0x0031
        L_0x0030:
            throw r5
        L_0x0031:
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0268js.mo9171a(java.lang.Object):void");
    }
}
