package p000;

import android.content.Context;
import android.net.Uri;

/* renamed from: azb */
/* compiled from: PG */
final class azb implements ari {

    /* renamed from: a */
    private static final String[] f1882a = {"_data"};

    /* renamed from: b */
    private final Context f1883b;

    /* renamed from: c */
    private final axo f1884c;

    /* renamed from: d */
    private final axo f1885d;

    /* renamed from: e */
    private final Uri f1886e;

    /* renamed from: f */
    private final int f1887f;

    /* renamed from: g */
    private final int f1888g;

    /* renamed from: h */
    private final aqz f1889h;

    /* renamed from: i */
    private final Class f1890i;

    /* renamed from: j */
    private volatile boolean f1891j;

    /* renamed from: k */
    private volatile ari f1892k;

    /* renamed from: a */
    public final Class mo1510a() {
        return this.f1890i;
    }

    /* renamed from: d */
    public final int mo1518d() {
        return 1;
    }

    public azb(Context context, axo axo, axo axo2, Uri uri, int i, int i2, aqz aqz, Class cls) {
        this.f1883b = context.getApplicationContext();
        this.f1884c = axo;
        this.f1885d = axo2;
        this.f1886e = uri;
        this.f1887f = i;
        this.f1888g = i2;
        this.f1889h = aqz;
        this.f1890i = cls;
    }

    /* renamed from: c */
    public final void mo1517c() {
        this.f1891j = true;
        ari ari = this.f1892k;
        if (ari != null) {
            ari.mo1517c();
        }
    }

