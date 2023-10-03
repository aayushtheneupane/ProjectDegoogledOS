package p000;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.view.WindowManager;
import java.util.Set;

/* renamed from: foj */
/* compiled from: PG */
public final class foj {

    /* renamed from: a */
    public static volatile int f10148a;

    /* renamed from: b */
    private static volatile int f10149b;

    private foj() {
    }

    /* renamed from: a */
    public static int m9310a(Application application) {
        if (f10149b == 0) {
            synchronized (foj.class) {
                if (f10149b == 0) {
                    f10149b = Math.round(((WindowManager) application.getApplicationContext().getSystemService("window")).getDefaultDisplay().getRefreshRate());
                }
            }
        }
        return f10149b;
    }

    /* renamed from: a */
    public static irn m9311a(String str, Context context) {
        iir g = irn.f14886f.mo8793g();
        long elapsedCpuTime = Process.getElapsedCpuTime();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        irn irn = (irn) g.f14318b;
        irn.f14888a |= 1;
        irn.f14889b = elapsedCpuTime;
        boolean b = fom.m9323b(context);
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        irn irn2 = (irn) g.f14318b;
        irn2.f14888a |= 2;
        irn2.f14890c = b;
        int activeCount = Thread.activeCount();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        irn irn3 = (irn) g.f14318b;
        int i = irn3.f14888a | 4;
        irn3.f14888a = i;
        irn3.f14891d = activeCount;
        if (str != null) {
            str.getClass();
            irn3.f14888a = i | 8;
            irn3.f14892e = str;
        }
        return (irn) g.mo8770g();
    }

