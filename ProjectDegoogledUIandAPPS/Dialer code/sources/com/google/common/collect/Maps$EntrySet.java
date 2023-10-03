package com.google.common.collect;

import android.support.p002v7.appcompat.R$style;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract class Maps$EntrySet<K, V> extends Sets$ImprovedAbstractSet<Map.Entry<K, V>> {
    Maps$EntrySet() {
    }

    public void clear() {
        map().clear();
    }

    public boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Map map = map();
            if (map != null) {
                Object obj2 = null;
                try {
                    obj2 = map.get(key);
                } catch (ClassCastException | NullPointerException unused) {
                }
                if (!R$style.equal(obj2, entry.getValue()) || (obj2 == null && !map().containsKey(key))) {
                    return false;
                }
                return true;
            }
            throw new NullPointerException();
        }
        return false;
    }

    public boolean isEmpty() {
        return map().isEmpty();
    }

    /* access modifiers changed from: package-private */
    public abstract Map<K, V> map();

    public boolean remove(Object obj) {
        if (contains(obj)) {
            return map().keySet().remove(((Map.Entry) obj).getKey());
        }
        return false;
    }

    public boolean removeAll(Collection<?> collection) {
        if (collection != null) {
            try {
                return Collections2.removeAllImpl((Set<?>) this, collection);
            } catch (UnsupportedOperationException unused) {
                return Collections2.removeAllImpl((Set<?>) this, collection.iterator());
            }
        } else {
            throw new NullPointerException();
        }
    }

    public boolean retainAll(Collection<?> collection) {
        if (collection != null) {
            try {
                return super.retainAll(collection);
            } catch (UnsupportedOperationException unused) {
                HashSet hashSet = new HashSet(Collections2.capacity(collection.size()));
                for (Object next : collection) {
                    if (contains(next)) {
                        hashSet.add(((Map.Entry) next).getKey());
                    }
                }
                return map().keySet().retainAll(hashSet);
            }
        } else {
            throw new NullPointerException();
        }
    }

    public int size() {
        return map().size();
    }
}
