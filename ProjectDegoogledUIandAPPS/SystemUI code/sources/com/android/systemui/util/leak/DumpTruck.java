package com.android.systemui.util.leak;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Debug;
import android.os.Process;
import android.util.Log;
import androidx.core.content.FileProvider;
import com.android.systemui.Dependency;
import com.android.systemui.util.leak.GarbageMonitor;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DumpTruck {
    final StringBuilder body = new StringBuilder();
    private final Context context;
    private Uri hprofUri;
    private long pss;

    public DumpTruck(Context context2) {
        this.context = context2;
    }

    public DumpTruck captureHeaps(int[] iArr) {
        int i;
        GarbageMonitor.ProcessMemInfo memInfo;
        int[] iArr2 = iArr;
        GarbageMonitor garbageMonitor = (GarbageMonitor) Dependency.get(GarbageMonitor.class);
        File file = new File(this.context.getCacheDir(), "leak");
        file.mkdirs();
        this.hprofUri = null;
        this.body.setLength(0);
        StringBuilder sb = this.body;
        sb.append("Build: ");
        sb.append(Build.DISPLAY);
        sb.append("\n\nProcesses:\n");
        ArrayList arrayList = new ArrayList();
        int myPid = Process.myPid();
        int[] copyOf = Arrays.copyOf(iArr2, iArr2.length);
        int length = copyOf.length;
        int i2 = 0;
        while (i2 < length) {
            int i3 = copyOf[i2];
            StringBuilder sb2 = this.body;
            sb2.append("  pid ");
            sb2.append(i3);
            if (garbageMonitor == null || (memInfo = garbageMonitor.getMemInfo(i3)) == null) {
                i = myPid;
            } else {
                StringBuilder sb3 = this.body;
                sb3.append(":");
                sb3.append(" up=");
                sb3.append(memInfo.getUptime());
                sb3.append(" pss=");
                sb3.append(memInfo.currentPss);
                sb3.append(" uss=");
                sb3.append(memInfo.currentUss);
                this.pss = memInfo.currentPss;
                i = myPid;
            }
            if (i3 == i) {
                String path = new File(file, String.format("heap-%d.ahprof", new Object[]{Integer.valueOf(i3)})).getPath();
                Log.v("DumpTruck", "Dumping memory info for process " + i3 + " to " + path);
                try {
                    Debug.dumpHprofData(path);
                    arrayList.add(path);
                    this.body.append(" (hprof attached)");
                } catch (IOException e) {
                    Log.e("DumpTruck", "error dumping memory:", e);
                    StringBuilder sb4 = this.body;
                    sb4.append("\n** Could not dump heap: \n");
                    sb4.append(e.toString());
                    sb4.append("\n");
                }
            }
            this.body.append("\n");
            i2++;
            myPid = i;
        }
        try {
            String canonicalPath = new File(file, String.format("hprof-%d.zip", new Object[]{Long.valueOf(System.currentTimeMillis())})).getCanonicalPath();
            if (zipUp(canonicalPath, arrayList)) {
                this.hprofUri = FileProvider.getUriForFile(this.context, "com.android.systemui.fileprovider", new File(canonicalPath));
                Log.v("DumpTruck", "Heap dump accessible at URI: " + this.hprofUri);
            }
        } catch (IOException e2) {
            Log.e("DumpTruck", "unable to zip up heapdumps", e2);
            StringBuilder sb5 = this.body;
            sb5.append("\n** Could not zip up files: \n");
            sb5.append(e2.toString());
            sb5.append("\n");
        }
        return this;
    }

    public Intent createShareIntent() {
        Intent intent = new Intent("android.intent.action.SEND_MULTIPLE");
        intent.addFlags(268435456);
        intent.addFlags(1);
        intent.putExtra("android.intent.extra.SUBJECT", String.format("SystemUI memory dump (pss=%dM)", new Object[]{Long.valueOf(this.pss / 1024)}));
        intent.putExtra("android.intent.extra.TEXT", this.body.toString());
        if (this.hprofUri != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.hprofUri);
            intent.setType("application/zip");
            intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
            intent.setClipData(new ClipData(new ClipDescription("content", new String[]{"text/plain"}), new ClipData.Item(this.hprofUri)));
            intent.addFlags(1);
        }
        return intent;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0045, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        $closeResource(r7, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0049, code lost:
        throw r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0051, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        $closeResource(r7, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0055, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean zipUp(java.lang.String r7, java.util.ArrayList<java.lang.String> r8) {
        /*
            r0 = 0
            java.util.zip.ZipOutputStream r1 = new java.util.zip.ZipOutputStream     // Catch:{ IOException -> 0x0056 }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0056 }
            r2.<init>(r7)     // Catch:{ IOException -> 0x0056 }
            r1.<init>(r2)     // Catch:{ IOException -> 0x0056 }
            r7 = 1048576(0x100000, float:1.469368E-39)
            byte[] r2 = new byte[r7]     // Catch:{ all -> 0x004f }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ all -> 0x004f }
        L_0x0013:
            boolean r3 = r8.hasNext()     // Catch:{ all -> 0x004f }
            r4 = 0
            if (r3 == 0) goto L_0x004a
            java.lang.Object r3 = r8.next()     // Catch:{ all -> 0x004f }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x004f }
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch:{ all -> 0x004f }
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ all -> 0x004f }
            r6.<init>(r3)     // Catch:{ all -> 0x004f }
            r5.<init>(r6)     // Catch:{ all -> 0x004f }
            java.util.zip.ZipEntry r6 = new java.util.zip.ZipEntry     // Catch:{ all -> 0x0043 }
            r6.<init>(r3)     // Catch:{ all -> 0x0043 }
            r1.putNextEntry(r6)     // Catch:{ all -> 0x0043 }
        L_0x0032:
            int r3 = r5.read(r2, r0, r7)     // Catch:{ all -> 0x0043 }
            if (r3 <= 0) goto L_0x003c
            r1.write(r2, r0, r3)     // Catch:{ all -> 0x0043 }
            goto L_0x0032
        L_0x003c:
            r1.closeEntry()     // Catch:{ all -> 0x0043 }
            $closeResource(r4, r5)     // Catch:{ all -> 0x004f }
            goto L_0x0013
        L_0x0043:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0045 }
        L_0x0045:
            r8 = move-exception
            $closeResource(r7, r5)     // Catch:{ all -> 0x004f }
            throw r8     // Catch:{ all -> 0x004f }
        L_0x004a:
            r7 = 1
            $closeResource(r4, r1)     // Catch:{ IOException -> 0x0056 }
            return r7
        L_0x004f:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0051 }
        L_0x0051:
            r8 = move-exception
            $closeResource(r7, r1)     // Catch:{ IOException -> 0x0056 }
            throw r8     // Catch:{ IOException -> 0x0056 }
        L_0x0056:
            r7 = move-exception
            java.lang.String r8 = "DumpTruck"
            java.lang.String r1 = "error zipping up profile data"
            android.util.Log.e(r8, r1, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.util.leak.DumpTruck.zipUp(java.lang.String, java.util.ArrayList):boolean");
    }

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
}
