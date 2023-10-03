package com.google.common.collect;

import com.google.common.base.C1508E;
import com.google.common.base.C1509F;
import com.google.common.base.C1511H;
import com.google.common.base.C1547v;
import java.util.Collection;
import java.util.Iterator;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.google.common.collect.ea */
public final class C1652ea {

    /* renamed from: GN */
    static final C1695sb f2522GN = new C1636Z();

    /* renamed from: B */
    public static C1692rb m4567B(Object obj) {
        return new C1634Y(obj);
    }

    /* renamed from: a */
    public static boolean m4573a(Iterator it, Object obj) {
        C1509F y = C1511H.m3972y(obj);
        C1508E.checkNotNull(y, "predicate");
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            } else if (y.apply(it.next())) {
                break;
            } else {
                i++;
            }
        }
        if (i != -1) {
            return true;
        }
        return false;
    }

    /* renamed from: b */
    public static Object m4576b(Iterator it) {
        Object next = it.next();
        if (!it.hasNext()) {
            return next;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("expected one element but was: <" + next);
        for (int i = 0; i < 4 && it.hasNext(); i++) {
            StringBuilder Pa = C0632a.m1011Pa(", ");
            Pa.append(it.next());
            sb.append(Pa.toString());
        }
        if (it.hasNext()) {
            sb.append(", ...");
        }
        sb.append('>');
        throw new IllegalArgumentException(sb.toString());
    }

    /* renamed from: c */
    public static C1647cb m4578c(Iterator it) {
        if (it instanceof C1649da) {
            return (C1649da) it;
        }
        return new C1649da(it);
    }

    /* renamed from: d */
    static Object m4579d(Iterator it) {
        if (!it.hasNext()) {
            return null;
        }
        Object next = it.next();
        it.remove();
        return next;
    }

    /* renamed from: e */
    public static int m4580e(Iterator it) {
        int i = 0;
        while (it.hasNext()) {
            it.next();
            i++;
        }
        return i;
    }

    static C1695sb emptyListIterator() {
        return f2522GN;
    }

    /* renamed from: f */
    public static C1692rb m4582f(Iterator it) {
        if (it == null) {
            throw new NullPointerException();
        } else if (it instanceof C1692rb) {
            return (C1692rb) it;
        } else {
            return new C1640aa(it);
        }
    }

    /* renamed from: e */
    public static C1692rb m4581e(Object... objArr) {
        return m4569a(objArr, 0, objArr.length, 0);
    }

    /* renamed from: a */
    public static boolean m4574a(Iterator it, Collection collection) {
        C1509F d = C1511H.m3971d(collection);
        if (d != null) {
            boolean z = false;
            while (it.hasNext()) {
                if (d.apply(it.next())) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }
        throw new NullPointerException();
    }

    /* JADX WARNING: Removed duplicated region for block: B:2:0x0006  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m4575a(java.util.Iterator r3, java.util.Iterator r4) {
        /*
        L_0x0000:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x001d
            boolean r0 = r4.hasNext()
            r1 = 0
            if (r0 != 0) goto L_0x000e
            return r1
        L_0x000e:
            java.lang.Object r0 = r3.next()
            java.lang.Object r2 = r4.next()
            boolean r0 = android.support.p016v4.media.session.C0107q.m131b(r0, r2)
            if (r0 != 0) goto L_0x0000
            return r1
        L_0x001d:
            boolean r3 = r4.hasNext()
            r3 = r3 ^ 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.C1652ea.m4575a(java.util.Iterator, java.util.Iterator):boolean");
    }

    /* renamed from: b */
    public static Object m4577b(Iterator it, Object obj) {
        return it.hasNext() ? it.next() : obj;
    }

    /* renamed from: a */
    public static boolean m4572a(Collection collection, Iterator it) {
        if (collection == null) {
            throw new NullPointerException();
        } else if (it != null) {
            boolean z = false;
            while (it.hasNext()) {
                z |= collection.add(it.next());
            }
            return z;
        } else {
            throw new NullPointerException();
        }
    }

    /* renamed from: a */
    public static C1692rb m4568a(Iterator it, C1509F f) {
        if (it == null) {
            throw new NullPointerException();
        } else if (f != null) {
            return new C1643ba(it, f);
        } else {
            throw new NullPointerException();
        }
    }

    /* renamed from: a */
    public static Iterator m4570a(Iterator it, C1547v vVar) {
        if (vVar != null) {
            return new C1646ca(it, vVar);
        }
        throw new NullPointerException();
    }

    /* renamed from: a */
    static void m4571a(Iterator it) {
        if (it != null) {
            while (it.hasNext()) {
                it.next();
                it.remove();
            }
            return;
        }
        throw new NullPointerException();
    }

    /* renamed from: a */
    static C1695sb m4569a(Object[] objArr, int i, int i2, int i3) {
        C1508E.checkArgument(i2 >= 0);
        C1508E.m3963c(i, i + i2, objArr.length);
        C1508E.m3959K(i3, i2);
        if (i2 == 0) {
            return f2522GN;
        }
        return new C1632X(i2, i3, objArr, i);
    }
}
