package androidx.lifecycle;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: androidx.lifecycle.c */
class C0446c {
    static C0446c sInstance = new C0446c();

    /* renamed from: Fp */
    private final Map f436Fp = new HashMap();

    /* renamed from: we */
    private final Map f437we = new HashMap();

    C0446c() {
    }

    /* renamed from: a */
    private void m445a(Map map, C0445b bVar, Lifecycle$Event lifecycle$Event, Class cls) {
        Lifecycle$Event lifecycle$Event2 = (Lifecycle$Event) map.get(bVar);
        if (lifecycle$Event2 != null && lifecycle$Event != lifecycle$Event2) {
            Method method = bVar.mMethod;
            StringBuilder Pa = C0632a.m1011Pa("Method ");
            Pa.append(method.getName());
            Pa.append(" in ");
            Pa.append(cls.getName());
            Pa.append(" already declared with different @OnLifecycleEvent value: previous value ");
            Pa.append(lifecycle$Event2);
            Pa.append(", new value ");
            Pa.append(lifecycle$Event);
            throw new IllegalArgumentException(Pa.toString());
        } else if (lifecycle$Event2 == null) {
            map.put(bVar, lifecycle$Event);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public C0444a mo4451b(Class cls) {
        C0444a aVar = (C0444a) this.f437we.get(cls);
        if (aVar != null) {
            return aVar;
        }
        return m444a(cls, (Method[]) null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public boolean mo4452c(Class cls) {
        Boolean bool = (Boolean) this.f436Fp.get(cls);
        if (bool != null) {
            return bool.booleanValue();
        }
        try {
            Method[] declaredMethods = cls.getDeclaredMethods();
            for (Method annotation : declaredMethods) {
                if (((C0464u) annotation.getAnnotation(C0464u.class)) != null) {
                    m444a(cls, declaredMethods);
                    return true;
                }
            }
            this.f436Fp.put(cls, false);
            return false;
        } catch (NoClassDefFoundError e) {
            throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e);
        }
    }

    /* renamed from: a */
    private C0444a m444a(Class cls, Method[] methodArr) {
        int i;
        C0444a b;
        Class superclass = cls.getSuperclass();
        HashMap hashMap = new HashMap();
        if (!(superclass == null || (b = mo4451b(superclass)) == null)) {
            hashMap.putAll(b.f435Ep);
        }
        for (Class b2 : cls.getInterfaces()) {
            for (Map.Entry entry : mo4451b(b2).f435Ep.entrySet()) {
                m445a(hashMap, (C0445b) entry.getKey(), (Lifecycle$Event) entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            try {
                methodArr = cls.getDeclaredMethods();
            } catch (NoClassDefFoundError e) {
                throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e);
            }
        }
        boolean z = false;
        for (Method method : methodArr) {
            C0464u uVar = (C0464u) method.getAnnotation(C0464u.class);
            if (uVar != null) {
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i = 0;
                } else if (parameterTypes[0].isAssignableFrom(C0453j.class)) {
                    i = 1;
                } else {
                    throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                }
                Lifecycle$Event value = uVar.value();
                if (parameterTypes.length > 1) {
                    if (!parameterTypes[1].isAssignableFrom(Lifecycle$Event.class)) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    } else if (value == Lifecycle$Event.ON_ANY) {
                        i = 2;
                    } else {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                }
                if (parameterTypes.length <= 2) {
                    m445a(hashMap, new C0445b(i, method), value, cls);
                    z = true;
                } else {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
            }
        }
        C0444a aVar = new C0444a(hashMap);
        this.f437we.put(cls, aVar);
        this.f436Fp.put(cls, Boolean.valueOf(z));
        return aVar;
    }
}
