package p000;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/* renamed from: et */
/* compiled from: PG */
public final class C0132et {

    /* renamed from: a */
    public static final Method f8967a;

    /* renamed from: b */
    public static final Method f8968b;

    /* renamed from: c */
    private static final Class f8969c;

    /* renamed from: d */
    private static final Field f8970d;

    /* renamed from: e */
    private static final Field f8971e;

    /* renamed from: f */
    private static final Method f8972f;

    /* renamed from: g */
    private static final Handler f8973g = new Handler(Looper.getMainLooper());

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
            cls = null;
        }
        f8969c = cls;
        try {
            field = Activity.class.getDeclaredField("mMainThread");
            field.setAccessible(true);
        } catch (Throwable th2) {
            field = null;
        }
        f8970d = field;
        try {
            field2 = Activity.class.getDeclaredField("mToken");
            field2.setAccessible(true);
        } catch (Throwable th3) {
            field2 = null;
        }
        f8971e = field2;
        Class cls2 = f8969c;
        if (cls2 != null) {
            try {
                method = cls2.getDeclaredMethod("performStopActivity", new Class[]{IBinder.class, Boolean.TYPE, String.class});
                method.setAccessible(true);
            } catch (Throwable th4) {
                method = null;
            }
        } else {
            method = null;
        }
        f8967a = method;
        Class cls3 = f8969c;
        if (cls3 != null) {
            try {
                method2 = cls3.getDeclaredMethod("performStopActivity", new Class[]{IBinder.class, Boolean.TYPE});
                method2.setAccessible(true);
            } catch (Throwable th5) {
                method2 = null;
            }
        } else {
            method2 = null;
        }
        f8968b = method2;
        Class cls4 = f8969c;
        if (m8135a() && cls4 != null) {
            try {
                Method declaredMethod = cls4.getDeclaredMethod("requestRelaunchActivity", new Class[]{IBinder.class, List.class, List.class, Integer.TYPE, Boolean.TYPE, Configuration.class, Configuration.class, Boolean.TYPE, Boolean.TYPE});
                declaredMethod.setAccessible(true);
                method3 = declaredMethod;
            } catch (Throwable th6) {
            }
        }
        f8972f = method3;
    }

    /* renamed from: a */
    private static boolean m8135a() {
        return Build.VERSION.SDK_INT == 26 || Build.VERSION.SDK_INT == 27;
    }

    /* renamed from: a */
    protected static boolean m8137a(Object obj, Activity activity) {
        try {
            Object obj2 = f8971e.get(activity);
            if (obj2 != obj) {
                return false;
            }
            f8973g.postAtFrontOfQueue(new C0130er(f8970d.get(activity), obj2));
            return true;
        } catch (Throwable th) {
            Log.e("ActivityRecreator", "Exception while fetching field values", th);
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m8136a(Activity activity) {
        Object obj;
        Application application;
        C0131es esVar;
        if (Build.VERSION.SDK_INT >= 28) {
            activity.recreate();
            return true;
        } else if (m8135a() && f8972f == null) {
            return false;
        } else {
            if (f8968b == null && f8967a == null) {
                return false;
            }
            try {
                Object obj2 = f8971e.get(activity);
                if (obj2 == null || (obj = f8970d.get(activity)) == null) {
                    return false;
                }
                application = activity.getApplication();
                esVar = new C0131es(activity);
                application.registerActivityLifecycleCallbacks(esVar);
                f8973g.post(new C0128ep(esVar, obj2));
                if (m8135a()) {
                    f8972f.invoke(obj, new Object[]{obj2, null, null, 0, false, null, null, false, false});
                } else {
                    activity.recreate();
                }
                f8973g.post(new C0129eq(application, esVar));
                return true;
            } catch (Throwable th) {
                return false;
            }
        }
    }
}
