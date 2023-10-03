package com.google.common.collect;

import java.util.Map;
import java.util.Set;

/* renamed from: com.google.common.collect.ob */
final class C1683ob {
    /* renamed from: a */
    static /* synthetic */ Map.Entry m4615a(Map.Entry entry, Object obj) {
        if (entry == null) {
            return null;
        }
        return new Synchronized$SynchronizedEntry(entry, obj);
    }

    static Map map(Map map, Object obj) {
        return new Synchronized$SynchronizedMap(map, obj);
    }

    static Set set(Set set, Object obj) {
        return new Synchronized$SynchronizedSet(set, obj);
    }
}
