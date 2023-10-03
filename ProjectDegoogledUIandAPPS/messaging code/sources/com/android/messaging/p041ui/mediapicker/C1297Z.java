package com.android.messaging.p041ui.mediapicker;

import android.content.Context;
import android.net.Uri;
import com.android.messaging.datamodel.MediaScratchFileProvider;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1478ua;

/* renamed from: com.android.messaging.ui.mediapicker.Z */
public class C1297Z extends C1478ua {

    /* renamed from: Qd */
    private final float f2052Qd;

    /* renamed from: Rd */
    private Uri f2053Rd;
    private final byte[] mBytes;
    private final C1360x mCallback;
    private final Context mContext;
    private Exception mException;
    private int mHeight;
    private int mWidth;

    public C1297Z(int i, int i2, float f, byte[] bArr, Context context, C1360x xVar) {
        C1424b.m3592ia(f >= 0.0f && f <= 1.0f);
        C1424b.m3594t(bArr);
        C1424b.m3594t(context);
        C1424b.m3594t(xVar);
        this.mWidth = i;
        this.mHeight = i2;
        this.f2052Qd = f;
        this.mBytes = bArr;
        this.mContext = context;
        this.mCallback = xVar;
        this.f2053Rd = MediaScratchFileProvider.m1259k("jpg");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x010f, code lost:
        r3 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x016b, code lost:
        r4.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0170, code lost:
        r5.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:?, code lost:
        r2.flush();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0179, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x017b, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:?, code lost:
        r10.f2053Rd = null;
        r10.mException = r4;
        com.android.messaging.util.C1430e.m3622e("MessagingApp", "error trying to flush and close the outputStream" + r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0199, code lost:
        throw r10;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x003e */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004f A[Catch:{ IOException -> 0x00d6, all -> 0x00d2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x006b A[Catch:{ IOException -> 0x00d6, all -> 0x00d2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x010f A[ExcHandler: all (th java.lang.Throwable), Splitter:B:13:0x0039] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0138  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x013d  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0142 A[SYNTHETIC, Splitter:B:65:0x0142] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x016b  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0170  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0175 A[SYNTHETIC, Splitter:B:85:0x0175] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object mo6323a(java.lang.Object[] r11) {
        /*
            r10 = this;
            java.lang.Void[] r11 = (java.lang.Void[]) r11
            java.lang.String r11 = "error trying to flush and close the outputStream"
            java.lang.String r0 = "MessagingApp"
            r1 = 0
            android.content.Context r2 = r10.mContext     // Catch:{ IOException -> 0x0119, all -> 0x0113 }
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch:{ IOException -> 0x0119, all -> 0x0113 }
            android.net.Uri r3 = r10.f2053Rd     // Catch:{ IOException -> 0x0119, all -> 0x0113 }
            java.io.OutputStream r2 = r2.openOutputStream(r3)     // Catch:{ IOException -> 0x0119, all -> 0x0113 }
            float r3 = r10.f2052Qd     // Catch:{ IOException -> 0x0111, all -> 0x010f }
            r4 = 1065353216(0x3f800000, float:1.0)
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 == 0) goto L_0x00d9
            com.android.messaging.util.exif.d r3 = new com.android.messaging.util.exif.d     // Catch:{ IOException -> 0x0111, all -> 0x010f }
            r3.<init>()     // Catch:{ IOException -> 0x0111, all -> 0x010f }
            r4 = 0
            byte[] r5 = r10.mBytes     // Catch:{ IOException -> 0x003d, all -> 0x010f }
            java.io.ByteArrayInputStream r6 = new java.io.ByteArrayInputStream     // Catch:{ IOException -> 0x003d, all -> 0x010f }
            r6.<init>(r5)     // Catch:{ IOException -> 0x003d, all -> 0x010f }
            r3.mo8100d(r6)     // Catch:{ IOException -> 0x003d, all -> 0x010f }
            int r5 = com.android.messaging.util.exif.C1435d.TAG_ORIENTATION     // Catch:{ IOException -> 0x003d, all -> 0x010f }
            java.lang.Integer r5 = r3.mo8097Va(r5)     // Catch:{ IOException -> 0x003d, all -> 0x010f }
            if (r5 == 0) goto L_0x0038
            int r5 = r5.intValue()     // Catch:{ IOException -> 0x003d, all -> 0x010f }
            goto L_0x0039
        L_0x0038:
            r5 = r4
        L_0x0039:
            r3.mo8101p(r1)     // Catch:{ IOException -> 0x003e, all -> 0x010f }
            goto L_0x003e
        L_0x003d:
            r5 = r4
        L_0x003e:
            byte[] r6 = r10.mBytes     // Catch:{ IOException -> 0x0111, all -> 0x010f }
            byte[] r7 = r10.mBytes     // Catch:{ IOException -> 0x0111, all -> 0x010f }
            int r7 = r7.length     // Catch:{ IOException -> 0x0111, all -> 0x010f }
            android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeByteArray(r6, r4, r7)     // Catch:{ IOException -> 0x0111, all -> 0x010f }
            com.android.messaging.util.exif.c r5 = com.android.messaging.util.exif.C1435d.m3656Ua(r5)     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            boolean r5 = r5.f2251lL     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            if (r5 == 0) goto L_0x006b
            int r5 = r10.mWidth     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            int r6 = r4.getHeight()     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            com.android.messaging.util.C1424b.equals((int) r5, (int) r6)     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            int r5 = r10.mHeight     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            int r6 = r4.getWidth()     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            com.android.messaging.util.C1424b.equals((int) r5, (int) r6)     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            int r5 = r10.mHeight     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            float r5 = (float) r5     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            float r6 = r10.f2052Qd     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            float r5 = r5 * r6
            int r5 = (int) r5     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            int r6 = r10.mWidth     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            goto L_0x0086
        L_0x006b:
            int r5 = r10.mWidth     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            int r6 = r4.getWidth()     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            com.android.messaging.util.C1424b.equals((int) r5, (int) r6)     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            int r5 = r10.mHeight     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            int r6 = r4.getHeight()     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            com.android.messaging.util.C1424b.equals((int) r5, (int) r6)     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            int r5 = r10.mWidth     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            int r6 = r10.mHeight     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            float r6 = (float) r6     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            float r7 = r10.f2052Qd     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            float r6 = r6 * r7
            int r6 = (int) r6     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
        L_0x0086:
            int r7 = r4.getHeight()     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            int r7 = r7 - r6
            int r7 = r7 / 2
            int r8 = r4.getWidth()     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            int r8 = r8 - r5
            int r8 = r8 / 2
            r10.mWidth = r5     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            r10.mHeight = r6     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            android.graphics.Bitmap$Config r9 = android.graphics.Bitmap.Config.ARGB_8888     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            android.graphics.Bitmap r5 = android.graphics.Bitmap.createBitmap(r5, r6, r9)     // Catch:{ IOException -> 0x00d6, all -> 0x00d2 }
            int r6 = r4.getDensity()     // Catch:{ IOException -> 0x00d0 }
            r5.setDensity(r6)     // Catch:{ IOException -> 0x00d0 }
            android.graphics.Canvas r6 = new android.graphics.Canvas     // Catch:{ IOException -> 0x00d0 }
            r6.<init>(r5)     // Catch:{ IOException -> 0x00d0 }
            android.graphics.Matrix r9 = new android.graphics.Matrix     // Catch:{ IOException -> 0x00d0 }
            r9.<init>()     // Catch:{ IOException -> 0x00d0 }
            int r8 = -r8
            float r8 = (float) r8     // Catch:{ IOException -> 0x00d0 }
            int r7 = -r7
            float r7 = (float) r7     // Catch:{ IOException -> 0x00d0 }
            r9.postTranslate(r8, r7)     // Catch:{ IOException -> 0x00d0 }
            r6.drawBitmap(r4, r9, r1)     // Catch:{ IOException -> 0x00d0 }
            r6.save()     // Catch:{ IOException -> 0x00d0 }
            int r6 = com.android.messaging.util.exif.C1435d.TAG_ORIENTATION     // Catch:{ IOException -> 0x00d0 }
            int r7 = r3.mo8096Ta(r6)     // Catch:{ IOException -> 0x00d0 }
            com.android.messaging.util.exif.k r6 = r3.mo8094I(r6, r7)     // Catch:{ IOException -> 0x00d0 }
            r3.mo8102xk()     // Catch:{ IOException -> 0x00d0 }
            r3.mo8099b(r6)     // Catch:{ IOException -> 0x00d0 }
            r3.mo8098a((android.graphics.Bitmap) r5, (java.io.OutputStream) r2)     // Catch:{ IOException -> 0x00d0 }
            goto L_0x00e0
        L_0x00d0:
            r3 = move-exception
            goto L_0x011e
        L_0x00d2:
            r3 = move-exception
            r5 = r1
            goto L_0x0169
        L_0x00d6:
            r3 = move-exception
            r5 = r1
            goto L_0x011e
        L_0x00d9:
            byte[] r3 = r10.mBytes     // Catch:{ IOException -> 0x0111, all -> 0x010f }
            r2.write(r3)     // Catch:{ IOException -> 0x0111, all -> 0x010f }
            r4 = r1
            r5 = r4
        L_0x00e0:
            if (r4 == 0) goto L_0x00e5
            r4.recycle()
        L_0x00e5:
            if (r5 == 0) goto L_0x00ea
            r5.recycle()
        L_0x00ea:
            if (r2 == 0) goto L_0x0167
            r2.flush()     // Catch:{ IOException -> 0x00f3 }
            goto L_0x015f
        L_0x00f1:
            r10 = move-exception
            goto L_0x010b
        L_0x00f3:
            r3 = move-exception
            r10.f2053Rd = r1     // Catch:{ all -> 0x00f1 }
            r10.mException = r3     // Catch:{ all -> 0x00f1 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f1 }
            r10.<init>()     // Catch:{ all -> 0x00f1 }
            r10.append(r11)     // Catch:{ all -> 0x00f1 }
            r10.append(r3)     // Catch:{ all -> 0x00f1 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x00f1 }
            com.android.messaging.util.C1430e.m3622e(r0, r10)     // Catch:{ all -> 0x00f1 }
            goto L_0x015f
        L_0x010b:
            r2.close()     // Catch:{ IOException -> 0x010e }
        L_0x010e:
            throw r10
        L_0x010f:
            r3 = move-exception
            goto L_0x0116
        L_0x0111:
            r3 = move-exception
            goto L_0x011c
        L_0x0113:
            r2 = move-exception
            r3 = r2
            r2 = r1
        L_0x0116:
            r4 = r1
            r5 = r4
            goto L_0x0169
        L_0x0119:
            r2 = move-exception
            r3 = r2
            r2 = r1
        L_0x011c:
            r4 = r1
            r5 = r4
        L_0x011e:
            r10.f2053Rd = r1     // Catch:{ all -> 0x0168 }
            r10.mException = r3     // Catch:{ all -> 0x0168 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0168 }
            r6.<init>()     // Catch:{ all -> 0x0168 }
            java.lang.String r7 = "Unable to persist image to temp storage "
            r6.append(r7)     // Catch:{ all -> 0x0168 }
            r6.append(r3)     // Catch:{ all -> 0x0168 }
            java.lang.String r3 = r6.toString()     // Catch:{ all -> 0x0168 }
            com.android.messaging.util.C1430e.m3622e(r0, r3)     // Catch:{ all -> 0x0168 }
            if (r4 == 0) goto L_0x013b
            r4.recycle()
        L_0x013b:
            if (r5 == 0) goto L_0x0140
            r5.recycle()
        L_0x0140:
            if (r2 == 0) goto L_0x0167
            r2.flush()     // Catch:{ IOException -> 0x0148 }
            goto L_0x015f
        L_0x0146:
            r10 = move-exception
            goto L_0x0163
        L_0x0148:
            r3 = move-exception
            r10.f2053Rd = r1     // Catch:{ all -> 0x0146 }
            r10.mException = r3     // Catch:{ all -> 0x0146 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0146 }
            r10.<init>()     // Catch:{ all -> 0x0146 }
            r10.append(r11)     // Catch:{ all -> 0x0146 }
            r10.append(r3)     // Catch:{ all -> 0x0146 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x0146 }
            com.android.messaging.util.C1430e.m3622e(r0, r10)     // Catch:{ all -> 0x0146 }
        L_0x015f:
            r2.close()     // Catch:{ IOException -> 0x0167 }
            goto L_0x0167
        L_0x0163:
            r2.close()     // Catch:{ IOException -> 0x0166 }
        L_0x0166:
            throw r10
        L_0x0167:
            return r1
        L_0x0168:
            r3 = move-exception
        L_0x0169:
            if (r4 == 0) goto L_0x016e
            r4.recycle()
        L_0x016e:
            if (r5 == 0) goto L_0x0173
            r5.recycle()
        L_0x0173:
            if (r2 == 0) goto L_0x019a
            r2.flush()     // Catch:{ IOException -> 0x017b }
            goto L_0x0192
        L_0x0179:
            r10 = move-exception
            goto L_0x0196
        L_0x017b:
            r4 = move-exception
            r10.f2053Rd = r1     // Catch:{ all -> 0x0179 }
            r10.mException = r4     // Catch:{ all -> 0x0179 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0179 }
            r10.<init>()     // Catch:{ all -> 0x0179 }
            r10.append(r11)     // Catch:{ all -> 0x0179 }
            r10.append(r4)     // Catch:{ all -> 0x0179 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x0179 }
            com.android.messaging.util.C1430e.m3622e(r0, r10)     // Catch:{ all -> 0x0179 }
        L_0x0192:
            r2.close()     // Catch:{ IOException -> 0x019a }
            goto L_0x019a
        L_0x0196:
            r2.close()     // Catch:{ IOException -> 0x0199 }
        L_0x0199:
            throw r10
        L_0x019a:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.p041ui.mediapicker.C1297Z.mo6323a(java.lang.Object[]):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Object obj) {
        Void voidR = (Void) obj;
        Uri uri = this.f2053Rd;
        if (uri != null) {
            this.mCallback.mo7968a(uri, "image/jpeg", this.mWidth, this.mHeight);
            return;
        }
        C1424b.m3594t(this.mException);
        this.mCallback.mo7969b(this.mException);
    }
}
