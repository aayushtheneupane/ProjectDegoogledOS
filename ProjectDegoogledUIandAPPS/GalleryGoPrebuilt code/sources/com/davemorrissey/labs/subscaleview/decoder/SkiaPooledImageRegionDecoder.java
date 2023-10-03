package com.davemorrissey.labs.subscaleview.decoder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.support.p002v7.widget.RecyclerView;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* compiled from: PG */
public class SkiaPooledImageRegionDecoder implements bgs {

    /* renamed from: d */
    public static /* synthetic */ int f4786d;

    /* renamed from: a */
    public bgu f4787a = new bgu((byte[]) null);

    /* renamed from: b */
    public Context f4788b;

    /* renamed from: c */
    public long f4789c = RecyclerView.FOREVER_NS;

    /* renamed from: e */
    private final ReadWriteLock f4790e = new ReentrantReadWriteLock(true);

    /* renamed from: f */
    private final Bitmap.Config f4791f;

    /* renamed from: g */
    private Uri f4792g;

    /* renamed from: h */
    private final Point f4793h = new Point(0, 0);

    /* renamed from: i */
    private final AtomicBoolean f4794i = new AtomicBoolean(false);

    static {
        SkiaPooledImageRegionDecoder.class.getSimpleName();
    }

    public static void setDebug(boolean z) {
    }

    public SkiaPooledImageRegionDecoder() {
        Bitmap.Config config = bgo.f2285H;
        if (config != null) {
            this.f4791f = config;
        } else {
            this.f4791f = Bitmap.Config.RGB_565;
        }
    }

