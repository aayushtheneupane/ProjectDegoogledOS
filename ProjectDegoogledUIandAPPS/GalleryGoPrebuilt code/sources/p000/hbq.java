package p000;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import java.util.HashMap;
import java.util.Map;

/* renamed from: hbq */
/* compiled from: PG */
public class hbq extends Application implements hbe, hbs {

    /* renamed from: b */
    public hlz f12461b;

    /* renamed from: c */
    public gtk f12462c;

    /* renamed from: d */
    public Map f12463d;

    /* renamed from: e */
    public Map f12464e;

    static {
        SystemClock.elapsedRealtime();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v11, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v1, resolved type: java.lang.Class<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: java.lang.Class<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: java.lang.Class<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v18, resolved type: java.util.Set} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v27, resolved type: java.util.Set} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v31, resolved type: java.util.Set} */
    /* JADX WARNING: type inference failed for: r1v6, types: [boolean] */
    /* JADX WARNING: type inference failed for: r1v21 */
    /* JADX WARNING: type inference failed for: r1v25 */
    /* JADX WARNING: type inference failed for: r1v32 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0390 A[SYNTHETIC, Splitter:B:100:0x0390] */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x03b4 A[Catch:{ IOException -> 0x0394, all -> 0x054f }] */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x03b5 A[Catch:{ IOException -> 0x0394, all -> 0x054f }] */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x03bd A[Catch:{ IOException -> 0x0394, all -> 0x054f }] */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x04da A[Catch:{ IOException -> 0x0394, all -> 0x054f }] */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x0505 A[SYNTHETIC, Splitter:B:148:0x0505] */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x0526 A[Catch:{ IOException -> 0x0394, all -> 0x054f }] */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x0527 A[Catch:{ IOException -> 0x0394, all -> 0x054f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void attachBaseContext(android.content.Context r29) {
        /*
            r28 = this;
            boolean r0 = mo455a()
            if (r0 != 0) goto L_0x000a
            super.attachBaseContext(r29)
            return
        L_0x000a:
            super.attachBaseContext(r29)
            boolean r0 = p000.C0123ek.f8462b
            if (r0 != 0) goto L_0x0578
            int r0 = android.os.Build.VERSION.SDK_INT
            android.content.pm.ApplicationInfo r0 = r28.getApplicationInfo()     // Catch:{ RuntimeException -> 0x001c }
            r2 = r0
            goto L_0x0027
        L_0x0019:
            r0 = move-exception
            goto L_0x0551
        L_0x001c:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = "MultiDex"
            java.lang.String r3 = "Failure while trying to obtain ApplicationInfo from Context. Must be running in test mode. Skip patching."
            android.util.Log.w(r0, r3, r2)     // Catch:{ Exception -> 0x0019 }
            r2 = 0
        L_0x0027:
            if (r2 == 0) goto L_0x0578
            java.util.Set r3 = p000.C0123ek.f8461a     // Catch:{ Exception -> 0x0019 }
            monitor-enter(r3)     // Catch:{ Exception -> 0x0019 }
            java.lang.String r0 = r2.sourceDir     // Catch:{ all -> 0x054a }
            java.util.Set r4 = p000.C0123ek.f8461a     // Catch:{ all -> 0x054a }
            boolean r4 = r4.contains(r0)     // Catch:{ all -> 0x054a }
            if (r4 != 0) goto L_0x0546
            java.util.Set r4 = p000.C0123ek.f8461a     // Catch:{ all -> 0x054a }
            r4.add(r0)     // Catch:{ all -> 0x054a }
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x054a }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x054a }
            r0.<init>()     // Catch:{ all -> 0x054a }
            java.lang.String r4 = "MultiDex is not guaranteed to work in SDK version "
            r0.append(r4)     // Catch:{ all -> 0x054a }
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x054a }
            r0.append(r4)     // Catch:{ all -> 0x054a }
            java.lang.String r4 = ": SDK version higher than "
            r0.append(r4)     // Catch:{ all -> 0x054a }
            r4 = 20
            r0.append(r4)     // Catch:{ all -> 0x054a }
            java.lang.String r4 = " should be backed by "
            r0.append(r4)     // Catch:{ all -> 0x054a }
            java.lang.String r4 = "runtime with built-in multidex capabilty but it's not the "
            r0.append(r4)     // Catch:{ all -> 0x054a }
            java.lang.String r4 = "case here: java.vm.version=\""
            r0.append(r4)     // Catch:{ all -> 0x054a }
            java.lang.String r4 = "java.vm.version"
            java.lang.String r4 = java.lang.System.getProperty(r4)     // Catch:{ all -> 0x054a }
            r0.append(r4)     // Catch:{ all -> 0x054a }
            java.lang.String r4 = "\""
            r0.append(r4)     // Catch:{ all -> 0x054a }
            java.lang.String r4 = "MultiDex"
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x054a }
            android.util.Log.w(r4, r0)     // Catch:{ all -> 0x054a }
            java.lang.ClassLoader r4 = r28.getClassLoader()     // Catch:{ RuntimeException -> 0x0539 }
            if (r4 == 0) goto L_0x052e
            r5 = 0
            java.io.File r0 = new java.io.File     // Catch:{ all -> 0x0168 }
            java.io.File r6 = r28.getFilesDir()     // Catch:{ all -> 0x0168 }
            java.lang.String r7 = "secondary-dexes"
            r0.<init>(r6, r7)     // Catch:{ all -> 0x0168 }
            boolean r6 = r0.isDirectory()     // Catch:{ all -> 0x0168 }
            if (r6 == 0) goto L_0x0170
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0168 }
            r6.<init>()     // Catch:{ all -> 0x0168 }
            java.lang.String r7 = "Clearing old secondary dex dir ("
            r6.append(r7)     // Catch:{ all -> 0x0168 }
            java.lang.String r7 = r0.getPath()     // Catch:{ all -> 0x0168 }
            r6.append(r7)     // Catch:{ all -> 0x0168 }
            java.lang.String r7 = ")."
            r6.append(r7)     // Catch:{ all -> 0x0168 }
            r6.toString()     // Catch:{ all -> 0x0168 }
            java.io.File[] r6 = r0.listFiles()     // Catch:{ all -> 0x0168 }
            if (r6 != 0) goto L_0x00d4
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0168 }
            r6.<init>()     // Catch:{ all -> 0x0168 }
            java.lang.String r7 = "Failed to list secondary dex dir content ("
            r6.append(r7)     // Catch:{ all -> 0x0168 }
            java.lang.String r0 = r0.getPath()     // Catch:{ all -> 0x0168 }
            r6.append(r0)     // Catch:{ all -> 0x0168 }
            java.lang.String r0 = ")."
            r6.append(r0)     // Catch:{ all -> 0x0168 }
            java.lang.String r0 = "MultiDex"
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0168 }
            android.util.Log.w(r0, r6)     // Catch:{ all -> 0x0168 }
            goto L_0x0170
        L_0x00d4:
            int r7 = r6.length     // Catch:{ all -> 0x0168 }
            r8 = 0
        L_0x00d6:
            if (r8 >= r7) goto L_0x0132
            r9 = r6[r8]     // Catch:{ all -> 0x0168 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0168 }
            r10.<init>()     // Catch:{ all -> 0x0168 }
            java.lang.String r11 = "Trying to delete old file "
            r10.append(r11)     // Catch:{ all -> 0x0168 }
            java.lang.String r11 = r9.getPath()     // Catch:{ all -> 0x0168 }
            r10.append(r11)     // Catch:{ all -> 0x0168 }
            java.lang.String r11 = " of size "
            r10.append(r11)     // Catch:{ all -> 0x0168 }
            long r11 = r9.length()     // Catch:{ all -> 0x0168 }
            r10.append(r11)     // Catch:{ all -> 0x0168 }
            r10.toString()     // Catch:{ all -> 0x0168 }
            boolean r10 = r9.delete()     // Catch:{ all -> 0x0168 }
            if (r10 != 0) goto L_0x011b
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0168 }
            r10.<init>()     // Catch:{ all -> 0x0168 }
            java.lang.String r11 = "Failed to delete old file "
            r10.append(r11)     // Catch:{ all -> 0x0168 }
            java.lang.String r9 = r9.getPath()     // Catch:{ all -> 0x0168 }
            r10.append(r9)     // Catch:{ all -> 0x0168 }
            java.lang.String r9 = "MultiDex"
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x0168 }
            android.util.Log.w(r9, r10)     // Catch:{ all -> 0x0168 }
            goto L_0x012f
        L_0x011b:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0168 }
            r10.<init>()     // Catch:{ all -> 0x0168 }
            java.lang.String r11 = "Deleted old file "
            r10.append(r11)     // Catch:{ all -> 0x0168 }
            java.lang.String r9 = r9.getPath()     // Catch:{ all -> 0x0168 }
            r10.append(r9)     // Catch:{ all -> 0x0168 }
            r10.toString()     // Catch:{ all -> 0x0168 }
        L_0x012f:
            int r8 = r8 + 1
            goto L_0x00d6
        L_0x0132:
            boolean r6 = r0.delete()     // Catch:{ all -> 0x0168 }
            if (r6 != 0) goto L_0x0153
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0168 }
            r6.<init>()     // Catch:{ all -> 0x0168 }
            java.lang.String r7 = "Failed to delete secondary dex dir "
            r6.append(r7)     // Catch:{ all -> 0x0168 }
            java.lang.String r0 = r0.getPath()     // Catch:{ all -> 0x0168 }
            r6.append(r0)     // Catch:{ all -> 0x0168 }
            java.lang.String r0 = "MultiDex"
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0168 }
            android.util.Log.w(r0, r6)     // Catch:{ all -> 0x0168 }
            goto L_0x0170
        L_0x0153:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0168 }
            r6.<init>()     // Catch:{ all -> 0x0168 }
            java.lang.String r7 = "Deleted old secondary dex dir "
            r6.append(r7)     // Catch:{ all -> 0x0168 }
            java.lang.String r0 = r0.getPath()     // Catch:{ all -> 0x0168 }
            r6.append(r0)     // Catch:{ all -> 0x0168 }
            r6.toString()     // Catch:{ all -> 0x0168 }
            goto L_0x0170
        L_0x0168:
            r0 = move-exception
            java.lang.String r6 = "MultiDex"
            java.lang.String r7 = "Something went wrong when trying to clear old MultiDex extraction, continuing without cleaning."
            android.util.Log.w(r6, r7, r0)     // Catch:{ all -> 0x054a }
        L_0x0170:
            java.io.File r0 = new java.io.File     // Catch:{ all -> 0x054a }
            java.lang.String r6 = r2.dataDir     // Catch:{ all -> 0x054a }
            java.lang.String r7 = "code_cache"
            r0.<init>(r6, r7)     // Catch:{ all -> 0x054a }
            p000.C0123ek.m7654a(r0)     // Catch:{ IOException -> 0x017d }
        L_0x017c:
            goto L_0x018d
        L_0x017d:
            r0 = move-exception
            java.io.File r0 = new java.io.File     // Catch:{ all -> 0x054a }
            java.io.File r6 = r28.getFilesDir()     // Catch:{ all -> 0x054a }
            java.lang.String r7 = "code_cache"
            r0.<init>(r6, r7)     // Catch:{ all -> 0x054a }
            p000.C0123ek.m7654a(r0)     // Catch:{ all -> 0x054a }
            goto L_0x017c
        L_0x018d:
            java.io.File r6 = new java.io.File     // Catch:{ all -> 0x054a }
            java.lang.String r7 = "secondary-dexes"
            r6.<init>(r0, r7)     // Catch:{ all -> 0x054a }
            p000.C0123ek.m7654a(r6)     // Catch:{ all -> 0x054a }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x054a }
            r0.<init>()     // Catch:{ all -> 0x054a }
            java.lang.String r7 = "MultiDexExtractor.load("
            r0.append(r7)     // Catch:{ all -> 0x054a }
            java.lang.String r7 = r2.sourceDir     // Catch:{ all -> 0x054a }
            r0.append(r7)     // Catch:{ all -> 0x054a }
            java.lang.String r7 = ", "
            r0.append(r7)     // Catch:{ all -> 0x054a }
            r0.append(r5)     // Catch:{ all -> 0x054a }
            java.lang.String r7 = ")"
            r0.append(r7)     // Catch:{ all -> 0x054a }
            r0.toString()     // Catch:{ all -> 0x054a }
            java.io.File r7 = new java.io.File     // Catch:{ all -> 0x054a }
            java.lang.String r0 = r2.sourceDir     // Catch:{ all -> 0x054a }
            r7.<init>(r0)     // Catch:{ all -> 0x054a }
            long r11 = p000.C0321lr.m14632b((java.io.File) r7)     // Catch:{ all -> 0x054a }
            java.io.File r2 = new java.io.File     // Catch:{ all -> 0x054a }
            java.lang.String r0 = "MultiDex.lock"
            r2.<init>(r6, r0)     // Catch:{ all -> 0x054a }
            java.io.RandomAccessFile r14 = new java.io.RandomAccessFile     // Catch:{ all -> 0x054a }
            java.lang.String r0 = "rw"
            r14.<init>(r2, r0)     // Catch:{ all -> 0x054a }
            java.nio.channels.FileChannel r15 = r14.getChannel()     // Catch:{ all -> 0x04f7 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x04e9 }
            r0.<init>()     // Catch:{ all -> 0x04e9 }
            java.lang.String r8 = "Blocking on lock "
            r0.append(r8)     // Catch:{ all -> 0x04e9 }
            java.lang.String r8 = r2.getPath()     // Catch:{ all -> 0x04e9 }
            r0.append(r8)     // Catch:{ all -> 0x04e9 }
            r0.toString()     // Catch:{ all -> 0x04e9 }
            java.nio.channels.FileLock r16 = r15.lock()     // Catch:{ all -> 0x04e9 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x04dd }
            r0.<init>()     // Catch:{ all -> 0x04dd }
            java.lang.String r8 = r2.getPath()     // Catch:{ all -> 0x04dd }
            r0.append(r8)     // Catch:{ all -> 0x04dd }
            java.lang.String r8 = " locked"
            r0.append(r8)     // Catch:{ all -> 0x04dd }
            r0.toString()     // Catch:{ all -> 0x04dd }
            android.content.SharedPreferences r0 = p000.C0321lr.m14623a((android.content.Context) r28)     // Catch:{ all -> 0x04dd }
            java.lang.String r8 = "timestamp"
            r9 = -1
            long r17 = r0.getLong(r8, r9)     // Catch:{ all -> 0x04dd }
            long r19 = p000.C0321lr.m14622a((java.io.File) r7)     // Catch:{ all -> 0x04dd }
            r21 = 2
            r13 = 1
            int r8 = (r17 > r19 ? 1 : (r17 == r19 ? 0 : -1))
            if (r8 != 0) goto L_0x0376
            java.lang.String r8 = "crc"
            long r17 = r0.getLong(r8, r9)     // Catch:{ all -> 0x04dd }
            int r0 = (r17 > r11 ? 1 : (r17 == r11 ? 0 : -1))
            if (r0 != 0) goto L_0x036c
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x034a }
            r0.<init>()     // Catch:{ IOException -> 0x034a }
            java.lang.String r8 = r7.getName()     // Catch:{ IOException -> 0x034a }
            r0.append(r8)     // Catch:{ IOException -> 0x034a }
            java.lang.String r8 = ".classes"
            r0.append(r8)     // Catch:{ IOException -> 0x034a }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x034a }
            android.content.SharedPreferences r8 = p000.C0321lr.m14623a((android.content.Context) r28)     // Catch:{ IOException -> 0x034a }
            java.lang.String r1 = "dex.number"
            int r1 = r8.getInt(r1, r13)     // Catch:{ IOException -> 0x034a }
            java.util.ArrayList r13 = new java.util.ArrayList     // Catch:{ IOException -> 0x034a }
            int r5 = r1 + -1
            r13.<init>(r5)     // Catch:{ IOException -> 0x034a }
            r5 = 2
        L_0x0247:
            if (r5 > r1) goto L_0x0340
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x034a }
            r9.<init>()     // Catch:{ IOException -> 0x034a }
            r9.append(r0)     // Catch:{ IOException -> 0x034a }
            r9.append(r5)     // Catch:{ IOException -> 0x034a }
            java.lang.String r10 = ".zip"
            r9.append(r10)     // Catch:{ IOException -> 0x034a }
            java.lang.String r9 = r9.toString()     // Catch:{ IOException -> 0x034a }
            em r10 = new em     // Catch:{ IOException -> 0x034a }
            r10.<init>(r6, r9)     // Catch:{ IOException -> 0x034a }
            boolean r9 = r10.isFile()     // Catch:{ IOException -> 0x034a }
            if (r9 == 0) goto L_0x0316
            r9 = r0
            r22 = r1
            long r0 = p000.C0321lr.m14632b((java.io.File) r10)     // Catch:{ IOException -> 0x034a }
            r10.f8534a = r0     // Catch:{ IOException -> 0x034a }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x034a }
            r0.<init>()     // Catch:{ IOException -> 0x034a }
            java.lang.String r1 = "dex.crc."
            r0.append(r1)     // Catch:{ IOException -> 0x034a }
            r0.append(r5)     // Catch:{ IOException -> 0x034a }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x034a }
            r24 = r14
            r23 = r15
            r14 = -1
            long r0 = r8.getLong(r0, r14)     // Catch:{ IOException -> 0x0310, all -> 0x0309 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0310, all -> 0x0309 }
            r14.<init>()     // Catch:{ IOException -> 0x0310, all -> 0x0309 }
            java.lang.String r15 = "dex.time."
            r14.append(r15)     // Catch:{ IOException -> 0x0310, all -> 0x0309 }
            r14.append(r5)     // Catch:{ IOException -> 0x0310, all -> 0x0309 }
            java.lang.String r14 = r14.toString()     // Catch:{ IOException -> 0x0310, all -> 0x0309 }
            r25 = r2
            r19 = r3
            r2 = -1
            long r14 = r8.getLong(r14, r2)     // Catch:{ IOException -> 0x033e }
            long r2 = r10.lastModified()     // Catch:{ IOException -> 0x033e }
            int r20 = (r14 > r2 ? 1 : (r14 == r2 ? 0 : -1))
            if (r20 != 0) goto L_0x02d0
            r20 = r8
            r26 = r9
            long r8 = r10.f8534a     // Catch:{ IOException -> 0x033e }
            int r27 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r27 != 0) goto L_0x02d0
            r13.add(r10)     // Catch:{ IOException -> 0x033e }
            int r5 = r5 + 1
            r3 = r19
            r8 = r20
            r1 = r22
            r15 = r23
            r14 = r24
            r2 = r25
            r0 = r26
            r9 = -1
            goto L_0x0247
        L_0x02d0:
            java.io.IOException r5 = new java.io.IOException     // Catch:{ IOException -> 0x033e }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x033e }
            r8.<init>()     // Catch:{ IOException -> 0x033e }
            java.lang.String r9 = "Invalid extracted dex: "
            r8.append(r9)     // Catch:{ IOException -> 0x033e }
            r8.append(r10)     // Catch:{ IOException -> 0x033e }
            java.lang.String r9 = ", expected modification time: "
            r8.append(r9)     // Catch:{ IOException -> 0x033e }
            r8.append(r14)     // Catch:{ IOException -> 0x033e }
            java.lang.String r9 = ", modification time: "
            r8.append(r9)     // Catch:{ IOException -> 0x033e }
            r8.append(r2)     // Catch:{ IOException -> 0x033e }
            java.lang.String r2 = ", expected crc: "
            r8.append(r2)     // Catch:{ IOException -> 0x033e }
            r8.append(r0)     // Catch:{ IOException -> 0x033e }
            java.lang.String r0 = ", file crc: "
            r8.append(r0)     // Catch:{ IOException -> 0x033e }
            long r0 = r10.f8534a     // Catch:{ IOException -> 0x033e }
            r8.append(r0)     // Catch:{ IOException -> 0x033e }
            java.lang.String r0 = r8.toString()     // Catch:{ IOException -> 0x033e }
            r5.<init>(r0)     // Catch:{ IOException -> 0x033e }
            throw r5     // Catch:{ IOException -> 0x033e }
        L_0x0309:
            r0 = move-exception
            r25 = r2
            r19 = r3
            goto L_0x04e6
        L_0x0310:
            r0 = move-exception
            r25 = r2
            r19 = r3
            goto L_0x0353
        L_0x0316:
            r25 = r2
            r19 = r3
            r24 = r14
            r23 = r15
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x033e }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x033e }
            r1.<init>()     // Catch:{ IOException -> 0x033e }
            java.lang.String r2 = "Missing extracted secondary dex file '"
            r1.append(r2)     // Catch:{ IOException -> 0x033e }
            java.lang.String r2 = r10.getPath()     // Catch:{ IOException -> 0x033e }
            r1.append(r2)     // Catch:{ IOException -> 0x033e }
            java.lang.String r2 = "'"
            r1.append(r2)     // Catch:{ IOException -> 0x033e }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x033e }
            r0.<init>(r1)     // Catch:{ IOException -> 0x033e }
            throw r0     // Catch:{ IOException -> 0x033e }
        L_0x033e:
            r0 = move-exception
            goto L_0x0353
        L_0x0340:
            r25 = r2
            r19 = r3
            r24 = r14
            r23 = r15
            r1 = 1
            goto L_0x038e
        L_0x034a:
            r0 = move-exception
            r25 = r2
            r19 = r3
            r24 = r14
            r23 = r15
        L_0x0353:
            java.lang.String r1 = "MultiDex"
            java.lang.String r2 = "Failed to reload existing extracted secondary dex files, falling back to fresh extraction"
            android.util.Log.w(r1, r2, r0)     // Catch:{ all -> 0x04db }
            java.util.List r0 = p000.C0321lr.m14625a((java.io.File) r7, (java.io.File) r6)     // Catch:{ all -> 0x04db }
            long r9 = p000.C0321lr.m14622a((java.io.File) r7)     // Catch:{ all -> 0x04db }
            r8 = r28
            r1 = 1
            r13 = r0
            p000.C0321lr.m14628a(r8, r9, r11, r13)     // Catch:{ all -> 0x04db }
            r13 = r0
            goto L_0x038e
        L_0x036c:
            r25 = r2
            r19 = r3
            r24 = r14
            r23 = r15
            r1 = 1
            goto L_0x037f
        L_0x0376:
            r25 = r2
            r19 = r3
            r24 = r14
            r23 = r15
            r1 = 1
        L_0x037f:
            java.util.List r0 = p000.C0321lr.m14625a((java.io.File) r7, (java.io.File) r6)     // Catch:{ all -> 0x04db }
            long r9 = p000.C0321lr.m14622a((java.io.File) r7)     // Catch:{ all -> 0x04db }
            r8 = r28
            r13 = r0
            p000.C0321lr.m14628a(r8, r9, r11, r13)     // Catch:{ all -> 0x04db }
            r13 = r0
        L_0x038e:
            if (r16 == 0) goto L_0x03b0
            r16.release()     // Catch:{ IOException -> 0x0394 }
            goto L_0x03b1
        L_0x0394:
            r0 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x054f }
            r2.<init>()     // Catch:{ all -> 0x054f }
            java.lang.String r3 = "Failed to release lock on "
            r2.append(r3)     // Catch:{ all -> 0x054f }
            java.lang.String r3 = r25.getPath()     // Catch:{ all -> 0x054f }
            r2.append(r3)     // Catch:{ all -> 0x054f }
            java.lang.String r3 = "MultiDex"
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x054f }
            android.util.Log.e(r3, r2)     // Catch:{ all -> 0x054f }
            goto L_0x03b2
        L_0x03b0:
        L_0x03b1:
            r0 = 0
        L_0x03b2:
            if (r23 != 0) goto L_0x03b5
            goto L_0x03b8
        L_0x03b5:
            p000.C0321lr.m14629a((java.io.Closeable) r23)     // Catch:{ all -> 0x054f }
        L_0x03b8:
            p000.C0321lr.m14629a((java.io.Closeable) r24)     // Catch:{ all -> 0x054f }
            if (r0 != 0) goto L_0x04da
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x054f }
            r0.<init>()     // Catch:{ all -> 0x054f }
            java.lang.String r2 = "load found "
            r0.append(r2)     // Catch:{ all -> 0x054f }
            int r2 = r13.size()     // Catch:{ all -> 0x054f }
            r0.append(r2)     // Catch:{ all -> 0x054f }
            java.lang.String r2 = " secondary dex files"
            r0.append(r2)     // Catch:{ all -> 0x054f }
            r0.toString()     // Catch:{ all -> 0x054f }
            boolean r0 = r13.isEmpty()     // Catch:{ all -> 0x054f }
            if (r0 != 0) goto L_0x04d8
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x054f }
            java.lang.String r0 = "pathList"
            java.lang.reflect.Field r0 = p000.C0123ek.m7653a(r4, r0)     // Catch:{ all -> 0x054f }
            java.lang.Object r2 = r0.get(r4)     // Catch:{ all -> 0x054f }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x054f }
            r3.<init>()     // Catch:{ all -> 0x054f }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x054f }
            r4.<init>(r13)     // Catch:{ all -> 0x054f }
            r5 = 3
            java.lang.Class[] r7 = new java.lang.Class[r5]     // Catch:{ all -> 0x054f }
            java.lang.Class<java.util.ArrayList> r0 = java.util.ArrayList.class
            r8 = 0
            r7[r8] = r0     // Catch:{ all -> 0x054f }
            java.lang.Class<java.io.File> r0 = java.io.File.class
            r7[r1] = r0     // Catch:{ all -> 0x054f }
            java.lang.Class<java.util.ArrayList> r0 = java.util.ArrayList.class
            r7[r21] = r0     // Catch:{ all -> 0x054f }
            java.lang.String r8 = "makeDexElements"
            java.lang.Class r0 = r2.getClass()     // Catch:{ all -> 0x054f }
            r9 = r0
        L_0x0409:
            if (r9 == 0) goto L_0x04a9
            java.lang.reflect.Method r0 = r9.getDeclaredMethod(r8, r7)     // Catch:{ NoSuchMethodException -> 0x04a1 }
            boolean r10 = r0.isAccessible()     // Catch:{ NoSuchMethodException -> 0x04a1 }
            if (r10 == 0) goto L_0x0416
            goto L_0x041a
        L_0x0416:
            r0.setAccessible(r1)     // Catch:{ NoSuchMethodException -> 0x04a1 }
        L_0x041a:
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x054f }
            r7 = 0
            r5[r7] = r4     // Catch:{ all -> 0x054f }
            r5[r1] = r6     // Catch:{ all -> 0x054f }
            r5[r21] = r3     // Catch:{ all -> 0x054f }
            java.lang.Object r0 = r0.invoke(r2, r5)     // Catch:{ all -> 0x054f }
            java.lang.Object[] r0 = (java.lang.Object[]) r0     // Catch:{ all -> 0x054f }
            java.lang.String r1 = "dexElements"
            java.lang.reflect.Field r1 = p000.C0123ek.m7653a(r2, r1)     // Catch:{ all -> 0x054f }
            java.lang.Object r4 = r1.get(r2)     // Catch:{ all -> 0x054f }
            java.lang.Object[] r4 = (java.lang.Object[]) r4     // Catch:{ all -> 0x054f }
            java.lang.Class r5 = r4.getClass()     // Catch:{ all -> 0x054f }
            java.lang.Class r5 = r5.getComponentType()     // Catch:{ all -> 0x054f }
            int r6 = r4.length     // Catch:{ all -> 0x054f }
            int r7 = r0.length     // Catch:{ all -> 0x054f }
            int r8 = r6 + r7
            java.lang.Object r5 = java.lang.reflect.Array.newInstance(r5, r8)     // Catch:{ all -> 0x054f }
            java.lang.Object[] r5 = (java.lang.Object[]) r5     // Catch:{ all -> 0x054f }
            r8 = 0
            java.lang.System.arraycopy(r4, r8, r5, r8, r6)     // Catch:{ all -> 0x054f }
            java.lang.System.arraycopy(r0, r8, r5, r6, r7)     // Catch:{ all -> 0x054f }
            r1.set(r2, r5)     // Catch:{ all -> 0x054f }
            int r0 = r3.size()     // Catch:{ all -> 0x054f }
            if (r0 <= 0) goto L_0x04d8
            int r0 = r3.size()     // Catch:{ all -> 0x054f }
            r8 = 0
        L_0x045c:
            if (r8 >= r0) goto L_0x046e
            java.lang.String r1 = "MultiDex"
            java.lang.String r4 = "Exception in makeDexElement"
            java.lang.Object r5 = r3.get(r8)     // Catch:{ all -> 0x054f }
            java.io.IOException r5 = (java.io.IOException) r5     // Catch:{ all -> 0x054f }
            android.util.Log.w(r1, r4, r5)     // Catch:{ all -> 0x054f }
            int r8 = r8 + 1
            goto L_0x045c
        L_0x046e:
            java.lang.String r0 = "dexElementsSuppressedExceptions"
            java.lang.reflect.Field r0 = p000.C0123ek.m7653a(r2, r0)     // Catch:{ all -> 0x054f }
            java.lang.Object r1 = r0.get(r2)     // Catch:{ all -> 0x054f }
            java.io.IOException[] r1 = (java.io.IOException[]) r1     // Catch:{ all -> 0x054f }
            if (r1 == 0) goto L_0x0490
            int r4 = r3.size()     // Catch:{ all -> 0x054f }
            int r5 = r1.length     // Catch:{ all -> 0x054f }
            int r4 = r4 + r5
            java.io.IOException[] r4 = new java.io.IOException[r4]     // Catch:{ all -> 0x054f }
            r3.toArray(r4)     // Catch:{ all -> 0x054f }
            int r3 = r3.size()     // Catch:{ all -> 0x054f }
            r10 = 0
            java.lang.System.arraycopy(r1, r10, r4, r3, r5)     // Catch:{ all -> 0x054f }
            goto L_0x049d
        L_0x0490:
            int r1 = r3.size()     // Catch:{ all -> 0x054f }
            java.io.IOException[] r1 = new java.io.IOException[r1]     // Catch:{ all -> 0x054f }
            java.lang.Object[] r1 = r3.toArray(r1)     // Catch:{ all -> 0x054f }
            r4 = r1
            java.io.IOException[] r4 = (java.io.IOException[]) r4     // Catch:{ all -> 0x054f }
        L_0x049d:
            r0.set(r2, r4)     // Catch:{ all -> 0x054f }
            goto L_0x04d8
        L_0x04a1:
            r0 = move-exception
            r10 = 0
            java.lang.Class r9 = r9.getSuperclass()     // Catch:{ all -> 0x054f }
            goto L_0x0409
        L_0x04a9:
            java.lang.NoSuchMethodException r0 = new java.lang.NoSuchMethodException     // Catch:{ all -> 0x054f }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x054f }
            r1.<init>()     // Catch:{ all -> 0x054f }
            java.lang.String r3 = "Method "
            r1.append(r3)     // Catch:{ all -> 0x054f }
            r1.append(r8)     // Catch:{ all -> 0x054f }
            java.lang.String r3 = " with parameters "
            r1.append(r3)     // Catch:{ all -> 0x054f }
            java.util.List r3 = java.util.Arrays.asList(r7)     // Catch:{ all -> 0x054f }
            r1.append(r3)     // Catch:{ all -> 0x054f }
            java.lang.String r3 = " not found in "
            r1.append(r3)     // Catch:{ all -> 0x054f }
            java.lang.Class r2 = r2.getClass()     // Catch:{ all -> 0x054f }
            r1.append(r2)     // Catch:{ all -> 0x054f }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x054f }
            r0.<init>(r1)     // Catch:{ all -> 0x054f }
            throw r0     // Catch:{ all -> 0x054f }
        L_0x04d8:
            monitor-exit(r19)     // Catch:{ all -> 0x054f }
            return
        L_0x04da:
            throw r0     // Catch:{ all -> 0x054f }
        L_0x04db:
            r0 = move-exception
            goto L_0x04e6
        L_0x04dd:
            r0 = move-exception
            r25 = r2
            r19 = r3
            r24 = r14
            r23 = r15
        L_0x04e6:
            r1 = r0
            goto L_0x0503
        L_0x04e9:
            r0 = move-exception
            r25 = r2
            r19 = r3
            r24 = r14
            r23 = r15
            r1 = r0
            r16 = 0
            goto L_0x0503
        L_0x04f7:
            r0 = move-exception
            r25 = r2
            r19 = r3
            r24 = r14
            r1 = r0
            r16 = 0
            r23 = 0
        L_0x0503:
            if (r16 == 0) goto L_0x0524
            r16.release()     // Catch:{ IOException -> 0x0509 }
            goto L_0x0524
        L_0x0509:
            r0 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x054f }
            r0.<init>()     // Catch:{ all -> 0x054f }
            java.lang.String r2 = "Failed to release lock on "
            r0.append(r2)     // Catch:{ all -> 0x054f }
            java.lang.String r2 = r25.getPath()     // Catch:{ all -> 0x054f }
            r0.append(r2)     // Catch:{ all -> 0x054f }
            java.lang.String r2 = "MultiDex"
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x054f }
            android.util.Log.e(r2, r0)     // Catch:{ all -> 0x054f }
        L_0x0524:
            if (r23 != 0) goto L_0x0527
            goto L_0x052a
        L_0x0527:
            p000.C0321lr.m14629a((java.io.Closeable) r23)     // Catch:{ all -> 0x054f }
        L_0x052a:
            p000.C0321lr.m14629a((java.io.Closeable) r24)     // Catch:{ all -> 0x054f }
            throw r1     // Catch:{ all -> 0x054f }
        L_0x052e:
            r19 = r3
            java.lang.String r0 = "MultiDex"
            java.lang.String r1 = "Context class loader is null. Must be running in test mode. Skip patching."
            android.util.Log.e(r0, r1)     // Catch:{ all -> 0x054f }
            monitor-exit(r19)     // Catch:{ all -> 0x054f }
            return
        L_0x0539:
            r0 = move-exception
            r19 = r3
            r1 = r0
            java.lang.String r0 = "MultiDex"
            java.lang.String r2 = "Failure while trying to obtain Context class loader. Must be running in test mode. Skip patching."
            android.util.Log.w(r0, r2, r1)     // Catch:{ all -> 0x054f }
            monitor-exit(r19)     // Catch:{ all -> 0x054f }
            return
        L_0x0546:
            r19 = r3
            monitor-exit(r19)     // Catch:{ all -> 0x054f }
            return
        L_0x054a:
            r0 = move-exception
            r19 = r3
        L_0x054d:
            monitor-exit(r19)     // Catch:{ all -> 0x054f }
            throw r0     // Catch:{ Exception -> 0x0019 }
        L_0x054f:
            r0 = move-exception
            goto L_0x054d
        L_0x0551:
            java.lang.String r1 = "MultiDex"
            java.lang.String r2 = "Multidex installation failure"
            android.util.Log.e(r1, r2, r0)
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Multi dex installation failed ("
            r2.append(r3)
            java.lang.String r0 = r0.getMessage()
            r2.append(r0)
            java.lang.String r0 = ")."
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L_0x0578:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.hbq.attachBaseContext(android.content.Context):void");
    }

    /* renamed from: l */
    public final long mo3320l() {
        int i = Build.VERSION.SDK_INT;
        return Process.getStartElapsedRealtime();
    }

    /* renamed from: a */
    private static final boolean mo455a() {
        if (gte.f12007a == null) {
            gte.f12007a = true;
        }
        return gte.f12007a.booleanValue();
    }

    public void onCreate() {
        hlj a;
        if (mo455a()) {
            hnf hnf = hnf.f13084a;
            long l = mo3320l();
            long max = Math.max(0, System.currentTimeMillis() - Math.max(0, SystemClock.elapsedRealtime() - l));
            hlz hlz = this.f12461b;
            ife.m12898e((Object) hnf);
            hlp a2 = hlz.f13017b.mo7580a("Application creation", hlz.f13018c, max, l, hlz.f13019d);
            try {
                hlj a3 = hnb.m11765a("Application.onCreate");
                try {
                    new hbp();
                    gtk gtk = this.f12462c;
                    for (gti gti : gti.values()) {
                        gtj gtj = (gtj) gtk.f12019a.get(gti);
                        if (gtj != null) {
                            gtj.mo7033a();
                        }
                    }
                    hlj a4 = hnb.m11765a("Startup Listeners");
                    try {
                        Map map = this.f12464e;
                        if (gte.m10777a((Context) this)) {
                            HashMap hashMap = new HashMap(((hvb) map).f13455b + ((hvb) this.f12463d).f13455b);
                            hashMap.putAll(this.f12464e);
                            hashMap.putAll(this.f12463d);
                            map = hashMap;
                        }
                        for (Map.Entry entry : map.entrySet()) {
                            a = hnb.m11766a((String) entry.getKey(), hnf);
                            ((hbc) ((iqk) entry.getValue()).mo2097a()).mo7253a();
                            if (a != null) {
                                a.close();
                            }
                        }
                        if (a4 != null) {
                            a4.close();
                        }
                        super.onCreate();
                        if (a3 != null) {
                            a3.close();
                        }
                        a2.close();
                        return;
                    } catch (Throwable th) {
                        if (a4 != null) {
                            a4.close();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    if (a3 != null) {
                        a3.close();
                    }
                    throw th2;
                }
            } catch (Throwable th3) {
                try {
                    a2.close();
                } catch (Throwable th4) {
                    ifn.m12935a(th3, th4);
                }
                throw th3;
            }
        } else {
            super.onCreate();
            return;
        }
        throw th;
    }
}
