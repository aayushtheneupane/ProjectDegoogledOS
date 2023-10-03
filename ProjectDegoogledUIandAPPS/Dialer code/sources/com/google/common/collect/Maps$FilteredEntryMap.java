package com.google.common.collect;

import com.google.common.base.MoreObjects;
import com.google.common.base.Predicate;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class Maps$FilteredEntryMap<K, V> extends Maps$AbstractFilteredMap<K, V> {
    final Set<Map.Entry<K, V>> filteredEntrySet;

    private class EntrySet extends ForwardingSet<Map.Entry<K, V>> {
        /* synthetic */ EntrySet(Maps$1 maps$1) {
        }

        /* access modifiers changed from: protected */
        public Object delegate() {
            return Maps$FilteredEntryMap.this.filteredEntrySet;
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return new TransformedIterator<Map.Entry<K, V>, Map.Entry<K, V>>(Maps$FilteredEntryMap.this.filteredEntrySet.iterator()) {
                /* access modifiers changed from: package-private */
                public Object transform(Object obj) {
                    final Map.Entry entry = (Map.Entry) obj;
                    return new ForwardingMapEntry<K, V>() {
                        /* access modifiers changed from: protected */
                        public Object delegate() {
                            return entry;
                        }

                        public V setValue(V v) {
                            MoreObjects.checkArgument(Maps$FilteredEntryMap.this.apply(entry.getKey(), v));
                            return entry.setValue(v);
                        }
                    };
                }
            };
        }

        /* access modifiers changed from: protected */
        /* renamed from: delegate  reason: collision with other method in class */
        public Collection m134delegate() {
            return Maps$FilteredEntryMap.this.filteredEntrySet;
        }

        /* access modifiers changed from: protected */
        /* renamed from: delegate  reason: collision with other method in class */
        public Set<Map.Entry<K, V>> m135delegate() {
            return Maps$FilteredEntryMap.this.filteredEntrySet;
        }
    }

    class KeySet extends Maps$KeySet<K, V> {
        KeySet() {
            super(Maps$FilteredEntryMap.this);
        }

        public boolean remove(Object obj) {
            if (!Maps$FilteredEntryMap.this.containsKey(obj)) {
                return false;
            }
            Maps$FilteredEntryMap.this.unfiltered.remove(obj);
            return true;
        }

        public boolean removeAll(Collection<?> collection) {
            Maps$FilteredEntryMap maps$FilteredEntryMap = Maps$FilteredEntryMap.this;
            return Maps$FilteredEntryMap.removeAllKeys(maps$FilteredEntryMap.unfiltered, maps$FilteredEntryMap.predicate, collection);
        }

        public boolean retainAll(Collection<?> collection) {
            Maps$FilteredEntryMap maps$FilteredEntryMap = Maps$FilteredEntryMap.this;
            return Maps$FilteredEntryMap.retainAllKeys(maps$FilteredEntryMap.unfiltered, maps$FilteredEntryMap.predicate, collection);
        }

        public Object[] toArray() {
            return Lists.newArrayList(iterator()).toArray();
        }

        public <T> T[] toArray(T[] tArr) {
            return Lists.newArrayList(iterator()).toArray(tArr);
        }
    }

    Maps$FilteredEntryMap(Map<K, V> map, Predicate<? super Map.Entry<K, V>> predicate) {
        super(map, predicate);
        this.filteredEntrySet = Collections2.filter(map.entrySet(), this.predicate);
    }

    static <K, V> boolean removeAllKeys(Map<K, V> map, Predicate<? super Map.Entry<K, V>> predicate, Collection<?> collection) {
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            Map.Entry next = it.next();
            if (predicate.apply(next) && collection.contains(next.getKey())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    static <K, V> boolean retainAllKeys(Map<K, V> map, Predicate<? super Map.Entry<K, V>> predicate, Collection<?> collection) {
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            Map.Entry next = it.next();
            if (predicate.apply(next) && !collection.contains(next.getKey())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public Set<Map.Entry<K, V>> createEntrySet() {
        return new EntrySet((Maps$1) null);
    }

    /* access modifiers changed from: package-private */
    public Set<K> createKeySet() {
        return new KeySet();
    }
}
