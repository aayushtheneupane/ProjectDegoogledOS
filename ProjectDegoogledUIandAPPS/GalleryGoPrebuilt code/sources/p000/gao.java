package p000;

/* renamed from: gao */
/* compiled from: PG */
final /* synthetic */ class gao implements Runnable {

    /* renamed from: a */
    private final gay f10795a;

    public gao(gay gay) {
        this.f10795a = gay;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0017, code lost:
        if (r2.cancel(true) == false) goto L_0x001a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        ((android.database.sqlite.SQLiteDatabase) p000.ife.m12871b((java.util.concurrent.Future) r2)).close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r4 = this;
            gay r0 = r4.f10795a
            java.lang.Object r1 = r0.f10811k
            monitor-enter(r1)
            ieh r2 = r0.f10815o     // Catch:{ all -> 0x0049 }
            int r3 = r0.f10816p     // Catch:{ all -> 0x0049 }
            if (r3 != 0) goto L_0x0047
            if (r2 != 0) goto L_0x000e
            goto L_0x0047
        L_0x000e:
            r3 = 0
            r0.f10815o = r3     // Catch:{ all -> 0x0049 }
            monitor-exit(r1)     // Catch:{ all -> 0x0049 }
            r1 = 1
            boolean r1 = r2.cancel(r1)
            if (r1 == 0) goto L_0x001a
            goto L_0x0025
        L_0x001a:
            java.lang.Object r1 = p000.ife.m12871b((java.util.concurrent.Future) r2)     // Catch:{ ExecutionException -> 0x0024 }
            android.database.sqlite.SQLiteDatabase r1 = (android.database.sqlite.SQLiteDatabase) r1     // Catch:{ ExecutionException -> 0x0024 }
            r1.close()     // Catch:{ ExecutionException -> 0x0024 }
            goto L_0x0025
        L_0x0024:
            r1 = move-exception
        L_0x0025:
            android.content.Context r1 = r0.f10802b
            r1.unregisterComponentCallbacks(r0)
            java.util.Set r0 = r0.f10810j
            java.util.Iterator r0 = r0.iterator()
        L_0x0030:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0046
            java.lang.Object r1 = r0.next()
            java.lang.ref.WeakReference r1 = (java.lang.ref.WeakReference) r1
            java.lang.Object r1 = r1.get()
            if (r1 != 0) goto L_0x0030
            r0.remove()
            goto L_0x0030
        L_0x0046:
            return
        L_0x0047:
            monitor-exit(r1)     // Catch:{ all -> 0x0049 }
            return
        L_0x0049:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0049 }
            goto L_0x004d
        L_0x004c:
            throw r0
        L_0x004d:
            goto L_0x004c
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.gao.run():void");
    }
}
