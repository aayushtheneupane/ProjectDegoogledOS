package androidx.lifecycle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: androidx.lifecycle.n */
public class C0457n {

    /* renamed from: Rp */
    private static Map f445Rp = new HashMap();

    /* renamed from: Sp */
    private static Map f446Sp = new HashMap();

    /* renamed from: a */
    private static C0448e m469a(Constructor constructor, Object obj) {
        try {
            return (C0448e) constructor.newInstance(new Object[]{obj});
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    /* renamed from: e */
    private static int m470e(Class cls) {
        Constructor<?> constructor;
        Integer num = (Integer) f445Rp.get(cls);
        if (num != null) {
            return num.intValue();
        }
        int i = 1;
        if (cls.getCanonicalName() != null) {
            int i2 = 0;
            ArrayList arrayList = null;
            try {
                Package packageR = cls.getPackage();
                String canonicalName = cls.getCanonicalName();
                String name = packageR != null ? packageR.getName() : "";
                if (!name.isEmpty()) {
                    canonicalName = canonicalName.substring(name.length() + 1);
                }
                String y = m473y(canonicalName);
                if (!name.isEmpty()) {
                    y = name + "." + y;
                }
                constructor = Class.forName(y).getDeclaredConstructor(new Class[]{cls});
                if (!constructor.isAccessible()) {
                    constructor.setAccessible(true);
                }
            } catch (ClassNotFoundException unused) {
                constructor = null;
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            if (constructor != null) {
                f446Sp.put(cls, Collections.singletonList(constructor));
            } else if (!C0446c.sInstance.mo4452c(cls)) {
                Class superclass = cls.getSuperclass();
                if (m471f(superclass)) {
                    if (m470e(superclass) != 1) {
                        arrayList = new ArrayList((Collection) f446Sp.get(superclass));
                    }
                }
                Class[] interfaces = cls.getInterfaces();
                int length = interfaces.length;
                while (true) {
                    if (i2 < length) {
                        Class cls2 = interfaces[i2];
                        if (m471f(cls2)) {
                            if (m470e(cls2) == 1) {
                                break;
                            }
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                            }
                            arrayList.addAll((Collection) f446Sp.get(cls2));
                        }
                        i2++;
                    } else if (arrayList != null) {
                        f446Sp.put(cls, arrayList);
                    }
                }
            }
            i = 2;
        }
        f445Rp.put(cls, Integer.valueOf(i));
        return i;
    }

    /* renamed from: f */
    private static boolean m471f(Class cls) {
        return cls != null && C0452i.class.isAssignableFrom(cls);
    }

    /* renamed from: k */
    static C0451h m472k(Object obj) {
        boolean z = obj instanceof C0451h;
        boolean z2 = obj instanceof C0447d;
        if (z && z2) {
            return new FullLifecycleObserverAdapter((C0447d) obj, (C0451h) obj);
        }
        if (z2) {
            return new FullLifecycleObserverAdapter((C0447d) obj, (C0451h) null);
        }
        if (z) {
            return (C0451h) obj;
        }
        Class<?> cls = obj.getClass();
        if (m470e(cls) != 2) {
            return new ReflectiveGenericLifecycleObserver(obj);
        }
        List list = (List) f446Sp.get(cls);
        if (list.size() == 1) {
            return new SingleGeneratedAdapterObserver(m469a((Constructor) list.get(0), obj));
        }
        C0448e[] eVarArr = new C0448e[list.size()];
        for (int i = 0; i < list.size(); i++) {
            eVarArr[i] = m469a((Constructor) list.get(i), obj);
        }
        return new CompositeGeneratedAdaptersObserver(eVarArr);
    }

    /* renamed from: y */
    public static String m473y(String str) {
        return str.replace(".", "_") + "_LifecycleAdapter";
    }
}
