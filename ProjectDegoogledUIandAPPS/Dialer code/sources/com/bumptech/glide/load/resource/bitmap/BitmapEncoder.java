package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;

public class BitmapEncoder implements ResourceEncoder<Bitmap> {
    public static final Option<Bitmap.CompressFormat> COMPRESSION_FORMAT = Option.memory("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionFormat");
    public static final Option<Integer> COMPRESSION_QUALITY = Option.memory("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionQuality", 90);
    private final ArrayPool arrayPool;

    public BitmapEncoder(ArrayPool arrayPool2) {
        this.arrayPool = arrayPool2;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:24|25|(2:44|45)|46|47) */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0095, code lost:
        if (r6 == null) goto L_0x009a;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:46:0x0105 */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0090 A[Catch:{ all -> 0x0085 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0102 A[SYNTHETIC, Splitter:B:44:0x0102] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean encode(java.lang.Object r9, java.io.File r10, com.bumptech.glide.load.Options r11) {
        /*
            r8 = this;
            com.bumptech.glide.load.engine.Resource r9 = (com.bumptech.glide.load.engine.Resource) r9
            java.lang.String r0 = "BitmapEncoder"
            java.lang.Object r9 = r9.get()
            android.graphics.Bitmap r9 = (android.graphics.Bitmap) r9
            com.bumptech.glide.load.Option<android.graphics.Bitmap$CompressFormat> r1 = COMPRESSION_FORMAT
            java.lang.Object r1 = r11.get(r1)
            android.graphics.Bitmap$CompressFormat r1 = (android.graphics.Bitmap.CompressFormat) r1
            if (r1 == 0) goto L_0x0015
            goto L_0x0020
        L_0x0015:
            boolean r1 = r9.hasAlpha()
            if (r1 == 0) goto L_0x001e
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.PNG
            goto L_0x0020
        L_0x001e:
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.JPEG
        L_0x0020:
            int r2 = r9.getWidth()
            int r3 = r9.getHeight()
            java.lang.String r4 = java.lang.String.valueOf(r1)
            int r5 = r4.length()
            int r5 = r5 + 34
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r5)
            java.lang.String r5 = "encode: ["
            r6.append(r5)
            r6.append(r2)
            java.lang.String r2 = "x"
            r6.append(r2)
            r6.append(r3)
            java.lang.String r2 = "] "
            java.lang.String r2 = com.android.tools.p006r8.GeneratedOutlineSupport.outline12(r6, r2, r4)
            int r3 = android.os.Build.VERSION.SDK_INT
            android.os.Trace.beginSection(r2)
            long r2 = com.bumptech.glide.util.LogTime.getLogTime()     // Catch:{ all -> 0x0106 }
            com.bumptech.glide.load.Option<java.lang.Integer> r4 = COMPRESSION_QUALITY     // Catch:{ all -> 0x0106 }
            java.lang.Object r4 = r11.get(r4)     // Catch:{ all -> 0x0106 }
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ all -> 0x0106 }
            int r4 = r4.intValue()     // Catch:{ all -> 0x0106 }
            r5 = 0
            r6 = 0
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0088 }
            r7.<init>(r10)     // Catch:{ IOException -> 0x0088 }
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r10 = r8.arrayPool     // Catch:{ IOException -> 0x0082, all -> 0x007f }
            if (r10 == 0) goto L_0x0076
            com.bumptech.glide.load.data.BufferedOutputStream r10 = new com.bumptech.glide.load.data.BufferedOutputStream     // Catch:{ IOException -> 0x0082, all -> 0x007f }
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r8 = r8.arrayPool     // Catch:{ IOException -> 0x0082, all -> 0x007f }
            r10.<init>(r7, r8)     // Catch:{ IOException -> 0x0082, all -> 0x007f }
            r6 = r10
            goto L_0x0077
        L_0x0076:
            r6 = r7
        L_0x0077:
            r9.compress(r1, r4, r6)     // Catch:{ IOException -> 0x0088 }
            r6.close()     // Catch:{ IOException -> 0x0088 }
            r5 = 1
            goto L_0x0097
        L_0x007f:
            r8 = move-exception
            goto L_0x0100
        L_0x0082:
            r8 = move-exception
            r6 = r7
            goto L_0x0089
        L_0x0085:
            r8 = move-exception
            r7 = r6
            goto L_0x0100
        L_0x0088:
            r8 = move-exception
        L_0x0089:
            r10 = 3
            boolean r10 = android.util.Log.isLoggable(r0, r10)     // Catch:{ all -> 0x0085 }
            if (r10 == 0) goto L_0x0095
            java.lang.String r10 = "Failed to encode Bitmap"
            android.util.Log.d(r0, r10, r8)     // Catch:{ all -> 0x0085 }
        L_0x0095:
            if (r6 == 0) goto L_0x009a
        L_0x0097:
            r6.close()     // Catch:{ IOException -> 0x009a }
        L_0x009a:
            r8 = 2
            boolean r8 = android.util.Log.isLoggable(r0, r8)     // Catch:{ all -> 0x0106 }
            if (r8 == 0) goto L_0x00fa
            java.lang.String r8 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x0106 }
            int r10 = com.bumptech.glide.util.Util.getBitmapByteSize(r9)     // Catch:{ all -> 0x0106 }
            double r1 = com.bumptech.glide.util.LogTime.getElapsedMillis(r2)     // Catch:{ all -> 0x0106 }
            com.bumptech.glide.load.Option<android.graphics.Bitmap$CompressFormat> r3 = COMPRESSION_FORMAT     // Catch:{ all -> 0x0106 }
            java.lang.Object r11 = r11.get(r3)     // Catch:{ all -> 0x0106 }
            java.lang.String r11 = java.lang.String.valueOf(r11)     // Catch:{ all -> 0x0106 }
            boolean r9 = r9.hasAlpha()     // Catch:{ all -> 0x0106 }
            int r3 = r8.length()     // Catch:{ all -> 0x0106 }
            int r3 = r3 + 105
            int r4 = r11.length()     // Catch:{ all -> 0x0106 }
            int r3 = r3 + r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0106 }
            r4.<init>(r3)     // Catch:{ all -> 0x0106 }
            java.lang.String r3 = "Compressed with type: "
            r4.append(r3)     // Catch:{ all -> 0x0106 }
            r4.append(r8)     // Catch:{ all -> 0x0106 }
            java.lang.String r8 = " of size "
            r4.append(r8)     // Catch:{ all -> 0x0106 }
            r4.append(r10)     // Catch:{ all -> 0x0106 }
            java.lang.String r8 = " in "
            r4.append(r8)     // Catch:{ all -> 0x0106 }
            r4.append(r1)     // Catch:{ all -> 0x0106 }
            java.lang.String r8 = ", options format: "
            r4.append(r8)     // Catch:{ all -> 0x0106 }
            r4.append(r11)     // Catch:{ all -> 0x0106 }
            java.lang.String r8 = ", hasAlpha: "
            r4.append(r8)     // Catch:{ all -> 0x0106 }
            r4.append(r9)     // Catch:{ all -> 0x0106 }
            java.lang.String r8 = r4.toString()     // Catch:{ all -> 0x0106 }
            android.util.Log.v(r0, r8)     // Catch:{ all -> 0x0106 }
        L_0x00fa:
            int r8 = android.os.Build.VERSION.SDK_INT
            android.os.Trace.endSection()
            return r5
        L_0x0100:
            if (r7 == 0) goto L_0x0105
            r7.close()     // Catch:{ IOException -> 0x0105 }
        L_0x0105:
            throw r8     // Catch:{ all -> 0x0106 }
        L_0x0106:
            r8 = move-exception
            int r9 = android.os.Build.VERSION.SDK_INT
            android.os.Trace.endSection()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.BitmapEncoder.encode(java.lang.Object, java.io.File, com.bumptech.glide.load.Options):boolean");
    }

    public EncodeStrategy getEncodeStrategy(Options options) {
        return EncodeStrategy.TRANSFORMED;
    }
}
