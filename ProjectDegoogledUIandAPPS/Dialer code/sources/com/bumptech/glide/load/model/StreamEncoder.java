package com.bumptech.glide.load.model;

import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.InputStream;

public class StreamEncoder implements Encoder<InputStream> {
    private final ArrayPool byteArrayPool;

    public StreamEncoder(ArrayPool arrayPool) {
        this.byteArrayPool = arrayPool;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x003a A[Catch:{ all -> 0x002f }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0041 A[SYNTHETIC, Splitter:B:23:0x0041] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x004c A[SYNTHETIC, Splitter:B:29:0x004c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean encode(java.lang.Object r5, java.io.File r6, com.bumptech.glide.load.Options r7) {
        /*
            r4 = this;
            java.io.InputStream r5 = (java.io.InputStream) r5
            java.lang.String r7 = "StreamEncoder"
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r0 = r4.byteArrayPool
            java.lang.Class<byte[]> r1 = byte[].class
            r2 = 65536(0x10000, float:9.18355E-41)
            java.lang.Object r0 = r0.get(r2, r1)
            byte[] r0 = (byte[]) r0
            r1 = 0
            r2 = 0
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0032 }
            r3.<init>(r6)     // Catch:{ IOException -> 0x0032 }
        L_0x0017:
            int r6 = r5.read(r0)     // Catch:{ IOException -> 0x002c, all -> 0x002a }
            r2 = -1
            if (r6 == r2) goto L_0x0022
            r3.write(r0, r1, r6)     // Catch:{ IOException -> 0x002c, all -> 0x002a }
            goto L_0x0017
        L_0x0022:
            r3.close()     // Catch:{ IOException -> 0x002c, all -> 0x002a }
            r1 = 1
            r3.close()     // Catch:{ IOException -> 0x0044 }
            goto L_0x0044
        L_0x002a:
            r5 = move-exception
            goto L_0x004a
        L_0x002c:
            r5 = move-exception
            r2 = r3
            goto L_0x0033
        L_0x002f:
            r5 = move-exception
            r3 = r2
            goto L_0x004a
        L_0x0032:
            r5 = move-exception
        L_0x0033:
            r6 = 3
            boolean r6 = android.util.Log.isLoggable(r7, r6)     // Catch:{ all -> 0x002f }
            if (r6 == 0) goto L_0x003f
            java.lang.String r6 = "Failed to encode data onto the OutputStream"
            android.util.Log.d(r7, r6, r5)     // Catch:{ all -> 0x002f }
        L_0x003f:
            if (r2 == 0) goto L_0x0044
            r2.close()     // Catch:{ IOException -> 0x0044 }
        L_0x0044:
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r4 = r4.byteArrayPool
            r4.put(r0)
            return r1
        L_0x004a:
            if (r3 == 0) goto L_0x004f
            r3.close()     // Catch:{ IOException -> 0x004f }
        L_0x004f:
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r4 = r4.byteArrayPool
            r4.put(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.model.StreamEncoder.encode(java.lang.Object, java.io.File, com.bumptech.glide.load.Options):boolean");
    }
}
