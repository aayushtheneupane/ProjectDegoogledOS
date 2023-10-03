package com.davemorrissey.labs.subscaleview.decoder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.os.Build;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* compiled from: PG */
public class SkiaImageRegionDecoder implements bgs {

    /* renamed from: a */
    private BitmapRegionDecoder f4783a;

    /* renamed from: b */
    private final ReadWriteLock f4784b = new ReentrantReadWriteLock(true);

    /* renamed from: c */
    private final Bitmap.Config f4785c;

    public SkiaImageRegionDecoder() {
        Bitmap.Config config = bgo.f2285H;
        if (config != null) {
            this.f4785c = config;
        } else {
            this.f4785c = Bitmap.Config.RGB_565;
        }
    }

    /* renamed from: a */
    public final Bitmap mo2016a(Rect rect, int i) {
        m4708c().lock();
        try {
            BitmapRegionDecoder bitmapRegionDecoder = this.f4783a;
            if (bitmapRegionDecoder == null || bitmapRegionDecoder.isRecycled()) {
                throw new IllegalStateException("Cannot decode region after decoder has been recycled");
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = i;
            options.inPreferredConfig = this.f4785c;
            Bitmap decodeRegion = this.f4783a.decodeRegion(rect, options);
            if (decodeRegion != null) {
                return decodeRegion;
            }
            throw new RuntimeException("Skia image decoder returned null bitmap - image format may not be supported");
        } finally {
            m4708c().unlock();
        }
    }

    /* renamed from: c */
    private final Lock m4708c() {
        int i = Build.VERSION.SDK_INT;
        return this.f4784b.readLock();
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00df A[SYNTHETIC, Splitter:B:40:0x00df] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.graphics.Point mo2017a(android.content.Context r7, android.net.Uri r8) {
        /*
            r6 = this;
            java.lang.String r0 = r8.toString()
            java.lang.String r1 = "android.resource://"
            boolean r1 = r0.startsWith(r1)
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x007e
            java.lang.String r0 = r8.getAuthority()
            java.lang.String r1 = r7.getPackageName()
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0021
            android.content.res.Resources r1 = r7.getResources()
            goto L_0x0029
        L_0x0021:
            android.content.pm.PackageManager r1 = r7.getPackageManager()
            android.content.res.Resources r1 = r1.getResourcesForApplication(r0)
        L_0x0029:
            java.util.List r8 = r8.getPathSegments()
            int r4 = r8.size()
            r5 = 2
            if (r4 != r5) goto L_0x004e
            java.lang.Object r4 = r8.get(r3)
            java.lang.String r4 = (java.lang.String) r4
            java.lang.String r5 = "drawable"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x004d
            java.lang.Object r8 = r8.get(r2)
            java.lang.String r8 = (java.lang.String) r8
            int r8 = r1.getIdentifier(r8, r5, r0)
            goto L_0x006f
        L_0x004d:
            goto L_0x006e
        L_0x004e:
            if (r4 != r2) goto L_0x006d
            java.lang.Object r0 = r8.get(r3)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            boolean r0 = android.text.TextUtils.isDigitsOnly(r0)
            if (r0 != 0) goto L_0x005e
            goto L_0x006e
        L_0x005e:
            java.lang.Object r8 = r8.get(r3)     // Catch:{ NumberFormatException -> 0x006a }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ NumberFormatException -> 0x006a }
            int r8 = java.lang.Integer.parseInt(r8)     // Catch:{ NumberFormatException -> 0x006a }
            goto L_0x006f
        L_0x006a:
            r8 = move-exception
            r8 = 0
            goto L_0x006f
        L_0x006d:
        L_0x006e:
            r8 = 0
        L_0x006f:
            android.content.res.Resources r7 = r7.getResources()
            java.io.InputStream r7 = r7.openRawResource(r8)
            android.graphics.BitmapRegionDecoder r7 = android.graphics.BitmapRegionDecoder.newInstance(r7, r3)
            r6.f4783a = r7
            goto L_0x00c6
        L_0x007e:
            java.lang.String r1 = "file:///android_asset/"
            boolean r1 = r0.startsWith(r1)
            if (r1 == 0) goto L_0x009c
            r8 = 22
            java.lang.String r8 = r0.substring(r8)
            android.content.res.AssetManager r7 = r7.getAssets()
            java.io.InputStream r7 = r7.open(r8, r2)
            android.graphics.BitmapRegionDecoder r7 = android.graphics.BitmapRegionDecoder.newInstance(r7, r3)
            r6.f4783a = r7
            goto L_0x00c6
        L_0x009c:
            java.lang.String r1 = "file://"
            boolean r1 = r0.startsWith(r1)
            if (r1 == 0) goto L_0x00b1
            r7 = 7
            java.lang.String r7 = r0.substring(r7)
            android.graphics.BitmapRegionDecoder r7 = android.graphics.BitmapRegionDecoder.newInstance(r7, r3)
            r6.f4783a = r7
            goto L_0x00c6
        L_0x00b1:
            android.content.ContentResolver r7 = r7.getContentResolver()     // Catch:{ all -> 0x00da }
            java.io.InputStream r7 = r7.openInputStream(r8)     // Catch:{ all -> 0x00da }
            android.graphics.BitmapRegionDecoder r8 = android.graphics.BitmapRegionDecoder.newInstance(r7, r3)     // Catch:{ all -> 0x00d8 }
            r6.f4783a = r8     // Catch:{ all -> 0x00d8 }
            if (r7 == 0) goto L_0x00c6
            r7.close()     // Catch:{ Exception -> 0x00c5 }
            goto L_0x00c6
        L_0x00c5:
            r7 = move-exception
        L_0x00c6:
            android.graphics.Point r7 = new android.graphics.Point
            android.graphics.BitmapRegionDecoder r8 = r6.f4783a
            int r8 = r8.getWidth()
            android.graphics.BitmapRegionDecoder r0 = r6.f4783a
            int r0 = r0.getHeight()
            r7.<init>(r8, r0)
            return r7
        L_0x00d8:
            r8 = move-exception
            goto L_0x00dd
        L_0x00da:
            r7 = move-exception
            r8 = r7
            r7 = 0
        L_0x00dd:
            if (r7 == 0) goto L_0x00e4
            r7.close()     // Catch:{ Exception -> 0x00e3 }
            goto L_0x00e4
        L_0x00e3:
            r7 = move-exception
        L_0x00e4:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.davemorrissey.labs.subscaleview.decoder.SkiaImageRegionDecoder.mo2017a(android.content.Context, android.net.Uri):android.graphics.Point");
    }

    /* renamed from: a */
    public final synchronized boolean mo2018a() {
        BitmapRegionDecoder bitmapRegionDecoder;
        bitmapRegionDecoder = this.f4783a;
        return bitmapRegionDecoder != null && !bitmapRegionDecoder.isRecycled();
    }

    /* renamed from: b */
    public final synchronized void mo2019b() {
        this.f4784b.writeLock().lock();
        try {
            this.f4783a.recycle();
            this.f4783a = null;
        } finally {
            this.f4784b.writeLock().unlock();
        }
    }
}
