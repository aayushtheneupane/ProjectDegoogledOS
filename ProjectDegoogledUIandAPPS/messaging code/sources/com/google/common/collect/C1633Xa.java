package com.google.common.collect;

import com.google.common.base.C1547v;
import com.google.common.base.C1551z;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: com.google.common.collect.Xa */
public final class C1633Xa {

    /* renamed from: lN */
    static final C1551z f2508lN = C1552A.f2421lN.mo8511Wa("=");

    /* renamed from: a */
    static C1692rb m4538a(C1692rb rbVar) {
        return new C1609Qa(rbVar);
    }

    /* renamed from: b */
    static boolean m4541b(Map map, Object obj) {
        if (map != null) {
            try {
                return map.containsKey(obj);
            } catch (ClassCastException | NullPointerException unused) {
                return false;
            }
        } else {
            throw new NullPointerException();
        }
    }

    /* renamed from: c */
    static Object m4542c(Map map, Object obj) {
        if (map != null) {
            try {
                return map.get(obj);
            } catch (ClassCastException | NullPointerException unused) {
                return null;
            }
        } else {
            throw new NullPointerException();
        }
    }

    /* renamed from: d */
    static Object m4543d(Map map, Object obj) {
        if (map != null) {
            try {
                return map.remove(obj);
            } catch (ClassCastException | NullPointerException unused) {
                return null;
            }
        } else {
            throw new NullPointerException();
        }
    }

    /* renamed from: db */
    static int m4545db(int i) {
        if (i < 3) {
            C1630W.m4536e(i, "expectedSize");
            return i + 1;
        } else if (i < 1073741824) {
            return (i / 3) + i;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    /* renamed from: g */
    static Iterator m4546g(Iterator it) {
        return C1652ea.m4570a(it, (C1547v) Maps$EntryFunction.VALUE);
    }

    /* renamed from: i */
    public static Map.Entry m4547i(Object obj, Object obj2) {
        return new ImmutableEntry(obj, obj2);
    }

    public static HashMap newHashMap() {
        return new HashMap();
    }

    /* renamed from: xl */
    static C1547v m4548xl() {
        return Maps$EntryFunction.KEY;
    }

    /* renamed from: yl */
    public static LinkedHashMap m4549yl() {
        return new LinkedHashMap();
    }

    /* renamed from: a */
    static boolean m4540a(Map map, Object obj) {
        if (map == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return map.entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    /* renamed from: d */
    static String m4544d(Map map) {
        StringBuilder bb = C1552A.m4041bb(map.size());
        bb.append('{');
        f2508lN.mo8565a(bb, map);
        bb.append('}');
        return bb.toString();
    }

    /* renamed from: a */
    static Object m4539a(Map.Entry entry) {
        if (entry == null) {
            return null;
        }
        return entry.getKey();
    }
}
