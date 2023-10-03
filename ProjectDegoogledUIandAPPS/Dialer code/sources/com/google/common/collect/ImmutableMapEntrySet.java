package com.google.common.collect;

import java.io.Serializable;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

abstract class ImmutableMapEntrySet<K, V> extends ImmutableSet<Map.Entry<K, V>> {

    private static class EntrySetSerializedForm<K, V> implements Serializable {
        private static final long serialVersionUID = 0;
        final ImmutableMap<K, V> map;

        EntrySetSerializedForm(ImmutableMap<K, V> immutableMap) {
            this.map = immutableMap;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.map.entrySet();
        }
    }

    static final class RegularEntrySet<K, V> extends ImmutableMapEntrySet<K, V> {
        private final transient Map.Entry<K, V>[] entries;
        private final transient ImmutableMap<K, V> map;

        RegularEntrySet(ImmutableMap<K, V> immutableMap, Map.Entry<K, V>[] entryArr) {
            this.map = immutableMap;
            this.entries = entryArr;
        }

        /* access modifiers changed from: package-private */
        public ImmutableList<Map.Entry<K, V>> createAsList() {
            return new RegularImmutableAsList(this, (Object[]) this.entries);
        }

        public void forEach(Consumer<? super Map.Entry<K, V>> consumer) {
            if (consumer != null) {
                for (Map.Entry<K, V> accept : this.entries) {
                    consumer.accept(accept);
                }
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: package-private */
        public ImmutableMap<K, V> map() {
            return this.map;
        }

        public Spliterator<Map.Entry<K, V>> spliterator() {
            return Spliterators.spliterator(this.entries, 1297);
        }

        public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
            return Collections2.forArray(this.entries);
        }
    }

    ImmutableMapEntrySet() {
    }

    public boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object obj2 = map().get(entry.getKey());
        if (obj2 == null || !obj2.equals(entry.getValue())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return map().hashCode();
    }

    /* access modifiers changed from: package-private */
    public boolean isHashCodeFast() {
        return map().isHashCodeFast();
    }

    /* access modifiers changed from: package-private */
    public boolean isPartialView() {
        return map().isPartialView();
    }

    /* access modifiers changed from: package-private */
    public abstract ImmutableMap<K, V> map();

    public int size() {
        return map().size();
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new EntrySetSerializedForm(map());
    }
}
