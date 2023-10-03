package com.google.common.collect;

import java.util.Iterator;
import java.util.Map;

/* renamed from: com.google.common.collect.Ta */
class C1625Ta extends C1668jb {
    final Map map;

    C1625Ta(Map map2) {
        if (map2 != null) {
            this.map = map2;
            return;
        }
        throw new NullPointerException();
    }

    public void clear() {
        map().clear();
    }

    public boolean contains(Object obj) {
        return map().containsKey(obj);
    }

    public boolean isEmpty() {
        return map().isEmpty();
    }

    public Iterator iterator() {
        return C1652ea.m4570a(map().entrySet().iterator(), C1633Xa.m4548xl());
    }

    /* access modifiers changed from: package-private */
    public Map map() {
        return this.map;
    }

    public boolean remove(Object obj) {
        if (!map().containsKey(obj)) {
            return false;
        }
        map().remove(obj);
        return true;
    }

    public int size() {
        return map().size();
    }
}
