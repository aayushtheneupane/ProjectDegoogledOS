package com.android.p032ex.photo.util;

import android.content.ContentResolver;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import com.android.p032ex.photo.p035b.C0715b;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;

/* renamed from: com.android.ex.photo.util.e */
public class C0745e {
    /* access modifiers changed from: private */

    /* renamed from: Cw */
    public static final Pattern f913Cw = Pattern.compile("^(?:.*;)?base64,.*");

    /* renamed from: Dw */
    public static final ImageUtils$ImageSize f914Dw = ImageUtils$ImageSize.NORMAL;

    static {
        int i = Build.VERSION.SDK_INT;
    }

    /* renamed from: a */
    public static C0715b m1194a(ContentResolver contentResolver, Uri uri, int i) {
        C0744d dVar;
        C0715b bVar = new C0715b();
        if ("data".equals(uri.getScheme())) {
            dVar = new C0743c(contentResolver, uri);
        } else {
            dVar = new C0742b(contentResolver, uri);
        }
        try {
            Point a = m1193a(dVar);
            if (a == null) {
                bVar.status = 1;
                return bVar;
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = Math.max(a.x / i, a.y / i);
            bVar.bitmap = m1192a(dVar, (Rect) null, options);
            bVar.status = 0;
            return bVar;
        } catch (FileNotFoundException | IllegalArgumentException unused) {
            return bVar;
        } catch (IOException unused2) {
            bVar.status = 1;
            return bVar;
        } catch (SecurityException unused3) {
            bVar.status = 1;
            return bVar;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x0076 A[SYNTHETIC, Splitter:B:47:0x0076] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0084 A[SYNTHETIC, Splitter:B:57:0x0084] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x008b A[SYNTHETIC, Splitter:B:63:0x008b] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap m1192a(com.android.p032ex.photo.util.C0744d r11, android.graphics.Rect r12, android.graphics.BitmapFactory.Options r13) {
        /*
            java.lang.String r0 = "ImageUtils"
            r1 = 0
            java.io.InputStream r2 = r11.createInputStream()     // Catch:{ OutOfMemoryError -> 0x007a, IOException -> 0x006c, all -> 0x0068 }
            r3 = -1
            int r3 = com.android.p032ex.photo.util.C0741a.m1190a(r2, r3)     // Catch:{ OutOfMemoryError -> 0x0064, IOException -> 0x0060, all -> 0x005c }
            if (r2 == 0) goto L_0x0012
            r2.close()     // Catch:{ OutOfMemoryError -> 0x0064, IOException -> 0x0060, all -> 0x005c }
        L_0x0012:
            java.io.InputStream r11 = r11.createInputStream()     // Catch:{ OutOfMemoryError -> 0x0064, IOException -> 0x0060, all -> 0x005c }
            android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeStream(r11, r12, r13)     // Catch:{ OutOfMemoryError -> 0x005a, IOException -> 0x0058 }
            if (r11 == 0) goto L_0x0030
            if (r4 != 0) goto L_0x0030
            boolean r12 = r13.inJustDecodeBounds     // Catch:{ OutOfMemoryError -> 0x005a, IOException -> 0x0058 }
            if (r12 == 0) goto L_0x0023
            goto L_0x0030
        L_0x0023:
            java.lang.String r12 = "ImageUtils#decodeStream(InputStream, Rect, Options): Image bytes cannot be decoded into a Bitmap"
            android.util.Log.w(r0, r12)     // Catch:{ OutOfMemoryError -> 0x005a, IOException -> 0x0058 }
            java.lang.UnsupportedOperationException r12 = new java.lang.UnsupportedOperationException     // Catch:{ OutOfMemoryError -> 0x005a, IOException -> 0x0058 }
            java.lang.String r13 = "Image bytes cannot be decoded into a Bitmap."
            r12.<init>(r13)     // Catch:{ OutOfMemoryError -> 0x005a, IOException -> 0x0058 }
            throw r12     // Catch:{ OutOfMemoryError -> 0x005a, IOException -> 0x0058 }
        L_0x0030:
            if (r4 == 0) goto L_0x0052
            if (r3 == 0) goto L_0x0052
            android.graphics.Matrix r9 = new android.graphics.Matrix     // Catch:{ OutOfMemoryError -> 0x005a, IOException -> 0x0058 }
            r9.<init>()     // Catch:{ OutOfMemoryError -> 0x005a, IOException -> 0x0058 }
            float r12 = (float) r3     // Catch:{ OutOfMemoryError -> 0x005a, IOException -> 0x0058 }
            r9.postRotate(r12)     // Catch:{ OutOfMemoryError -> 0x005a, IOException -> 0x0058 }
            r5 = 0
            r6 = 0
            int r7 = r4.getWidth()     // Catch:{ OutOfMemoryError -> 0x005a, IOException -> 0x0058 }
            int r8 = r4.getHeight()     // Catch:{ OutOfMemoryError -> 0x005a, IOException -> 0x0058 }
            r10 = 1
            android.graphics.Bitmap r12 = android.graphics.Bitmap.createBitmap(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ OutOfMemoryError -> 0x005a, IOException -> 0x0058 }
            if (r11 == 0) goto L_0x0051
            r11.close()     // Catch:{ IOException -> 0x0051 }
        L_0x0051:
            return r12
        L_0x0052:
            if (r11 == 0) goto L_0x0057
            r11.close()     // Catch:{ IOException -> 0x0057 }
        L_0x0057:
            return r4
        L_0x0058:
            r12 = move-exception
            goto L_0x006f
        L_0x005a:
            r12 = move-exception
            goto L_0x007d
        L_0x005c:
            r11 = move-exception
            r12 = r11
            r11 = r2
            goto L_0x0089
        L_0x0060:
            r11 = move-exception
            r12 = r11
            r11 = r2
            goto L_0x006f
        L_0x0064:
            r11 = move-exception
            r12 = r11
            r11 = r2
            goto L_0x007d
        L_0x0068:
            r11 = move-exception
            r12 = r11
            r11 = r1
            goto L_0x0089
        L_0x006c:
            r11 = move-exception
            r12 = r11
            r11 = r1
        L_0x006f:
            java.lang.String r13 = "ImageUtils#decodeStream(InputStream, Rect, Options) threw an IOE"
            android.util.Log.e(r0, r13, r12)     // Catch:{ all -> 0x0088 }
            if (r11 == 0) goto L_0x0079
            r11.close()     // Catch:{ IOException -> 0x0079 }
        L_0x0079:
            return r1
        L_0x007a:
            r11 = move-exception
            r12 = r11
            r11 = r1
        L_0x007d:
            java.lang.String r13 = "ImageUtils#decodeStream(InputStream, Rect, Options) threw an OOME"
            android.util.Log.e(r0, r13, r12)     // Catch:{ all -> 0x0088 }
            if (r11 == 0) goto L_0x0087
            r11.close()     // Catch:{ IOException -> 0x0087 }
        L_0x0087:
            return r1
        L_0x0088:
            r12 = move-exception
        L_0x0089:
            if (r11 == 0) goto L_0x008e
            r11.close()     // Catch:{ IOException -> 0x008e }
        L_0x008e:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.p032ex.photo.util.C0745e.m1192a(com.android.ex.photo.util.d, android.graphics.Rect, android.graphics.BitmapFactory$Options):android.graphics.Bitmap");
    }

    /* renamed from: a */
    private static Point m1193a(C0744d dVar) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        m1192a(dVar, (Rect) null, options);
        return new Point(options.outWidth, options.outHeight);
    }
}
