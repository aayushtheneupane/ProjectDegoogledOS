package p000;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: hqo */
/* compiled from: PG */
public final class hqo {

    /* renamed from: a */
    private static final Object f13268a;

    static {
        Object a = m11923a();
        f13268a = a;
        if (a != null) {
            m11924a("getStackTraceElement", Throwable.class, Integer.TYPE);
        }
        if (f13268a != null) {
            m11928b();
        }
    }

    /* renamed from: a */
    private static Object m11923a() {
        try {
            return Class.forName("sun.misc.SharedSecrets", false, (ClassLoader) null).getMethod("getJavaLangAccess", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            return null;
        }
    }

    /* renamed from: a */
    private static Method m11924a(String str, Class... clsArr) {
        try {
            return Class.forName("sun.misc.JavaLangAccess", false, (ClassLoader) null).getMethod(str, clsArr);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            return null;
        }
    }

    /* renamed from: b */
    private static void m11928b() {
        try {
            Method a = m11924a("getStackTraceDepth", Throwable.class);
            if (a != null) {
                a.invoke(m11923a(), new Object[]{new Throwable()});
            }
        } catch (IllegalAccessException | UnsupportedOperationException | InvocationTargetException e) {
        }
    }

    @Deprecated
    /* renamed from: b */
    public static RuntimeException m11927b(Throwable th) {
        m11925a(th);
        throw new RuntimeException(th);
    }

    /* renamed from: a */
    public static void m11926a(Throwable th, Class cls) {
        if (th != null) {
            ife.m12898e((Object) th);
            if (cls.isInstance(th)) {
                throw ((Throwable) cls.cast(th));
            }
        }
        if (th != null) {
            m11925a(th);
        }
    }

    /* renamed from: a */
    public static void m11925a(Throwable th) {
        ife.m12898e((Object) th);
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        } else if (th instanceof Error) {
            throw ((Error) th);
        }
    }
}
