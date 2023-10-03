package p000;

import android.os.Build;
import android.os.StrictMode;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: apu */
/* compiled from: PG */
public final class apu implements Closeable {

    /* renamed from: a */
    public final File f1380a;

    /* renamed from: b */
    public final File f1381b;

    /* renamed from: c */
    public final File f1382c;

    /* renamed from: d */
    public final int f1383d;

    /* renamed from: e */
    public long f1384e = 0;

    /* renamed from: f */
    public Writer f1385f;

    /* renamed from: g */
    public final LinkedHashMap f1386g = new LinkedHashMap(0, 0.75f, true);

    /* renamed from: h */
    public int f1387h;

    /* renamed from: i */
    private final File f1388i;

    /* renamed from: j */
    private final int f1389j;

    /* renamed from: k */
    private final long f1390k;

    /* renamed from: l */
    private long f1391l = 0;

    /* renamed from: m */
    private final ThreadPoolExecutor f1392m = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new apq((byte[]) null));

    /* renamed from: n */
    private final Callable f1393n = new C0018app(this);

    public apu(File file, long j) {
        File file2 = file;
        this.f1380a = file2;
        this.f1389j = 1;
        this.f1381b = new File(file2, "journal");
        this.f1382c = new File(file2, "journal.tmp");
        this.f1388i = new File(file2, "journal.bkp");
        this.f1383d = 1;
        this.f1390k = j;
    }

    /* renamed from: f */
    private final void m1421f() {
        if (this.f1385f == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public final synchronized void close() {
        if (this.f1385f != null) {
            ArrayList arrayList = new ArrayList(this.f1386g.values());
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                apr apr = ((aps) arrayList.get(i)).f1376e;
                if (apr != null) {
                    apr.mo1456a();
                }
            }
            mo1468d();
            m1418a(this.f1385f);
            this.f1385f = null;
        }
    }

    /* renamed from: a */
    private static void m1418a(Writer writer) {
        int i = Build.VERSION.SDK_INT;
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitUnbufferedIo().build());
        try {
            writer.close();
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00bb, code lost:
        if (mo1466c() == false) goto L_0x00bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00be, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void mo1463a(p000.apr r11, boolean r12) {
        /*
            r10 = this;
            monitor-enter(r10)
            aps r0 = r11.f1368a     // Catch:{ all -> 0x0101 }
            apr r1 = r0.f1376e     // Catch:{ all -> 0x0101 }
            if (r1 != r11) goto L_0x00fb
            r1 = 1
            r2 = 0
            if (r12 == 0) goto L_0x0045
            boolean r3 = r0.f1375d     // Catch:{ all -> 0x0101 }
            if (r3 != 0) goto L_0x0044
            r3 = 0
        L_0x0010:
            int r4 = r10.f1383d     // Catch:{ all -> 0x0101 }
            if (r3 >= r4) goto L_0x0046
            boolean[] r3 = r11.f1369b     // Catch:{ all -> 0x0101 }
            boolean r3 = r3[r2]     // Catch:{ all -> 0x0101 }
            if (r3 == 0) goto L_0x002b
            java.io.File r3 = r0.mo1460c()     // Catch:{ all -> 0x0101 }
            boolean r3 = r3.exists()     // Catch:{ all -> 0x0101 }
            if (r3 == 0) goto L_0x0026
            r3 = 1
            goto L_0x0010
        L_0x0026:
            r11.mo1456a()     // Catch:{ all -> 0x0101 }
            monitor-exit(r10)
            return
        L_0x002b:
            r11.mo1456a()     // Catch:{ all -> 0x0101 }
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0101 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0101 }
            r0 = 61
            r12.<init>(r0)     // Catch:{ all -> 0x0101 }
            java.lang.String r0 = "Newly created entry didn't create value for index 0"
            r12.append(r0)     // Catch:{ all -> 0x0101 }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x0101 }
            r11.<init>(r12)     // Catch:{ all -> 0x0101 }
            throw r11     // Catch:{ all -> 0x0101 }
        L_0x0044:
            goto L_0x0046
        L_0x0045:
        L_0x0046:
            r11 = 0
        L_0x0047:
            int r3 = r10.f1383d     // Catch:{ all -> 0x0101 }
            if (r11 < r3) goto L_0x00c7
            int r11 = r10.f1387h     // Catch:{ all -> 0x0101 }
            int r11 = r11 + r1
            r10.f1387h = r11     // Catch:{ all -> 0x0101 }
            r11 = 0
            r0.f1376e = r11     // Catch:{ all -> 0x0101 }
            boolean r11 = r0.f1375d     // Catch:{ all -> 0x0101 }
            r11 = r11 | r12
            r1 = 10
            r2 = 32
            if (r11 != 0) goto L_0x007c
            java.util.LinkedHashMap r11 = r10.f1386g     // Catch:{ all -> 0x0101 }
            java.lang.String r12 = r0.f1372a     // Catch:{ all -> 0x0101 }
            r11.remove(r12)     // Catch:{ all -> 0x0101 }
            java.io.Writer r11 = r10.f1385f     // Catch:{ all -> 0x0101 }
            java.lang.String r12 = "REMOVE"
            r11.append(r12)     // Catch:{ all -> 0x0101 }
            java.io.Writer r11 = r10.f1385f     // Catch:{ all -> 0x0101 }
            r11.append(r2)     // Catch:{ all -> 0x0101 }
            java.io.Writer r11 = r10.f1385f     // Catch:{ all -> 0x0101 }
            java.lang.String r12 = r0.f1372a     // Catch:{ all -> 0x0101 }
            r11.append(r12)     // Catch:{ all -> 0x0101 }
            java.io.Writer r11 = r10.f1385f     // Catch:{ all -> 0x0101 }
            r11.append(r1)     // Catch:{ all -> 0x0101 }
            goto L_0x00a9
        L_0x007c:
            r0.f1375d = true     // Catch:{ all -> 0x0101 }
            java.io.Writer r11 = r10.f1385f     // Catch:{ all -> 0x0101 }
            java.lang.String r3 = "CLEAN"
            r11.append(r3)     // Catch:{ all -> 0x0101 }
            java.io.Writer r11 = r10.f1385f     // Catch:{ all -> 0x0101 }
            r11.append(r2)     // Catch:{ all -> 0x0101 }
            java.io.Writer r11 = r10.f1385f     // Catch:{ all -> 0x0101 }
            java.lang.String r2 = r0.f1372a     // Catch:{ all -> 0x0101 }
            r11.append(r2)     // Catch:{ all -> 0x0101 }
            java.io.Writer r11 = r10.f1385f     // Catch:{ all -> 0x0101 }
            java.lang.String r0 = r0.mo1458a()     // Catch:{ all -> 0x0101 }
            r11.append(r0)     // Catch:{ all -> 0x0101 }
            java.io.Writer r11 = r10.f1385f     // Catch:{ all -> 0x0101 }
            r11.append(r1)     // Catch:{ all -> 0x0101 }
            if (r12 == 0) goto L_0x00a9
            long r11 = r10.f1391l     // Catch:{ all -> 0x0101 }
            r0 = 1
            long r11 = r11 + r0
            r10.f1391l = r11     // Catch:{ all -> 0x0101 }
        L_0x00a9:
            java.io.Writer r11 = r10.f1385f     // Catch:{ all -> 0x0101 }
            m1419b((java.io.Writer) r11)     // Catch:{ all -> 0x0101 }
            long r11 = r10.f1384e     // Catch:{ all -> 0x0101 }
            long r0 = r10.f1390k     // Catch:{ all -> 0x0101 }
            int r2 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            if (r2 <= 0) goto L_0x00b7
            goto L_0x00bf
        L_0x00b7:
            boolean r11 = r10.mo1466c()     // Catch:{ all -> 0x0101 }
            if (r11 != 0) goto L_0x00bf
        L_0x00bd:
            monitor-exit(r10)
            return
        L_0x00bf:
            java.util.concurrent.ThreadPoolExecutor r11 = r10.f1392m     // Catch:{ all -> 0x0101 }
            java.util.concurrent.Callable r12 = r10.f1393n     // Catch:{ all -> 0x0101 }
            r11.submit(r12)     // Catch:{ all -> 0x0101 }
            goto L_0x00bd
        L_0x00c7:
            java.io.File r11 = r0.mo1460c()     // Catch:{ all -> 0x0101 }
            if (r12 == 0) goto L_0x00f4
            boolean r3 = r11.exists()     // Catch:{ all -> 0x0101 }
            if (r3 == 0) goto L_0x00f0
            java.io.File r3 = r0.mo1459b()     // Catch:{ all -> 0x0101 }
            r11.renameTo(r3)     // Catch:{ all -> 0x0101 }
            long[] r11 = r0.f1373b     // Catch:{ all -> 0x0101 }
            r4 = r11[r2]     // Catch:{ all -> 0x0101 }
            long r6 = r3.length()     // Catch:{ all -> 0x0101 }
            long[] r11 = r0.f1373b     // Catch:{ all -> 0x0101 }
            r11[r2] = r6     // Catch:{ all -> 0x0101 }
            long r8 = r10.f1384e     // Catch:{ all -> 0x0101 }
            long r8 = r8 - r4
            long r8 = r8 + r6
            r10.f1384e = r8     // Catch:{ all -> 0x0101 }
            r11 = 1
            goto L_0x0047
        L_0x00f0:
            r11 = 1
            goto L_0x0047
        L_0x00f4:
            m1416a((java.io.File) r11)     // Catch:{ all -> 0x0101 }
            r11 = 1
            goto L_0x0047
        L_0x00fb:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0101 }
            r11.<init>()     // Catch:{ all -> 0x0101 }
            throw r11     // Catch:{ all -> 0x0101 }
        L_0x0101:
            r11 = move-exception
            monitor-exit(r10)
            goto L_0x0105
        L_0x0104:
            throw r11
        L_0x0105:
            goto L_0x0104
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.apu.mo1463a(apr, boolean):void");
    }

    /* renamed from: e */
    public final void mo1469e() {
        close();
        apx.m1433a(this.f1380a);
    }

    /* renamed from: a */
    public static void m1416a(File file) {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    /* renamed from: b */
    public final synchronized apr mo1464b(String str) {
        m1421f();
        aps aps = (aps) this.f1386g.get(str);
        if (aps == null) {
            aps = new aps(this, str);
            this.f1386g.put(str, aps);
        } else if (aps.f1376e != null) {
            return null;
        }
        apr apr = new apr(this, aps);
        aps.f1376e = apr;
        this.f1385f.append("DIRTY");
        this.f1385f.append(' ');
        this.f1385f.append(str);
        this.f1385f.append(10);
        m1419b(this.f1385f);
        return apr;
    }

    /* renamed from: b */
    private static void m1419b(Writer writer) {
        int i = Build.VERSION.SDK_INT;
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitUnbufferedIo().build());
        try {
            writer.flush();
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005d, code lost:
        return null;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized p000.apt mo1461a(java.lang.String r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            r6.m1421f()     // Catch:{ all -> 0x005e }
            java.util.LinkedHashMap r0 = r6.f1386g     // Catch:{ all -> 0x005e }
            java.lang.Object r0 = r0.get(r7)     // Catch:{ all -> 0x005e }
            aps r0 = (p000.aps) r0     // Catch:{ all -> 0x005e }
            r1 = 0
            if (r0 == 0) goto L_0x005c
            boolean r2 = r0.f1375d     // Catch:{ all -> 0x005e }
            if (r2 == 0) goto L_0x005c
            java.io.File[] r2 = r0.f1374c     // Catch:{ all -> 0x005e }
            int r3 = r2.length     // Catch:{ all -> 0x005e }
            r4 = 0
        L_0x0017:
            if (r4 < r3) goto L_0x004f
            int r1 = r6.f1387h     // Catch:{ all -> 0x005e }
            int r1 = r1 + 1
            r6.f1387h = r1     // Catch:{ all -> 0x005e }
            java.io.Writer r1 = r6.f1385f     // Catch:{ all -> 0x005e }
            java.lang.String r2 = "READ"
            r1.append(r2)     // Catch:{ all -> 0x005e }
            java.io.Writer r1 = r6.f1385f     // Catch:{ all -> 0x005e }
            r2 = 32
            r1.append(r2)     // Catch:{ all -> 0x005e }
            java.io.Writer r1 = r6.f1385f     // Catch:{ all -> 0x005e }
            r1.append(r7)     // Catch:{ all -> 0x005e }
            java.io.Writer r7 = r6.f1385f     // Catch:{ all -> 0x005e }
            r1 = 10
            r7.append(r1)     // Catch:{ all -> 0x005e }
            boolean r7 = r6.mo1466c()     // Catch:{ all -> 0x005e }
            if (r7 == 0) goto L_0x0046
            java.util.concurrent.ThreadPoolExecutor r7 = r6.f1392m     // Catch:{ all -> 0x005e }
            java.util.concurrent.Callable r1 = r6.f1393n     // Catch:{ all -> 0x005e }
            r7.submit(r1)     // Catch:{ all -> 0x005e }
        L_0x0046:
            apt r7 = new apt     // Catch:{ all -> 0x005e }
            java.io.File[] r0 = r0.f1374c     // Catch:{ all -> 0x005e }
            r7.<init>(r0)     // Catch:{ all -> 0x005e }
            monitor-exit(r6)
            return r7
        L_0x004f:
            r5 = r2[r4]     // Catch:{ all -> 0x005e }
            boolean r5 = r5.exists()     // Catch:{ all -> 0x005e }
            if (r5 == 0) goto L_0x005a
            int r4 = r4 + 1
            goto L_0x0017
        L_0x005a:
            monitor-exit(r6)
            return r1
        L_0x005c:
            monitor-exit(r6)
            return r1
        L_0x005e:
            r7 = move-exception
            monitor-exit(r6)
            goto L_0x0062
        L_0x0061:
            throw r7
        L_0x0062:
            goto L_0x0061
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.apu.mo1461a(java.lang.String):apt");
    }

    /* renamed from: c */
    public final boolean mo1466c() {
        int i = this.f1387h;
        return i >= 2000 && i >= this.f1386g.size();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r4 = java.lang.String.valueOf(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006f, code lost:
        if (r4.length() != 0) goto L_0x0077;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0071, code lost:
        r4 = new java.lang.String("unexpected journal line: ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0077, code lost:
        r4 = "unexpected journal line: ".concat(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x007e, code lost:
        throw new java.io.IOException(r4);
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo1462a() {
        /*
            r11 = this;
            java.lang.String r0 = ", "
            apw r1 = new apw
            java.io.FileInputStream r2 = new java.io.FileInputStream
            java.io.File r3 = r11.f1381b
            r2.<init>(r3)
            java.nio.charset.Charset r3 = p000.apx.f1400a
            r1.<init>(r2, r3)
            java.lang.String r2 = r1.mo1471a()     // Catch:{ all -> 0x01b7 }
            java.lang.String r3 = r1.mo1471a()     // Catch:{ all -> 0x01b7 }
            java.lang.String r4 = r1.mo1471a()     // Catch:{ all -> 0x01b7 }
            java.lang.String r5 = r1.mo1471a()     // Catch:{ all -> 0x01b7 }
            java.lang.String r6 = r1.mo1471a()     // Catch:{ all -> 0x01b7 }
            java.lang.String r7 = "libcore.io.DiskLruCache"
            boolean r7 = r7.equals(r2)     // Catch:{ all -> 0x01b7 }
            if (r7 == 0) goto L_0x0164
            java.lang.String r7 = "1"
            boolean r7 = r7.equals(r3)     // Catch:{ all -> 0x01b7 }
            if (r7 == 0) goto L_0x0164
            int r7 = r11.f1389j     // Catch:{ all -> 0x01b7 }
            java.lang.String r7 = java.lang.Integer.toString(r7)     // Catch:{ all -> 0x01b7 }
            boolean r4 = r7.equals(r4)     // Catch:{ all -> 0x01b7 }
            if (r4 == 0) goto L_0x0164
            int r4 = r11.f1383d     // Catch:{ all -> 0x01b7 }
            java.lang.String r4 = java.lang.Integer.toString(r4)     // Catch:{ all -> 0x01b7 }
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x01b7 }
            if (r4 == 0) goto L_0x0164
            java.lang.String r4 = ""
            boolean r4 = r4.equals(r6)     // Catch:{ all -> 0x01b7 }
            if (r4 == 0) goto L_0x0164
            r0 = 0
            r2 = 0
        L_0x0056:
            r3 = -1
            java.lang.String r4 = r1.mo1471a()     // Catch:{ EOFException -> 0x0138 }
            r5 = 32
            int r6 = r4.indexOf(r5)     // Catch:{ EOFException -> 0x0138 }
            java.lang.String r7 = "unexpected journal line: "
            if (r6 != r3) goto L_0x007f
            java.io.IOException r0 = new java.io.IOException     // Catch:{ EOFException -> 0x0138 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ EOFException -> 0x0138 }
            int r5 = r4.length()     // Catch:{ EOFException -> 0x0138 }
            if (r5 != 0) goto L_0x0077
            java.lang.String r4 = new java.lang.String     // Catch:{ EOFException -> 0x0138 }
            r4.<init>(r7)     // Catch:{ EOFException -> 0x0138 }
            goto L_0x007b
        L_0x0077:
            java.lang.String r4 = r7.concat(r4)     // Catch:{ EOFException -> 0x0138 }
        L_0x007b:
            r0.<init>(r4)     // Catch:{ EOFException -> 0x0138 }
            throw r0     // Catch:{ EOFException -> 0x0138 }
        L_0x007f:
            int r8 = r6 + 1
            int r5 = r4.indexOf(r5, r8)     // Catch:{ EOFException -> 0x0138 }
            if (r5 == r3) goto L_0x008c
            java.lang.String r8 = r4.substring(r8, r5)     // Catch:{ EOFException -> 0x0138 }
            goto L_0x00a2
        L_0x008c:
            java.lang.String r8 = r4.substring(r8)     // Catch:{ EOFException -> 0x0138 }
            r9 = 6
            if (r6 != r9) goto L_0x00a2
            java.lang.String r9 = "REMOVE"
            boolean r9 = r4.startsWith(r9)     // Catch:{ EOFException -> 0x0138 }
            if (r9 == 0) goto L_0x00a2
            java.util.LinkedHashMap r4 = r11.f1386g     // Catch:{ EOFException -> 0x0138 }
            r4.remove(r8)     // Catch:{ EOFException -> 0x0138 }
            goto L_0x011a
        L_0x00a2:
            java.util.LinkedHashMap r9 = r11.f1386g     // Catch:{ EOFException -> 0x0138 }
            java.lang.Object r9 = r9.get(r8)     // Catch:{ EOFException -> 0x0138 }
            aps r9 = (p000.aps) r9     // Catch:{ EOFException -> 0x0138 }
            if (r9 != 0) goto L_0x00b6
            aps r9 = new aps     // Catch:{ EOFException -> 0x0138 }
            r9.<init>(r11, r8)     // Catch:{ EOFException -> 0x0138 }
            java.util.LinkedHashMap r10 = r11.f1386g     // Catch:{ EOFException -> 0x0138 }
            r10.put(r8, r9)     // Catch:{ EOFException -> 0x0138 }
        L_0x00b6:
            r8 = 5
            if (r5 == r3) goto L_0x00f8
            if (r6 != r8) goto L_0x00f8
            java.lang.String r10 = "CLEAN"
            boolean r10 = r4.startsWith(r10)     // Catch:{ EOFException -> 0x0138 }
            if (r10 == 0) goto L_0x00f8
            int r5 = r5 + 1
            java.lang.String r4 = r4.substring(r5)     // Catch:{ EOFException -> 0x0138 }
            java.lang.String r5 = " "
            java.lang.String[] r4 = r4.split(r5)     // Catch:{ EOFException -> 0x0138 }
            r9.f1375d = true     // Catch:{ EOFException -> 0x0138 }
            r5 = 0
            r9.f1376e = r5     // Catch:{ EOFException -> 0x0138 }
            int r5 = r4.length     // Catch:{ EOFException -> 0x0138 }
            apu r6 = r9.f1377f     // Catch:{ EOFException -> 0x0138 }
            int r6 = r6.f1383d     // Catch:{ EOFException -> 0x0138 }
            if (r5 != r6) goto L_0x00f3
            r5 = 0
        L_0x00dd:
            int r6 = r4.length     // Catch:{ NumberFormatException -> 0x00ed }
            if (r5 >= r6) goto L_0x011a
            long[] r6 = r9.f1373b     // Catch:{ NumberFormatException -> 0x00ed }
            r7 = r4[r5]     // Catch:{ NumberFormatException -> 0x00ed }
            long r7 = java.lang.Long.parseLong(r7)     // Catch:{ NumberFormatException -> 0x00ed }
            r6[r5] = r7     // Catch:{ NumberFormatException -> 0x00ed }
            int r5 = r5 + 1
            goto L_0x00dd
        L_0x00ed:
            r0 = move-exception
            java.io.IOException r0 = p000.aps.m1411a((java.lang.String[]) r4)     // Catch:{ EOFException -> 0x0138 }
            throw r0     // Catch:{ EOFException -> 0x0138 }
        L_0x00f3:
            java.io.IOException r0 = p000.aps.m1411a((java.lang.String[]) r4)     // Catch:{ EOFException -> 0x0138 }
            throw r0     // Catch:{ EOFException -> 0x0138 }
        L_0x00f8:
            if (r5 != r3) goto L_0x010c
            if (r6 != r8) goto L_0x010c
            java.lang.String r8 = "DIRTY"
            boolean r8 = r4.startsWith(r8)     // Catch:{ EOFException -> 0x0138 }
            if (r8 == 0) goto L_0x010c
            apr r4 = new apr     // Catch:{ EOFException -> 0x0138 }
            r4.<init>(r11, r9)     // Catch:{ EOFException -> 0x0138 }
            r9.f1376e = r4     // Catch:{ EOFException -> 0x0138 }
            goto L_0x011a
        L_0x010c:
            if (r5 == r3) goto L_0x010f
            goto L_0x011e
        L_0x010f:
            r5 = 4
            if (r6 != r5) goto L_0x011e
            java.lang.String r5 = "READ"
            boolean r5 = r4.startsWith(r5)     // Catch:{ EOFException -> 0x0138 }
            if (r5 == 0) goto L_0x011e
        L_0x011a:
            int r2 = r2 + 1
            goto L_0x0056
        L_0x011e:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ EOFException -> 0x0138 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ EOFException -> 0x0138 }
            int r5 = r4.length()     // Catch:{ EOFException -> 0x0138 }
            if (r5 != 0) goto L_0x0130
            java.lang.String r4 = new java.lang.String     // Catch:{ EOFException -> 0x0138 }
            r4.<init>(r7)     // Catch:{ EOFException -> 0x0138 }
            goto L_0x0134
        L_0x0130:
            java.lang.String r4 = r7.concat(r4)     // Catch:{ EOFException -> 0x0138 }
        L_0x0134:
            r0.<init>(r4)     // Catch:{ EOFException -> 0x0138 }
            throw r0     // Catch:{ EOFException -> 0x0138 }
        L_0x0138:
            r0 = move-exception
            java.util.LinkedHashMap r0 = r11.f1386g     // Catch:{ all -> 0x01b7 }
            int r0 = r0.size()     // Catch:{ all -> 0x01b7 }
            int r2 = r2 - r0
            r11.f1387h = r2     // Catch:{ all -> 0x01b7 }
            int r0 = r1.f1396b     // Catch:{ all -> 0x01b7 }
            if (r0 == r3) goto L_0x015d
            java.io.BufferedWriter r0 = new java.io.BufferedWriter     // Catch:{ all -> 0x01b7 }
            java.io.OutputStreamWriter r2 = new java.io.OutputStreamWriter     // Catch:{ all -> 0x01b7 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ all -> 0x01b7 }
            java.io.File r4 = r11.f1381b     // Catch:{ all -> 0x01b7 }
            r5 = 1
            r3.<init>(r4, r5)     // Catch:{ all -> 0x01b7 }
            java.nio.charset.Charset r4 = p000.apx.f1400a     // Catch:{ all -> 0x01b7 }
            r2.<init>(r3, r4)     // Catch:{ all -> 0x01b7 }
            r0.<init>(r2)     // Catch:{ all -> 0x01b7 }
            r11.f1385f = r0     // Catch:{ all -> 0x01b7 }
            goto L_0x0160
        L_0x015d:
            r11.mo1465b()     // Catch:{ all -> 0x01b7 }
        L_0x0160:
            p000.apx.m1432a((java.io.Closeable) r1)
            return
        L_0x0164:
            java.io.IOException r4 = new java.io.IOException     // Catch:{ all -> 0x01b7 }
            java.lang.String r7 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x01b7 }
            int r7 = r7.length()     // Catch:{ all -> 0x01b7 }
            int r7 = r7 + 35
            java.lang.String r8 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x01b7 }
            int r8 = r8.length()     // Catch:{ all -> 0x01b7 }
            int r7 = r7 + r8
            java.lang.String r8 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x01b7 }
            int r8 = r8.length()     // Catch:{ all -> 0x01b7 }
            int r7 = r7 + r8
            java.lang.String r8 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x01b7 }
            int r8 = r8.length()     // Catch:{ all -> 0x01b7 }
            int r7 = r7 + r8
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x01b7 }
            r8.<init>(r7)     // Catch:{ all -> 0x01b7 }
            java.lang.String r7 = "unexpected journal header: ["
            r8.append(r7)     // Catch:{ all -> 0x01b7 }
            r8.append(r2)     // Catch:{ all -> 0x01b7 }
            r8.append(r0)     // Catch:{ all -> 0x01b7 }
            r8.append(r3)     // Catch:{ all -> 0x01b7 }
            r8.append(r0)     // Catch:{ all -> 0x01b7 }
            r8.append(r5)     // Catch:{ all -> 0x01b7 }
            r8.append(r0)     // Catch:{ all -> 0x01b7 }
            r8.append(r6)     // Catch:{ all -> 0x01b7 }
            java.lang.String r0 = "]"
            r8.append(r0)     // Catch:{ all -> 0x01b7 }
            java.lang.String r0 = r8.toString()     // Catch:{ all -> 0x01b7 }
            r4.<init>(r0)     // Catch:{ all -> 0x01b7 }
            throw r4     // Catch:{ all -> 0x01b7 }
        L_0x01b7:
            r0 = move-exception
            p000.apx.m1432a((java.io.Closeable) r1)
            goto L_0x01bd
        L_0x01bc:
            throw r0
        L_0x01bd:
            goto L_0x01bc
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.apu.mo1462a():void");
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: b */
    public final synchronized void mo1465b() {
        Writer writer = this.f1385f;
        if (writer != null) {
            m1418a(writer);
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f1382c), apx.f1400a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f1389j));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f1383d));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (aps aps : this.f1386g.values()) {
                if (aps.f1376e != null) {
                    String str = aps.f1372a;
                    StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 7);
                    sb.append("DIRTY ");
                    sb.append(str);
                    sb.append(10);
                    bufferedWriter.write(sb.toString());
                } else {
                    String str2 = aps.f1372a;
                    String a = aps.mo1458a();
                    StringBuilder sb2 = new StringBuilder(String.valueOf(str2).length() + 7 + String.valueOf(a).length());
                    sb2.append("CLEAN ");
                    sb2.append(str2);
                    sb2.append(a);
                    sb2.append(10);
                    bufferedWriter.write(sb2.toString());
                }
            }
            m1418a((Writer) bufferedWriter);
            if (this.f1381b.exists()) {
                m1417a(this.f1381b, this.f1388i, true);
            }
            m1417a(this.f1382c, this.f1381b, false);
            this.f1388i.delete();
            this.f1385f = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f1381b, true), apx.f1400a));
        } catch (Throwable th) {
            m1418a((Writer) bufferedWriter);
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004b, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0094, code lost:
        return;
     */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized void m1420c(java.lang.String r9) {
        /*
            r8 = this;
            monitor-enter(r8)
            r8.m1421f()     // Catch:{ all -> 0x0095 }
            java.util.LinkedHashMap r0 = r8.f1386g     // Catch:{ all -> 0x0095 }
            java.lang.Object r0 = r0.get(r9)     // Catch:{ all -> 0x0095 }
            aps r0 = (p000.aps) r0     // Catch:{ all -> 0x0095 }
            if (r0 == 0) goto L_0x0093
            apr r1 = r0.f1376e     // Catch:{ all -> 0x0095 }
            if (r1 != 0) goto L_0x0093
            r1 = 1
            r2 = 0
            r3 = 0
        L_0x0015:
            int r4 = r8.f1383d     // Catch:{ all -> 0x0095 }
            if (r3 < r4) goto L_0x004c
            int r0 = r8.f1387h     // Catch:{ all -> 0x0095 }
            int r0 = r0 + r1
            r8.f1387h = r0     // Catch:{ all -> 0x0095 }
            java.io.Writer r0 = r8.f1385f     // Catch:{ all -> 0x0095 }
            java.lang.String r1 = "REMOVE"
            r0.append(r1)     // Catch:{ all -> 0x0095 }
            java.io.Writer r0 = r8.f1385f     // Catch:{ all -> 0x0095 }
            r1 = 32
            r0.append(r1)     // Catch:{ all -> 0x0095 }
            java.io.Writer r0 = r8.f1385f     // Catch:{ all -> 0x0095 }
            r0.append(r9)     // Catch:{ all -> 0x0095 }
            java.io.Writer r0 = r8.f1385f     // Catch:{ all -> 0x0095 }
            r1 = 10
            r0.append(r1)     // Catch:{ all -> 0x0095 }
            java.util.LinkedHashMap r0 = r8.f1386g     // Catch:{ all -> 0x0095 }
            r0.remove(r9)     // Catch:{ all -> 0x0095 }
            boolean r9 = r8.mo1466c()     // Catch:{ all -> 0x0095 }
            if (r9 == 0) goto L_0x004a
            java.util.concurrent.ThreadPoolExecutor r9 = r8.f1392m     // Catch:{ all -> 0x0095 }
            java.util.concurrent.Callable r0 = r8.f1393n     // Catch:{ all -> 0x0095 }
            r9.submit(r0)     // Catch:{ all -> 0x0095 }
        L_0x004a:
            monitor-exit(r8)
            return
        L_0x004c:
            java.io.File r3 = r0.mo1459b()     // Catch:{ all -> 0x0095 }
            boolean r4 = r3.exists()     // Catch:{ all -> 0x0095 }
            if (r4 != 0) goto L_0x0057
        L_0x0056:
            goto L_0x005e
        L_0x0057:
            boolean r4 = r3.delete()     // Catch:{ all -> 0x0095 }
            if (r4 == 0) goto L_0x006e
            goto L_0x0056
        L_0x005e:
            long r3 = r8.f1384e     // Catch:{ all -> 0x0095 }
            long[] r5 = r0.f1373b     // Catch:{ all -> 0x0095 }
            r6 = r5[r2]     // Catch:{ all -> 0x0095 }
            long r3 = r3 - r6
            r8.f1384e = r3     // Catch:{ all -> 0x0095 }
            r3 = 0
            r5[r2] = r3     // Catch:{ all -> 0x0095 }
            r3 = 1
            goto L_0x0015
        L_0x006e:
            java.io.IOException r9 = new java.io.IOException     // Catch:{ all -> 0x0095 }
            java.lang.String r0 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x0095 }
            java.lang.String r1 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0095 }
            int r1 = r1.length()     // Catch:{ all -> 0x0095 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0095 }
            int r1 = r1 + 17
            r2.<init>(r1)     // Catch:{ all -> 0x0095 }
            java.lang.String r1 = "failed to delete "
            r2.append(r1)     // Catch:{ all -> 0x0095 }
            r2.append(r0)     // Catch:{ all -> 0x0095 }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x0095 }
            r9.<init>(r0)     // Catch:{ all -> 0x0095 }
            throw r9     // Catch:{ all -> 0x0095 }
        L_0x0093:
            monitor-exit(r8)
            return
        L_0x0095:
            r9 = move-exception
            monitor-exit(r8)
            goto L_0x0099
        L_0x0098:
            throw r9
        L_0x0099:
            goto L_0x0098
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.apu.m1420c(java.lang.String):void");
    }

    /* renamed from: a */
    public static void m1417a(File file, File file2, boolean z) {
        if (z) {
            m1416a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    /* renamed from: d */
    public final void mo1468d() {
        while (this.f1384e > this.f1390k) {
            m1420c((String) ((Map.Entry) this.f1386g.entrySet().iterator().next()).getKey());
        }
    }
}
