package p000;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.os.Build;
import android.os.IBinder;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/* renamed from: gxo */
/* compiled from: PG */
final class gxo {

    /* renamed from: a */
    public static final Field f12261a;

    /* renamed from: b */
    public static final Field f12262b;

    /* renamed from: c */
    public static final Method f12263c;

    /* renamed from: d */
    public static final Method f12264d;

    /* renamed from: e */
    private static final Class f12265e;

    /* renamed from: f */
    private static final Method f12266f;

    static {
        Class<?> cls;
        Field field;
        Field field2;
        Method method;
        Method method2;
        Method method3 = null;
        try {
            cls = Class.forName("android.app.ActivityThread");
        } catch (Throwable th) {
            ifn.m12932a(th);
            cls = null;
        }
        f12265e = cls;
        try {
            field = Activity.class.getDeclaredField("mMainThread");
            field.setAccessible(true);
        } catch (Throwable th2) {
            ifn.m12932a(th2);
            field = null;
        }
        f12261a = field;
        try {
            field2 = Activity.class.getDeclaredField("mToken");
            field2.setAccessible(true);
        } catch (Throwable th3) {
            ifn.m12932a(th3);
            field2 = null;
        }
        f12262b = field2;
        Class cls2 = f12265e;
        if (cls2 != null) {
            try {
                method = cls2.getDeclaredMethod("performStopActivity", new Class[]{IBinder.class, Boolean.TYPE, String.class});
                method.setAccessible(true);
            } catch (Throwable th4) {
                ifn.m12932a(th4);
                method = null;
            }
        } else {
            method = null;
        }
        f12263c = method;
        Class cls3 = f12265e;
        if (cls3 != null) {
            try {
                method2 = cls3.getDeclaredMethod("performStopActivity", new Class[]{IBinder.class, Boolean.TYPE});
                method2.setAccessible(true);
            } catch (Throwable th5) {
                ifn.m12932a(th5);
                method2 = null;
            }
        } else {
            method2 = null;
        }
        f12264d = method2;
        Class cls4 = f12265e;
        if (m11007a() && cls4 != null) {
            try {
                Method declaredMethod = cls4.getDeclaredMethod("requestRelaunchActivity", new Class[]{IBinder.class, List.class, List.class, Integer.TYPE, Boolean.TYPE, Configuration.class, Configuration.class, Boolean.TYPE, Boolean.TYPE});
                declaredMethod.setAccessible(true);
                method3 = declaredMethod;
            } catch (Throwable th6) {
                ifn.m12932a(th6);
            }
        }
        f12266f = method3;
    }

    /* renamed from: a */
    private static boolean m11007a() {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        if (Build.VERSION.SDK_INT == 26 || Build.VERSION.SDK_INT == 27) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    static void m11006a(Activity activity) {
        Object obj;
        Application application;
        gxn gxn;
        fxk.m9830b();
        if (Build.VERSION.SDK_INT >= 28 || (Build.VERSION.SDK_INT == 27 && Build.VERSION.PREVIEW_SDK_INT != 0)) {
            activity.recreate();
        } else if (m11007a() && f12266f == null) {
        } else {
            if (f12264d != null || f12263c != null) {
                try {
                    Object obj2 = f12262b.get(activity);
                    if (obj2 != null && (obj = f12261a.get(activity)) != null) {
                        application = activity.getApplication();
                        gxn = new gxn(activity);
                        application.registerActivityLifecycleCallbacks(gxn);
                        fxk.m9824a((Runnable) new gxj(gxn, obj2));
                        if (m11007a()) {
                            f12266f.invoke(obj, new Object[]{obj2, null, null, 0, false, null, null, false, false});
                        } else {
                            activity.recreate();
                        }
                        fxk.m9824a((Runnable) new gxk(application, gxn));
                    }
                } catch (Throwable th) {
                    ifn.m12932a(th);
                }
            }
        }
    }
}
