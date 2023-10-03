package com.google.common.base;

import java.lang.reflect.Method;

public final class Throwables {
    static final String SHARED_SECRETS_CLASSNAME = "sun.misc.SharedSecrets";
    private static final Object jla;

    static {
        Object obj = null;
        try {
            obj = Class.forName(SHARED_SECRETS_CLASSNAME, false, (ClassLoader) null).getMethod("getJavaLangAccess", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable unused) {
        }
        jla = obj;
        if (jla != null) {
            getJlaMethod("getStackTraceElement", Throwable.class, Integer.TYPE);
        }
        if (jla != null) {
            getJlaMethod("getStackTraceDepth", Throwable.class);
        }
    }

    private static Method getJlaMethod(String str, Class<?>... clsArr) throws ThreadDeath {
        try {
            return Class.forName("sun.misc.JavaLangAccess", false, (ClassLoader) null).getMethod(str, clsArr);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable unused) {
            return null;
        }
    }

    @Deprecated
    public static RuntimeException propagate(Throwable th) {
        throwIfUnchecked(th);
        throw new RuntimeException(th);
    }

    @Deprecated
    public static void propagateIfPossible(Throwable th) {
        if (th != null) {
            throwIfUnchecked(th);
        }
    }

    public static void throwIfUnchecked(Throwable th) {
        if (th == null) {
            throw new NullPointerException();
        } else if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        } else if (th instanceof Error) {
            throw ((Error) th);
        }
    }
}
