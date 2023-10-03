package com.google.android.libraries.performance.primes.metriccapture;

import android.content.Context;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.Build;
import android.os.Process;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
public final class PackageStatsCapture {
    public static final PackageStatsInvocation[] GETTER_INVOCATIONS = {new PackageStatsInvocation("getPackageSizeInfo", new Class[]{String.class, IPackageStatsObserver.class}), new PackageStatsInvocation("getPackageSizeInfo", new Class[]{String.class, Integer.TYPE, IPackageStatsObserver.class}), new PackageStatsInvocation("getPackageSizeInfoAsUser", new Class[]{String.class, Integer.TYPE, IPackageStatsObserver.class})};

    /* compiled from: PG */
    final class PackageStatsCallback extends IPackageStatsObserver.Stub {

        /* renamed from: a */
        public final Semaphore f5115a = new Semaphore(1);

        /* renamed from: b */
        public volatile PackageStats f5116b;

        private PackageStatsCallback() {
        }

        public /* synthetic */ PackageStatsCallback(byte[] bArr) {
        }

        public void onGetStatsCompleted(PackageStats packageStats, boolean z) {
            if (z) {
                String valueOf = String.valueOf(packageStats);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 30);
                sb.append("Success getting PackageStats: ");
                sb.append(valueOf);
                flw.m9199b("PackageStatsCapture", sb.toString(), new Object[0]);
                this.f5116b = packageStats;
            } else {
                flw.m9202d("PackageStatsCapture", "Failure getting PackageStats", new Object[0]);
            }
            this.f5115a.release();
        }
    }

    private PackageStatsCapture() {
    }

    /* compiled from: PG */
    final class PackageStatsInvocation {

        /* renamed from: a */
        private final String f5117a;

        /* renamed from: b */
        private final Class[] f5118b;

        public PackageStatsInvocation(String str, Class[] clsArr) {
            this.f5117a = str;
            this.f5118b = clsArr;
        }

        public boolean invoke(PackageManager packageManager, String str, int i, IPackageStatsObserver iPackageStatsObserver) {
            Object[] objArr;
            try {
                Method method = packageManager.getClass().getMethod(this.f5117a, this.f5118b);
                Class<IPackageStatsObserver>[] clsArr = this.f5118b;
                int length = clsArr.length;
                if (length == 2) {
                    if (clsArr[0] == String.class && clsArr[1] == IPackageStatsObserver.class) {
                        objArr = new Object[]{str, iPackageStatsObserver};
                    }
                    throw new IllegalArgumentException("Invalid parameter for PackageStatsInvocation.");
                }
                if (length == 3 && clsArr[0] == String.class && clsArr[1] == Integer.TYPE && this.f5118b[2] == IPackageStatsObserver.class) {
                    objArr = new Object[]{str, Integer.valueOf(i), iPackageStatsObserver};
                }
                throw new IllegalArgumentException("Invalid parameter for PackageStatsInvocation.");
                method.invoke(packageManager, objArr);
                return true;
            } catch (NoSuchMethodException e) {
                flw.m9192a("PackageStatsCapture", "PackageStats getter not found", (Throwable) e, new Object[0]);
                return false;
            } catch (Error | Exception e2) {
                flw.m9201c("PackageStatsCapture", e2.getClass().getSimpleName() + " for " + this.f5117a + '(' + Arrays.asList(this.f5118b) + ") invocation", new Object[0]);
                return false;
            }
        }
    }

    public static PackageStats getPackageStats(Context context) {
        fxk.m9836c();
        int i = Build.VERSION.SDK_INT;
        return fol.m9321a(context);
    }

    static PackageStats getPackageStatsUsingInternalAPI(Context context, long j, PackageStatsInvocation... packageStatsInvocationArr) {
        if (!m4977a()) {
            flw.m9202d("PackageStatsCapture", "Callback implementation stripped by proguard.", new Object[0]);
            return null;
        }
        PackageStatsCallback packageStatsCallback = new PackageStatsCallback((byte[]) null);
        try {
            packageStatsCallback.f5115a.acquire();
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getPackageName();
            int myUid = Process.myUid();
            for (PackageStatsInvocation invoke : packageStatsInvocationArr) {
                if (invoke.invoke(packageManager, packageName, myUid, packageStatsCallback)) {
                    flw.m9201c("PackageStatsCapture", "Success invoking PackageStats capture.", new Object[0]);
                    if (packageStatsCallback.f5115a.tryAcquire(j, TimeUnit.MILLISECONDS)) {
                        return packageStatsCallback.f5116b;
                    }
                    flw.m9202d("PackageStatsCapture", "Timeout while waiting for PackageStats callback", new Object[0]);
                    return null;
                }
            }
            flw.m9202d("PackageStatsCapture", "Couldn't capture PackageStats.", new Object[0]);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }

    /* renamed from: a */
    private static boolean m4977a() {
        try {
            return !Modifier.isAbstract(PackageStatsCallback.class.getMethod("onGetStatsCompleted", new Class[]{PackageStats.class, Boolean.TYPE}).getModifiers());
        } catch (Error | Exception e) {
            flw.m9192a("PackageStatsCapture", "failure", e, new Object[0]);
            return false;
        }
    }
}
