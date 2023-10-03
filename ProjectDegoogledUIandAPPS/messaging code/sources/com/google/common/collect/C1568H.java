package com.google.common.collect;

import java.util.concurrent.ConcurrentMap;

/* renamed from: com.google.common.collect.H */
public abstract class C1568H extends C1570I implements ConcurrentMap {
    protected C1568H() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: ml */
    public abstract ConcurrentMap mo8674ml();

    public Object putIfAbsent(Object obj, Object obj2) {
        return mo8674ml().putIfAbsent(obj, obj2);
    }

    public boolean remove(Object obj, Object obj2) {
        return mo8674ml().remove(obj, obj2);
    }

    public Object replace(Object obj, Object obj2) {
        return mo8674ml().replace(obj, obj2);
    }

    public boolean replace(Object obj, Object obj2, Object obj3) {
        return mo8674ml().replace(obj, obj2, obj3);
    }
}
