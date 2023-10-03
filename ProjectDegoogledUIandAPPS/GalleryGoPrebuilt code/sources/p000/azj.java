package p000;

/* renamed from: azj */
/* compiled from: PG */
public final class azj implements arc {

    /* renamed from: a */
    private static final aqy f1904a = aqy.m1471a("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionQuality", 90);

    /* renamed from: b */
    private static final aqy f1905b = new aqy("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionFormat", (Object) null, aqy.f1468a);

    /* renamed from: c */
    private final aui f1906c;

    /* renamed from: a */
    public final int mo1509a() {
        return 2;
    }

    @Deprecated
    public azj() {
        this.f1906c = null;
    }

    public azj(aui aui) {
        this.f1906c = aui;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0060 A[SYNTHETIC, Splitter:B:30:0x0060] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0069 A[SYNTHETIC, Splitter:B:37:0x0069] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ boolean mo1488a(java.lang.Object r6, java.io.File r7, p000.aqz r8) {
        /*
            r5 = this;
            aua r6 = (p000.aua) r6
            java.lang.Object r6 = r6.mo1605b()
            android.graphics.Bitmap r6 = (android.graphics.Bitmap) r6
            aqy r0 = f1905b
            java.lang.Object r0 = r8.mo1502a((p000.aqy) r0)
            android.graphics.Bitmap$CompressFormat r0 = (android.graphics.Bitmap.CompressFormat) r0
            if (r0 != 0) goto L_0x001d
            boolean r0 = r6.hasAlpha()
            if (r0 == 0) goto L_0x001b
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.PNG
            goto L_0x001d
        L_0x001b:
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.JPEG
        L_0x001d:
            r6.getWidth()
            r6.getHeight()
            p000.bfk.m2412a()     // Catch:{ all -> 0x0073 }
            aqy r1 = f1904a     // Catch:{ all -> 0x0073 }
            java.lang.Object r8 = r8.mo1502a((p000.aqy) r1)     // Catch:{ all -> 0x0073 }
            java.lang.Integer r8 = (java.lang.Integer) r8     // Catch:{ all -> 0x0073 }
            int r8 = r8.intValue()     // Catch:{ all -> 0x0073 }
            r1 = 1
            r2 = 0
            r3 = 0
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0066, all -> 0x005d }
            r4.<init>(r7)     // Catch:{ IOException -> 0x0066, all -> 0x005d }
            aui r7 = r5.f1906c     // Catch:{ IOException -> 0x005a, all -> 0x0056 }
            if (r7 == 0) goto L_0x0044
            arg r2 = new arg     // Catch:{ IOException -> 0x005a, all -> 0x0056 }
            r2.<init>(r4, r7)     // Catch:{ IOException -> 0x005a, all -> 0x0056 }
            goto L_0x0045
        L_0x0044:
            r2 = r4
        L_0x0045:
            r6.compress(r0, r8, r2)     // Catch:{ IOException -> 0x0054, all -> 0x0051 }
            r2.close()     // Catch:{ IOException -> 0x0054, all -> 0x0051 }
            r2.close()     // Catch:{ IOException -> 0x004f }
            goto L_0x0072
        L_0x004f:
            r6 = move-exception
            goto L_0x0072
        L_0x0051:
            r6 = move-exception
            goto L_0x005e
        L_0x0054:
            r6 = move-exception
            goto L_0x0067
        L_0x0056:
            r6 = move-exception
            r2 = r4
            goto L_0x005e
        L_0x005a:
            r6 = move-exception
            r2 = r4
            goto L_0x0067
        L_0x005d:
            r6 = move-exception
        L_0x005e:
            if (r2 == 0) goto L_0x0065
            r2.close()     // Catch:{ IOException -> 0x0064 }
            goto L_0x0065
        L_0x0064:
            r7 = move-exception
        L_0x0065:
            throw r6     // Catch:{ all -> 0x0073 }
        L_0x0066:
            r6 = move-exception
        L_0x0067:
            if (r2 == 0) goto L_0x0070
            r2.close()     // Catch:{ IOException -> 0x006d }
            goto L_0x0071
        L_0x006d:
            r6 = move-exception
            r1 = 0
            goto L_0x0072
        L_0x0070:
        L_0x0071:
            r1 = 0
        L_0x0072:
            return r1
        L_0x0073:
            r6 = move-exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.azj.mo1488a(java.lang.Object, java.io.File, aqz):boolean");
    }
}
