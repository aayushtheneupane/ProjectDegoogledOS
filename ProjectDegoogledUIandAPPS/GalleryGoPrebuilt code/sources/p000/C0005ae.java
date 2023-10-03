package p000;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: ae */
/* compiled from: PG */
public final class C0005ae {

    /* renamed from: a */
    private static final Map f263a = new HashMap();

    /* renamed from: b */
    private static final Map f264b = new HashMap();

    /* renamed from: a */
    private static C0519t m277a(Constructor constructor, Object obj) {
        try {
            return (C0519t) constructor.newInstance(new Object[]{obj});
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0099, code lost:
        if (r5.booleanValue() == false) goto L_0x00c5;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int m276a(java.lang.Class r10) {
        /*
            java.lang.String r0 = "."
            java.util.Map r1 = f263a
            java.lang.Object r1 = r1.get(r10)
            java.lang.Integer r1 = (java.lang.Integer) r1
            if (r1 != 0) goto L_0x0133
            java.lang.String r1 = r10.getCanonicalName()
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0127
            r1 = 0
            r4 = 0
            java.lang.Package r5 = r10.getPackage()     // Catch:{ ClassNotFoundException -> 0x0085, NoSuchMethodException -> 0x007e }
            java.lang.String r6 = r10.getCanonicalName()     // Catch:{ ClassNotFoundException -> 0x0085, NoSuchMethodException -> 0x007e }
            if (r5 != 0) goto L_0x0023
            java.lang.String r5 = ""
            goto L_0x0027
        L_0x0023:
            java.lang.String r5 = r5.getName()     // Catch:{ ClassNotFoundException -> 0x0085, NoSuchMethodException -> 0x007e }
        L_0x0027:
            boolean r7 = r5.isEmpty()     // Catch:{ ClassNotFoundException -> 0x0085, NoSuchMethodException -> 0x007e }
            if (r7 == 0) goto L_0x002e
            goto L_0x0037
        L_0x002e:
            int r7 = r5.length()     // Catch:{ ClassNotFoundException -> 0x0085, NoSuchMethodException -> 0x007e }
            int r7 = r7 + r3
            java.lang.String r6 = r6.substring(r7)     // Catch:{ ClassNotFoundException -> 0x0085, NoSuchMethodException -> 0x007e }
        L_0x0037:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x0085, NoSuchMethodException -> 0x007e }
            r7.<init>()     // Catch:{ ClassNotFoundException -> 0x0085, NoSuchMethodException -> 0x007e }
            java.lang.String r8 = "_"
            java.lang.String r6 = r6.replace(r0, r8)     // Catch:{ ClassNotFoundException -> 0x0085, NoSuchMethodException -> 0x007e }
            r7.append(r6)     // Catch:{ ClassNotFoundException -> 0x0085, NoSuchMethodException -> 0x007e }
            java.lang.String r6 = "_LifecycleAdapter"
            r7.append(r6)     // Catch:{ ClassNotFoundException -> 0x0085, NoSuchMethodException -> 0x007e }
            java.lang.String r6 = r7.toString()     // Catch:{ ClassNotFoundException -> 0x0085, NoSuchMethodException -> 0x007e }
            boolean r7 = r5.isEmpty()     // Catch:{ ClassNotFoundException -> 0x0085, NoSuchMethodException -> 0x007e }
            if (r7 != 0) goto L_0x0066
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x0085, NoSuchMethodException -> 0x007e }
            r7.<init>()     // Catch:{ ClassNotFoundException -> 0x0085, NoSuchMethodException -> 0x007e }
            r7.append(r5)     // Catch:{ ClassNotFoundException -> 0x0085, NoSuchMethodException -> 0x007e }
            r7.append(r0)     // Catch:{ ClassNotFoundException -> 0x0085, NoSuchMethodException -> 0x007e }
            r7.append(r6)     // Catch:{ ClassNotFoundException -> 0x0085, NoSuchMethodException -> 0x007e }
            java.lang.String r6 = r7.toString()     // Catch:{ ClassNotFoundException -> 0x0085, NoSuchMethodException -> 0x007e }
        L_0x0066:
            java.lang.Class r0 = java.lang.Class.forName(r6)     // Catch:{ ClassNotFoundException -> 0x0085, NoSuchMethodException -> 0x007e }
            java.lang.Class[] r5 = new java.lang.Class[r3]     // Catch:{ ClassNotFoundException -> 0x0085, NoSuchMethodException -> 0x007e }
            r5[r4] = r10     // Catch:{ ClassNotFoundException -> 0x0085, NoSuchMethodException -> 0x007e }
            java.lang.reflect.Constructor r0 = r0.getDeclaredConstructor(r5)     // Catch:{ ClassNotFoundException -> 0x0085, NoSuchMethodException -> 0x007e }
            boolean r5 = r0.isAccessible()     // Catch:{ ClassNotFoundException -> 0x0085, NoSuchMethodException -> 0x007e }
            if (r5 == 0) goto L_0x0079
        L_0x0078:
            goto L_0x0087
        L_0x0079:
            r0.setAccessible(r3)     // Catch:{ ClassNotFoundException -> 0x0085, NoSuchMethodException -> 0x007e }
            goto L_0x0078
        L_0x007e:
            r10 = move-exception
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r10)
            throw r0
        L_0x0085:
            r0 = move-exception
            r0 = r1
        L_0x0087:
            if (r0 != 0) goto L_0x011c
            o r0 = p000.C0384o.f15337a
            java.util.Map r5 = r0.f15338b
            java.lang.Object r5 = r5.get(r10)
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            if (r5 == 0) goto L_0x009e
            boolean r0 = r5.booleanValue()
            if (r0 != 0) goto L_0x009c
            goto L_0x00c5
        L_0x009c:
            goto L_0x0128
        L_0x009e:
            java.lang.reflect.Method[] r5 = r0.mo9506a(r10)
            int r6 = r5.length
            r7 = 0
        L_0x00a4:
            if (r7 >= r6) goto L_0x00bc
            r8 = r5[r7]
            java.lang.Class<android.arch.lifecycle.OnLifecycleEvent> r9 = android.arch.lifecycle.OnLifecycleEvent.class
            java.lang.annotation.Annotation r8 = r8.getAnnotation(r9)
            android.arch.lifecycle.OnLifecycleEvent r8 = (android.arch.lifecycle.OnLifecycleEvent) r8
            if (r8 != 0) goto L_0x00b5
            int r7 = r7 + 1
            goto L_0x00a4
        L_0x00b5:
            r0.mo9505a(r10, r5)
            r2 = 1
            goto L_0x0129
        L_0x00bc:
            java.util.Map r0 = r0.f15338b
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r4)
            r0.put(r10, r5)
        L_0x00c5:
            java.lang.Class r0 = r10.getSuperclass()
            boolean r5 = m279b(r0)
            if (r5 == 0) goto L_0x00e3
            int r1 = m276a((java.lang.Class) r0)
            if (r1 == r3) goto L_0x0128
            java.util.ArrayList r1 = new java.util.ArrayList
            java.util.Map r5 = f264b
            java.lang.Object r0 = r5.get(r0)
            java.util.Collection r0 = (java.util.Collection) r0
            r1.<init>(r0)
            goto L_0x00e5
        L_0x00e3:
        L_0x00e5:
            java.lang.Class[] r0 = r10.getInterfaces()
            int r5 = r0.length
        L_0x00ea:
            if (r4 >= r5) goto L_0x0112
            r6 = r0[r4]
            boolean r7 = m279b(r6)
            if (r7 != 0) goto L_0x00f5
            goto L_0x010f
        L_0x00f5:
            int r7 = m276a((java.lang.Class) r6)
            if (r7 != r3) goto L_0x00fc
            goto L_0x0128
        L_0x00fc:
            if (r1 == 0) goto L_0x00ff
            goto L_0x0104
        L_0x00ff:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
        L_0x0104:
            java.util.Map r7 = f264b
            java.lang.Object r6 = r7.get(r6)
            java.util.Collection r6 = (java.util.Collection) r6
            r1.addAll(r6)
        L_0x010f:
            int r4 = r4 + 1
            goto L_0x00ea
        L_0x0112:
            if (r1 == 0) goto L_0x011b
            java.util.Map r0 = f264b
            r0.put(r10, r1)
            goto L_0x0129
        L_0x011b:
            goto L_0x0128
        L_0x011c:
            java.util.Map r1 = f264b
            java.util.List r0 = java.util.Collections.singletonList(r0)
            r1.put(r10, r0)
            goto L_0x0129
        L_0x0127:
        L_0x0128:
            r2 = 1
        L_0x0129:
            java.util.Map r0 = f263a
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            r0.put(r10, r1)
            return r2
        L_0x0133:
            int r10 = r1.intValue()
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0005ae.m276a(java.lang.Class):int");
    }

    /* renamed from: b */
    private static boolean m279b(Class cls) {
        return cls != null && C0654y.class.isAssignableFrom(cls);
    }

    /* renamed from: a */
    static C0627x m278a(Object obj) {
        boolean z = obj instanceof C0627x;
        boolean z2 = obj instanceof C0465r;
        if (z && z2) {
            return new C0492s((C0465r) obj, (C0627x) obj);
        }
        if (z2) {
            return new C0492s((C0465r) obj, (C0627x) null);
        }
        if (z) {
            return (C0627x) obj;
        }
        Class<?> cls = obj.getClass();
        if (m276a((Class) cls) != 2) {
            return new C0012al(obj);
        }
        List list = (List) f264b.get(cls);
        if (list.size() == 1) {
            return new C0019aq(m277a((Constructor) list.get(0), obj));
        }
        C0519t[] tVarArr = new C0519t[list.size()];
        for (int i = 0; i < list.size(); i++) {
            tVarArr[i] = m277a((Constructor) list.get(i), obj);
        }
        return new C0411p(tVarArr);
    }
}
