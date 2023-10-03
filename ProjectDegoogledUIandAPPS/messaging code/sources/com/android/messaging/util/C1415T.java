package com.android.messaging.util;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.util.Log;
import com.android.messaging.C0967f;
import com.android.messaging.util.exif.C1434c;
import com.android.messaging.util.exif.C1435d;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.util.T */
public class C1415T {

    /* renamed from: Cc */
    private final C1434c f2218Cc;

    /* renamed from: eK */
    private int f2219eK;

    /* renamed from: fK */
    private Bitmap f2220fK;

    /* renamed from: gK */
    private Bitmap f2221gK;

    /* renamed from: hK */
    private int f2222hK;

    /* renamed from: iK */
    private float f2223iK;

    /* renamed from: jK */
    private boolean f2224jK;

    /* renamed from: kK */
    private final int f2225kK;

    /* renamed from: lK */
    private final int f2226lK;
    private final String mContentType;
    private final Context mContext;
    private int mHeight;

    /* renamed from: mK */
    private final int f2227mK;
    private final Matrix mMatrix = new Matrix();
    private final BitmapFactory.Options mOptions;
    private final Uri mUri;
    private int mWidth;
    private final int mWidthLimit;

    private C1415T(int i, int i2, int i3, int i4, int i5, int i6, Uri uri, Context context, String str) {
        this.mWidth = i;
        this.mHeight = i2;
        this.f2218Cc = C1435d.m3656Ua(i3);
        this.mWidthLimit = i4;
        this.f2225kK = i5;
        this.f2226lK = i6;
        this.mUri = uri;
        this.mWidth = i;
        this.mContext = context;
        this.f2222hK = 95;
        this.f2223iK = 1.0f;
        this.f2224jK = false;
        this.mOptions = new BitmapFactory.Options();
        BitmapFactory.Options options = this.mOptions;
        options.inScaled = false;
        options.inDensity = 0;
        options.inTargetDensity = 0;
        options.inSampleSize = 1;
        options.inJustDecodeBounds = false;
        options.inMutable = false;
        this.f2227mK = Math.max(16, ((ActivityManager) context.getSystemService("activity")).getMemoryClass());
        this.mContentType = str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x007a, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x007b, code lost:
        if (r12 != null) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r12.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0081, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r2.addSuppressed(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0085, code lost:
        throw r4;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x0086 */
    /* renamed from: Hb */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] m3558Hb(int r12) {
        /*
            r11 = this;
            java.lang.String r0 = "MessagingAppImage"
            r1 = 0
            android.content.Context r2 = r11.mContext     // Catch:{ OutOfMemoryError -> 0x01b5 }
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r3 = 2
            boolean r3 = android.util.Log.isLoggable(r0, r3)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            if (r3 == 0) goto L_0x0056
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r4.<init>()     // Catch:{ OutOfMemoryError -> 0x01b5 }
            java.lang.String r5 = "getResizedImageData: attempt="
            r4.append(r5)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r4.append(r12)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            java.lang.String r12 = " limit (w="
            r4.append(r12)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            int r12 = r11.mWidthLimit     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r4.append(r12)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            java.lang.String r12 = " h="
            r4.append(r12)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            int r12 = r11.f2225kK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r4.append(r12)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            java.lang.String r12 = ") quality="
            r4.append(r12)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            int r12 = r11.f2222hK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r4.append(r12)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            java.lang.String r12 = " scale="
            r4.append(r12)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            float r12 = r11.f2223iK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r4.append(r12)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            java.lang.String r12 = " sampleSize="
            r4.append(r12)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            int r12 = r11.f2219eK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r4.append(r12)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            java.lang.String r12 = r4.toString()     // Catch:{ OutOfMemoryError -> 0x01b5 }
            com.android.messaging.util.C1430e.m3628v(r0, r12)     // Catch:{ OutOfMemoryError -> 0x01b5 }
        L_0x0056:
            android.graphics.Bitmap r12 = r11.f2221gK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            if (r12 != 0) goto L_0x016d
            android.graphics.Bitmap r12 = r11.f2220fK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            if (r12 != 0) goto L_0x0092
            android.graphics.BitmapFactory$Options r12 = r11.mOptions     // Catch:{ OutOfMemoryError -> 0x01b5 }
            int r4 = r11.f2219eK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r12.inSampleSize = r4     // Catch:{ OutOfMemoryError -> 0x01b5 }
            android.net.Uri r12 = r11.mUri     // Catch:{ IOException -> 0x0086 }
            java.io.InputStream r12 = r2.openInputStream(r12)     // Catch:{ IOException -> 0x0086 }
            android.graphics.BitmapFactory$Options r2 = r11.mOptions     // Catch:{ all -> 0x0078 }
            android.graphics.Bitmap r2 = android.graphics.BitmapFactory.decodeStream(r12, r1, r2)     // Catch:{ all -> 0x0078 }
            r11.f2220fK = r2     // Catch:{ all -> 0x0078 }
            if (r12 == 0) goto L_0x0086
            r12.close()     // Catch:{ IOException -> 0x0086 }
            goto L_0x0086
        L_0x0078:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x007a }
        L_0x007a:
            r4 = move-exception
            if (r12 == 0) goto L_0x0085
            r12.close()     // Catch:{ all -> 0x0081 }
            goto L_0x0085
        L_0x0081:
            r12 = move-exception
            r2.addSuppressed(r12)     // Catch:{ IOException -> 0x0086 }
        L_0x0085:
            throw r4     // Catch:{ IOException -> 0x0086 }
        L_0x0086:
            android.graphics.Bitmap r12 = r11.f2220fK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            if (r12 != 0) goto L_0x0092
            if (r3 == 0) goto L_0x0091
            java.lang.String r11 = "getResizedImageData: got empty decoded bitmap"
            com.android.messaging.util.C1430e.m3628v(r0, r11)     // Catch:{ OutOfMemoryError -> 0x01b5 }
        L_0x0091:
            return r1
        L_0x0092:
            java.lang.String r12 = ","
            if (r3 == 0) goto L_0x00bc
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r2.<init>()     // Catch:{ OutOfMemoryError -> 0x01b5 }
            java.lang.String r4 = "getResizedImageData: decoded w,h="
            r2.append(r4)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            android.graphics.Bitmap r4 = r11.f2220fK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            int r4 = r4.getWidth()     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r2.append(r4)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r2.append(r12)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            android.graphics.Bitmap r4 = r11.f2220fK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            int r4 = r4.getHeight()     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r2.append(r4)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            java.lang.String r2 = r2.toString()     // Catch:{ OutOfMemoryError -> 0x01b5 }
            com.android.messaging.util.C1430e.m3628v(r0, r2)     // Catch:{ OutOfMemoryError -> 0x01b5 }
        L_0x00bc:
            android.graphics.Bitmap r2 = r11.f2220fK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            int r7 = r2.getWidth()     // Catch:{ OutOfMemoryError -> 0x01b5 }
            android.graphics.Bitmap r2 = r11.f2220fK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            int r8 = r2.getHeight()     // Catch:{ OutOfMemoryError -> 0x01b5 }
            int r2 = r11.mWidthLimit     // Catch:{ OutOfMemoryError -> 0x01b5 }
            if (r7 > r2) goto L_0x00d0
            int r2 = r11.f2225kK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            if (r8 <= r2) goto L_0x00f3
        L_0x00d0:
            int r2 = r11.mWidthLimit     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r4 = 1065353216(0x3f800000, float:1.0)
            if (r2 != 0) goto L_0x00d8
            r2 = r4
            goto L_0x00dd
        L_0x00d8:
            float r2 = (float) r7     // Catch:{ OutOfMemoryError -> 0x01b5 }
            int r5 = r11.mWidthLimit     // Catch:{ OutOfMemoryError -> 0x01b5 }
            float r5 = (float) r5     // Catch:{ OutOfMemoryError -> 0x01b5 }
            float r2 = r2 / r5
        L_0x00dd:
            int r5 = r11.f2225kK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            if (r5 != 0) goto L_0x00e2
            goto L_0x00e7
        L_0x00e2:
            float r4 = (float) r8     // Catch:{ OutOfMemoryError -> 0x01b5 }
            int r5 = r11.f2225kK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            float r5 = (float) r5     // Catch:{ OutOfMemoryError -> 0x01b5 }
            float r4 = r4 / r5
        L_0x00e7:
            float r2 = java.lang.Math.max(r2, r4)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            float r4 = r11.f2223iK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            int r4 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r4 >= 0) goto L_0x00f3
            r11.f2223iK = r2     // Catch:{ OutOfMemoryError -> 0x01b5 }
        L_0x00f3:
            float r2 = r11.f2223iK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            double r4 = (double) r2     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r9 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r2 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r2 > 0) goto L_0x0108
            com.android.messaging.util.exif.c r2 = r11.f2218Cc     // Catch:{ OutOfMemoryError -> 0x01b5 }
            int r2 = r2.rotation     // Catch:{ OutOfMemoryError -> 0x01b5 }
            if (r2 == 0) goto L_0x0103
            goto L_0x0108
        L_0x0103:
            android.graphics.Bitmap r12 = r11.f2220fK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r11.f2221gK = r12     // Catch:{ OutOfMemoryError -> 0x01b5 }
            goto L_0x016d
        L_0x0108:
            android.graphics.Matrix r2 = r11.mMatrix     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r2.reset()     // Catch:{ OutOfMemoryError -> 0x01b5 }
            android.graphics.Matrix r2 = r11.mMatrix     // Catch:{ OutOfMemoryError -> 0x01b5 }
            com.android.messaging.util.exif.c r4 = r11.f2218Cc     // Catch:{ OutOfMemoryError -> 0x01b5 }
            int r4 = r4.rotation     // Catch:{ OutOfMemoryError -> 0x01b5 }
            float r4 = (float) r4     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r2.postRotate(r4)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            android.graphics.Matrix r2 = r11.mMatrix     // Catch:{ OutOfMemoryError -> 0x01b5 }
            com.android.messaging.util.exif.c r4 = r11.f2218Cc     // Catch:{ OutOfMemoryError -> 0x01b5 }
            int r4 = r4.scaleX     // Catch:{ OutOfMemoryError -> 0x01b5 }
            float r4 = (float) r4     // Catch:{ OutOfMemoryError -> 0x01b5 }
            float r5 = r11.f2223iK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            float r4 = r4 / r5
            com.android.messaging.util.exif.c r5 = r11.f2218Cc     // Catch:{ OutOfMemoryError -> 0x01b5 }
            int r5 = r5.scaleY     // Catch:{ OutOfMemoryError -> 0x01b5 }
            float r5 = (float) r5     // Catch:{ OutOfMemoryError -> 0x01b5 }
            float r6 = r11.f2223iK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            float r5 = r5 / r6
            r2.postScale(r4, r5)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            android.graphics.Bitmap r4 = r11.f2220fK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r5 = 0
            r6 = 0
            android.graphics.Matrix r9 = r11.mMatrix     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r10 = 0
            android.graphics.Bitmap r2 = android.graphics.Bitmap.createBitmap(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r11.f2221gK = r2     // Catch:{ OutOfMemoryError -> 0x01b5 }
            android.graphics.Bitmap r2 = r11.f2221gK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            if (r2 != 0) goto L_0x0145
            if (r3 == 0) goto L_0x0144
            java.lang.String r11 = "getResizedImageData: got empty scaled bitmap"
            com.android.messaging.util.C1430e.m3628v(r0, r11)     // Catch:{ OutOfMemoryError -> 0x01b5 }
        L_0x0144:
            return r1
        L_0x0145:
            if (r3 == 0) goto L_0x016d
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r2.<init>()     // Catch:{ OutOfMemoryError -> 0x01b5 }
            java.lang.String r4 = "getResizedImageData: scaled w,h="
            r2.append(r4)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            android.graphics.Bitmap r4 = r11.f2221gK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            int r4 = r4.getWidth()     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r2.append(r4)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r2.append(r12)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            android.graphics.Bitmap r12 = r11.f2221gK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            int r12 = r12.getHeight()     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r2.append(r12)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            java.lang.String r12 = r2.toString()     // Catch:{ OutOfMemoryError -> 0x01b5 }
            com.android.messaging.util.C1430e.m3628v(r0, r12)     // Catch:{ OutOfMemoryError -> 0x01b5 }
        L_0x016d:
            android.graphics.Bitmap r12 = r11.f2221gK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            int r2 = r11.f2222hK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            byte[] r1 = com.android.messaging.util.C1416U.m3567a((android.graphics.Bitmap) r12, (int) r2)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            if (r1 == 0) goto L_0x01ba
            if (r3 == 0) goto L_0x01ba
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r12.<init>()     // Catch:{ OutOfMemoryError -> 0x01b5 }
            java.lang.String r2 = "getResizedImageData: Encoded down to "
            r12.append(r2)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            int r2 = r1.length     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r12.append(r2)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            java.lang.String r2 = "@"
            r12.append(r2)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            android.graphics.Bitmap r2 = r11.f2221gK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            int r2 = r2.getWidth()     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r12.append(r2)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            java.lang.String r2 = "/"
            r12.append(r2)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            android.graphics.Bitmap r2 = r11.f2221gK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            int r2 = r2.getHeight()     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r12.append(r2)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            java.lang.String r2 = "~"
            r12.append(r2)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            int r11 = r11.f2222hK     // Catch:{ OutOfMemoryError -> 0x01b5 }
            r12.append(r11)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            java.lang.String r11 = r12.toString()     // Catch:{ OutOfMemoryError -> 0x01b5 }
            com.android.messaging.util.C1430e.m3628v(r0, r11)     // Catch:{ OutOfMemoryError -> 0x01b5 }
            goto L_0x01ba
        L_0x01b5:
            java.lang.String r11 = "getResizedImageData - image too big (OutOfMemoryError), will try  with smaller scale factor"
            com.android.messaging.util.C1430e.m3630w(r0, r11)
        L_0x01ba:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.util.C1415T.m3558Hb(int):byte[]");
    }

    /* renamed from: Ib */
    private void m3559Ib(int i) {
        int i2;
        boolean isLoggable = Log.isLoggable("MessagingAppImage", 2);
        if (i <= 0 || (i2 = this.f2222hK) <= 50) {
            if (i > 0) {
                float f = this.f2223iK;
                if (((double) f) < 1.125d) {
                    this.f2222hK = 95;
                    this.f2223iK = f / 0.75f;
                    if (isLoggable) {
                        StringBuilder Pa = C0632a.m1011Pa("getResizedImageData: Retrying at scale ");
                        Pa.append(this.f2223iK);
                        C1430e.m3628v("MessagingAppImage", Pa.toString());
                    }
                    Bitmap bitmap = this.f2221gK;
                    if (!(bitmap == null || bitmap == this.f2220fK)) {
                        bitmap.recycle();
                    }
                    this.f2221gK = null;
                    return;
                }
            }
            if (i > 0 || this.f2224jK) {
                this.f2219eK *= 2;
                this.f2222hK = 95;
                this.f2223iK = 1.0f;
                if (isLoggable) {
                    StringBuilder Pa2 = C0632a.m1011Pa("getResizedImageData: Retrying at sampleSize ");
                    Pa2.append(this.f2219eK);
                    C1430e.m3628v("MessagingAppImage", Pa2.toString());
                }
                Bitmap bitmap2 = this.f2221gK;
                if (!(bitmap2 == null || bitmap2 == this.f2220fK)) {
                    bitmap2.recycle();
                }
                this.f2221gK = null;
                Bitmap bitmap3 = this.f2220fK;
                if (bitmap3 != null) {
                    bitmap3.recycle();
                    this.f2220fK = null;
                    return;
                }
                return;
            }
            C0967f.get().mo6656Sd();
            this.f2224jK = true;
            if (isLoggable) {
                C1430e.m3628v("MessagingAppImage", "getResizedImageData: Retrying after reclaiming memory ");
                return;
            }
            return;
        }
        this.f2222hK = Math.max(50, Math.min((int) (Math.sqrt((((double) this.f2226lK) * 1.0d) / ((double) i)) * ((double) i2)), (int) (((double) this.f2222hK) * 0.8500000238418579d)));
        if (isLoggable) {
            StringBuilder Pa3 = C0632a.m1011Pa("getResizedImageData: Retrying at quality ");
            Pa3.append(this.f2222hK);
            C1430e.m3628v("MessagingAppImage", Pa3.toString());
        }
    }

    /* JADX WARNING: type inference failed for: r3v1 */
    /* JADX WARNING: type inference failed for: r3v19 */
    /* JADX WARNING: type inference failed for: r3v36 */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x02a4, code lost:
        if (r0 == null) goto L_0x02cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x02c7, code lost:
        if (r0 != null) goto L_0x02c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x02c9, code lost:
        r0.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x013d, code lost:
        if (r3 != null) goto L_0x013f;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x02d2 A[SYNTHETIC, Splitter:B:138:0x02d2] */
    /* JADX WARNING: Removed duplicated region for block: B:153:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0124 A[Catch:{ FileNotFoundException -> 0x0125, NullPointerException -> 0x010a, all -> 0x0107, all -> 0x02ce }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0147  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] m3560a(int r16, int r17, int r18, int r19, int r20, int r21, android.net.Uri r22, android.content.Context r23, java.lang.String r24) {
        /*
            com.android.messaging.util.T r11 = new com.android.messaging.util.T
            r1 = r11
            r2 = r16
            r3 = r17
            r4 = r18
            r5 = r19
            r6 = r20
            r7 = r21
            r8 = r22
            r9 = r23
            r10 = r24
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10)
            java.lang.String r0 = r11.mContentType
            android.net.Uri r1 = r11.mUri
            boolean r0 = com.android.messaging.util.C1416U.m3569b(r0, r1)
            java.lang.String r1 = "MessagingApp"
            r2 = 0
            if (r0 == 0) goto L_0x00cc
            android.net.Uri r0 = r11.mUri
            boolean r0 = com.android.messaging.datamodel.MediaScratchFileProvider.m1258d(r0)
            if (r0 == 0) goto L_0x0039
            android.net.Uri r0 = r11.mUri
            java.io.File r0 = com.android.messaging.datamodel.MediaScratchFileProvider.m1257c(r0)
            java.lang.String r0 = r0.getAbsolutePath()
        L_0x0037:
            r3 = r0
            goto L_0x0064
        L_0x0039:
            android.net.Uri r0 = r11.mUri
            java.lang.String r0 = r0.getScheme()
            java.lang.String r3 = "file"
            boolean r0 = android.text.TextUtils.equals(r0, r3)
            if (r0 != 0) goto L_0x005d
            java.lang.String r0 = "Expected a GIF file uri, but actual uri = "
            java.lang.StringBuilder r0 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r0)
            android.net.Uri r3 = r11.mUri
            java.lang.String r3 = r3.toString()
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            com.android.messaging.util.C1424b.fail(r0)
        L_0x005d:
            android.net.Uri r0 = r11.mUri
            java.lang.String r0 = r0.getPath()
            goto L_0x0037
        L_0x0064:
            int r0 = r11.mWidth
            int r4 = r11.mHeight
            boolean r0 = com.android.messaging.util.GifTranscoder.m3540G(r0, r4)
            java.lang.String r4 = "Could not create FileInputStream with path of "
            if (r0 == 0) goto L_0x00ac
            java.lang.String r0 = "gif"
            android.net.Uri r5 = com.android.messaging.datamodel.MediaScratchFileProvider.m1259k(r0)
            java.io.File r0 = com.android.messaging.datamodel.MediaScratchFileProvider.m1257c(r5)
            java.lang.String r6 = r0.getAbsolutePath()
            android.content.Context r7 = r11.mContext
            boolean r3 = com.android.messaging.util.GifTranscoder.m3541c(r7, r3, r6)
            if (r3 == 0) goto L_0x009f
            byte[] r0 = com.google.common.p043io.C1720i.m4651c(r0)     // Catch:{ IOException -> 0x008b }
            goto L_0x00a0
        L_0x008b:
            r0 = move-exception
            r3 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r4)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            com.android.messaging.util.C1430e.m3623e(r1, r0, r3)
        L_0x009f:
            r0 = r2
        L_0x00a0:
            android.content.Context r1 = r11.mContext
            android.content.ContentResolver r1 = r1.getContentResolver()
            r1.delete(r5, r2, r2)
            r2 = r0
            goto L_0x02cd
        L_0x00ac:
            java.io.File r0 = new java.io.File     // Catch:{ IOException -> 0x00b7 }
            r0.<init>(r3)     // Catch:{ IOException -> 0x00b7 }
            byte[] r2 = com.google.common.p043io.C1720i.m4651c(r0)     // Catch:{ IOException -> 0x00b7 }
            goto L_0x02cd
        L_0x00b7:
            r0 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r4)
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            com.android.messaging.util.C1430e.m3623e(r1, r3, r0)
            goto L_0x02cd
        L_0x00cc:
            int r0 = r11.mWidth
            r3 = -1
            r4 = 1
            r5 = 0
            if (r0 == r3) goto L_0x00d7
            int r0 = r11.mHeight
            if (r0 != r3) goto L_0x0101
        L_0x00d7:
            android.content.Context r0 = r11.mContext
            android.content.ContentResolver r0 = r0.getContentResolver()
            android.graphics.BitmapFactory$Options r3 = r11.mOptions     // Catch:{ FileNotFoundException -> 0x0125, NullPointerException -> 0x010a, all -> 0x0107 }
            r3.inJustDecodeBounds = r4     // Catch:{ FileNotFoundException -> 0x0125, NullPointerException -> 0x010a, all -> 0x0107 }
            android.net.Uri r3 = r11.mUri     // Catch:{ FileNotFoundException -> 0x0125, NullPointerException -> 0x010a, all -> 0x0107 }
            java.io.InputStream r3 = r0.openInputStream(r3)     // Catch:{ FileNotFoundException -> 0x0125, NullPointerException -> 0x010a, all -> 0x0107 }
            android.graphics.BitmapFactory$Options r0 = r11.mOptions     // Catch:{ FileNotFoundException -> 0x0105, NullPointerException -> 0x0103 }
            android.graphics.BitmapFactory.decodeStream(r3, r2, r0)     // Catch:{ FileNotFoundException -> 0x0105, NullPointerException -> 0x0103 }
            android.graphics.BitmapFactory$Options r0 = r11.mOptions     // Catch:{ FileNotFoundException -> 0x0105, NullPointerException -> 0x0103 }
            int r0 = r0.outWidth     // Catch:{ FileNotFoundException -> 0x0105, NullPointerException -> 0x0103 }
            r11.mWidth = r0     // Catch:{ FileNotFoundException -> 0x0105, NullPointerException -> 0x0103 }
            android.graphics.BitmapFactory$Options r0 = r11.mOptions     // Catch:{ FileNotFoundException -> 0x0105, NullPointerException -> 0x0103 }
            int r0 = r0.outHeight     // Catch:{ FileNotFoundException -> 0x0105, NullPointerException -> 0x0103 }
            r11.mHeight = r0     // Catch:{ FileNotFoundException -> 0x0105, NullPointerException -> 0x0103 }
            android.graphics.BitmapFactory$Options r0 = r11.mOptions     // Catch:{ FileNotFoundException -> 0x0105, NullPointerException -> 0x0103 }
            r0.inJustDecodeBounds = r5     // Catch:{ FileNotFoundException -> 0x0105, NullPointerException -> 0x0103 }
            if (r3 == 0) goto L_0x0101
            r3.close()     // Catch:{ IOException -> 0x0101 }
        L_0x0101:
            r0 = r4
            goto L_0x0143
        L_0x0103:
            r0 = move-exception
            goto L_0x010c
        L_0x0105:
            r0 = move-exception
            goto L_0x0127
        L_0x0107:
            r0 = move-exception
            goto L_0x02d0
        L_0x010a:
            r0 = move-exception
            r3 = r2
        L_0x010c:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x02ce }
            r6.<init>()     // Catch:{ all -> 0x02ce }
            java.lang.String r7 = "NPE trying to open the uri "
            r6.append(r7)     // Catch:{ all -> 0x02ce }
            android.net.Uri r7 = r11.mUri     // Catch:{ all -> 0x02ce }
            r6.append(r7)     // Catch:{ all -> 0x02ce }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x02ce }
            com.android.messaging.util.C1430e.m3623e(r1, r6, r0)     // Catch:{ all -> 0x02ce }
            if (r3 == 0) goto L_0x0142
            goto L_0x013f
        L_0x0125:
            r0 = move-exception
            r3 = r2
        L_0x0127:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x02ce }
            r6.<init>()     // Catch:{ all -> 0x02ce }
            java.lang.String r7 = "Could not open file corresponding to uri "
            r6.append(r7)     // Catch:{ all -> 0x02ce }
            android.net.Uri r7 = r11.mUri     // Catch:{ all -> 0x02ce }
            r6.append(r7)     // Catch:{ all -> 0x02ce }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x02ce }
            com.android.messaging.util.C1430e.m3623e(r1, r6, r0)     // Catch:{ all -> 0x02ce }
            if (r3 == 0) goto L_0x0142
        L_0x013f:
            r3.close()     // Catch:{ IOException -> 0x0142 }
        L_0x0142:
            r0 = r5
        L_0x0143:
            if (r0 != 0) goto L_0x0147
            goto L_0x02cd
        L_0x0147:
            java.lang.String r0 = "MessagingAppImage"
            r2 = 2
            boolean r2 = android.util.Log.isLoggable(r0, r2)
            int r3 = r11.mHeight
            int r6 = r11.mWidth
            int r7 = r11.f2227mK
            int r7 = r7 * 1024
            int r7 = r7 * 1024
            int r7 = r7 / 8
            int r8 = r11.f2226lK
            int r8 = r8 * 8
            int r9 = r11.f2225kK
            float r9 = (float) r9
            r10 = 1069547520(0x3fc00000, float:1.5)
            float r9 = r9 * r10
            int r9 = (int) r9
            int r12 = r11.mWidthLimit
            float r12 = (float) r12
            float r12 = r12 * r10
            int r12 = (int) r12
            float r8 = (float) r8
            float r8 = r8 * r10
            float r8 = r8 * r10
            int r8 = (int) r8
            int r7 = java.lang.Math.min(r8, r7)
            if (r3 >= r9) goto L_0x017c
            if (r6 >= r12) goto L_0x017c
            int r8 = r3 * r6
            if (r8 >= r7) goto L_0x017c
            r8 = r4
            goto L_0x017d
        L_0x017c:
            r8 = r5
        L_0x017d:
            r10 = r4
        L_0x017e:
            java.lang.String r13 = " p="
            java.lang.String r14 = " w="
            java.lang.String r15 = " vs "
            if (r8 != 0) goto L_0x021f
            int r4 = r4 * 2
            r8 = 536870911(0x1fffffff, float:1.0842021E-19)
            if (r4 < r8) goto L_0x01cb
            r2 = 5
            java.lang.Object[] r2 = new java.lang.Object[r2]
            int r3 = r11.mWidthLimit
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r5] = r3
            int r3 = r11.f2225kK
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r10] = r3
            int r3 = r11.f2226lK
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r4 = 2
            r2[r4] = r3
            r3 = 3
            int r4 = r11.mWidth
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r2[r3] = r4
            r3 = 4
            int r4 = r11.mHeight
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r2[r3] = r4
            java.lang.String r3 = "Cannot resize image: widthLimit=%d heightLimit=%d byteLimit=%d imageWidth=%d imageHeight=%d"
            java.lang.String r2 = java.lang.String.format(r3, r2)
            com.android.messaging.util.C1430e.m3630w(r0, r2)
            java.lang.String r0 = "Image cannot be resized"
            com.android.messaging.util.C1424b.fail(r0)
            goto L_0x025f
        L_0x01cb:
            if (r2 == 0) goto L_0x0208
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r8 = "computeInitialSampleSize: Increasing sampleSize to "
            r5.append(r8)
            r5.append(r4)
            java.lang.String r8 = " as h="
            r5.append(r8)
            r5.append(r3)
            r5.append(r15)
            r5.append(r9)
            r5.append(r14)
            r5.append(r6)
            r5.append(r15)
            r5.append(r12)
            r5.append(r13)
            int r3 = r3 * r6
            r5.append(r3)
            r5.append(r15)
            r5.append(r7)
            java.lang.String r3 = r5.toString()
            com.android.messaging.util.C1430e.m3628v(r0, r3)
        L_0x0208:
            int r3 = r11.mHeight
            int r3 = r3 / r4
            int r5 = r11.mWidth
            int r6 = r5 / r4
            if (r3 >= r9) goto L_0x0219
            if (r6 >= r12) goto L_0x0219
            int r5 = r3 * r6
            if (r5 >= r7) goto L_0x0219
            r5 = 1
            goto L_0x021a
        L_0x0219:
            r5 = 0
        L_0x021a:
            r8 = r5
            r10 = 1
            r5 = 0
            goto L_0x017e
        L_0x021f:
            if (r2 == 0) goto L_0x025c
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r5 = "computeInitialSampleSize: Initial sampleSize "
            r2.append(r5)
            r2.append(r4)
            java.lang.String r5 = " for h="
            r2.append(r5)
            r2.append(r3)
            r2.append(r15)
            r2.append(r9)
            r2.append(r14)
            r2.append(r6)
            r2.append(r15)
            r2.append(r12)
            r2.append(r13)
            int r3 = r3 * r6
            r2.append(r3)
            r2.append(r15)
            r2.append(r7)
            java.lang.String r2 = r2.toString()
            com.android.messaging.util.C1430e.m3628v(r0, r2)
        L_0x025c:
            r11.f2219eK = r4
            r5 = 1
        L_0x025f:
            if (r5 != 0) goto L_0x0263
            goto L_0x02cc
        L_0x0263:
            r0 = 0
        L_0x0264:
            r2 = 6
            if (r0 >= r2) goto L_0x02ba
            byte[] r2 = r11.m3558Hb(r0)     // Catch:{ FileNotFoundException -> 0x0292 }
            if (r2 == 0) goto L_0x0285
            int r3 = r2.length     // Catch:{ FileNotFoundException -> 0x0292 }
            int r4 = r11.f2226lK     // Catch:{ FileNotFoundException -> 0x0292 }
            if (r3 > r4) goto L_0x0285
            android.graphics.Bitmap r0 = r11.f2221gK
            if (r0 == 0) goto L_0x027d
            android.graphics.Bitmap r1 = r11.f2220fK
            if (r0 == r1) goto L_0x027d
            r0.recycle()
        L_0x027d:
            android.graphics.Bitmap r0 = r11.f2220fK
            if (r0 == 0) goto L_0x02cd
            r0.recycle()
            goto L_0x02cd
        L_0x0285:
            if (r2 != 0) goto L_0x0289
            r2 = 0
            goto L_0x028a
        L_0x0289:
            int r2 = r2.length     // Catch:{ FileNotFoundException -> 0x0292 }
        L_0x028a:
            r11.m3559Ib(r2)     // Catch:{ FileNotFoundException -> 0x0292 }
            int r0 = r0 + 1
            goto L_0x0264
        L_0x0290:
            r0 = move-exception
            goto L_0x02a7
        L_0x0292:
            java.lang.String r0 = "File disappeared during resizing"
            com.android.messaging.util.C1430e.m3622e(r1, r0)     // Catch:{ all -> 0x0290 }
            android.graphics.Bitmap r0 = r11.f2221gK
            if (r0 == 0) goto L_0x02a2
            android.graphics.Bitmap r1 = r11.f2220fK
            if (r0 == r1) goto L_0x02a2
            r0.recycle()
        L_0x02a2:
            android.graphics.Bitmap r0 = r11.f2220fK
            if (r0 == 0) goto L_0x02cc
            goto L_0x02c9
        L_0x02a7:
            android.graphics.Bitmap r1 = r11.f2221gK
            if (r1 == 0) goto L_0x02b2
            android.graphics.Bitmap r2 = r11.f2220fK
            if (r1 == r2) goto L_0x02b2
            r1.recycle()
        L_0x02b2:
            android.graphics.Bitmap r1 = r11.f2220fK
            if (r1 == 0) goto L_0x02b9
            r1.recycle()
        L_0x02b9:
            throw r0
        L_0x02ba:
            android.graphics.Bitmap r0 = r11.f2221gK
            if (r0 == 0) goto L_0x02c5
            android.graphics.Bitmap r1 = r11.f2220fK
            if (r0 == r1) goto L_0x02c5
            r0.recycle()
        L_0x02c5:
            android.graphics.Bitmap r0 = r11.f2220fK
            if (r0 == 0) goto L_0x02cc
        L_0x02c9:
            r0.recycle()
        L_0x02cc:
            r2 = 0
        L_0x02cd:
            return r2
        L_0x02ce:
            r0 = move-exception
            r2 = r3
        L_0x02d0:
            if (r2 == 0) goto L_0x02d5
            r2.close()     // Catch:{ IOException -> 0x02d5 }
        L_0x02d5:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.util.C1415T.m3560a(int, int, int, int, int, int, android.net.Uri, android.content.Context, java.lang.String):byte[]");
    }
}
