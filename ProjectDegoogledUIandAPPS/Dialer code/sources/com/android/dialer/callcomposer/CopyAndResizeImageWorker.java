package com.android.dialer.callcomposer;

import android.content.Context;
import android.net.Uri;
import android.support.p000v4.util.Pair;
import com.android.dialer.common.Assert;
import com.android.dialer.common.concurrent.DialerExecutor;
import java.io.File;

class CopyAndResizeImageWorker implements DialerExecutor.Worker<Uri, Pair<File, String>> {
    private final Context context;

    private static /* synthetic */ void $closeResource(Throwable th, AutoCloseable autoCloseable) {
        if (th != null) {
            try {
                autoCloseable.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } else {
            autoCloseable.close();
        }
    }

    CopyAndResizeImageWorker(Context context2) {
        Assert.isNotNull(context2);
        this.context = context2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        $closeResource(r4, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0063, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0066, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0067, code lost:
        if (r5 != null) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0069, code lost:
        $closeResource(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x006c, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object doInBackground(java.lang.Object r5) throws java.lang.Throwable {
        /*
            r4 = this;
            android.net.Uri r5 = (android.net.Uri) r5
            r0 = 0
            android.media.ExifInterface r1 = new android.media.ExifInterface     // Catch:{ Exception -> 0x0014 }
            java.lang.String r2 = r5.getPath()     // Catch:{ Exception -> 0x0014 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0014 }
            java.lang.String r2 = "Orientation"
            r3 = 1
            int r1 = r1.getAttributeInt(r2, r3)     // Catch:{ Exception -> 0x0014 }
            goto L_0x0015
        L_0x0014:
            r1 = r0
        L_0x0015:
            android.content.Context r2 = r4.context
            android.content.ContentResolver r2 = r2.getContentResolver()
            java.io.InputStream r5 = r2.openInputStream(r5)
            android.graphics.Bitmap r2 = android.graphics.BitmapFactory.decodeStream(r5)     // Catch:{ all -> 0x0064 }
            r3 = 3
            if (r1 == r3) goto L_0x0034
            r3 = 6
            if (r1 == r3) goto L_0x0031
            r3 = 8
            if (r1 == r3) goto L_0x002e
            goto L_0x0036
        L_0x002e:
            r0 = 270(0x10e, float:3.78E-43)
            goto L_0x0036
        L_0x0031:
            r0 = 90
            goto L_0x0036
        L_0x0034:
            r0 = 180(0xb4, float:2.52E-43)
        L_0x0036:
            android.graphics.Bitmap r0 = com.android.dialer.callcomposer.util.BitmapResizer.resizeForEnrichedCalling(r2, r0)     // Catch:{ all -> 0x0064 }
            android.content.Context r4 = r4.context     // Catch:{ all -> 0x0064 }
            java.io.File r4 = com.android.dialer.util.DialerUtils.createShareableFile(r4)     // Catch:{ all -> 0x0064 }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x0064 }
            r1.<init>(r4)     // Catch:{ all -> 0x0064 }
            android.graphics.Bitmap$CompressFormat r2 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ all -> 0x005d }
            r3 = 80
            r0.compress(r2, r3, r1)     // Catch:{ all -> 0x005d }
            android.support.v4.util.Pair r0 = new android.support.v4.util.Pair     // Catch:{ all -> 0x005d }
            java.lang.String r2 = "image/jpeg"
            r0.<init>(r4, r2)     // Catch:{ all -> 0x005d }
            r4 = 0
            $closeResource(r4, r1)     // Catch:{ all -> 0x0064 }
            if (r5 == 0) goto L_0x005c
            $closeResource(r4, r5)
        L_0x005c:
            return r0
        L_0x005d:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x005f }
        L_0x005f:
            r0 = move-exception
            $closeResource(r4, r1)     // Catch:{ all -> 0x0064 }
            throw r0     // Catch:{ all -> 0x0064 }
        L_0x0064:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0066 }
        L_0x0066:
            r0 = move-exception
            if (r5 == 0) goto L_0x006c
            $closeResource(r4, r5)
        L_0x006c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.callcomposer.CopyAndResizeImageWorker.doInBackground(java.lang.Object):java.lang.Object");
    }
}
