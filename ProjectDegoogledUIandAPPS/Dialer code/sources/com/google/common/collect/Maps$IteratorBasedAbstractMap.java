package com.google.common.collect;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

abstract class Maps$IteratorBasedAbstractMap<K, V> extends AbstractMap<K, V> {
    Maps$IteratorBasedAbstractMap() {
    }

    public void clear() {
        Collections2.clear(entryIterator());
    }

    /* access modifiers changed from: package-private */
    public abstract Iterator<Map.Entry<K, V>> entryIterator();

    public Set<Map.Entry<K, V>> entrySet() {
        return new Maps$EntrySet<K, V>() {
            public void forEach(Consumer<? super Map.Entry<K, V>> consumer) {
                Maps$IteratorBasedAbstractMap.this.forEachEntry(consumer);
            }

            public Iterator<Map.Entry<K, V>> iterator() {
                return Maps$IteratorBasedAbstractMap.this.entryIterator();
            }

            /* access modifiers changed from: package-private */
            public Map<K, V> map() {
                return Maps$IteratorBasedAbstractMap.this;
            }

            public Spliterator<Map.Entry<K, V>> spliterator() {
                return Maps$IteratorBasedAbstractMap.this.entrySpliterator();
            }
        };
    }

    /* access modifiers changed from: package-private */
    public Spliterator<Map.Entry<K, V>> entrySpliterator() {
        return Spliterators.spliterator(entryIterator(), (long) size(), 65);
    }

    /* access modifiers changed from: package-private */
    public void forEachEntry(Consumer<? super Map.Entry<K, V>> consumer) {
        entryIterator().forEachRemaining(consumer);
    }

    public abstract int size();
}
