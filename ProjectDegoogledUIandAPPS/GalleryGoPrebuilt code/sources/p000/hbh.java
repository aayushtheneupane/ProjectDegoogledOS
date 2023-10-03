package p000;

/* renamed from: hbh */
/* compiled from: PG */
final /* synthetic */ class hbh implements ice {

    /* renamed from: a */
    private final hbk f12444a;

    /* renamed from: b */
    private final boolean f12445b;

    public hbh(hbk hbk, boolean z) {
        this.f12444a = hbk;
        this.f12445b = z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0062 A[SYNTHETIC, Splitter:B:20:0x0062] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x007a A[Catch:{ all -> 0x0080, IOException -> 0x0095 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.ieh mo2539a() {
        /*
            r8 = this;
            hbk r0 = r8.f12444a
            boolean r1 = r8.f12445b
            r2 = 0
            iqk r3 = r0.f12455e     // Catch:{ IOException -> 0x0095 }
            java.lang.Object r3 = r3.mo2097a()     // Catch:{ IOException -> 0x0095 }
            android.content.pm.PackageInfo r3 = (android.content.pm.PackageInfo) r3     // Catch:{ IOException -> 0x0095 }
            int r4 = r3.versionCode     // Catch:{ IOException -> 0x0095 }
            android.content.pm.ApplicationInfo r5 = r3.applicationInfo     // Catch:{ IOException -> 0x0095 }
            java.lang.String r5 = r5.dataDir     // Catch:{ IOException -> 0x0095 }
            if (r5 == 0) goto L_0x008d
            java.io.File r5 = new java.io.File     // Catch:{ IOException -> 0x0095 }
            android.content.pm.ApplicationInfo r3 = r3.applicationInfo     // Catch:{ IOException -> 0x0095 }
            java.lang.String r3 = r3.dataDir     // Catch:{ IOException -> 0x0095 }
            java.lang.String r6 = "files"
            r5.<init>(r3, r6)     // Catch:{ IOException -> 0x0095 }
            java.io.File r3 = new java.io.File     // Catch:{ IOException -> 0x0095 }
            java.lang.String r6 = "tiktok"
            r3.<init>(r5, r6)     // Catch:{ IOException -> 0x0095 }
            r3.mkdirs()     // Catch:{ IOException -> 0x0095 }
            java.io.File r5 = new java.io.File     // Catch:{ IOException -> 0x0095 }
            java.lang.String r6 = "103243289"
            r5.<init>(r3, r6)     // Catch:{ IOException -> 0x0095 }
            boolean r3 = r5.createNewFile()     // Catch:{ IOException -> 0x0095 }
            boolean r6 = r5.exists()     // Catch:{ IOException -> 0x0095 }
            if (r6 == 0) goto L_0x0085
            boolean r6 = r5.isFile()     // Catch:{ IOException -> 0x0095 }
            if (r6 == 0) goto L_0x0085
            java.io.RandomAccessFile r6 = new java.io.RandomAccessFile     // Catch:{ IOException -> 0x0095 }
            java.lang.String r7 = "rw"
            r6.<init>(r5, r7)     // Catch:{ IOException -> 0x0095 }
            if (r3 != 0) goto L_0x0057
            int r3 = p000.hbk.m11136a((java.io.RandomAccessFile) r6)     // Catch:{ all -> 0x0080 }
            if (r4 == r3) goto L_0x0051
            goto L_0x0057
        L_0x0051:
            r6.close()     // Catch:{ IOException -> 0x0095 }
            r3 = r2
            goto L_0x0060
        L_0x0057:
            r3 = -1
            p000.hbk.m11137a(r6, r3)     // Catch:{ all -> 0x0080 }
            hbi r3 = new hbi     // Catch:{ all -> 0x0080 }
            r3.<init>(r0, r6, r4)     // Catch:{ all -> 0x0080 }
        L_0x0060:
            if (r3 == 0) goto L_0x007a
            inw r4 = r0.f12454d     // Catch:{ IOException -> 0x0095 }
            java.lang.Object r4 = r4.mo9034a()     // Catch:{ IOException -> 0x0095 }
            goo r4 = (p000.goo) r4     // Catch:{ IOException -> 0x0095 }
            iek r5 = r0.f12453c     // Catch:{ IOException -> 0x0095 }
            java.util.concurrent.Callable r3 = p000.hmq.m11749a((java.util.concurrent.Callable) r3)     // Catch:{ IOException -> 0x0095 }
            ieh r3 = r5.mo5933a((java.util.concurrent.Callable) r3)     // Catch:{ IOException -> 0x0095 }
            ieh r0 = r4.mo6881a(r3)     // Catch:{ IOException -> 0x0095 }
            goto L_0x00ef
        L_0x007a:
            ieh r0 = p000.ife.m12820a((java.lang.Object) r2)     // Catch:{ IOException -> 0x0095 }
            goto L_0x00ef
        L_0x0080:
            r3 = move-exception
            r6.close()     // Catch:{ IOException -> 0x0095 }
            throw r3     // Catch:{ IOException -> 0x0095 }
        L_0x0085:
            java.io.IOException r3 = new java.io.IOException     // Catch:{ IOException -> 0x0095 }
            java.lang.String r4 = "Something went wrong creating file to store package version. Will not run package replaced listeners. Will try again on next startup."
            r3.<init>(r4)     // Catch:{ IOException -> 0x0095 }
            throw r3     // Catch:{ IOException -> 0x0095 }
        L_0x008d:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException     // Catch:{ IOException -> 0x0095 }
            java.lang.String r4 = "PackageInfo was invalid."
            r3.<init>(r4)     // Catch:{ IOException -> 0x0095 }
            throw r3     // Catch:{ IOException -> 0x0095 }
        L_0x0095:
            r3 = move-exception
            java.lang.String r4 = "StartupAfterPkgReplaced"
            if (r1 != 0) goto L_0x009b
            goto L_0x00e5
        L_0x009b:
            int r1 = android.os.Build.VERSION.SDK_INT
            android.content.Context r1 = r0.f12451a
            boolean r1 = p000.exp.m8338b(r1)
            r5 = 0
            if (r1 != 0) goto L_0x00e1
            java.lang.String r1 = "StartupAfterPackageReplaced failed, device was locked. Will reschedule."
            android.util.Log.w(r4, r1, r3)
            android.content.IntentFilter r1 = new android.content.IntentFilter
            r1.<init>()
            java.lang.String r3 = "android.intent.action.USER_UNLOCKED"
            r1.addAction(r3)
            java.util.concurrent.atomic.AtomicBoolean r3 = new java.util.concurrent.atomic.AtomicBoolean
            r3.<init>()
            hbj r4 = new hbj
            r4.<init>(r0, r3)
            android.content.Context r6 = r0.f12451a
            r6.registerReceiver(r4, r1)
            android.content.Context r1 = r0.f12451a
            boolean r1 = p000.exp.m8338b(r1)
            if (r1 == 0) goto L_0x00db
            r1 = 1
            boolean r1 = r3.compareAndSet(r5, r1)
            if (r1 == 0) goto L_0x00db
            android.content.Context r1 = r0.f12451a
            r1.unregisterReceiver(r4)
            r0.mo7256a((boolean) r5)
        L_0x00db:
            ieh r0 = p000.ife.m12820a((java.lang.Object) r2)
            goto L_0x00ef
        L_0x00e1:
            r0.mo7256a((boolean) r5)
        L_0x00e5:
            java.lang.String r0 = "StartupAfterPackageReplaced failed, will try again next startup: "
            android.util.Log.e(r4, r0, r3)
            ieh r0 = p000.ife.m12820a((java.lang.Object) r2)
        L_0x00ef:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.hbh.mo2539a():ieh");
    }
}