    /* renamed from: b */
    public final void mo1516b() {
        ari ari = this.f1892k;
        if (ari != null) {
            ari.mo1516b();
        }
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [ari] */
    /* JADX WARNING: type inference failed for: r1v7, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v12 */
    /* JADX WARNING: type inference failed for: r1v17 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0099 A[SYNTHETIC, Splitter:B:24:0x0099] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo1514a(p000.apb r10, p000.arh r11) {
        /*
            r9 = this;
            boolean r0 = android.os.Environment.isExternalStorageLegacy()     // Catch:{ FileNotFoundException -> 0x00fc }
            r1 = 0
            if (r0 == 0) goto L_0x009d
            axo r0 = r9.f1884c     // Catch:{ FileNotFoundException -> 0x00fc }
            android.net.Uri r8 = r9.f1886e     // Catch:{ FileNotFoundException -> 0x00fc }
            android.content.Context r2 = r9.f1883b     // Catch:{ all -> 0x0095 }
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch:{ all -> 0x0095 }
            java.lang.String[] r4 = f1882a     // Catch:{ all -> 0x0095 }
            r5 = 0
            r6 = 0
            r7 = 0
            r3 = r8
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0095 }
            if (r2 == 0) goto L_0x006c
            boolean r3 = r2.moveToFirst()     // Catch:{ all -> 0x0091 }
            if (r3 == 0) goto L_0x006c
            java.lang.String r3 = "_data"
            int r3 = r2.getColumnIndexOrThrow(r3)     // Catch:{ all -> 0x0091 }
            java.lang.String r3 = r2.getString(r3)     // Catch:{ all -> 0x0091 }
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x0091 }
            if (r4 != 0) goto L_0x0047
            java.io.File r4 = new java.io.File     // Catch:{ all -> 0x0091 }
            r4.<init>(r3)     // Catch:{ all -> 0x0091 }
            r2.close()     // Catch:{ FileNotFoundException -> 0x00fc }
            int r2 = r9.f1887f     // Catch:{ FileNotFoundException -> 0x00fc }
            int r3 = r9.f1888g     // Catch:{ FileNotFoundException -> 0x00fc }
            aqz r5 = r9.f1889h     // Catch:{ FileNotFoundException -> 0x00fc }
            axn r0 = r0.mo1697a(r4, r2, r3, r5)     // Catch:{ FileNotFoundException -> 0x00fc }
            goto L_0x00bc
        L_0x0047:
            java.io.FileNotFoundException r10 = new java.io.FileNotFoundException     // Catch:{ all -> 0x0091 }
            java.lang.String r0 = java.lang.String.valueOf(r8)     // Catch:{ all -> 0x0091 }
            java.lang.String r1 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0091 }
            int r1 = r1.length()     // Catch:{ all -> 0x0091 }
            int r1 = r1 + 40
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0091 }
            r3.<init>(r1)     // Catch:{ all -> 0x0091 }
            java.lang.String r1 = "File path was empty in media store for: "
            r3.append(r1)     // Catch:{ all -> 0x0091 }
            r3.append(r0)     // Catch:{ all -> 0x0091 }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x0091 }
            r10.<init>(r0)     // Catch:{ all -> 0x0091 }
            throw r10     // Catch:{ all -> 0x0091 }
        L_0x006c:
            java.io.FileNotFoundException r10 = new java.io.FileNotFoundException     // Catch:{ all -> 0x0091 }
            java.lang.String r0 = java.lang.String.valueOf(r8)     // Catch:{ all -> 0x0091 }
            java.lang.String r1 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0091 }
            int r1 = r1.length()     // Catch:{ all -> 0x0091 }
            int r1 = r1 + 33
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0091 }
            r3.<init>(r1)     // Catch:{ all -> 0x0091 }
            java.lang.String r1 = "Failed to media store entry for: "
            r3.append(r1)     // Catch:{ all -> 0x0091 }
            r3.append(r0)     // Catch:{ all -> 0x0091 }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x0091 }
            r10.<init>(r0)     // Catch:{ all -> 0x0091 }
            throw r10     // Catch:{ all -> 0x0091 }
        L_0x0091:
            r10 = move-exception
            r1 = r2
            goto L_0x0096
        L_0x0095:
            r10 = move-exception
        L_0x0096:
            if (r1 != 0) goto L_0x0099
            goto L_0x009c
        L_0x0099:
            r1.close()     // Catch:{ FileNotFoundException -> 0x00fc }
        L_0x009c:
            throw r10     // Catch:{ FileNotFoundException -> 0x00fc }
        L_0x009d:
            android.content.Context r0 = r9.f1883b     // Catch:{ FileNotFoundException -> 0x00fc }
            java.lang.String r2 = "android.permission.ACCESS_MEDIA_LOCATION"
            int r0 = r0.checkSelfPermission(r2)     // Catch:{ FileNotFoundException -> 0x00fc }
            if (r0 == 0) goto L_0x00aa
            android.net.Uri r0 = r9.f1886e     // Catch:{ FileNotFoundException -> 0x00fc }
            goto L_0x00b0
        L_0x00aa:
            android.net.Uri r0 = r9.f1886e     // Catch:{ FileNotFoundException -> 0x00fc }
            android.net.Uri r0 = android.provider.MediaStore.setRequireOriginal(r0)     // Catch:{ FileNotFoundException -> 0x00fc }
        L_0x00b0:
            axo r2 = r9.f1885d     // Catch:{ FileNotFoundException -> 0x00fc }
            int r3 = r9.f1887f     // Catch:{ FileNotFoundException -> 0x00fc }
            int r4 = r9.f1888g     // Catch:{ FileNotFoundException -> 0x00fc }
            aqz r5 = r9.f1889h     // Catch:{ FileNotFoundException -> 0x00fc }
            axn r0 = r2.mo1697a(r0, r3, r4, r5)     // Catch:{ FileNotFoundException -> 0x00fc }
        L_0x00bc:
            if (r0 == 0) goto L_0x00c1
            ari r1 = r0.f1831c     // Catch:{ FileNotFoundException -> 0x00fc }
            goto L_0x00c2
        L_0x00c1:
        L_0x00c2:
            if (r1 != 0) goto L_0x00ee
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException     // Catch:{ FileNotFoundException -> 0x00fc }
            android.net.Uri r0 = r9.f1886e     // Catch:{ FileNotFoundException -> 0x00fc }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ FileNotFoundException -> 0x00fc }
            java.lang.String r1 = java.lang.String.valueOf(r0)     // Catch:{ FileNotFoundException -> 0x00fc }
            int r1 = r1.length()     // Catch:{ FileNotFoundException -> 0x00fc }
            int r1 = r1 + 29
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x00fc }
            r2.<init>(r1)     // Catch:{ FileNotFoundException -> 0x00fc }
            java.lang.String r1 = "Failed to build fetcher for: "
            r2.append(r1)     // Catch:{ FileNotFoundException -> 0x00fc }
            r2.append(r0)     // Catch:{ FileNotFoundException -> 0x00fc }
            java.lang.String r0 = r2.toString()     // Catch:{ FileNotFoundException -> 0x00fc }
            r10.<init>(r0)     // Catch:{ FileNotFoundException -> 0x00fc }
            r11.mo1524a((java.lang.Exception) r10)     // Catch:{ FileNotFoundException -> 0x00fc }
            return
        L_0x00ee:
            r9.f1892k = r1     // Catch:{ FileNotFoundException -> 0x00fc }
            boolean r0 = r9.f1891j     // Catch:{ FileNotFoundException -> 0x00fc }
            if (r0 != 0) goto L_0x00f8
            r1.mo1514a(r10, r11)     // Catch:{ FileNotFoundException -> 0x00fc }
            return
        L_0x00f8:
            r9.mo1517c()     // Catch:{ FileNotFoundException -> 0x00fc }
            return
        L_0x00fc:
            r10 = move-exception
            r11.mo1524a((java.lang.Exception) r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.azb.mo1514a(apb, arh):void");
    }
}
