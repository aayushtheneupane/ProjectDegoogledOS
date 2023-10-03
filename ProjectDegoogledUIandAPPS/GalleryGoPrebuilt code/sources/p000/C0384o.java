package p000;

import android.arch.lifecycle.OnLifecycleEvent;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* renamed from: o */
/* compiled from: PG */
final class C0384o {

    /* renamed from: a */
    public static final C0384o f15337a = new C0384o();

    /* renamed from: b */
    public final Map f15338b = new HashMap();

    /* renamed from: c */
    private final Map f15339c = new HashMap();

    /* renamed from: a */
    public final C0330m mo9505a(Class cls, Method[] methodArr) {
        int i;
        C0330m b;
        Class superclass = cls.getSuperclass();
        HashMap hashMap = new HashMap();
        if (!(superclass == null || (b = mo9507b(superclass)) == null)) {
            hashMap.putAll(b.f15221b);
        }
        for (Class b2 : cls.getInterfaces()) {
            for (Map.Entry entry : mo9507b(b2).f15221b.entrySet()) {
                m14887a(hashMap, (C0357n) entry.getKey(), (C0546u) entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            methodArr = mo9506a(cls);
        }
        boolean z = false;
        for (Method method : methodArr) {
            OnLifecycleEvent onLifecycleEvent = (OnLifecycleEvent) method.getAnnotation(OnLifecycleEvent.class);
            if (onLifecycleEvent != null) {
                Class[] parameterTypes = method.getParameterTypes();
                int length = parameterTypes.length;
                if (length <= 0) {
                    i = 0;
                } else if (parameterTypes[0].isAssignableFrom(C0681z.class)) {
                    i = 1;
                } else {
                    throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                }
                C0546u a = onLifecycleEvent.mo678a();
                if (length > 1) {
                    if (!parameterTypes[1].isAssignableFrom(C0546u.class)) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    } else if (a == C0546u.ON_ANY) {
                        i = 2;
                    } else {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                }
                if (length <= 2) {
                    m14887a(hashMap, new C0357n(i, method), a, cls);
                    z = true;
                } else {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
            }
        }
        C0330m mVar = new C0330m(hashMap);
        this.f15339c.put(cls, mVar);
        this.f15338b.put(cls, Boolean.valueOf(z));
        return mVar;
    }

    /* renamed from: a */
    public final Method[] mo9506a(Class cls) {
        try {
            return cls.getDeclaredMethods();
        } catch (NoClassDefFoundError e) {
            throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final C0330m mo9507b(Class cls) {
        C0330m mVar = (C0330m) this.f15339c.get(cls);
        return mVar == null ? mo9505a(cls, (Method[]) null) : mVar;
    }

    /* renamed from: a */
    private static final void m14887a(Map map, C0357n nVar, C0546u uVar, Class cls) {
        C0546u uVar2 = (C0546u) map.get(nVar);
        if (uVar2 != null && uVar != uVar2) {
            Method method = nVar.f15262b;
            throw new IllegalArgumentException("Method " + method.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + uVar2 + ", new value " + uVar);
        } else if (uVar2 == null) {
            map.put(nVar, uVar);
        }
    }
}
