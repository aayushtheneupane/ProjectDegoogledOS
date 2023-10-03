package p000;

import java.lang.reflect.InvocationTargetException;

/* renamed from: ft */
/* compiled from: PG */
public class C0159ft {

    /* renamed from: a */
    private static final C0309lf f10560a = new C0309lf();

    /* renamed from: c */
    public C0147fh mo6175c(ClassLoader classLoader, String str) {
        try {
            return (C0147fh) m9580b(classLoader, str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (InstantiationException e) {
            throw new C0146fg("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e);
        } catch (IllegalAccessException e2) {
            throw new C0146fg("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e2);
        } catch (NoSuchMethodException e3) {
            throw new C0146fg("Unable to instantiate fragment " + str + ": could not find Fragment constructor", e3);
        } catch (InvocationTargetException e4) {
            throw new C0146fg("Unable to instantiate fragment " + str + ": calling Fragment constructor caused an exception", e4);
        }
    }

    /* renamed from: a */
    static boolean m9579a(ClassLoader classLoader, String str) {
        try {
            return C0147fh.class.isAssignableFrom(m9581d(classLoader, str));
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /* renamed from: d */
    private static Class m9581d(ClassLoader classLoader, String str) {
        Class cls = (Class) f10560a.get(str);
        if (cls != null) {
            return cls;
        }
        Class<?> cls2 = Class.forName(str, false, classLoader);
        f10560a.put(str, cls2);
        return cls2;
    }

    /* renamed from: b */
    public static Class m9580b(ClassLoader classLoader, String str) {
        try {
            return m9581d(classLoader, str);
        } catch (ClassNotFoundException e) {
            throw new C0146fg("Unable to instantiate fragment " + str + ": make sure class name exists", e);
        } catch (ClassCastException e2) {
            throw new C0146fg("Unable to instantiate fragment " + str + ": make sure class is a valid subclass of Fragment", e2);
        }
    }
}
