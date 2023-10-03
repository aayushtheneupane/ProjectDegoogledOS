package p000;

/* renamed from: bha */
/* compiled from: PG */
final class bha implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ bhw f2358a;

    /* renamed from: b */
    private final /* synthetic */ bhd f2359b;

    public bha(bhd bhd, bhw bhw) {
        this.f2359b = bhd;
        this.f2358a = bhw;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x011c, code lost:
        if (r3 == null) goto L_0x0120;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r9 = this;
            bhd r0 = r9.f2359b
            bhw r1 = r9.f2358a
            bgw r2 = r0.f2363c
            int[] r3 = r1.f2416e
            int r3 = p000.bgv.m2519a(r3)
            r4 = r3 & 2
            r5 = 1
            if (r4 == 0) goto L_0x0012
            goto L_0x0018
        L_0x0012:
            boolean r4 = p000.bgw.m2520a(r3)
            if (r4 == 0) goto L_0x0042
        L_0x0018:
            android.content.Context r2 = r2.f2356a
            java.lang.String r4 = "connectivity"
            java.lang.Object r2 = r2.getSystemService(r4)
            android.net.ConnectivityManager r2 = (android.net.ConnectivityManager) r2
            android.net.NetworkInfo r4 = r2.getActiveNetworkInfo()
            if (r4 == 0) goto L_0x003c
            boolean r4 = r4.isConnected()
            if (r4 == 0) goto L_0x003c
            boolean r3 = p000.bgw.m2520a(r3)
            if (r3 != 0) goto L_0x0035
            goto L_0x0042
        L_0x0035:
            boolean r2 = p000.C0321lr.m14630a((android.net.ConnectivityManager) r2)
            r2 = r2 ^ r5
            if (r2 != 0) goto L_0x0042
        L_0x003c:
            bhc r0 = r0.f2366f
            r0.mo2036a(r1, r5)
            return
        L_0x0042:
            java.util.Map r2 = p000.bhd.f2361a
            monitor-enter(r2)
            java.util.Map r3 = p000.bhd.f2361a     // Catch:{ all -> 0x014b }
            java.lang.String r4 = r1.f2413b     // Catch:{ all -> 0x014b }
            java.lang.Object r3 = r3.get(r4)     // Catch:{ all -> 0x014b }
            bic r3 = (p000.bic) r3     // Catch:{ all -> 0x014b }
            if (r3 != 0) goto L_0x0146
            bic r3 = new bic     // Catch:{ all -> 0x014b }
            bhn r4 = r0.f2362b     // Catch:{ all -> 0x014b }
            android.content.Context r6 = r0.f2364d     // Catch:{ all -> 0x014b }
            r3.<init>(r4, r6)     // Catch:{ all -> 0x014b }
            java.util.Map r4 = p000.bhd.f2361a     // Catch:{ all -> 0x014b }
            java.lang.String r6 = r1.f2413b     // Catch:{ all -> 0x014b }
            r4.put(r6, r3)     // Catch:{ all -> 0x014b }
            r3.mo2079b(r1)     // Catch:{ all -> 0x014b }
            android.content.Intent r4 = new android.content.Intent     // Catch:{ all -> 0x014b }
            java.lang.String r6 = "com.firebase.jobdispatcher.ACTION_EXECUTE"
            r4.<init>(r6)     // Catch:{ all -> 0x014b }
            android.content.Context r6 = r0.f2364d     // Catch:{ all -> 0x014b }
            java.lang.String r7 = r1.f2413b     // Catch:{ all -> 0x014b }
            android.content.Intent r4 = r4.setClassName(r6, r7)     // Catch:{ all -> 0x014b }
            android.content.Context r6 = r0.f2364d     // Catch:{ SecurityException -> 0x008b }
            boolean r4 = r6.bindService(r4, r3, r5)     // Catch:{ SecurityException -> 0x008b }
            if (r4 == 0) goto L_0x00c3
            bhb r1 = new bhb     // Catch:{ all -> 0x014b }
            r1.<init>(r3)     // Catch:{ all -> 0x014b }
            java.util.concurrent.ScheduledExecutorService r0 = r0.f2367g     // Catch:{ all -> 0x014b }
            r3 = 18
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ all -> 0x014b }
            r0.schedule(r1, r3, r5)     // Catch:{ all -> 0x014b }
            goto L_0x0144
        L_0x008b:
            r4 = move-exception
            java.lang.String r5 = "FJD.ExecutionDelegator"
            java.lang.String r6 = r1.f2413b     // Catch:{ all -> 0x014b }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x014b }
            java.lang.String r7 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x014b }
            int r7 = r7.length()     // Catch:{ all -> 0x014b }
            int r7 = r7 + 20
            java.lang.String r8 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x014b }
            int r8 = r8.length()     // Catch:{ all -> 0x014b }
            int r7 = r7 + r8
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x014b }
            r8.<init>(r7)     // Catch:{ all -> 0x014b }
            java.lang.String r7 = "Failed to bind to "
            r8.append(r7)     // Catch:{ all -> 0x014b }
            r8.append(r6)     // Catch:{ all -> 0x014b }
            java.lang.String r6 = ": "
            r8.append(r6)     // Catch:{ all -> 0x014b }
            r8.append(r4)     // Catch:{ all -> 0x014b }
            java.lang.String r4 = r8.toString()     // Catch:{ all -> 0x014b }
            android.util.Log.e(r5, r4)     // Catch:{ all -> 0x014b }
        L_0x00c3:
            java.lang.String r4 = "FJD.ExecutionDelegator"
            java.lang.String r5 = "Unable to bind to "
            java.lang.String r6 = r1.f2413b     // Catch:{ all -> 0x014b }
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x014b }
            int r7 = r6.length()     // Catch:{ all -> 0x014b }
            if (r7 != 0) goto L_0x00d9
            java.lang.String r6 = new java.lang.String     // Catch:{ all -> 0x014b }
            r6.<init>(r5)     // Catch:{ all -> 0x014b }
            goto L_0x00dd
        L_0x00d9:
            java.lang.String r6 = r5.concat(r6)     // Catch:{ all -> 0x014b }
        L_0x00dd:
            android.util.Log.e(r4, r6)     // Catch:{ all -> 0x014b }
            java.util.Map r4 = p000.bhd.f2361a     // Catch:{ all -> 0x014b }
            java.lang.String r5 = r1.f2413b     // Catch:{ all -> 0x014b }
            r4.remove(r5)     // Catch:{ all -> 0x014b }
            r3.mo2078b()     // Catch:{ all -> 0x014b }
            java.lang.String r3 = r1.f2413b     // Catch:{ all -> 0x014b }
            android.content.Context r4 = r0.f2364d     // Catch:{ NameNotFoundException -> 0x011f }
            android.content.pm.PackageManager r4 = r4.getPackageManager()     // Catch:{ NameNotFoundException -> 0x011f }
            android.content.ComponentName r5 = new android.content.ComponentName     // Catch:{ NameNotFoundException -> 0x011f }
            android.content.Context r6 = r0.f2364d     // Catch:{ NameNotFoundException -> 0x011f }
            r5.<init>(r6, r3)     // Catch:{ NameNotFoundException -> 0x011f }
            r3 = 0
            android.content.pm.ServiceInfo r3 = r4.getServiceInfo(r5, r3)     // Catch:{ NameNotFoundException -> 0x011f }
            java.lang.String r4 = java.lang.String.valueOf(r3)     // Catch:{ NameNotFoundException -> 0x011f }
            java.lang.String r5 = java.lang.String.valueOf(r4)     // Catch:{ NameNotFoundException -> 0x011f }
            int r5 = r5.length()     // Catch:{ NameNotFoundException -> 0x011f }
            int r5 = r5 + 14
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ NameNotFoundException -> 0x011f }
            r6.<init>(r5)     // Catch:{ NameNotFoundException -> 0x011f }
            java.lang.String r5 = "serviceInfo = "
            r6.append(r5)     // Catch:{ NameNotFoundException -> 0x011f }
            r6.append(r4)     // Catch:{ NameNotFoundException -> 0x011f }
            r6.toString()     // Catch:{ NameNotFoundException -> 0x011f }
            if (r3 != 0) goto L_0x0144
            goto L_0x0120
        L_0x011f:
            r3 = move-exception
        L_0x0120:
            java.lang.String r3 = "FJD.ExecutionDelegator"
            java.lang.String r4 = "Canceling job for removed service: "
            java.lang.String r5 = r1.f2412a     // Catch:{ all -> 0x014b }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x014b }
            int r6 = r5.length()     // Catch:{ all -> 0x014b }
            if (r6 != 0) goto L_0x0136
            java.lang.String r5 = new java.lang.String     // Catch:{ all -> 0x014b }
            r5.<init>(r4)     // Catch:{ all -> 0x014b }
            goto L_0x013a
        L_0x0136:
            java.lang.String r5 = r4.concat(r5)     // Catch:{ all -> 0x014b }
        L_0x013a:
            android.util.Log.w(r3, r5)     // Catch:{ all -> 0x014b }
            bgy r0 = r0.f2365e     // Catch:{ all -> 0x014b }
            java.lang.String r1 = r1.f2412a     // Catch:{ all -> 0x014b }
            r0.mo2030a((java.lang.String) r1)     // Catch:{ all -> 0x014b }
        L_0x0144:
            monitor-exit(r2)     // Catch:{ all -> 0x014b }
            return
        L_0x0146:
            r3.mo2079b(r1)     // Catch:{ all -> 0x014b }
            monitor-exit(r2)     // Catch:{ all -> 0x014b }
            return
        L_0x014b:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x014b }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.bha.run():void");
    }
}
