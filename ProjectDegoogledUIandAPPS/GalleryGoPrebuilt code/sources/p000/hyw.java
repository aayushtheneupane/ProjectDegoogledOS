package p000;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: hyw */
/* compiled from: PG */
final class hyw {

    /* renamed from: a */
    public final Object f13664a;

    /* renamed from: b */
    public final Method f13665b;

    /* renamed from: c */
    private final Method f13666c;

    private hyw(Object obj, Method method, Method method2) {
        this.f13664a = obj;
        this.f13666c = method;
        this.f13665b = method2;
    }

    /* renamed from: a */
    public static hyw m12485a() {
        try {
            Object invoke = Class.forName("sun.misc.SharedSecrets").getMethod("getJavaLangAccess", new Class[0]).invoke((Object) null, new Object[0]);
            Method method = Class.forName("sun.misc.JavaLangAccess").getMethod("getStackTraceElement", new Class[]{Throwable.class, Integer.TYPE});
            Method method2 = Class.forName("sun.misc.JavaLangAccess").getMethod("getStackTraceDepth", new Class[]{Throwable.class});
            StackTraceElement stackTraceElement = (StackTraceElement) method.invoke(invoke, new Object[]{new Throwable(), 0});
            ((Integer) method2.invoke(invoke, new Object[]{new Throwable()})).intValue();
            return new hyw(invoke, method, method2);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            return null;
        }
    }

    /* renamed from: a */
    public final StackTraceElement mo8278a(Throwable th, int i) {
        try {
            return (StackTraceElement) this.f13666c.invoke(this.f13664a, new Object[]{th, Integer.valueOf(i)});
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e.getCause());
            } else if (e.getCause() instanceof Error) {
                throw ((Error) e.getCause());
            } else {
                throw new RuntimeException(e.getCause());
            }
        } catch (IllegalAccessException e2) {
            throw new AssertionError(e2);
        }
    }
}
