package android.support.p000v4.graphics;

import android.content.Context;
import android.os.Process;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.File;
import java.io.IOException;

/* renamed from: android.support.v4.graphics.PathParser */
public class PathParser {
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0040 A[SYNTHETIC, Splitter:B:24:0x0040] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0046 A[SYNTHETIC, Splitter:B:29:0x0046] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean copyToFile(java.io.File r4, java.io.InputStream r5) {
        /*
            r0 = 0
            r1 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0023 }
            r2.<init>(r4, r0)     // Catch:{ IOException -> 0x0023 }
            r4 = 1024(0x400, float:1.435E-42)
            byte[] r4 = new byte[r4]     // Catch:{ IOException -> 0x001e, all -> 0x001b }
        L_0x000b:
            int r1 = r5.read(r4)     // Catch:{ IOException -> 0x001e, all -> 0x001b }
            r3 = -1
            if (r1 == r3) goto L_0x0016
            r2.write(r4, r0, r1)     // Catch:{ IOException -> 0x001e, all -> 0x001b }
            goto L_0x000b
        L_0x0016:
            r4 = 1
            r2.close()     // Catch:{ IOException -> 0x001a }
        L_0x001a:
            return r4
        L_0x001b:
            r4 = move-exception
            r1 = r2
            goto L_0x0044
        L_0x001e:
            r4 = move-exception
            r1 = r2
            goto L_0x0024
        L_0x0021:
            r4 = move-exception
            goto L_0x0044
        L_0x0023:
            r4 = move-exception
        L_0x0024:
            java.lang.String r5 = "TypefaceCompatUtil"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0021 }
            r2.<init>()     // Catch:{ all -> 0x0021 }
            java.lang.String r3 = "Error copying resource contents to temp file: "
            r2.append(r3)     // Catch:{ all -> 0x0021 }
            java.lang.String r4 = r4.getMessage()     // Catch:{ all -> 0x0021 }
            r2.append(r4)     // Catch:{ all -> 0x0021 }
            java.lang.String r4 = r2.toString()     // Catch:{ all -> 0x0021 }
            android.util.Log.e(r5, r4)     // Catch:{ all -> 0x0021 }
            if (r1 == 0) goto L_0x0043
            r1.close()     // Catch:{ IOException -> 0x0043 }
        L_0x0043:
            return r0
        L_0x0044:
            if (r1 == 0) goto L_0x0049
            r1.close()     // Catch:{ IOException -> 0x0049 }
        L_0x0049:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.graphics.PathParser.copyToFile(java.io.File, java.io.InputStream):boolean");
    }

    public static File getTempFile(Context context) {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13(".font");
        outline13.append(Process.myPid());
        outline13.append("-");
        outline13.append(Process.myTid());
        outline13.append("-");
        String sb = outline13.toString();
        int i = 0;
        while (i < 100) {
            File file = new File(context.getCacheDir(), GeneratedOutlineSupport.outline5(sb, i));
            try {
                if (file.createNewFile()) {
                    return file;
                }
                i++;
            } catch (IOException unused) {
            }
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0035, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003a, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r9.addSuppressed(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003e, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0041, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x004a, code lost:
        throw r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.nio.ByteBuffer mmap(android.content.Context r7, android.os.CancellationSignal r8, android.net.Uri r9) {
        /*
            android.content.ContentResolver r7 = r7.getContentResolver()
            r0 = 0
            java.lang.String r1 = "r"
            android.os.ParcelFileDescriptor r7 = r7.openFileDescriptor(r9, r1, r8)     // Catch:{ IOException -> 0x004b }
            if (r7 != 0) goto L_0x0013
            if (r7 == 0) goto L_0x0012
            r7.close()     // Catch:{ IOException -> 0x004b }
        L_0x0012:
            return r0
        L_0x0013:
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch:{ all -> 0x003f }
            java.io.FileDescriptor r9 = r7.getFileDescriptor()     // Catch:{ all -> 0x003f }
            r8.<init>(r9)     // Catch:{ all -> 0x003f }
            java.nio.channels.FileChannel r1 = r8.getChannel()     // Catch:{ all -> 0x0033 }
            long r5 = r1.size()     // Catch:{ all -> 0x0033 }
            java.nio.channels.FileChannel$MapMode r2 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch:{ all -> 0x0033 }
            r3 = 0
            java.nio.MappedByteBuffer r9 = r1.map(r2, r3, r5)     // Catch:{ all -> 0x0033 }
            r8.close()     // Catch:{ all -> 0x003f }
            r7.close()     // Catch:{ IOException -> 0x004b }
            return r9
        L_0x0033:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x0035 }
        L_0x0035:
            r1 = move-exception
            r8.close()     // Catch:{ all -> 0x003a }
            goto L_0x003e
        L_0x003a:
            r8 = move-exception
            r9.addSuppressed(r8)     // Catch:{ all -> 0x003f }
        L_0x003e:
            throw r1     // Catch:{ all -> 0x003f }
        L_0x003f:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0041 }
        L_0x0041:
            r9 = move-exception
            r7.close()     // Catch:{ all -> 0x0046 }
            goto L_0x004a
        L_0x0046:
            r7 = move-exception
            r8.addSuppressed(r7)     // Catch:{ IOException -> 0x004b }
        L_0x004a:
            throw r9     // Catch:{ IOException -> 0x004b }
        L_0x004b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.graphics.PathParser.mmap(android.content.Context, android.os.CancellationSignal, android.net.Uri):java.nio.ByteBuffer");
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0014 A[SYNTHETIC, Splitter:B:13:0x0014] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean copyToFile(java.io.File r0, android.content.res.Resources r1, int r2) {
        /*
            java.io.InputStream r1 = r1.openRawResource(r2)     // Catch:{ all -> 0x0010 }
            boolean r0 = copyToFile(r0, r1)     // Catch:{ all -> 0x000e }
            if (r1 == 0) goto L_0x000d
            r1.close()     // Catch:{ IOException -> 0x000d }
        L_0x000d:
            return r0
        L_0x000e:
            r0 = move-exception
            goto L_0x0012
        L_0x0010:
            r0 = move-exception
            r1 = 0
        L_0x0012:
            if (r1 == 0) goto L_0x0017
            r1.close()     // Catch:{ IOException -> 0x0017 }
        L_0x0017:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.graphics.PathParser.copyToFile(java.io.File, android.content.res.Resources, int):boolean");
    }
}
