package p000;

import android.os.Build;
import android.os.Process;
import java.util.concurrent.Executor;

/* renamed from: gte */
/* compiled from: PG */
public final class gte {

    /* renamed from: a */
    public static Boolean f12007a;

    /* renamed from: b */
    private static String f12008b;

    /* renamed from: c */
    private static Boolean f12009c;

    /* renamed from: d */
    private static Boolean f12010d;

    /* renamed from: a */
    public static iel m10774a(iel iel) {
        return gpt.m10595a(new grk(ife.m12837a((Executor) iel), iel), iel);
    }

    /* renamed from: a */
    public static boolean m10776a(long j, float f) {
        return (1073741823 & ((int) j)) < ((int) (f * 1.07374182E9f));
    }

    private gte() {
    }

    /* renamed from: a */
    public static boolean m10775a() {
        if (f12009c == null) {
            int myUid = Process.myUid();
            int i = Build.VERSION.SDK_INT;
            f12009c = Boolean.valueOf(Process.isApplicationUid(myUid));
        }
        return f12009c.booleanValue();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00c0, code lost:
        if (r9 > r10) goto L_0x00cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00c2, code lost:
        if (r10 != r9) goto L_0x00c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00c4, code lost:
        r6 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00c6, code lost:
        r6 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        r6 = java.lang.Boolean.valueOf(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00cc, code lost:
        r6 = r8[r10];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00ce, code lost:
        if (r6 != 0) goto L_0x00dc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00d0, code lost:
        r6 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00d7, code lost:
        android.os.StrictMode.setThreadPolicy(r3);
        r5 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00de, code lost:
        if (r6 == 58) goto L_0x00e1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        r6 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0133, code lost:
        if (r12 != false) goto L_0x0139;
     */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x012a  */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:58:0x00d4=Splitter:B:58:0x00d4, B:47:0x00bc=Splitter:B:47:0x00bc} */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m10777a(android.content.Context r12) {
        /*
            java.lang.Boolean r0 = f12010d
            if (r0 != 0) goto L_0x013f
            boolean r0 = m10775a()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0137
            java.lang.String r0 = r12.getPackageName()
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 28
            r5 = 0
            if (r3 < r4) goto L_0x0024
            java.lang.String r3 = android.app.Application.getProcessName()
            boolean r3 = r0.equals(r3)
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            goto L_0x0026
        L_0x0024:
            r3 = r5
        L_0x0026:
            if (r3 != 0) goto L_0x012f
            java.lang.String r3 = android.os.Build.FINGERPRINT
            java.lang.String r6 = "robolectric"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x007a
            java.lang.String r3 = f12008b
            if (r3 == 0) goto L_0x0037
            goto L_0x006e
        L_0x0037:
            int r3 = android.os.Build.VERSION.SDK_INT
            if (r3 >= r4) goto L_0x003c
            goto L_0x0042
        L_0x003c:
            java.lang.String r3 = android.app.Application.getProcessName()
            f12008b = r3
        L_0x0042:
            java.lang.String r3 = "android.app.ActivityThread"
            java.lang.Class<gte> r4 = p000.gte.class
            java.lang.ClassLoader r4 = r4.getClassLoader()     // Catch:{ all -> 0x006b }
            java.lang.Class r3 = java.lang.Class.forName(r3, r2, r4)     // Catch:{ all -> 0x006b }
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x006b }
            java.lang.String r4 = "currentProcessName"
            java.lang.Class[] r6 = new java.lang.Class[r2]     // Catch:{ all -> 0x006b }
            java.lang.reflect.Method r3 = r3.getDeclaredMethod(r4, r6)     // Catch:{ all -> 0x006b }
            r3.setAccessible(r1)     // Catch:{ all -> 0x006b }
            java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch:{ all -> 0x006b }
            java.lang.Object r3 = r3.invoke(r5, r4)     // Catch:{ all -> 0x006b }
            boolean r4 = r3 instanceof java.lang.String     // Catch:{ all -> 0x006b }
            if (r4 != 0) goto L_0x0066
            goto L_0x006c
        L_0x0066:
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x006b }
            f12008b = r3     // Catch:{ all -> 0x006b }
            goto L_0x006c
        L_0x006b:
            r3 = move-exception
        L_0x006c:
            java.lang.String r3 = f12008b
        L_0x006e:
            if (r3 == 0) goto L_0x0079
            boolean r3 = r3.equals(r0)
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            goto L_0x007c
        L_0x0079:
            goto L_0x007b
        L_0x007a:
        L_0x007b:
            r3 = r5
        L_0x007c:
            if (r3 == 0) goto L_0x0084
            boolean r12 = r3.booleanValue()
            goto L_0x0133
        L_0x0084:
            android.os.StrictMode$ThreadPolicy r3 = android.os.StrictMode.allowThreadDiskReads()
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Exception -> 0x00f5, all -> 0x00f0 }
            java.io.File r6 = new java.io.File     // Catch:{ Exception -> 0x00f5, all -> 0x00f0 }
            java.lang.String r7 = "/proc/self/cmdline"
            r6.<init>(r7)     // Catch:{ Exception -> 0x00f5, all -> 0x00f0 }
            r4.<init>(r6)     // Catch:{ Exception -> 0x00f5, all -> 0x00f0 }
            java.lang.String r6 = "UTF-8"
            byte[] r6 = r0.getBytes(r6)     // Catch:{ all -> 0x00e6 }
            int r7 = r6.length     // Catch:{ all -> 0x00e6 }
            int r7 = r7 + r1
            byte[] r8 = new byte[r7]     // Catch:{ all -> 0x00e6 }
            r9 = 0
        L_0x009f:
            if (r9 >= r7) goto L_0x00ac
            int r10 = r7 - r9
            int r10 = r4.read(r8, r9, r10)     // Catch:{ all -> 0x00e6 }
            r11 = -1
            if (r10 == r11) goto L_0x00ac
            int r9 = r9 + r10
            goto L_0x009f
        L_0x00ac:
            int r7 = r6.length     // Catch:{ all -> 0x00e6 }
            if (r9 < r7) goto L_0x00bc
            r7 = 0
        L_0x00b0:
            int r10 = r6.length     // Catch:{ all -> 0x00e6 }
            if (r7 >= r10) goto L_0x00c0
            byte r10 = r6[r7]     // Catch:{ all -> 0x00e6 }
            byte r11 = r8[r7]     // Catch:{ all -> 0x00e6 }
            if (r10 != r11) goto L_0x00bc
            int r7 = r7 + 1
            goto L_0x00b0
        L_0x00bc:
            r4.close()     // Catch:{ Exception -> 0x00f5, all -> 0x00f0 }
            goto L_0x00f6
        L_0x00c0:
            if (r9 > r10) goto L_0x00cc
            if (r10 != r9) goto L_0x00c6
            r6 = 1
            goto L_0x00c7
        L_0x00c6:
            r6 = 0
        L_0x00c7:
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)     // Catch:{ all -> 0x00e6 }
            goto L_0x00d4
        L_0x00cc:
            byte r6 = r8[r10]     // Catch:{ all -> 0x00e6 }
            if (r6 != 0) goto L_0x00dc
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r1)     // Catch:{ all -> 0x00e6 }
        L_0x00d4:
            r4.close()     // Catch:{ Exception -> 0x00f5, all -> 0x00f0 }
            android.os.StrictMode.setThreadPolicy(r3)
            r5 = r6
            goto L_0x00f9
        L_0x00dc:
            r7 = 58
            if (r6 == r7) goto L_0x00e1
            goto L_0x00bc
        L_0x00e1:
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x00e6 }
            goto L_0x00d4
        L_0x00e6:
            r6 = move-exception
            r4.close()     // Catch:{ all -> 0x00eb }
            goto L_0x00ef
        L_0x00eb:
            r4 = move-exception
            p000.ifn.m12935a((java.lang.Throwable) r6, (java.lang.Throwable) r4)     // Catch:{ Exception -> 0x00f5, all -> 0x00f0 }
        L_0x00ef:
            throw r6     // Catch:{ Exception -> 0x00f5, all -> 0x00f0 }
        L_0x00f0:
            r12 = move-exception
            android.os.StrictMode.setThreadPolicy(r3)
            throw r12
        L_0x00f5:
            r4 = move-exception
        L_0x00f6:
            android.os.StrictMode.setThreadPolicy(r3)
        L_0x00f9:
            if (r5 != 0) goto L_0x012a
            java.lang.String r3 = "activity"
            java.lang.Object r12 = r12.getSystemService(r3)
            android.app.ActivityManager r12 = (android.app.ActivityManager) r12
            java.util.List r12 = r12.getRunningAppProcesses()
            if (r12 == 0) goto L_0x0135
            int r3 = android.os.Process.myPid()
            java.util.Iterator r12 = r12.iterator()
        L_0x0111:
            boolean r4 = r12.hasNext()
            if (r4 == 0) goto L_0x0138
            java.lang.Object r4 = r12.next()
            android.app.ActivityManager$RunningAppProcessInfo r4 = (android.app.ActivityManager.RunningAppProcessInfo) r4
            int r5 = r4.pid
            if (r5 != r3) goto L_0x0111
            java.lang.String r4 = r4.processName
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x0111
            goto L_0x0135
        L_0x012a:
            boolean r12 = r5.booleanValue()
            goto L_0x0133
        L_0x012f:
            boolean r12 = r3.booleanValue()
        L_0x0133:
            if (r12 == 0) goto L_0x0136
        L_0x0135:
            goto L_0x0139
        L_0x0136:
            goto L_0x0138
        L_0x0137:
        L_0x0138:
            r1 = 0
        L_0x0139:
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r1)
            f12010d = r12
        L_0x013f:
            java.lang.Boolean r12 = f12010d
            boolean r12 = r12.booleanValue()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.gte.m10777a(android.content.Context):boolean");
    }

    /* renamed from: a */
    public static ieh m10772a(ieh ieh, Class cls, hpr hpr, Executor executor) {
        return ibd.m12603a(ieh, cls, hmq.m11742a(hpr), executor);
    }

    /* renamed from: a */
    public static ieh m10773a(ieh ieh, Class cls, icf icf, Executor executor) {
        return ibd.m12604a(ieh, cls, hmq.m11744a(icf), executor);
    }

    /* renamed from: a */
    public static ieh m10770a(ieh ieh, hpr hpr, Executor executor) {
        return ibv.m12657a(ieh, hmq.m11742a(hpr), executor);
    }

    /* renamed from: a */
    public static ieh m10771a(ieh ieh, icf icf, Executor executor) {
        return ibv.m12658a(ieh, hmq.m11744a(icf), executor);
    }

    @SafeVarargs
    /* renamed from: a */
    public static hnm m10769a(ieh... iehArr) {
        return new hnm(ife.m12867b(iehArr));
    }

    @SafeVarargs
    /* renamed from: b */
    public static hnm m10778b(ieh... iehArr) {
        return new hnm(ife.m12884c(iehArr));
    }
}
