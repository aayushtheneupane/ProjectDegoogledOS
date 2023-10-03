package androidx.fragment.app;

import java.lang.reflect.InvocationTargetException;
import p000a.p005b.C0027n;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: androidx.fragment.app.n */
public class C0428n {

    /* renamed from: Oo */
    private static final C0027n f416Oo = new C0027n();

    /* renamed from: b */
    static boolean m423b(ClassLoader classLoader, String str) {
        try {
            return C0424j.class.isAssignableFrom(m425d(classLoader, str));
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    /* renamed from: c */
    public static Class m424c(ClassLoader classLoader, String str) {
        try {
            return m425d(classLoader, str);
        } catch (ClassNotFoundException e) {
            throw new Fragment$InstantiationException(C0632a.m1023d("Unable to instantiate fragment ", str, ": make sure class name exists"), e);
        } catch (ClassCastException e2) {
            throw new Fragment$InstantiationException(C0632a.m1023d("Unable to instantiate fragment ", str, ": make sure class is a valid subclass of Fragment"), e2);
        }
    }

    /* renamed from: d */
    private static Class m425d(ClassLoader classLoader, String str) {
        Class cls = (Class) f416Oo.get(str);
        if (cls != null) {
            return cls;
        }
        Class<?> cls2 = Class.forName(str, false, classLoader);
        f416Oo.put(str, cls2);
        return cls2;
    }

    /* renamed from: a */
    public C0424j mo4424a(ClassLoader classLoader, String str) {
        try {
            return (C0424j) m424c(classLoader, str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (InstantiationException e) {
            throw new Fragment$InstantiationException(C0632a.m1023d("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e);
        } catch (IllegalAccessException e2) {
            throw new Fragment$InstantiationException(C0632a.m1023d("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e2);
        } catch (NoSuchMethodException e3) {
            throw new Fragment$InstantiationException(C0632a.m1023d("Unable to instantiate fragment ", str, ": could not find Fragment constructor"), e3);
        } catch (InvocationTargetException e4) {
            throw new Fragment$InstantiationException(C0632a.m1023d("Unable to instantiate fragment ", str, ": calling Fragment constructor caused an exception"), e4);
        }
    }
}
