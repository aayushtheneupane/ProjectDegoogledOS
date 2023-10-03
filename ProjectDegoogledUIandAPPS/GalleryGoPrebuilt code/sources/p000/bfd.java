package p000;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: bfd */
/* compiled from: PG */
public final class bfd {

    /* renamed from: a */
    public static /* synthetic */ int f2200a;

    static {
        new AtomicReference();
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0050 A[SYNTHETIC, Splitter:B:29:0x0050] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0057 A[SYNTHETIC, Splitter:B:33:0x0057] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.nio.ByteBuffer m2405a(java.io.File r8) {
        /*
            r0 = 0
            long r5 = r8.length()     // Catch:{ all -> 0x004c }
            r1 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r3 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r3 > 0) goto L_0x0044
            r1 = 0
            int r3 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r3 == 0) goto L_0x003c
            java.io.RandomAccessFile r7 = new java.io.RandomAccessFile     // Catch:{ all -> 0x004c }
            java.lang.String r1 = "r"
            r7.<init>(r8, r1)     // Catch:{ all -> 0x004c }
            java.nio.channels.FileChannel r0 = r7.getChannel()     // Catch:{ all -> 0x0039 }
            java.nio.channels.FileChannel$MapMode r2 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch:{ all -> 0x0037 }
            r3 = 0
            r1 = r0
            java.nio.MappedByteBuffer r8 = r1.map(r2, r3, r5)     // Catch:{ all -> 0x0037 }
            java.nio.MappedByteBuffer r8 = r8.load()     // Catch:{ all -> 0x0037 }
            if (r0 == 0) goto L_0x0031
            r0.close()     // Catch:{ IOException -> 0x0030 }
            goto L_0x0031
        L_0x0030:
            r0 = move-exception
        L_0x0031:
            r7.close()     // Catch:{ IOException -> 0x0035 }
            goto L_0x0036
        L_0x0035:
            r0 = move-exception
        L_0x0036:
            return r8
        L_0x0037:
            r8 = move-exception
            goto L_0x003b
        L_0x0039:
            r8 = move-exception
        L_0x003b:
            goto L_0x004e
        L_0x003c:
            java.io.IOException r8 = new java.io.IOException     // Catch:{ all -> 0x004c }
            java.lang.String r1 = "File unsuitable for memory mapping"
            r8.<init>(r1)     // Catch:{ all -> 0x004c }
            throw r8     // Catch:{ all -> 0x004c }
        L_0x0044:
            java.io.IOException r8 = new java.io.IOException     // Catch:{ all -> 0x004c }
            java.lang.String r1 = "File too large to map into memory"
            r8.<init>(r1)     // Catch:{ all -> 0x004c }
            throw r8     // Catch:{ all -> 0x004c }
        L_0x004c:
            r8 = move-exception
            r7 = r0
        L_0x004e:
            if (r0 == 0) goto L_0x0055
            r0.close()     // Catch:{ IOException -> 0x0054 }
            goto L_0x0055
        L_0x0054:
            r0 = move-exception
        L_0x0055:
            if (r7 == 0) goto L_0x005c
            r7.close()     // Catch:{ IOException -> 0x005b }
            goto L_0x005c
        L_0x005b:
            r0 = move-exception
        L_0x005c:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.bfd.m2405a(java.io.File):java.nio.ByteBuffer");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0030 A[SYNTHETIC, Splitter:B:18:0x0030] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0038 A[SYNTHETIC, Splitter:B:22:0x0038] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m2406a(java.nio.ByteBuffer r4, java.io.File r5) {
        /*
            r0 = 0
            r4.position(r0)
            r1 = 0
            java.io.RandomAccessFile r2 = new java.io.RandomAccessFile     // Catch:{ all -> 0x002c }
            java.lang.String r3 = "rw"
            r2.<init>(r5, r3)     // Catch:{ all -> 0x002c }
            java.nio.channels.FileChannel r1 = r2.getChannel()     // Catch:{ all -> 0x0029 }
            r1.write(r4)     // Catch:{ all -> 0x0029 }
            r1.force(r0)     // Catch:{ all -> 0x0029 }
            r1.close()     // Catch:{ all -> 0x0029 }
            r2.close()     // Catch:{ all -> 0x0029 }
            if (r1 == 0) goto L_0x0023
            r1.close()     // Catch:{ IOException -> 0x0022 }
            goto L_0x0023
        L_0x0022:
            r4 = move-exception
        L_0x0023:
            r2.close()     // Catch:{ IOException -> 0x0027 }
            return
        L_0x0027:
            r4 = move-exception
            return
        L_0x0029:
            r4 = move-exception
            goto L_0x002e
        L_0x002c:
            r4 = move-exception
            r2 = r1
        L_0x002e:
            if (r1 == 0) goto L_0x0035
            r1.close()     // Catch:{ IOException -> 0x0034 }
            goto L_0x0035
        L_0x0034:
            r5 = move-exception
        L_0x0035:
            if (r2 != 0) goto L_0x0038
            goto L_0x003d
        L_0x0038:
            r2.close()     // Catch:{ IOException -> 0x003c }
            goto L_0x003d
        L_0x003c:
            r5 = move-exception
        L_0x003d:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.bfd.m2406a(java.nio.ByteBuffer, java.io.File):void");
    }

    /* renamed from: a */
    public static InputStream m2404a(ByteBuffer byteBuffer) {
        return new bfb(byteBuffer);
    }
}
