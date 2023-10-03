package p000;

import android.util.Log;
import java.lang.reflect.Method;

/* renamed from: fxj */
/* compiled from: PG */
public final class fxj {

    /* renamed from: a */
    private static final Method f10665a;

    static {
        Method method = null;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            method = cls.getMethod("get", new Class[]{String.class, String.class});
            try {
                cls.getMethod("getInt", new Class[]{String.class, Integer.TYPE});
                cls.getMethod("getLong", new Class[]{String.class, Long.TYPE});
            } catch (Exception e) {
                e = e;
                try {
                    ifn.m12932a(e);
                    f10665a = method;
                } catch (Throwable th) {
                    th = th;
                }
            }
        } catch (Exception e2) {
            e = e2;
            ifn.m12932a(e);
            f10665a = method;
        } catch (Throwable th2) {
            th = th2;
            f10665a = method;
            throw th;
        }
        f10665a = method;
    }

    /* renamed from: a */
    public static String m9816a(String str, String str2) {
        try {
            return (String) f10665a.invoke((Object) null, new Object[]{str, str2});
        } catch (Exception e) {
            Log.e("SystemProperties", "get error", e);
            return str2;
        }
    }
}
