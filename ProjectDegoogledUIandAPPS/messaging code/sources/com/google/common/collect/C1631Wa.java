package com.google.common.collect;

import android.support.p016v4.media.session.C0107q;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.google.common.collect.Wa */
class C1631Wa extends AbstractCollection {
    final Map map;

    C1631Wa(Map map2) {
        if (map2 != null) {
            this.map = map2;
            return;
        }
        throw new NullPointerException();
    }

    public void clear() {
        this.map.clear();
    }

    public boolean contains(Object obj) {
        return this.map.containsValue(obj);
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public Iterator iterator() {
        return C1633Xa.m4546g(this.map.entrySet().iterator());
    }

    public boolean remove(Object obj) {
        try {
            return super.remove(obj);
        } catch (UnsupportedOperationException unused) {
            for (Map.Entry entry : this.map.entrySet()) {
                if (C0107q.m131b(obj, entry.getValue())) {
                    this.map.remove(entry.getKey());
                    return true;
                }
            }
            return false;
        }
    }

    public boolean removeAll(Collection collection) {
        if (collection != null) {
            try {
                return super.removeAll(collection);
            } catch (UnsupportedOperationException unused) {
                HashSet hashSet = new HashSet();
                for (Map.Entry entry : this.map.entrySet()) {
                    if (collection.contains(entry.getValue())) {
                        hashSet.add(entry.getKey());
                    }
                }
                return this.map.keySet().removeAll(hashSet);
            }
        } else {
            throw new NullPointerException();
        }
    }

    public boolean retainAll(Collection collection) {
        if (collection != null) {
            try {
                return super.retainAll(collection);
            } catch (UnsupportedOperationException unused) {
                HashSet hashSet = new HashSet();
                for (Map.Entry entry : this.map.entrySet()) {
                    if (collection.contains(entry.getValue())) {
                        hashSet.add(entry.getKey());
                    }
                }
                return this.map.keySet().retainAll(hashSet);
            }
        } else {
            throw new NullPointerException();
        }
    }

    public int size() {
        return this.map.size();
    }
}
