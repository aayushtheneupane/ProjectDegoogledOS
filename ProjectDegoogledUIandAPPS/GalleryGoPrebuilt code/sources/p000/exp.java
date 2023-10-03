package p000;

import android.content.Context;
import android.os.Build;
import android.os.UserManager;

/* renamed from: exp */
/* compiled from: PG */
public final class exp {

    /* renamed from: a */
    private static UserManager f9187a;

    /* renamed from: b */
    private static volatile boolean f9188b = false;

    /* renamed from: c */
    private static boolean f9189c = false;

    static {
        int i = Build.VERSION.SDK_INT;
    }

    private exp() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:0x007f, code lost:
        return r3;
     */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x007a A[Catch:{ NullPointerException -> 0x0061 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x007b A[Catch:{ NullPointerException -> 0x0061 }] */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean m8339c(android.content.Context r8) {
        /*
            boolean r0 = f9188b
            r1 = 1
            if (r0 != 0) goto L_0x0085
            java.lang.Class<exp> r0 = p000.exp.class
            monitor-enter(r0)
            boolean r2 = f9188b     // Catch:{ all -> 0x0082 }
            if (r2 != 0) goto L_0x0080
            boolean r2 = f9189c     // Catch:{ all -> 0x0082 }
            r3 = 0
            if (r2 == 0) goto L_0x0035
            android.content.Intent r2 = new android.content.Intent     // Catch:{ all -> 0x0082 }
            r2.<init>()     // Catch:{ all -> 0x0082 }
            java.lang.Class<com.google.android.libraries.directboot.DirectBootHelperService> r4 = com.google.android.libraries.directboot.DirectBootHelperService.class
            java.lang.String r4 = r4.getName()     // Catch:{ all -> 0x0082 }
            android.content.Intent r2 = r2.setClassName(r8, r4)     // Catch:{ all -> 0x0082 }
            android.content.pm.PackageManager r8 = r8.getPackageManager()     // Catch:{ all -> 0x0082 }
            r4 = 512(0x200, float:7.175E-43)
            java.util.List r8 = r8.queryIntentServices(r2, r4)     // Catch:{ all -> 0x0082 }
            if (r8 == 0) goto L_0x0034
            boolean r8 = r8.isEmpty()     // Catch:{ all -> 0x0082 }
            if (r8 != 0) goto L_0x0033
            goto L_0x006f
        L_0x0033:
        L_0x0034:
            goto L_0x0078
        L_0x0035:
            r2 = 1
        L_0x0037:
            r4 = 2
            r5 = 0
            if (r2 > r4) goto L_0x0071
            android.os.UserManager r4 = f9187a     // Catch:{ all -> 0x0082 }
            if (r4 != 0) goto L_0x0049
            java.lang.Class<android.os.UserManager> r4 = android.os.UserManager.class
            java.lang.Object r4 = r8.getSystemService(r4)     // Catch:{ all -> 0x0082 }
            android.os.UserManager r4 = (android.os.UserManager) r4     // Catch:{ all -> 0x0082 }
            f9187a = r4     // Catch:{ all -> 0x0082 }
        L_0x0049:
            android.os.UserManager r4 = f9187a     // Catch:{ all -> 0x0082 }
            if (r4 == 0) goto L_0x006e
            boolean r6 = r4.isUserUnlocked()     // Catch:{ NullPointerException -> 0x0061 }
            if (r6 != 0) goto L_0x005f
            android.os.UserHandle r6 = android.os.Process.myUserHandle()     // Catch:{ NullPointerException -> 0x0061 }
            boolean r8 = r4.isUserRunning(r6)     // Catch:{ NullPointerException -> 0x0061 }
            if (r8 == 0) goto L_0x005e
            goto L_0x0072
        L_0x005e:
        L_0x005f:
            r3 = 1
            goto L_0x0073
        L_0x0061:
            r4 = move-exception
            java.lang.String r6 = "DirectBootUtils"
            java.lang.String r7 = "Failed to check if user is unlocked."
            android.util.Log.w(r6, r7, r4)     // Catch:{ all -> 0x0082 }
            f9187a = r5     // Catch:{ all -> 0x0082 }
            int r2 = r2 + 1
            goto L_0x0037
        L_0x006e:
        L_0x006f:
            r3 = 1
            goto L_0x0078
        L_0x0071:
        L_0x0072:
        L_0x0073:
            if (r3 == 0) goto L_0x0078
            f9187a = r5     // Catch:{ all -> 0x0082 }
            goto L_0x0034
        L_0x0078:
            if (r3 != 0) goto L_0x007b
            goto L_0x007e
        L_0x007b:
            f9188b = r1     // Catch:{ all -> 0x0082 }
        L_0x007e:
            monitor-exit(r0)     // Catch:{ all -> 0x0082 }
            return r3
        L_0x0080:
            monitor-exit(r0)     // Catch:{ all -> 0x0082 }
            return r1
        L_0x0082:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0082 }
            throw r8
        L_0x0085:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.exp.m8339c(android.content.Context):boolean");
    }

    /* renamed from: a */
    public static boolean m8336a(Context context) {
        int i = Build.VERSION.SDK_INT;
        return !m8339c(context);
    }

    /* renamed from: b */
    public static boolean m8338b(Context context) {
        int i = Build.VERSION.SDK_INT;
        return m8339c(context);
    }

    /* renamed from: b */
    public static void m8337b() {
        int i = Build.VERSION.SDK_INT;
    }

    /* renamed from: a */
    public static synchronized void m8335a() {
        synchronized (exp.class) {
            f9189c = true;
        }
    }
}