    /* renamed from: a */
    private static void m9313a(Throwable th, StringBuilder sb, Set set, String str) {
        if (th != null && !set.contains(th)) {
            set.add(th);
            if (str != null) {
                sb.append(str);
            }
            sb.append(th.getClass().getName());
            sb.append(':');
            for (StackTraceElement append : th.getStackTrace()) {
                sb.append("\n\tat ");
                sb.append(append);
            }
            int i = Build.VERSION.SDK_INT;
            for (Throwable a : ifn.f14001a.mo8487a(th)) {
                m9313a(a, sb, set, "\nSuppressed: ");
            }
            if (th.getCause() != null) {
                m9313a(th.getCause(), sb, set, "\nCaused by: ");
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x005c  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m9312a(android.content.Context r11, java.lang.Throwable r12, java.lang.String r13) {
        /*
            java.lang.String r0 = "SilentFeedback"
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.lang.StackTraceElement[] r2 = r12.getStackTrace()
            java.lang.StackTraceElement[] r3 = r12.getStackTrace()
            int r3 = r3.length
            r4 = 0
            if (r3 == 0) goto L_0x010c
            r3 = 0
            android.content.pm.PackageManager r5 = r11.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0052 }
            android.content.Context r6 = r11.getApplicationContext()     // Catch:{ NameNotFoundException -> 0x0052 }
            java.lang.String r6 = r6.getPackageName()     // Catch:{ NameNotFoundException -> 0x0052 }
            r7 = 2
            android.content.pm.PackageInfo r5 = r5.getPackageInfo(r6, r7)     // Catch:{ NameNotFoundException -> 0x0052 }
            if (r5 == 0) goto L_0x004e
            android.content.pm.ActivityInfo[] r6 = r5.receivers
            if (r6 != 0) goto L_0x002c
            goto L_0x004f
        L_0x002c:
            android.content.pm.ActivityInfo[] r5 = r5.receivers
            int r6 = r5.length
            r7 = 0
        L_0x0030:
            if (r7 >= r6) goto L_0x0044
            r8 = r5[r7]
            java.lang.String r9 = r8.name
            java.lang.String r10 = "com\\.google\\.android\\.libraries\\.social\\.silentfeedback\\.\\w*\\.\\w*SilentFeedbackReceiver"
            boolean r9 = r9.matches(r10)
            if (r9 != 0) goto L_0x0041
            int r7 = r7 + 1
            goto L_0x0030
        L_0x0041:
            java.lang.String r5 = r8.name
            goto L_0x0046
        L_0x0044:
            r5 = r4
        L_0x0046:
            if (r5 != 0) goto L_0x005a
            java.lang.String r5 = "Could not find SilentFeedbackReceiver, not sending crash info."
            android.util.Log.e(r0, r5)
            goto L_0x0050
        L_0x004e:
        L_0x004f:
        L_0x0050:
            r5 = r4
            goto L_0x005a
        L_0x0052:
            r5 = move-exception
            java.lang.String r6 = "Could not find our own package. This should never happen. Not sending crash info."
            android.util.Log.e(r0, r6, r5)
            r5 = r4
        L_0x005a:
            if (r5 == 0) goto L_0x010b
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            android.content.ComponentName r6 = new android.content.ComponentName
            android.content.Context r7 = r11.getApplicationContext()
            r6.<init>(r7, r5)
            r0.setComponent(r6)
            android.content.Context r5 = r11.getApplicationContext()
            java.lang.String r5 = r5.getPackageName()
            r0.setPackage(r5)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.util.HashSet r6 = new java.util.HashSet
            r6.<init>()
            m9313a(r12, r5, r6, r4)
            r4 = r12
        L_0x0086:
            java.lang.Throwable r6 = r4.getCause()
            if (r6 == 0) goto L_0x0091
            java.lang.Throwable r4 = r4.getCause()
            goto L_0x0086
        L_0x0091:
            java.util.Iterator r1 = r1.iterator()
        L_0x0095:
            boolean r6 = r1.hasNext()
            java.lang.String r7 = ""
            if (r6 == 0) goto L_0x00b4
            java.lang.Object r6 = r1.next()
            ftf r6 = (p000.ftf) r6
            boolean r6 = r6.mo6177a()
            if (r6 == 0) goto L_0x0095
            java.lang.String r1 = r4.getMessage()
            if (r1 == 0) goto L_0x00b4
            java.lang.String r7 = r4.getMessage()
            goto L_0x00b5
        L_0x00b4:
        L_0x00b5:
            r1 = r2[r3]
            java.lang.String r2 = r1.getFileName()
            if (r2 == 0) goto L_0x00c2
            java.lang.String r2 = r1.getFileName()
            goto L_0x00c5
        L_0x00c2:
            java.lang.String r2 = "Unknown Source"
        L_0x00c5:
            java.lang.Class r12 = r12.getClass()
            java.lang.String r12 = r12.getName()
            java.lang.String r3 = "com.google.android.libraries.social.silentfeedback.SilentFeedbackReceiver.exceptionClass"
            r0.putExtra(r3, r12)
            java.lang.String r12 = "com.google.android.libraries.social.silentfeedback.SilentFeedbackReceiver.exceptionMessage"
            r0.putExtra(r12, r7)
            java.lang.String r12 = r5.toString()
            java.lang.String r3 = "com.google.android.libraries.social.silentfeedback.SilentFeedbackReceiver.stackTrace"
            r0.putExtra(r3, r12)
            java.lang.String r12 = r1.getClassName()
            java.lang.String r3 = "com.google.android.libraries.social.silentfeedback.SilentFeedbackReceiver.throwingClass"
            r0.putExtra(r3, r12)
            java.lang.String r12 = "com.google.android.libraries.social.silentfeedback.SilentFeedbackReceiver.throwingFile"
            r0.putExtra(r12, r2)
            int r12 = r1.getLineNumber()
            java.lang.String r2 = "com.google.android.libraries.social.silentfeedback.SilentFeedbackReceiver.throwingLine"
            r0.putExtra(r2, r12)
            java.lang.String r12 = r1.getMethodName()
            java.lang.String r1 = "com.google.android.libraries.social.silentfeedback.SilentFeedbackReceiver.throwingMethod"
            r0.putExtra(r1, r12)
            if (r13 == 0) goto L_0x0108
            java.lang.String r12 = "com.google.android.libraries.social.silentfeedback.SilentFeedbackReceiver.categoryTag"
            r0.putExtra(r12, r13)
            goto L_0x0109
        L_0x0108:
        L_0x0109:
            r4 = r0
            goto L_0x010e
        L_0x010b:
            goto L_0x010d
        L_0x010c:
        L_0x010d:
        L_0x010e:
            if (r4 == 0) goto L_0x0113
            r11.sendBroadcast(r4)
        L_0x0113:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.foj.m9312a(android.content.Context, java.lang.Throwable, java.lang.String):void");
    }

    /* renamed from: b */
    public static boolean m9315b(fuk fuk) {
        return "true".equals(fxj.m9816a("debug.social", "true")) && "true".equals(fxj.m9816a(fuk.f10612a, "true"));
    }

    /* renamed from: a */
    public static boolean m9314a(fuk fuk) {
        return "true".equals(fxj.m9816a(fuk.f10612a, "false"));
    }
}