    /* renamed from: a */
    public final Bitmap mo2016a(Rect rect, int i) {
        BitmapRegionDecoder c;
        String valueOf = String.valueOf(rect);
        String name = Thread.currentThread().getName();
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 25 + String.valueOf(name).length());
        sb.append("Decode region ");
        sb.append(valueOf);
        sb.append(" on thread ");
        sb.append(name);
        sb.toString();
        if ((rect.width() < this.f4793h.x || rect.height() < this.f4793h.y) && this.f4794i.compareAndSet(false, true) && this.f4789c < RecyclerView.FOREVER_NS) {
            new bgt(this).start();
        }
        this.f4790e.readLock().lock();
        try {
            bgu bgu = this.f4787a;
            if (bgu != null) {
                c = bgu.mo2025c();
                if (c != null) {
                    if (!c.isRecycled()) {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = i;
                        options.inPreferredConfig = this.f4791f;
                        Bitmap decodeRegion = c.decodeRegion(rect, options);
                        if (decodeRegion != null) {
                            this.f4787a.mo2021a(c);
                            this.f4790e.readLock().unlock();
                            return decodeRegion;
                        }
                        throw new RuntimeException("Skia image decoder returned null bitmap - image format may not be supported");
                    }
                }
                if (c != null) {
                    this.f4787a.mo2021a(c);
                }
            }
            throw new IllegalStateException("Cannot decode region after decoder has been recycled");
        } catch (Throwable th) {
            this.f4790e.readLock().unlock();
            throw th;
        }
    }

    /* renamed from: d */
    public final int mo3309d() {
        int i = Build.VERSION.SDK_INT;
        return Runtime.getRuntime().availableProcessors();
    }

    /* renamed from: a */
    public final Point mo2017a(Context context, Uri uri) {
        this.f4788b = context;
        this.f4792g = uri;
        mo3308c();
        return this.f4793h;
    }

    /* JADX WARNING: Removed duplicated region for block: B:71:0x0166 A[SYNTHETIC, Splitter:B:71:0x0166] */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo3308c() {
        /*
            r9 = this;
            android.net.Uri r0 = r9.f4792g
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "android.resource://"
            boolean r1 = r0.startsWith(r1)
            r2 = 1
            r3 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r5 = 0
            if (r1 == 0) goto L_0x00a2
            android.net.Uri r0 = r9.f4792g
            java.lang.String r0 = r0.getAuthority()
            android.content.Context r1 = r9.f4788b
            java.lang.String r1 = r1.getPackageName()
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x002e
            android.content.Context r1 = r9.f4788b
            android.content.res.Resources r1 = r1.getResources()
            goto L_0x0038
        L_0x002e:
            android.content.Context r1 = r9.f4788b
            android.content.pm.PackageManager r1 = r1.getPackageManager()
            android.content.res.Resources r1 = r1.getResourcesForApplication(r0)
        L_0x0038:
            android.net.Uri r6 = r9.f4792g
            java.util.List r6 = r6.getPathSegments()
            int r7 = r6.size()
            r8 = 2
            if (r7 != r8) goto L_0x005f
            java.lang.Object r7 = r6.get(r5)
            java.lang.String r7 = (java.lang.String) r7
            java.lang.String r8 = "drawable"
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x005e
            java.lang.Object r2 = r6.get(r2)
            java.lang.String r2 = (java.lang.String) r2
            int r0 = r1.getIdentifier(r2, r8, r0)
            goto L_0x0080
        L_0x005e:
            goto L_0x007f
        L_0x005f:
            if (r7 != r2) goto L_0x007e
            java.lang.Object r0 = r6.get(r5)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            boolean r0 = android.text.TextUtils.isDigitsOnly(r0)
            if (r0 != 0) goto L_0x006f
            goto L_0x007f
        L_0x006f:
            java.lang.Object r0 = r6.get(r5)     // Catch:{ NumberFormatException -> 0x007b }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ NumberFormatException -> 0x007b }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x007b }
            goto L_0x0080
        L_0x007b:
            r0 = move-exception
            r0 = 0
            goto L_0x0080
        L_0x007e:
        L_0x007f:
            r0 = 0
        L_0x0080:
            android.content.Context r1 = r9.f4788b     // Catch:{ Exception -> 0x0090 }
            android.content.res.Resources r1 = r1.getResources()     // Catch:{ Exception -> 0x0090 }
            android.content.res.AssetFileDescriptor r1 = r1.openRawResourceFd(r0)     // Catch:{ Exception -> 0x0090 }
            long r1 = r1.getLength()     // Catch:{ Exception -> 0x0090 }
            r3 = r1
            goto L_0x0092
        L_0x0090:
            r1 = move-exception
        L_0x0092:
            android.content.Context r1 = r9.f4788b
            android.content.res.Resources r1 = r1.getResources()
            java.io.InputStream r0 = r1.openRawResource(r0)
            android.graphics.BitmapRegionDecoder r0 = android.graphics.BitmapRegionDecoder.newInstance(r0, r5)
            goto L_0x012a
        L_0x00a2:
            java.lang.String r1 = "file:///android_asset/"
            boolean r1 = r0.startsWith(r1)
            if (r1 == 0) goto L_0x00d2
            r1 = 22
            java.lang.String r0 = r0.substring(r1)
            android.content.Context r1 = r9.f4788b     // Catch:{ Exception -> 0x00c0 }
            android.content.res.AssetManager r1 = r1.getAssets()     // Catch:{ Exception -> 0x00c0 }
            android.content.res.AssetFileDescriptor r1 = r1.openFd(r0)     // Catch:{ Exception -> 0x00c0 }
            long r3 = r1.getLength()     // Catch:{ Exception -> 0x00c0 }
            goto L_0x00c2
        L_0x00c0:
            r1 = move-exception
        L_0x00c2:
            android.content.Context r1 = r9.f4788b
            android.content.res.AssetManager r1 = r1.getAssets()
            java.io.InputStream r0 = r1.open(r0, r2)
            android.graphics.BitmapRegionDecoder r0 = android.graphics.BitmapRegionDecoder.newInstance(r0, r5)
            goto L_0x012a
        L_0x00d2:
            java.lang.String r1 = "file://"
            boolean r1 = r0.startsWith(r1)
            if (r1 == 0) goto L_0x00fc
            r1 = 7
            java.lang.String r1 = r0.substring(r1)
            android.graphics.BitmapRegionDecoder r1 = android.graphics.BitmapRegionDecoder.newInstance(r1, r5)
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x00f9 }
            r2.<init>(r0)     // Catch:{ Exception -> 0x00f9 }
            boolean r0 = r2.exists()     // Catch:{ Exception -> 0x00f9 }
            if (r0 == 0) goto L_0x00f5
            long r2 = r2.length()     // Catch:{ Exception -> 0x00f9 }
            r3 = r2
            goto L_0x00f6
        L_0x00f5:
        L_0x00f6:
            r0 = r1
            goto L_0x012a
        L_0x00f9:
            r0 = move-exception
            r0 = r1
            goto L_0x012a
        L_0x00fc:
            android.content.Context r0 = r9.f4788b     // Catch:{ all -> 0x0162 }
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch:{ all -> 0x0162 }
            android.net.Uri r1 = r9.f4792g     // Catch:{ all -> 0x0162 }
            java.io.InputStream r1 = r0.openInputStream(r1)     // Catch:{ all -> 0x0162 }
            android.graphics.BitmapRegionDecoder r2 = android.graphics.BitmapRegionDecoder.newInstance(r1, r5)     // Catch:{ all -> 0x015f }
            android.net.Uri r5 = r9.f4792g     // Catch:{ Exception -> 0x011d }
            java.lang.String r6 = "r"
            android.content.res.AssetFileDescriptor r0 = r0.openAssetFileDescriptor(r5, r6)     // Catch:{ Exception -> 0x011d }
            if (r0 == 0) goto L_0x011b
            long r3 = r0.getLength()     // Catch:{ Exception -> 0x011d }
            goto L_0x011c
        L_0x011b:
        L_0x011c:
            goto L_0x011f
        L_0x011d:
            r0 = move-exception
        L_0x011f:
            if (r1 != 0) goto L_0x0122
            goto L_0x0129
        L_0x0122:
            r1.close()     // Catch:{ Exception -> 0x0127 }
            goto L_0x0129
        L_0x0127:
            r0 = move-exception
        L_0x0129:
            r0 = r2
        L_0x012a:
            r9.f4789c = r3
            android.graphics.Point r1 = r9.f4793h
            int r2 = r0.getWidth()
            int r3 = r0.getHeight()
            r1.set(r2, r3)
            java.util.concurrent.locks.ReadWriteLock r1 = r9.f4790e
            java.util.concurrent.locks.Lock r1 = r1.writeLock()
            r1.lock()
            bgu r1 = r9.f4787a     // Catch:{ all -> 0x0154 }
            if (r1 != 0) goto L_0x0147
            goto L_0x014a
        L_0x0147:
            r1.mo2024b(r0)     // Catch:{ all -> 0x0154 }
        L_0x014a:
            java.util.concurrent.locks.ReadWriteLock r0 = r9.f4790e
            java.util.concurrent.locks.Lock r0 = r0.writeLock()
            r0.unlock()
            return
        L_0x0154:
            r0 = move-exception
            java.util.concurrent.locks.ReadWriteLock r1 = r9.f4790e
            java.util.concurrent.locks.Lock r1 = r1.writeLock()
            r1.unlock()
            throw r0
        L_0x015f:
            r0 = move-exception
            goto L_0x0164
        L_0x0162:
            r0 = move-exception
            r1 = 0
        L_0x0164:
            if (r1 == 0) goto L_0x016b
            r1.close()     // Catch:{ Exception -> 0x016a }
            goto L_0x016b
        L_0x016a:
            r1 = move-exception
        L_0x016b:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.davemorrissey.labs.subscaleview.decoder.SkiaPooledImageRegionDecoder.mo3308c():void");
    }

    /* renamed from: a */
    public final synchronized boolean mo2018a() {
        bgu bgu;
        bgu = this.f4787a;
        return bgu != null && !bgu.mo2022a();
    }

    /* renamed from: b */
    public final synchronized void mo2019b() {
        this.f4790e.writeLock().lock();
        try {
            bgu bgu = this.f4787a;
            if (bgu != null) {
                bgu.mo2026d();
                this.f4787a = null;
                this.f4788b = null;
                this.f4792g = null;
            }
        } finally {
            this.f4790e.writeLock().unlock();
        }
    }
}
