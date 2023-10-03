package p000;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.DisplayMetrics;
import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Lock;

/* renamed from: bac */
/* compiled from: PG */
public final class bac {

    /* renamed from: a */
    public static final aqy f1932a = aqy.m1471a("com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeFormat", aqj.f1456c);

    /* renamed from: b */
    public static final aqy f1933b = aqy.m1471a("com.bumptech.glide.load.resource.bitmap.Downsampler.AllowHardwareDecode", false);

    /* renamed from: c */
    public static final bab f1934c = new baa();

    /* renamed from: f */
    private static final aqy f1935f = aqy.m1471a("com.bumptech.glide.load.resource.bitmap.Downsampler.PreferredColorSpace", ara.SRGB);

    /* renamed from: g */
    private static final aqy f1936g = aqy.m1471a("com.bumptech.glide.load.resource.bitmap.Downsampler.FixBitmapSize", false);

    /* renamed from: h */
    private static final Queue f1937h = bfp.m2429a(0);

    /* renamed from: d */
    public final aui f1938d;

    /* renamed from: e */
    public final List f1939e;

    /* renamed from: i */
    private final auk f1940i;

    /* renamed from: j */
    private final DisplayMetrics f1941j;

    /* renamed from: k */
    private final bai f1942k;

    static {
        azz azz = azz.f1917a;
        Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"image/vnd.wap.wbmp", "image/x-ico"})));
        Collections.unmodifiableSet(EnumSet.of(ImageHeaderParser$ImageType.JPEG, ImageHeaderParser$ImageType.PNG_A, ImageHeaderParser$ImageType.PNG));
    }

    /* renamed from: a */
    private static boolean m2012a(int i) {
        return i == 90 || i == 270;
    }

    /* renamed from: b */
    private static int m2015b(double d) {
        return (int) (d + 0.5d);
    }

    public bac(List list, DisplayMetrics displayMetrics, auk auk, aui aui) {
        if (bai.f1947a == null) {
            synchronized (bai.class) {
                if (bai.f1947a == null) {
                    bai.f1947a = new bai();
                }
            }
        }
        this.f1942k = bai.f1947a;
        this.f1939e = list;
        this.f1941j = (DisplayMetrics) cns.m4632a((Object) displayMetrics);
        this.f1940i = (auk) cns.m4632a((Object) auk);
        this.f1938d = (aui) cns.m4632a((Object) aui);
    }

    /* JADX WARNING: Removed duplicated region for block: B:119:0x0288 A[Catch:{ IOException -> 0x027d, all -> 0x03d5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0293 A[Catch:{ IOException -> 0x027d, all -> 0x03d5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x0294 A[ADDED_TO_REGION, Catch:{ IOException -> 0x027d, all -> 0x03d5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x02a0 A[Catch:{ IOException -> 0x027d, all -> 0x03d5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x02a8 A[Catch:{ IOException -> 0x027d, all -> 0x03d5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x02da A[Catch:{ IOException -> 0x027d, all -> 0x03d5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x02ed A[Catch:{ IOException -> 0x027d, all -> 0x03d5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x02f8 A[Catch:{ IOException -> 0x027d, all -> 0x03d5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x0323 A[Catch:{ IOException -> 0x027d, all -> 0x03d5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x03c3 A[Catch:{ IOException -> 0x027d, all -> 0x03d5 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.aua mo1753a(p000.bal r26, int r27, int r28, p000.aqz r29, p000.bab r30) {
        /*
            r25 = this;
            r1 = r25
            r2 = r26
            r0 = r29
            r3 = r30
            java.lang.String r4 = "x"
            aui r5 = r1.f1938d
            java.lang.Class<byte[]> r6 = byte[].class
            r7 = 65536(0x10000, float:9.18355E-41)
            java.lang.Object r5 = r5.mo1634a(r7, r6)
            byte[] r5 = (byte[]) r5
            android.graphics.BitmapFactory$Options r6 = m2011a()
            r6.inTempStorage = r5
            aqy r7 = f1932a
            java.lang.Object r7 = r0.mo1502a((p000.aqy) r7)
            aqj r7 = (p000.aqj) r7
            aqy r8 = f1935f
            java.lang.Object r8 = r0.mo1502a((p000.aqy) r8)
            ara r8 = (p000.ara) r8
            aqy r9 = p000.azz.f1922f
            java.lang.Object r9 = r0.mo1502a((p000.aqy) r9)
            azz r9 = (p000.azz) r9
            aqy r10 = f1936g
            java.lang.Object r10 = r0.mo1502a((p000.aqy) r10)
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            aqy r11 = f1933b
            java.lang.Object r11 = r0.mo1502a((p000.aqy) r11)
            r12 = 1
            r13 = 0
            if (r11 == 0) goto L_0x005b
            aqy r11 = f1933b
            java.lang.Object r0 = r0.mo1502a((p000.aqy) r11)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x005a
            r0 = 1
            goto L_0x005c
        L_0x005a:
        L_0x005b:
            r0 = 0
        L_0x005c:
            p000.bfk.m2412a()     // Catch:{ all -> 0x03dc }
            auk r11 = r1.f1940i     // Catch:{ all -> 0x03dc }
            int[] r11 = m2014a(r2, r6, r3, r11)     // Catch:{ all -> 0x03dc }
            r14 = r11[r13]     // Catch:{ all -> 0x03dc }
            r11 = r11[r12]     // Catch:{ all -> 0x03dc }
            java.lang.String r15 = r6.outMimeType     // Catch:{ all -> 0x03dc }
            r15 = -1
            if (r14 != r15) goto L_0x0070
            r0 = 0
            goto L_0x0075
        L_0x0070:
            if (r11 != r15) goto L_0x0075
            r0 = 0
        L_0x0075:
            int r15 = r26.mo1758b()     // Catch:{ all -> 0x03dc }
            int r16 = p000.bax.m2065a((int) r15)     // Catch:{ all -> 0x03dc }
            boolean r17 = p000.bax.m2075b((int) r15)     // Catch:{ all -> 0x03dc }
            r13 = -2147483648(0xffffffff80000000, float:-0.0)
            r12 = r27
            if (r12 != r13) goto L_0x0090
            boolean r12 = m2012a((int) r16)     // Catch:{ all -> 0x03dc }
            if (r12 == 0) goto L_0x008f
            r12 = r11
            goto L_0x0090
        L_0x008f:
            r12 = r14
        L_0x0090:
            r18 = r5
            r5 = r28
            if (r5 == r13) goto L_0x0097
            goto L_0x00a0
        L_0x0097:
            boolean r5 = m2012a((int) r16)     // Catch:{ all -> 0x03d7 }
            if (r5 == 0) goto L_0x009f
            r5 = r14
            goto L_0x00a0
        L_0x009f:
            r5 = r11
        L_0x00a0:
            com.bumptech.glide.load.ImageHeaderParser$ImageType r13 = r26.mo1757a()     // Catch:{ all -> 0x03d7 }
            r29 = r15
            auk r15 = r1.f1940i     // Catch:{ all -> 0x03d7 }
            r19 = r8
            if (r14 > 0) goto L_0x00b6
            r21 = r0
            r20 = r7
            r16 = r10
            r1 = r11
            r11 = r12
            goto L_0x0240
        L_0x00b6:
            if (r11 <= 0) goto L_0x0238
            boolean r16 = m2012a((int) r16)     // Catch:{ all -> 0x0233 }
            if (r16 != 0) goto L_0x00c0
            r8 = r11
            goto L_0x00c1
        L_0x00c0:
            r8 = r14
        L_0x00c1:
            if (r16 != 0) goto L_0x00c7
            r16 = r10
            r10 = r14
            goto L_0x00ca
        L_0x00c7:
            r16 = r10
            r10 = r11
        L_0x00ca:
            r20 = r7
            float r7 = r9.mo1747a(r10, r8, r12, r5)     // Catch:{ all -> 0x0233 }
            r21 = 0
            int r22 = (r7 > r21 ? 1 : (r7 == r21 ? 0 : -1))
            if (r22 <= 0) goto L_0x01e0
            int r4 = r9.mo1748b(r10, r8, r12, r5)     // Catch:{ all -> 0x0233 }
            if (r4 == 0) goto L_0x01d8
            r21 = r0
            float r0 = (float) r10     // Catch:{ all -> 0x0233 }
            float r1 = r7 * r0
            r22 = r11
            r23 = r12
            double r11 = (double) r1     // Catch:{ all -> 0x0233 }
            int r1 = m2015b((double) r11)     // Catch:{ all -> 0x0233 }
            float r11 = (float) r8     // Catch:{ all -> 0x0233 }
            float r12 = r7 * r11
            r24 = r11
            double r11 = (double) r12     // Catch:{ all -> 0x0233 }
            int r1 = r10 / r1
            int r11 = m2015b((double) r11)     // Catch:{ all -> 0x0233 }
            int r11 = r8 / r11
            r12 = 1
            if (r4 == r12) goto L_0x0100
            int r1 = java.lang.Math.min(r1, r11)     // Catch:{ all -> 0x0233 }
            goto L_0x0104
        L_0x0100:
            int r1 = java.lang.Math.max(r1, r11)     // Catch:{ all -> 0x0233 }
        L_0x0104:
            int r11 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0233 }
            int r1 = java.lang.Integer.highestOneBit(r1)     // Catch:{ all -> 0x0233 }
            r11 = 1
            int r1 = java.lang.Math.max(r11, r1)     // Catch:{ all -> 0x0233 }
            if (r4 != r11) goto L_0x011b
            float r4 = (float) r1     // Catch:{ all -> 0x0233 }
            r11 = 1065353216(0x3f800000, float:1.0)
            float r7 = r11 / r7
            int r4 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r4 >= 0) goto L_0x011b
            int r1 = r1 + r1
        L_0x011b:
            r6.inSampleSize = r1     // Catch:{ all -> 0x0233 }
            com.bumptech.glide.load.ImageHeaderParser$ImageType r4 = com.bumptech.glide.load.ImageHeaderParser$ImageType.JPEG     // Catch:{ all -> 0x0233 }
            if (r13 == r4) goto L_0x016b
            com.bumptech.glide.load.ImageHeaderParser$ImageType r4 = com.bumptech.glide.load.ImageHeaderParser$ImageType.PNG     // Catch:{ all -> 0x0233 }
            if (r13 == r4) goto L_0x0159
            com.bumptech.glide.load.ImageHeaderParser$ImageType r4 = com.bumptech.glide.load.ImageHeaderParser$ImageType.PNG_A     // Catch:{ all -> 0x0233 }
            if (r13 == r4) goto L_0x0159
            com.bumptech.glide.load.ImageHeaderParser$ImageType r4 = com.bumptech.glide.load.ImageHeaderParser$ImageType.WEBP     // Catch:{ all -> 0x0233 }
            if (r13 == r4) goto L_0x014a
            com.bumptech.glide.load.ImageHeaderParser$ImageType r4 = com.bumptech.glide.load.ImageHeaderParser$ImageType.WEBP_A     // Catch:{ all -> 0x0233 }
            if (r13 != r4) goto L_0x0132
            goto L_0x014a
        L_0x0132:
            int r0 = r10 % r1
            if (r0 != 0) goto L_0x013f
            int r0 = r8 % r1
            if (r0 == 0) goto L_0x013b
            goto L_0x013f
        L_0x013b:
            int r10 = r10 / r1
            int r8 = r8 / r1
            goto L_0x0189
        L_0x013f:
            int[] r0 = m2014a(r2, r6, r3, r15)     // Catch:{ all -> 0x0233 }
            r1 = 0
            r10 = r0[r1]     // Catch:{ all -> 0x0233 }
            r1 = 1
            r8 = r0[r1]     // Catch:{ all -> 0x0233 }
            goto L_0x0188
        L_0x014a:
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0233 }
            float r1 = (float) r1     // Catch:{ all -> 0x0233 }
            float r0 = r0 / r1
            int r10 = java.lang.Math.round(r0)     // Catch:{ all -> 0x0233 }
            float r11 = r24 / r1
            int r8 = java.lang.Math.round(r11)     // Catch:{ all -> 0x0233 }
            goto L_0x0188
        L_0x0159:
            float r1 = (float) r1     // Catch:{ all -> 0x0233 }
            float r0 = r0 / r1
            double r7 = (double) r0     // Catch:{ all -> 0x0233 }
            double r7 = java.lang.Math.floor(r7)     // Catch:{ all -> 0x0233 }
            int r10 = (int) r7     // Catch:{ all -> 0x0233 }
            float r11 = r24 / r1
            double r0 = (double) r11     // Catch:{ all -> 0x0233 }
            double r0 = java.lang.Math.floor(r0)     // Catch:{ all -> 0x0233 }
            int r8 = (int) r0     // Catch:{ all -> 0x0233 }
            goto L_0x0189
        L_0x016b:
            r4 = 8
            int r7 = java.lang.Math.min(r1, r4)     // Catch:{ all -> 0x0233 }
            float r7 = (float) r7     // Catch:{ all -> 0x0233 }
            float r0 = r0 / r7
            double r10 = (double) r0     // Catch:{ all -> 0x0233 }
            double r10 = java.lang.Math.ceil(r10)     // Catch:{ all -> 0x0233 }
            int r10 = (int) r10     // Catch:{ all -> 0x0233 }
            float r11 = r24 / r7
            double r7 = (double) r11     // Catch:{ all -> 0x0233 }
            double r7 = java.lang.Math.ceil(r7)     // Catch:{ all -> 0x0233 }
            int r8 = (int) r7     // Catch:{ all -> 0x0233 }
            int r1 = r1 / r4
            if (r1 <= 0) goto L_0x0188
            int r10 = r10 / r1
            int r8 = r8 / r1
            goto L_0x0189
        L_0x0188:
        L_0x0189:
            r11 = r23
            float r0 = r9.mo1747a(r10, r8, r11, r5)     // Catch:{ all -> 0x0233 }
            double r0 = (double) r0     // Catch:{ all -> 0x0233 }
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0233 }
            int r4 = m2010a((double) r0)     // Catch:{ all -> 0x0233 }
            double r7 = (double) r4
            java.lang.Double.isNaN(r7)
            java.lang.Double.isNaN(r0)
            double r7 = r7 * r0
            int r7 = m2015b((double) r7)     // Catch:{ all -> 0x0233 }
            float r8 = (float) r7
            float r4 = (float) r4
            float r8 = r8 / r4
            double r8 = (double) r8
            java.lang.Double.isNaN(r0)
            java.lang.Double.isNaN(r8)
            double r8 = r0 / r8
            double r12 = (double) r7
            java.lang.Double.isNaN(r12)
            double r8 = r8 * r12
            int r4 = m2015b((double) r8)     // Catch:{ all -> 0x0233 }
            r6.inTargetDensity = r4     // Catch:{ all -> 0x0233 }
            int r0 = m2010a((double) r0)     // Catch:{ all -> 0x0233 }
            r6.inDensity = r0     // Catch:{ all -> 0x0233 }
            boolean r0 = m2013a((android.graphics.BitmapFactory.Options) r6)     // Catch:{ all -> 0x0233 }
            if (r0 != 0) goto L_0x01d1
            r0 = 0
            r6.inTargetDensity = r0     // Catch:{ all -> 0x0233 }
            r6.inDensity = r0     // Catch:{ all -> 0x0233 }
            r1 = r22
            goto L_0x0240
        L_0x01d1:
            r1 = 1
            r6.inScaled = r1     // Catch:{ all -> 0x0233 }
            r1 = r22
            goto L_0x0240
        L_0x01d8:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0233 }
            java.lang.String r1 = "Cannot round with null rounding"
            r0.<init>(r1)     // Catch:{ all -> 0x0233 }
            throw r0     // Catch:{ all -> 0x0233 }
        L_0x01e0:
            r22 = r11
            r11 = r12
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0233 }
            java.lang.String r1 = java.lang.String.valueOf(r9)     // Catch:{ all -> 0x0233 }
            java.lang.String r2 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x0233 }
            int r2 = r2.length()     // Catch:{ all -> 0x0233 }
            int r2 = r2 + 118
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0233 }
            r3.<init>(r2)     // Catch:{ all -> 0x0233 }
            java.lang.String r2 = "Cannot scale with factor: "
            r3.append(r2)     // Catch:{ all -> 0x0233 }
            r3.append(r7)     // Catch:{ all -> 0x0233 }
            java.lang.String r2 = " from: "
            r3.append(r2)     // Catch:{ all -> 0x0233 }
            r3.append(r1)     // Catch:{ all -> 0x0233 }
            java.lang.String r1 = ", source: ["
            r3.append(r1)     // Catch:{ all -> 0x0233 }
            r3.append(r14)     // Catch:{ all -> 0x0233 }
            r3.append(r4)     // Catch:{ all -> 0x0233 }
            r1 = r22
            r3.append(r1)     // Catch:{ all -> 0x0233 }
            java.lang.String r1 = "], target: ["
            r3.append(r1)     // Catch:{ all -> 0x0233 }
            r3.append(r11)     // Catch:{ all -> 0x0233 }
            r3.append(r4)     // Catch:{ all -> 0x0233 }
            r3.append(r5)     // Catch:{ all -> 0x0233 }
            java.lang.String r1 = "]"
            r3.append(r1)     // Catch:{ all -> 0x0233 }
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x0233 }
            r0.<init>(r1)     // Catch:{ all -> 0x0233 }
            throw r0     // Catch:{ all -> 0x0233 }
        L_0x0233:
            r0 = move-exception
            r4 = r25
            goto L_0x03d9
        L_0x0238:
            r21 = r0
            r20 = r7
            r16 = r10
            r1 = r11
            r11 = r12
        L_0x0240:
            r4 = r25
            bai r0 = r4.f1942k     // Catch:{ all -> 0x03d5 }
            if (r21 == 0) goto L_0x0263
            boolean r7 = r0.f1949b     // Catch:{ all -> 0x03d5 }
            if (r7 == 0) goto L_0x0263
            int r7 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x03d5 }
            if (r17 != 0) goto L_0x0263
            int r7 = r0.f1950c     // Catch:{ all -> 0x03d5 }
            if (r11 >= r7) goto L_0x0253
            goto L_0x0263
        L_0x0253:
            if (r5 < r7) goto L_0x0263
            boolean r0 = r0.mo1755a()     // Catch:{ all -> 0x03d5 }
            if (r0 == 0) goto L_0x0263
            android.graphics.Bitmap$Config r0 = android.graphics.Bitmap.Config.HARDWARE     // Catch:{ all -> 0x03d5 }
            r6.inPreferredConfig = r0     // Catch:{ all -> 0x03d5 }
            r0 = 0
            r6.inMutable = r0     // Catch:{ all -> 0x03d5 }
            goto L_0x028b
        L_0x0263:
            aqj r0 = p000.aqj.PREFER_ARGB_8888     // Catch:{ all -> 0x03d5 }
            r7 = r20
            if (r7 != r0) goto L_0x026e
            android.graphics.Bitmap$Config r0 = android.graphics.Bitmap.Config.ARGB_8888     // Catch:{ all -> 0x03d5 }
            r6.inPreferredConfig = r0     // Catch:{ all -> 0x03d5 }
            goto L_0x028b
        L_0x026e:
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x03d5 }
            com.bumptech.glide.load.ImageHeaderParser$ImageType r0 = r26.mo1757a()     // Catch:{ IOException -> 0x027d }
            boolean r0 = r0.hasAlpha()     // Catch:{ IOException -> 0x027d }
            if (r0 == 0) goto L_0x027e
            android.graphics.Bitmap$Config r0 = android.graphics.Bitmap.Config.ARGB_8888     // Catch:{ all -> 0x03d5 }
            goto L_0x0280
        L_0x027d:
            r0 = move-exception
        L_0x027e:
            android.graphics.Bitmap$Config r0 = android.graphics.Bitmap.Config.RGB_565     // Catch:{ all -> 0x03d5 }
        L_0x0280:
            r6.inPreferredConfig = r0     // Catch:{ all -> 0x03d5 }
            android.graphics.Bitmap$Config r0 = r6.inPreferredConfig     // Catch:{ all -> 0x03d5 }
            android.graphics.Bitmap$Config r7 = android.graphics.Bitmap.Config.RGB_565     // Catch:{ all -> 0x03d5 }
            if (r0 != r7) goto L_0x028b
            r7 = 1
            r6.inDither = r7     // Catch:{ all -> 0x03d5 }
        L_0x028b:
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x03d5 }
            int r0 = r6.inSampleSize     // Catch:{ all -> 0x03d5 }
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x03d5 }
            if (r14 >= 0) goto L_0x0294
            goto L_0x029a
        L_0x0294:
            if (r1 < 0) goto L_0x029a
            if (r16 == 0) goto L_0x029a
            r12 = r11
            goto L_0x02cc
        L_0x029a:
            boolean r0 = m2013a((android.graphics.BitmapFactory.Options) r6)     // Catch:{ all -> 0x03d5 }
            if (r0 == 0) goto L_0x02a8
            int r0 = r6.inTargetDensity     // Catch:{ all -> 0x03d5 }
            float r0 = (float) r0     // Catch:{ all -> 0x03d5 }
            int r5 = r6.inDensity     // Catch:{ all -> 0x03d5 }
            float r5 = (float) r5     // Catch:{ all -> 0x03d5 }
            float r0 = r0 / r5
            goto L_0x02ab
        L_0x02a8:
            r0 = 1065353216(0x3f800000, float:1.0)
        L_0x02ab:
            int r5 = r6.inSampleSize     // Catch:{ all -> 0x03d5 }
            float r7 = (float) r14     // Catch:{ all -> 0x03d5 }
            float r5 = (float) r5     // Catch:{ all -> 0x03d5 }
            float r7 = r7 / r5
            double r7 = (double) r7     // Catch:{ all -> 0x03d5 }
            double r7 = java.lang.Math.ceil(r7)     // Catch:{ all -> 0x03d5 }
            int r7 = (int) r7     // Catch:{ all -> 0x03d5 }
            float r1 = (float) r1     // Catch:{ all -> 0x03d5 }
            float r1 = r1 / r5
            double r8 = (double) r1     // Catch:{ all -> 0x03d5 }
            double r8 = java.lang.Math.ceil(r8)     // Catch:{ all -> 0x03d5 }
            int r1 = (int) r8     // Catch:{ all -> 0x03d5 }
            float r5 = (float) r7     // Catch:{ all -> 0x03d5 }
            float r5 = r5 * r0
            int r12 = java.lang.Math.round(r5)     // Catch:{ all -> 0x03d5 }
            float r1 = (float) r1     // Catch:{ all -> 0x03d5 }
            float r1 = r1 * r0
            int r5 = java.lang.Math.round(r1)     // Catch:{ all -> 0x03d5 }
        L_0x02cc:
            if (r12 <= 0) goto L_0x02e7
            if (r5 <= 0) goto L_0x02e7
            auk r0 = r4.f1940i     // Catch:{ all -> 0x03d5 }
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x03d5 }
            android.graphics.Bitmap$Config r1 = r6.inPreferredConfig     // Catch:{ all -> 0x03d5 }
            android.graphics.Bitmap$Config r7 = android.graphics.Bitmap.Config.HARDWARE     // Catch:{ all -> 0x03d5 }
            if (r1 == r7) goto L_0x02e7
            android.graphics.Bitmap$Config r1 = r6.outConfig     // Catch:{ all -> 0x03d5 }
            if (r1 == 0) goto L_0x02df
            goto L_0x02e1
        L_0x02df:
            android.graphics.Bitmap$Config r1 = r6.inPreferredConfig     // Catch:{ all -> 0x03d5 }
        L_0x02e1:
            android.graphics.Bitmap r0 = r0.mo1646b(r12, r5, r1)     // Catch:{ all -> 0x03d5 }
            r6.inBitmap = r0     // Catch:{ all -> 0x03d5 }
        L_0x02e7:
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x03d5 }
            r1 = 28
            if (r0 >= r1) goto L_0x02f8
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x03d5 }
            android.graphics.ColorSpace$Named r0 = android.graphics.ColorSpace.Named.SRGB     // Catch:{ all -> 0x03d5 }
            android.graphics.ColorSpace r0 = android.graphics.ColorSpace.get(r0)     // Catch:{ all -> 0x03d5 }
            r6.inPreferredColorSpace = r0     // Catch:{ all -> 0x03d5 }
            goto L_0x0316
        L_0x02f8:
            ara r0 = p000.ara.DISPLAY_P3     // Catch:{ all -> 0x03d5 }
            r8 = r19
            if (r8 == r0) goto L_0x02ff
            goto L_0x030e
        L_0x02ff:
            android.graphics.ColorSpace r0 = r6.outColorSpace     // Catch:{ all -> 0x03d5 }
            if (r0 == 0) goto L_0x030e
            android.graphics.ColorSpace r0 = r6.outColorSpace     // Catch:{ all -> 0x03d5 }
            boolean r0 = r0.isWideGamut()     // Catch:{ all -> 0x03d5 }
            if (r0 == 0) goto L_0x030e
            android.graphics.ColorSpace$Named r0 = android.graphics.ColorSpace.Named.DISPLAY_P3     // Catch:{ all -> 0x03d5 }
            goto L_0x0310
        L_0x030e:
            android.graphics.ColorSpace$Named r0 = android.graphics.ColorSpace.Named.SRGB     // Catch:{ all -> 0x03d5 }
        L_0x0310:
            android.graphics.ColorSpace r0 = android.graphics.ColorSpace.get(r0)     // Catch:{ all -> 0x03d5 }
            r6.inPreferredColorSpace = r0     // Catch:{ all -> 0x03d5 }
        L_0x0316:
            auk r0 = r4.f1940i     // Catch:{ all -> 0x03d5 }
            android.graphics.Bitmap r0 = m2016b(r2, r6, r3, r0)     // Catch:{ all -> 0x03d5 }
            auk r1 = r4.f1940i     // Catch:{ all -> 0x03d5 }
            r3.mo1752a(r1, r0)     // Catch:{ all -> 0x03d5 }
            if (r0 == 0) goto L_0x03c3
            android.util.DisplayMetrics r1 = r4.f1941j     // Catch:{ all -> 0x03d5 }
            int r1 = r1.densityDpi     // Catch:{ all -> 0x03d5 }
            r0.setDensity(r1)     // Catch:{ all -> 0x03d5 }
            auk r1 = r4.f1940i     // Catch:{ all -> 0x03d5 }
            boolean r2 = p000.bax.m2075b((int) r29)     // Catch:{ all -> 0x03d5 }
            if (r2 == 0) goto L_0x03b5
            android.graphics.Matrix r2 = new android.graphics.Matrix     // Catch:{ all -> 0x03d5 }
            r2.<init>()     // Catch:{ all -> 0x03d5 }
            r3 = -1028390912(0xffffffffc2b40000, float:-90.0)
            r5 = 1119092736(0x42b40000, float:90.0)
            r7 = 1127481344(0x43340000, float:180.0)
            r8 = -1082130432(0xffffffffbf800000, float:-1.0)
            switch(r29) {
                case 2: goto L_0x0370;
                case 3: goto L_0x036b;
                case 4: goto L_0x0361;
                case 5: goto L_0x0357;
                case 6: goto L_0x0352;
                case 7: goto L_0x0348;
                case 8: goto L_0x0343;
                default: goto L_0x0342;
            }     // Catch:{ all -> 0x03d5 }
        L_0x0342:
            goto L_0x0376
        L_0x0343:
            r2.setRotate(r3)     // Catch:{ all -> 0x03d5 }
            goto L_0x0376
        L_0x0348:
            r2.setRotate(r3)     // Catch:{ all -> 0x03d5 }
            r3 = 1065353216(0x3f800000, float:1.0)
            r2.postScale(r8, r3)     // Catch:{ all -> 0x03d5 }
            goto L_0x0376
        L_0x0352:
            r2.setRotate(r5)     // Catch:{ all -> 0x03d5 }
            goto L_0x0376
        L_0x0357:
            r2.setRotate(r5)     // Catch:{ all -> 0x03d5 }
            r3 = 1065353216(0x3f800000, float:1.0)
            r2.postScale(r8, r3)     // Catch:{ all -> 0x03d5 }
            goto L_0x0376
        L_0x0361:
            r2.setRotate(r7)     // Catch:{ all -> 0x03d5 }
            r3 = 1065353216(0x3f800000, float:1.0)
            r2.postScale(r8, r3)     // Catch:{ all -> 0x03d5 }
            goto L_0x0376
        L_0x036b:
            r2.setRotate(r7)     // Catch:{ all -> 0x03d5 }
            goto L_0x0376
        L_0x0370:
            r3 = 1065353216(0x3f800000, float:1.0)
            r2.setScale(r8, r3)     // Catch:{ all -> 0x03d5 }
        L_0x0376:
            android.graphics.RectF r3 = new android.graphics.RectF     // Catch:{ all -> 0x03d5 }
            int r5 = r0.getWidth()     // Catch:{ all -> 0x03d5 }
            float r5 = (float) r5     // Catch:{ all -> 0x03d5 }
            int r7 = r0.getHeight()     // Catch:{ all -> 0x03d5 }
            float r7 = (float) r7     // Catch:{ all -> 0x03d5 }
            r8 = 0
            r3.<init>(r8, r8, r5, r7)     // Catch:{ all -> 0x03d5 }
            r2.mapRect(r3)     // Catch:{ all -> 0x03d5 }
            float r5 = r3.width()     // Catch:{ all -> 0x03d5 }
            int r5 = java.lang.Math.round(r5)     // Catch:{ all -> 0x03d5 }
            float r7 = r3.height()     // Catch:{ all -> 0x03d5 }
            int r7 = java.lang.Math.round(r7)     // Catch:{ all -> 0x03d5 }
            android.graphics.Bitmap$Config r8 = p000.bax.m2066a((android.graphics.Bitmap) r0)     // Catch:{ all -> 0x03d5 }
            android.graphics.Bitmap r1 = r1.mo1642a(r5, r7, r8)     // Catch:{ all -> 0x03d5 }
            float r5 = r3.left     // Catch:{ all -> 0x03d5 }
            float r5 = -r5
            float r3 = r3.top     // Catch:{ all -> 0x03d5 }
            float r3 = -r3
            r2.postTranslate(r5, r3)     // Catch:{ all -> 0x03d5 }
            boolean r3 = r0.hasAlpha()     // Catch:{ all -> 0x03d5 }
            r1.setHasAlpha(r3)     // Catch:{ all -> 0x03d5 }
            p000.bax.m2071a((android.graphics.Bitmap) r0, (android.graphics.Bitmap) r1, (android.graphics.Matrix) r2)     // Catch:{ all -> 0x03d5 }
            goto L_0x03b6
        L_0x03b5:
            r1 = r0
        L_0x03b6:
            boolean r2 = r0.equals(r1)     // Catch:{ all -> 0x03d5 }
            if (r2 == 0) goto L_0x03bd
            goto L_0x03c4
        L_0x03bd:
            auk r2 = r4.f1940i     // Catch:{ all -> 0x03d5 }
            r2.mo1645a((android.graphics.Bitmap) r0)     // Catch:{ all -> 0x03d5 }
            goto L_0x03c4
        L_0x03c3:
            r1 = 0
        L_0x03c4:
            auk r0 = r4.f1940i     // Catch:{ all -> 0x03d5 }
            azk r0 = p000.azk.m1961a(r1, r0)     // Catch:{ all -> 0x03d5 }
            m2017b((android.graphics.BitmapFactory.Options) r6)
            aui r1 = r4.f1938d
            r5 = r18
            r1.mo1638a((java.lang.Object) r5)
            return r0
        L_0x03d5:
            r0 = move-exception
            goto L_0x03d9
        L_0x03d7:
            r0 = move-exception
            r4 = r1
        L_0x03d9:
            r5 = r18
            goto L_0x03de
        L_0x03dc:
            r0 = move-exception
            r4 = r1
        L_0x03de:
            m2017b((android.graphics.BitmapFactory.Options) r6)
            aui r1 = r4.f1938d
            r1.mo1638a((java.lang.Object) r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.bac.mo1753a(bal, int, int, aqz, bab):aua");
    }

    /* renamed from: a */
    public final aua mo1754a(InputStream inputStream, int i, int i2, aqz aqz, bab bab) {
        return mo1753a((bal) new baj(inputStream, this.f1939e, this.f1938d), i, i2, aqz, bab);
    }

    /* renamed from: b */
    private static Bitmap m2016b(bal bal, BitmapFactory.Options options, bab bab, auk auk) {
        IOException iOException;
        String str;
        Bitmap b;
        Lock lock;
        BitmapFactory.Options options2 = options;
        if (!options2.inJustDecodeBounds) {
            bab.mo1751a();
            bal.mo1759c();
        }
        int i = options2.outWidth;
        int i2 = options2.outHeight;
        String str2 = options2.outMimeType;
        bax.f1980a.lock();
        try {
            b = bal.mo1756a(options);
            lock = bax.f1980a;
        } catch (IOException e) {
            throw iOException;
        } catch (IllegalArgumentException e2) {
            IllegalArgumentException illegalArgumentException = e2;
            Bitmap bitmap = options2.inBitmap;
            if (bitmap != null) {
                int i3 = Build.VERSION.SDK_INT;
                int allocationByteCount = bitmap.getAllocationByteCount();
                StringBuilder sb = new StringBuilder(14);
                sb.append(" (");
                sb.append(allocationByteCount);
                sb.append(")");
                String sb2 = sb.toString();
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                String valueOf = String.valueOf(bitmap.getConfig());
                StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf).length() + 26 + String.valueOf(sb2).length());
                sb3.append("[");
                sb3.append(width);
                sb3.append("x");
                sb3.append(height);
                sb3.append("] ");
                sb3.append(valueOf);
                sb3.append(sb2);
                str = sb3.toString();
            } else {
                str = null;
            }
            StringBuilder sb4 = new StringBuilder(String.valueOf(str2).length() + 99 + String.valueOf(str).length());
            sb4.append("Exception decoding bitmap, outWidth: ");
            sb4.append(i);
            sb4.append(", outHeight: ");
            sb4.append(i2);
            sb4.append(", outMimeType: ");
            sb4.append(str2);
            sb4.append(", inBitmap: ");
            sb4.append(str);
            iOException = new IOException(sb4.toString(), illegalArgumentException);
            if (options2.inBitmap != null) {
                auk.mo1645a(options2.inBitmap);
                options2.inBitmap = null;
                b = m2016b(bal, options, bab, auk);
                lock = bax.f1980a;
            } else {
                throw iOException;
            }
        } catch (Throwable th) {
            bax.f1980a.unlock();
            throw th;
        }
        lock.unlock();
        return b;
    }

    /* renamed from: a */
    private static synchronized BitmapFactory.Options m2011a() {
        BitmapFactory.Options options;
        synchronized (bac.class) {
            synchronized (f1937h) {
                options = (BitmapFactory.Options) f1937h.poll();
            }
            if (options != null) {
                return options;
            }
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            m2018c(options2);
            return options2;
        }
    }

    /* renamed from: a */
    private static int m2010a(double d) {
        if (d > 1.0d) {
            d = 1.0d / d;
        }
        return (int) Math.round(d * 2.147483647E9d);
    }

    /* renamed from: a */
    private static int[] m2014a(bal bal, BitmapFactory.Options options, bab bab, auk auk) {
        options.inJustDecodeBounds = true;
        m2016b(bal, options, bab, auk);
        options.inJustDecodeBounds = false;
        return new int[]{options.outWidth, options.outHeight};
    }

    /* renamed from: a */
    private static boolean m2013a(BitmapFactory.Options options) {
        return options.inTargetDensity > 0 && options.inDensity > 0 && options.inTargetDensity != options.inDensity;
    }

    /* renamed from: b */
    private static void m2017b(BitmapFactory.Options options) {
        m2018c(options);
        synchronized (f1937h) {
            f1937h.offer(options);
        }
    }

    /* renamed from: c */
    private static void m2018c(BitmapFactory.Options options) {
        options.inTempStorage = null;
        options.inDither = false;
        options.inScaled = false;
        options.inSampleSize = 1;
        options.inPreferredConfig = null;
        options.inJustDecodeBounds = false;
        options.inDensity = 0;
        options.inTargetDensity = 0;
        int i = Build.VERSION.SDK_INT;
        options.inPreferredColorSpace = null;
        options.outColorSpace = null;
        options.outConfig = null;
        options.outWidth = 0;
        options.outHeight = 0;
        options.outMimeType = null;
        options.inBitmap = null;
        options.inMutable = true;
    }
}
