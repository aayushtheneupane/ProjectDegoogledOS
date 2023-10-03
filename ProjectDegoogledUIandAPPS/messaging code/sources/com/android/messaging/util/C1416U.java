package com.android.messaging.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import com.android.messaging.C0967f;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.util.U */
public class C1416U {

    /* renamed from: nK */
    private static final byte[] f2228nK = "GIF87a".getBytes(Charset.forName("US-ASCII"));

    /* renamed from: oK */
    private static final byte[] f2229oK = "GIF89a".getBytes(Charset.forName("US-ASCII"));

    /* renamed from: pK */
    private static final String[] f2230pK = {"mime_type"};
    private static volatile C1416U sInstance;

    /* renamed from: a */
    public static byte[] m3567a(Bitmap bitmap, int i) {
        boolean z = false;
        byte[] bArr = null;
        while (!z) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
                bArr = byteArrayOutputStream.toByteArray();
                z = true;
            } catch (OutOfMemoryError e) {
                C1430e.m3630w("MessagingApp", "OutOfMemory converting bitmap to bytes.");
                C0967f.get().mo6656Sd();
                throw e;
            }
        }
        return bArr;
    }

    /* renamed from: b */
    public static boolean m3569b(String str, Uri uri) {
        if (TextUtils.equals(str, "image/gif")) {
            return true;
        }
        if (!C1481w.isImageType(str)) {
            return false;
        }
        try {
            return m3568b(C0967f.get().getApplicationContext().getContentResolver().openInputStream(uri));
        } catch (Exception e) {
            C1430e.m3631w("MessagingApp", "Could not open GIF input stream", e);
            return false;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:10|11|12|13|14) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0028 */
    /* renamed from: g */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Rect m3570g(android.content.Context r4, android.net.Uri r5) {
        /*
            android.content.ContentResolver r4 = r4.getContentResolver()
            r0 = 0
            java.io.InputStream r4 = r4.openInputStream(r5)     // Catch:{ FileNotFoundException -> 0x0029 }
            if (r4 == 0) goto L_0x003f
            android.graphics.BitmapFactory$Options r1 = new android.graphics.BitmapFactory$Options     // Catch:{ all -> 0x0024 }
            r1.<init>()     // Catch:{ all -> 0x0024 }
            r2 = 1
            r1.inJustDecodeBounds = r2     // Catch:{ all -> 0x0024 }
            r2 = 0
            android.graphics.BitmapFactory.decodeStream(r4, r2, r1)     // Catch:{ all -> 0x0024 }
            android.graphics.Rect r2 = new android.graphics.Rect     // Catch:{ all -> 0x0024 }
            int r3 = r1.outWidth     // Catch:{ all -> 0x0024 }
            int r1 = r1.outHeight     // Catch:{ all -> 0x0024 }
            r2.<init>(r0, r0, r3, r1)     // Catch:{ all -> 0x0024 }
            r4.close()     // Catch:{ IOException -> 0x0023 }
        L_0x0023:
            return r2
        L_0x0024:
            r1 = move-exception
            r4.close()     // Catch:{ IOException -> 0x0028 }
        L_0x0028:
            throw r1     // Catch:{ FileNotFoundException -> 0x0029 }
        L_0x0029:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r1 = "Couldn't open input stream for uri = "
            r4.append(r1)
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            java.lang.String r5 = "MessagingApp"
            com.android.messaging.util.C1430e.m3622e(r5, r4)
        L_0x003f:
            android.graphics.Rect r4 = new android.graphics.Rect
            r5 = -1
            r4.<init>(r0, r0, r5, r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.util.C1416U.m3570g(android.content.Context, android.net.Uri):android.graphics.Rect");
    }

    public static C1416U get() {
        if (sInstance == null) {
            synchronized (C1416U.class) {
                if (sInstance == null) {
                    sInstance = new C1416U();
                }
            }
        }
        return sInstance;
    }

    /* renamed from: h */
    public static int m3571h(Context context, Uri uri) {
        try {
            return m3561a(context.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            C0632a.m1020a("getOrientation couldn't open: ", (Object) uri, "MessagingApp", (Throwable) e);
            return 0;
        }
    }

    public static void set(C1416U u) {
        sInstance = u;
    }

    /* renamed from: a */
    public static void m3565a(Bitmap bitmap, Canvas canvas, RectF rectF, RectF rectF2, Paint paint, boolean z, int i, int i2) {
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
        Matrix matrix = new Matrix();
        matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
        bitmapShader.setLocalMatrix(matrix);
        if (paint == null) {
            paint = new Paint();
        }
        paint.setAntiAlias(true);
        if (z) {
            paint.setColor(i);
            canvas.drawCircle(rectF2.centerX(), rectF2.centerX(), rectF2.width() / 2.0f, paint);
        }
        paint.setShader(bitmapShader);
        canvas.drawCircle(rectF2.centerX(), rectF2.centerX(), rectF2.width() / 2.0f, paint);
        paint.setShader((Shader) null);
        if (i2 != 0) {
            Paint paint2 = new Paint();
            paint2.setAntiAlias(true);
            paint2.setColor(i2);
            paint2.setStyle(Paint.Style.STROKE);
            paint2.setStrokeWidth(6.0f);
            canvas.drawCircle(rectF2.centerX(), rectF2.centerX(), (rectF2.width() / 2.0f) - (paint2.getStrokeWidth() / 2.0f), paint2);
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x002a */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m3568b(java.io.InputStream r4) {
        /*
            r0 = 0
            if (r4 == 0) goto L_0x002d
            r1 = 6
            byte[] r2 = new byte[r1]     // Catch:{ IOException -> 0x002a, all -> 0x0025 }
            int r3 = r4.read(r2, r0, r1)     // Catch:{ IOException -> 0x002a, all -> 0x0025 }
            if (r3 != r1) goto L_0x0021
            byte[] r1 = f2228nK     // Catch:{ IOException -> 0x002a, all -> 0x0025 }
            boolean r1 = java.util.Arrays.equals(r2, r1)     // Catch:{ IOException -> 0x002a, all -> 0x0025 }
            if (r1 != 0) goto L_0x001c
            byte[] r1 = f2229oK     // Catch:{ IOException -> 0x002a, all -> 0x0025 }
            boolean r1 = java.util.Arrays.equals(r2, r1)     // Catch:{ IOException -> 0x002a, all -> 0x0025 }
            if (r1 == 0) goto L_0x001d
        L_0x001c:
            r0 = 1
        L_0x001d:
            r4.close()     // Catch:{ IOException -> 0x0020 }
        L_0x0020:
            return r0
        L_0x0021:
            r4.close()     // Catch:{ IOException -> 0x002d }
            goto L_0x002d
        L_0x0025:
            r0 = move-exception
            r4.close()     // Catch:{ IOException -> 0x0029 }
        L_0x0029:
            throw r0
        L_0x002a:
            r4.close()     // Catch:{  }
        L_0x002d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.util.C1416U.m3568b(java.io.InputStream):boolean");
    }

    /* renamed from: a */
    public static void m3566a(View view, Drawable drawable) {
        if (C1464na.m3754Uj()) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    /* renamed from: a */
    public int mo8047a(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        boolean z = false;
        int i5 = 1;
        boolean z2 = i2 != -1;
        if (i != -1) {
            z = true;
        }
        if ((z2 && i3 > i2) || (z && i4 > i)) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (true) {
                if ((z2 && i6 / i5 <= i2) || (z && i7 / i5 <= i)) {
                    break;
                }
                i5 *= 2;
            }
        }
        return i5;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x002d  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m3564a(android.content.ContentResolver r8, android.net.Uri r9) {
        /*
            boolean r0 = com.android.messaging.util.C1488za.m3862B(r9)
            r1 = 0
            if (r0 == 0) goto L_0x0031
            java.lang.String[] r4 = f2230pK     // Catch:{ all -> 0x0029 }
            r5 = 0
            r6 = 0
            r7 = 0
            r2 = r8
            r3 = r9
            android.database.Cursor r8 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0029 }
            if (r8 == 0) goto L_0x0023
            boolean r0 = r8.moveToFirst()     // Catch:{ all -> 0x0021 }
            if (r0 == 0) goto L_0x0023
            r0 = 0
            java.lang.String r0 = r8.getString(r0)     // Catch:{ all -> 0x0021 }
            r1 = r0
            goto L_0x0023
        L_0x0021:
            r9 = move-exception
            goto L_0x002b
        L_0x0023:
            if (r8 == 0) goto L_0x0031
            r8.close()
            goto L_0x0031
        L_0x0029:
            r9 = move-exception
            r8 = r1
        L_0x002b:
            if (r8 == 0) goto L_0x0030
            r8.close()
        L_0x0030:
            throw r9
        L_0x0031:
            if (r1 != 0) goto L_0x0048
            java.lang.String r8 = r9.toString()
            android.webkit.MimeTypeMap r9 = android.webkit.MimeTypeMap.getSingleton()
            java.lang.String r8 = android.webkit.MimeTypeMap.getFileExtensionFromUrl(r8)
            java.lang.String r8 = r9.getMimeTypeFromExtension(r8)
            if (r8 != 0) goto L_0x0047
            java.lang.String r8 = "image/*"
        L_0x0047:
            r1 = r8
        L_0x0048:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.util.C1416U.m3564a(android.content.ContentResolver, android.net.Uri):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0024, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0029, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002a, code lost:
        com.android.messaging.util.C1430e.m3623e("MessagingApp", "getOrientation error closing input stream", r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002d, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x002e */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int m3561a(java.io.InputStream r5) {
        /*
            java.lang.String r0 = "getOrientation error closing input stream"
            java.lang.String r1 = "MessagingApp"
            r2 = 0
            if (r5 == 0) goto L_0x0031
            com.android.messaging.util.exif.d r3 = new com.android.messaging.util.exif.d     // Catch:{ IOException -> 0x002e, all -> 0x0024 }
            r3.<init>()     // Catch:{ IOException -> 0x002e, all -> 0x0024 }
            r3.mo8100d(r5)     // Catch:{ IOException -> 0x002e, all -> 0x0024 }
            int r4 = com.android.messaging.util.exif.C1435d.TAG_ORIENTATION     // Catch:{ IOException -> 0x002e, all -> 0x0024 }
            java.lang.Integer r3 = r3.mo8097Va(r4)     // Catch:{ IOException -> 0x002e, all -> 0x0024 }
            if (r3 == 0) goto L_0x001b
            int r2 = r3.intValue()     // Catch:{ IOException -> 0x002e, all -> 0x0024 }
        L_0x001b:
            r5.close()     // Catch:{ IOException -> 0x001f }
            goto L_0x0031
        L_0x001f:
            r5 = move-exception
            com.android.messaging.util.C1430e.m3623e(r1, r0, r5)
            goto L_0x0031
        L_0x0024:
            r2 = move-exception
            r5.close()     // Catch:{ IOException -> 0x0029 }
            goto L_0x002d
        L_0x0029:
            r5 = move-exception
            com.android.messaging.util.C1430e.m3623e(r1, r0, r5)
        L_0x002d:
            throw r2
        L_0x002e:
            r5.close()     // Catch:{ IOException -> 0x001f }
        L_0x0031:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.util.C1416U.m3561a(java.io.InputStream):int");
    }

    /* renamed from: a */
    public static Bitmap m3562a(Bitmap bitmap, int i, int i2) {
        float f = (float) i;
        float width = (float) bitmap.getWidth();
        float f2 = (float) i2;
        float height = (float) bitmap.getHeight();
        float max = Math.max(f / width, f2 / height);
        float f3 = width * max;
        float f4 = max * height;
        float f5 = (f - f3) / 2.0f;
        float f6 = (f2 - f4) / 2.0f;
        RectF rectF = new RectF(f5, f6, f3 + f5, f4 + f6);
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, bitmap.getConfig());
        new Canvas(createBitmap).drawBitmap(bitmap, (Rect) null, rectF, (Paint) null);
        return createBitmap;
    }

    /* renamed from: a */
    public static Drawable m3563a(Context context, Drawable drawable, int i) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState != null) {
            drawable = constantState.newDrawable(context.getResources()).mutate();
        }
        drawable.setColorFilter(i, PorterDuff.Mode.SRC_ATOP);
        return drawable;
    }
}
