package com.davemorrissey.labs.subscaleview.decoder;

import android.graphics.Bitmap;

/* compiled from: PG */
public class SkiaImageDecoder implements bgr {

    /* renamed from: a */
    private final Bitmap.Config f4782a;

    public SkiaImageDecoder() {
        Bitmap.Config config = bgo.f2285H;
        if (config != null) {
            this.f4782a = config;
        } else {
            this.f4782a = Bitmap.Config.RGB_565;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00d8 A[SYNTHETIC, Splitter:B:42:0x00d8] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.graphics.Bitmap mo2015a(android.content.Context r8, android.net.Uri r9) {
        /*
            r7 = this;
            java.lang.String r0 = r9.toString()
            android.graphics.BitmapFactory$Options r1 = new android.graphics.BitmapFactory$Options
            r1.<init>()
            android.graphics.Bitmap$Config r2 = r7.f4782a
            r1.inPreferredConfig = r2
            java.lang.String r2 = "android.resource://"
            boolean r2 = r0.startsWith(r2)
            if (r2 == 0) goto L_0x0080
            java.lang.String r0 = r9.getAuthority()
            java.lang.String r2 = r8.getPackageName()
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0028
            android.content.res.Resources r2 = r8.getResources()
            goto L_0x0030
        L_0x0028:
            android.content.pm.PackageManager r2 = r8.getPackageManager()
            android.content.res.Resources r2 = r2.getResourcesForApplication(r0)
        L_0x0030:
            java.util.List r9 = r9.getPathSegments()
            int r3 = r9.size()
            r4 = 2
            r5 = 1
            r6 = 0
            if (r3 != r4) goto L_0x0057
            java.lang.Object r3 = r9.get(r6)
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r4 = "drawable"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0056
            java.lang.Object r9 = r9.get(r5)
            java.lang.String r9 = (java.lang.String) r9
            int r6 = r2.getIdentifier(r9, r4, r0)
            goto L_0x0077
        L_0x0056:
            goto L_0x0076
        L_0x0057:
            if (r3 != r5) goto L_0x0075
            java.lang.Object r0 = r9.get(r6)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            boolean r0 = android.text.TextUtils.isDigitsOnly(r0)
            if (r0 != 0) goto L_0x0067
            goto L_0x0076
        L_0x0067:
            java.lang.Object r9 = r9.get(r6)     // Catch:{ NumberFormatException -> 0x0073 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ NumberFormatException -> 0x0073 }
            int r6 = java.lang.Integer.parseInt(r9)     // Catch:{ NumberFormatException -> 0x0073 }
            goto L_0x0077
        L_0x0073:
            r9 = move-exception
            goto L_0x0077
        L_0x0075:
        L_0x0076:
        L_0x0077:
            android.content.res.Resources r8 = r8.getResources()
            android.graphics.Bitmap r8 = android.graphics.BitmapFactory.decodeResource(r8, r6, r1)
            goto L_0x00c6
        L_0x0080:
            java.lang.String r2 = "file:///android_asset/"
            boolean r2 = r0.startsWith(r2)
            r3 = 0
            if (r2 == 0) goto L_0x009d
            r9 = 22
            java.lang.String r9 = r0.substring(r9)
            android.content.res.AssetManager r8 = r8.getAssets()
            java.io.InputStream r8 = r8.open(r9)
            android.graphics.Bitmap r8 = android.graphics.BitmapFactory.decodeStream(r8, r3, r1)
            goto L_0x00c6
        L_0x009d:
            java.lang.String r2 = "file://"
            boolean r2 = r0.startsWith(r2)
            if (r2 == 0) goto L_0x00b0
            r8 = 7
            java.lang.String r8 = r0.substring(r8)
            android.graphics.Bitmap r8 = android.graphics.BitmapFactory.decodeFile(r8, r1)
            goto L_0x00c6
        L_0x00b0:
            android.content.ContentResolver r8 = r8.getContentResolver()     // Catch:{ all -> 0x00d4 }
            java.io.InputStream r8 = r8.openInputStream(r9)     // Catch:{ all -> 0x00d4 }
            android.graphics.Bitmap r9 = android.graphics.BitmapFactory.decodeStream(r8, r3, r1)     // Catch:{ all -> 0x00d1 }
            if (r8 != 0) goto L_0x00bf
        L_0x00be:
            goto L_0x00c5
        L_0x00bf:
            r8.close()     // Catch:{ Exception -> 0x00c3 }
            goto L_0x00be
        L_0x00c3:
            r8 = move-exception
        L_0x00c5:
            r8 = r9
        L_0x00c6:
            if (r8 == 0) goto L_0x00c9
            return r8
        L_0x00c9:
            java.lang.RuntimeException r8 = new java.lang.RuntimeException
            java.lang.String r9 = "Skia image region decoder returned null bitmap - image format may not be supported"
            r8.<init>(r9)
            throw r8
        L_0x00d1:
            r9 = move-exception
            r3 = r8
            goto L_0x00d6
        L_0x00d4:
            r8 = move-exception
            r9 = r8
        L_0x00d6:
            if (r3 == 0) goto L_0x00dd
            r3.close()     // Catch:{ Exception -> 0x00dc }
            goto L_0x00dd
        L_0x00dc:
            r8 = move-exception
        L_0x00dd:
            goto L_0x00df
        L_0x00de:
            throw r9
        L_0x00df:
            goto L_0x00de
        */
        throw new UnsupportedOperationException("Method not decompiled: com.davemorrissey.labs.subscaleview.decoder.SkiaImageDecoder.mo2015a(android.content.Context, android.net.Uri):android.graphics.Bitmap");
    }
}
