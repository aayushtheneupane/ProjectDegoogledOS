package p000;

import android.content.Context;
import java.util.regex.Pattern;

/* renamed from: cwf */
/* compiled from: PG */
public final class cwf {

    /* renamed from: a */
    private static final Pattern f5790a = Pattern.compile("\\d+(\\.\\d+)+(\\.[a-z0-9]+)*");

    /* renamed from: b */
    private final Context f5791b;

    /* renamed from: c */
    private final String f5792c;

    /* renamed from: d */
    private boolean f5793d = false;

    public cwf(Context context, String str) {
        this.f5791b = context;
        this.f5792c = str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:0x0258 A[Catch:{ IOException -> 0x024f }] */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0259 A[Catch:{ IOException -> 0x024f }] */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0251 A[EDGE_INSN: B:121:0x0251->B:98:0x0251 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x024b A[SYNTHETIC, Splitter:B:94:0x024b] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0253 A[Catch:{ IOException -> 0x024f }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void m5499a(java.lang.String r19) {
        /*
            r18 = this;
            r1 = r18
            java.lang.System.loadLibrary(r19)     // Catch:{ UnsatisfiedLinkError -> 0x0006 }
            return
        L_0x0006:
            r0 = move-exception
            r2 = r0
            int r0 = r19.length()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            int r0 = r0 + 6
            r3.<init>(r0)
            java.lang.String r0 = "lib"
            r3.append(r0)
            r4 = r19
            r3.append(r4)
            java.lang.String r0 = ".so"
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            android.content.Context r0 = r1.f5791b
            java.io.File r0 = r0.getFilesDir()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r4 = java.io.File.separator
            java.lang.String r5 = r1.f5792c
            java.lang.String r6 = java.lang.String.valueOf(r0)
            int r6 = r6.length()
            java.lang.String r7 = java.lang.String.valueOf(r4)
            int r7 = r7.length()
            java.lang.String r8 = java.lang.String.valueOf(r5)
            int r8 = r8.length()
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            int r6 = r6 + r7
            int r6 = r6 + r8
            r9.<init>(r6)
            r9.append(r0)
            r9.append(r4)
            r9.append(r5)
            java.lang.String r0 = r9.toString()
            java.lang.String r4 = java.io.File.separator
            java.lang.String r5 = java.lang.String.valueOf(r0)
            int r5 = r5.length()
            java.lang.String r6 = java.lang.String.valueOf(r4)
            int r6 = r6.length()
            java.lang.String r7 = java.lang.String.valueOf(r3)
            int r7 = r7.length()
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            int r5 = r5 + r6
            int r5 = r5 + r7
            r8.<init>(r5)
            r8.append(r0)
            r8.append(r4)
            r8.append(r3)
            java.lang.String r4 = r8.toString()
            android.content.Context r5 = r1.f5791b
            android.content.pm.ApplicationInfo r5 = r5.getApplicationInfo()
            java.lang.String r5 = r5.sourceDir
            r6 = 1
            java.lang.Object[] r7 = new java.lang.Object[r6]
            r8 = 0
            r7[r8] = r5
            java.lang.Object[] r7 = new java.lang.Object[r6]
            r7[r8] = r4
            java.io.File r7 = new java.io.File
            r7.<init>(r4)
            boolean r7 = r7.exists()
            if (r7 != 0) goto L_0x0264
            java.io.File r7 = new java.io.File
            r7.<init>(r0)
            boolean r0 = r7.exists()
            if (r0 == 0) goto L_0x00b7
        L_0x00b6:
            goto L_0x00c0
        L_0x00b7:
            boolean r0 = r7.mkdirs()
            if (r0 != 0) goto L_0x00b6
        L_0x00bd:
            r4 = 0
            goto L_0x0265
        L_0x00c0:
            java.io.File r0 = new java.io.File
            android.content.Context r7 = r1.f5791b
            java.io.File r7 = r7.getFilesDir()
            java.lang.String r7 = r7.toString()
            r0.<init>(r7)
            java.io.File[] r0 = r0.listFiles()     // Catch:{ Exception -> 0x0130 }
            if (r0 == 0) goto L_0x0138
            int r7 = r0.length     // Catch:{ Exception -> 0x0130 }
            r10 = 0
        L_0x00d7:
            if (r10 >= r7) goto L_0x0138
            r11 = r0[r10]     // Catch:{ Exception -> 0x0130 }
            java.lang.String r12 = r11.getName()     // Catch:{ Exception -> 0x0130 }
            boolean r13 = r11.isDirectory()     // Catch:{ Exception -> 0x0130 }
            if (r13 == 0) goto L_0x012d
            java.util.regex.Pattern r13 = f5790a     // Catch:{ Exception -> 0x0130 }
            java.util.regex.Matcher r13 = r13.matcher(r12)     // Catch:{ Exception -> 0x0130 }
            boolean r13 = r13.matches()     // Catch:{ Exception -> 0x0130 }
            if (r13 == 0) goto L_0x012d
            java.lang.String r13 = r1.f5792c     // Catch:{ Exception -> 0x0130 }
            boolean r12 = r12.equals(r13)     // Catch:{ Exception -> 0x0130 }
            if (r12 == 0) goto L_0x00fa
            goto L_0x012d
        L_0x00fa:
            java.io.File[] r12 = r11.listFiles()     // Catch:{ Exception -> 0x0130 }
            java.lang.String r13 = "SafeLibraryLoader Failed to remove %s"
            if (r12 == 0) goto L_0x011c
            int r14 = r12.length     // Catch:{ Exception -> 0x0130 }
            r15 = 0
        L_0x0104:
            if (r15 >= r14) goto L_0x011c
            r16 = r12[r15]     // Catch:{ Exception -> 0x0130 }
            boolean r17 = r16.delete()     // Catch:{ Exception -> 0x0130 }
            if (r17 != 0) goto L_0x0119
            java.lang.Object[] r9 = new java.lang.Object[r6]     // Catch:{ Exception -> 0x0130 }
            java.lang.String r16 = r16.getAbsolutePath()     // Catch:{ Exception -> 0x0130 }
            r9[r8] = r16     // Catch:{ Exception -> 0x0130 }
            p000.cwn.m5514b(r13, r9)     // Catch:{ Exception -> 0x0130 }
        L_0x0119:
            int r15 = r15 + 1
            goto L_0x0104
        L_0x011c:
            boolean r9 = r11.delete()     // Catch:{ Exception -> 0x0130 }
            if (r9 != 0) goto L_0x012d
            java.lang.Object[] r9 = new java.lang.Object[r6]     // Catch:{ Exception -> 0x0130 }
            java.lang.String r11 = r11.getAbsolutePath()     // Catch:{ Exception -> 0x0130 }
            r9[r8] = r11     // Catch:{ Exception -> 0x0130 }
            p000.cwn.m5510a(r13, r9)     // Catch:{ Exception -> 0x0130 }
        L_0x012d:
            int r10 = r10 + 1
            goto L_0x00d7
        L_0x0130:
            r0 = move-exception
            java.lang.Object[] r7 = new java.lang.Object[r8]
            java.lang.String r9 = "SafeLibraryLoader Failed to remove old libs, "
            p000.cwn.m5515b((java.lang.Throwable) r0, (java.lang.String) r9, (java.lang.Object[]) r7)
        L_0x0138:
            java.lang.String[] r7 = android.os.Build.SUPPORTED_ABIS
            int r9 = r7.length
            r10 = 0
        L_0x013c:
            if (r10 < r9) goto L_0x015d
            java.io.File r0 = new java.io.File
            r0.<init>(r4)
            boolean r3 = r0.exists()
            if (r3 == 0) goto L_0x015b
            r0.delete()     // Catch:{ SecurityException -> 0x014e }
            goto L_0x00bd
        L_0x014e:
            r0 = move-exception
            r3 = r0
            java.lang.Object[] r0 = new java.lang.Object[r8]
            java.lang.String r4 = "SafeLibraryLoader Failed to remove old lib, "
            p000.cwn.m5515b((java.lang.Throwable) r3, (java.lang.String) r4, (java.lang.Object[]) r0)
            r4 = 0
            goto L_0x0265
        L_0x015b:
            goto L_0x00bd
        L_0x015d:
            r0 = r7[r10]
            java.lang.String r11 = java.lang.String.valueOf(r0)     // Catch:{ IOException -> 0x025d }
            int r11 = r11.length()     // Catch:{ IOException -> 0x025d }
            int r11 = r11 + 5
            java.lang.String r12 = java.lang.String.valueOf(r3)     // Catch:{ IOException -> 0x025d }
            int r12 = r12.length()     // Catch:{ IOException -> 0x025d }
            int r11 = r11 + r12
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x025d }
            r12.<init>(r11)     // Catch:{ IOException -> 0x025d }
            java.lang.String r11 = "lib/"
            r12.append(r11)     // Catch:{ IOException -> 0x025d }
            r12.append(r0)     // Catch:{ IOException -> 0x025d }
            java.lang.String r11 = "/"
            r12.append(r11)     // Catch:{ IOException -> 0x025d }
            r12.append(r3)     // Catch:{ IOException -> 0x025d }
            java.lang.String r11 = r12.toString()     // Catch:{ IOException -> 0x025d }
            java.util.zip.ZipFile r12 = new java.util.zip.ZipFile     // Catch:{ all -> 0x0244 }
            r12.<init>(r5)     // Catch:{ all -> 0x0244 }
            java.util.zip.ZipEntry r13 = r12.getEntry(r11)     // Catch:{ all -> 0x023e }
            java.lang.String r14 = " in "
            if (r13 == 0) goto L_0x020b
            java.io.InputStream r13 = r12.getInputStream(r13)     // Catch:{ all -> 0x023e }
            if (r13 == 0) goto L_0x01da
            java.io.BufferedInputStream r11 = new java.io.BufferedInputStream     // Catch:{ all -> 0x023e }
            r11.<init>(r13)     // Catch:{ all -> 0x023e }
            java.io.FileOutputStream r13 = new java.io.FileOutputStream     // Catch:{ all -> 0x01d6 }
            r13.<init>(r4)     // Catch:{ all -> 0x01d6 }
            r14 = 1024(0x400, float:1.435E-42)
            byte[] r14 = new byte[r14]     // Catch:{ all -> 0x01d2 }
        L_0x01ac:
            int r15 = r11.read(r14)     // Catch:{ all -> 0x01d2 }
            r6 = -1
            if (r15 == r6) goto L_0x01b8
            r13.write(r14, r8, r15)     // Catch:{ all -> 0x01d2 }
            r6 = 1
            goto L_0x01ac
        L_0x01b8:
            r13.flush()     // Catch:{ all -> 0x01d2 }
            r13.close()     // Catch:{ IOException -> 0x025d }
            r11.close()     // Catch:{ IOException -> 0x025d }
            r12.close()     // Catch:{ IOException -> 0x025d }
            r6 = 3
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ IOException -> 0x025d }
            r6[r8] = r5     // Catch:{ IOException -> 0x025d }
            r15 = 1
            r6[r15] = r0     // Catch:{ IOException -> 0x024f }
            r0 = 2
            r6[r0] = r3     // Catch:{ IOException -> 0x024f }
            goto L_0x0265
        L_0x01d2:
            r0 = move-exception
            r15 = 1
            goto L_0x0249
        L_0x01d6:
            r0 = move-exception
            r15 = 1
            goto L_0x0242
        L_0x01da:
            r15 = 1
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x023c }
            java.lang.String r6 = java.lang.String.valueOf(r11)     // Catch:{ all -> 0x023c }
            int r6 = r6.length()     // Catch:{ all -> 0x023c }
            int r6 = r6 + 25
            java.lang.String r13 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x023c }
            int r13 = r13.length()     // Catch:{ all -> 0x023c }
            int r6 = r6 + r13
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x023c }
            r13.<init>(r6)     // Catch:{ all -> 0x023c }
            java.lang.String r6 = "Null InputStream for "
            r13.append(r6)     // Catch:{ all -> 0x023c }
            r13.append(r11)     // Catch:{ all -> 0x023c }
            r13.append(r14)     // Catch:{ all -> 0x023c }
            r13.append(r5)     // Catch:{ all -> 0x023c }
            java.lang.String r6 = r13.toString()     // Catch:{ all -> 0x023c }
            r0.<init>(r6)     // Catch:{ all -> 0x023c }
            throw r0     // Catch:{ all -> 0x023c }
        L_0x020b:
            r15 = 1
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x023c }
            java.lang.String r6 = java.lang.String.valueOf(r11)     // Catch:{ all -> 0x023c }
            int r6 = r6.length()     // Catch:{ all -> 0x023c }
            int r6 = r6 + 17
            java.lang.String r13 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x023c }
            int r13 = r13.length()     // Catch:{ all -> 0x023c }
            int r6 = r6 + r13
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x023c }
            r13.<init>(r6)     // Catch:{ all -> 0x023c }
            java.lang.String r6 = "Did not find "
            r13.append(r6)     // Catch:{ all -> 0x023c }
            r13.append(r11)     // Catch:{ all -> 0x023c }
            r13.append(r14)     // Catch:{ all -> 0x023c }
            r13.append(r5)     // Catch:{ all -> 0x023c }
            java.lang.String r6 = r13.toString()     // Catch:{ all -> 0x023c }
            r0.<init>(r6)     // Catch:{ all -> 0x023c }
            throw r0     // Catch:{ all -> 0x023c }
        L_0x023c:
            r0 = move-exception
            goto L_0x0240
        L_0x023e:
            r0 = move-exception
            r15 = 1
        L_0x0240:
            r11 = 0
        L_0x0242:
            r13 = 0
            goto L_0x0249
        L_0x0244:
            r0 = move-exception
            r15 = 1
            r11 = 0
            r12 = 0
            r13 = 0
        L_0x0249:
            if (r13 == 0) goto L_0x0251
            r13.close()     // Catch:{ IOException -> 0x024f }
            goto L_0x0251
        L_0x024f:
            r0 = move-exception
            goto L_0x025f
        L_0x0251:
            if (r11 == 0) goto L_0x0256
            r11.close()     // Catch:{ IOException -> 0x024f }
        L_0x0256:
            if (r12 != 0) goto L_0x0259
            goto L_0x025c
        L_0x0259:
            r12.close()     // Catch:{ IOException -> 0x024f }
        L_0x025c:
            throw r0     // Catch:{ IOException -> 0x024f }
        L_0x025d:
            r0 = move-exception
            r15 = 1
        L_0x025f:
            int r10 = r10 + 1
            r6 = 1
            goto L_0x013c
        L_0x0264:
        L_0x0265:
            if (r4 == 0) goto L_0x026b
            java.lang.System.load(r4)
            return
        L_0x026b:
            goto L_0x026d
        L_0x026c:
            throw r2
        L_0x026d:
            goto L_0x026c
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.cwf.m5499a(java.lang.String):void");
    }

    /* renamed from: a */
    public final void mo3858a() {
        if (!this.f5793d) {
            this.f5793d = true;
            m5499a("native");
        }
    }
}
