package com.google.common.collect;

import android.support.p002v7.appcompat.R$style;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

class Maps$Values<K, V> extends AbstractCollection<V> {
    final Map<K, V> map;

    Maps$Values(Map<K, V> map2) {
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

    public void forEach(Consumer<? super V> consumer) {
        if (consumer != null) {
            this.map.forEach(new BiConsumer(consumer) {
                private final /* synthetic */ Consumer f$0;

                {
                    this.f$0 = r1;
                }

                public final void accept(Object obj, Object obj2) {
                    this.f$0.accept(obj2);
                }
            });
            return;
        }
        throw new NullPointerException();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public Iterator<V> iterator() {
        return Collections2.transform(this.map.entrySet().iterator(), Maps$EntryFunction.VALUE);
    }

    public boolean remove(Object obj) {
        try {
            return super.remove(obj);
        } catch (UnsupportedOperationException unused) {
            for (Map.Entry next : this.map.entrySet()) {
                if (R$style.equal(obj, next.getValue())) {
                    this.map.remove(next.getKey());
                    return true;
                }
            }
            return false;
        }
    }

    public boolean removeAll(Collection<?> collection) {
        if (collection != null) {
            try {
                return super.removeAll(collection);
            } catch (UnsupportedOperationException unused) {
                HashSet hashSet = new HashSet();
                for (Map.Entry next : this.map.entrySet()) {
                    if (collection.contains(next.getValue())) {
                        hashSet.add(next.getKey());
                    }
                }
                return this.map.keySet().removeAll(hashSet);
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
                HashSet hashSet = new HashSet();
                for (Map.Entry next : this.map.entrySet()) {
                    if (collection.contains(next.getValue())) {
                        hashSet.add(next.getKey());
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
